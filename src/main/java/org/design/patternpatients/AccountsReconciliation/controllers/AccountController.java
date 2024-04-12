package org.design.patternpatients.AccountsReconciliation.controllers;
import org.design.patternpatients.AccountsReconciliation.templatepattern.AccountsPayableTemplate;
import org.design.patternpatients.AccountsReconciliation.templatepattern.AccountsReceivableTemplate;
//import org.design.patternpatients.AccountsReconciliation.services.GeneralLedgerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @Autowired
    private AccountsReceivableTemplate accountsReceivableTemplate;
    @Autowired
    private AccountsPayableTemplate accountsPayableTemplate;

//    @Autowired
    //private GeneralLedgerTemplate generalLedgerTemplate;

    @GetMapping("/reconciliation")
    public String accountsReconciliation(Model model) {
        accountsReceivableTemplate.render(model);
        accountsPayableTemplate.render(model);
        //generalLedgerTemplate.render(model);
        return "reconciliation";
    }
}