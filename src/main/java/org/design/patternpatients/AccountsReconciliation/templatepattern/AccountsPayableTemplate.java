package org.design.patternpatients.AccountsReconciliation.templatepattern;
import org.design.patternpatients.AccountsReconciliation.entities.AccountItem;
import org.design.patternpatients.AccountsReconciliation.templatepattern.AccountService;
import org.design.patternpatients.AccountsReconciliation.templatepattern.ReconciliationTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.math.BigDecimal;
import java.util.List;
@Service
public class AccountsPayableTemplate implements ReconciliationTemplate {
    @Autowired
    private AccountService accountService;

    @Override
    public List<AccountItem> fetchAccounts() {
        return accountService.getAccountsPayableBills();
    }

    @Override
    public BigDecimal calculateTotal() {
        return accountService.getTotalAccountsPayable();
    }

    @Override
    public void render(Model model) {
        List<AccountItem> accountsPayableBills = fetchAccounts();

        BigDecimal totalAccountsPayable = calculateTotal();
        model.addAttribute("accountsPayableBills", accountsPayableBills);
        model.addAttribute("totalAccountsPayable", totalAccountsPayable);
    }
}
