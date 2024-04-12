'use strict';
var mainPage = document.querySelector('#main-page');
var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect(event) {
    username = document.querySelector('#name').value.trim();

    if (username) {
        hideElement(usernamePage);
        showElement(chatPage);
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}
// method to hide the element
function hideElement(element) {
    element.classList.add('hidden');
}
//method to show the element
function showElement(element) {
    element.classList.remove('hidden');
}
// method to handle messages clicking
function handleMessagesClick(event) {
    event.preventDefault();
    hideElement(mainPage);
    showElement(usernamePage);
}

function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public', onMessageReceived);

    // Tell your username to the server
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    )

    connectingElement.classList.add('hidden');
}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}


function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    if(messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT'
        };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}
// strategy pattern
    //strategy interface
class MessageHandler {
    handle(message) {
        throw new Error("Method 'handle' must be implemented.");
    }
}
    // strategy picker
    class MessageContext {
        constructor(handler) {
            this.handler = handler;
        }

        setHandler(handler) {
            this.handler = handler;
        }

        handleMessage(message) {
            this.handler.handle(message);
        }
    }
    // the concrete strategies
        //chat type
class ChatMessageHandler extends MessageHandler {
    handle(message) {
        var messageElement = document.createElement('li');
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);
        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);

        var textElement = document.createElement('p');
        var messageText = document.createTextNode(message.content);
        textElement.appendChild(messageText);

        messageElement.appendChild(textElement);

        messageArea.appendChild(messageElement);
        messageArea.scrollTop = messageArea.scrollHeight;
    }
}
        //join type
class JoinMessageHandler extends MessageHandler {
    handle(message) {
        // Handle JOIN messages
        var messageElement = document.createElement('li');
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';

        var textElement = document.createElement('p');
        var messageText = document.createTextNode(message.content);
        textElement.appendChild(messageText);

        messageElement.appendChild(textElement);

        messageArea.appendChild(messageElement);
        messageArea.scrollTop = messageArea.scrollHeight;
    }
}
        // leave
class LeaveMessageHandler extends MessageHandler {
    handle(message) {
        // Handle LEAVE messages
        var messageElement = document.createElement('li');
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';

        var textElement = document.createElement('p');
        var messageText = document.createTextNode(message.content);
        textElement.appendChild(messageText);

        messageElement.appendChild(textElement);

        messageArea.appendChild(messageElement);
        messageArea.scrollTop = messageArea.scrollHeight;
    }
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    var messageContext = new MessageContext();
    if (message.type === 'JOIN') {
        messageContext.setHandler(new JoinMessageHandler());
    } else if (message.type === 'LEAVE') {
        messageContext.setHandler(new LeaveMessageHandler());
    }
    else{
        messageContext.setHandler(new ChatMessageHandler());
    }
    //  the strategycaller to handle the message
    messageContext.handleMessage(message);
}

function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }
    var index = Math.abs(hash % colors.length);
    return colors[index];
}

usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', sendMessage, true)
document.querySelector('#messaging').addEventListener('click', handleMessagesClick);
// usernameForm.addEventListener('submit', handleUsernameFormSubmit);
