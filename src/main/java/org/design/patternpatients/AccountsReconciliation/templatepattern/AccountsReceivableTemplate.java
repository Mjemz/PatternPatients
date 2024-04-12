package org.design.patternpatients.AccountsReconciliation.templatepattern;
import org.design.patternpatients.AccountsReconciliation.entities.AccountItem;
import org.design.patternpatients.AccountsReconciliation.templatepattern.ReconciliationTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.design.patternpatients.AccountsReconciliation.templatepattern.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.math.BigDecimal;
import java.util.List;
@Service
public class AccountsReceivableTemplate implements ReconciliationTemplate {
    @Autowired
    private AccountService accountService;

    @Override
    public List<AccountItem> fetchAccounts() {
        return accountService.getAccountsReceivableInvoices();
    }

    @Override
    public BigDecimal calculateTotal() {
        return accountService.getTotalAccountsReceivable();
    }

    @Override
    public void render(Model model) {
        List<AccountItem> accountsReceivableInvoices = fetchAccounts();
        BigDecimal totalAccountsReceivable = calculateTotal();
        model.addAttribute("accountsReceivableInvoices", accountsReceivableInvoices);
        model.addAttribute("totalAccountsReceivable", totalAccountsReceivable);
    }
}
