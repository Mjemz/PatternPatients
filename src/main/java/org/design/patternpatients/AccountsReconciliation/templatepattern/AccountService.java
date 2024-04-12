package org.design.patternpatients.AccountsReconciliation.templatepattern;
import org.design.patternpatients.AccountsReconciliation.entities.AccountItem;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
@Component
public interface AccountService {
    List<AccountItem> getAccountsReceivableInvoices();
    List<AccountItem> getAccountsPayableBills();
//    List<AccountItem> getGeneralLedgerEntries();
    BigDecimal getTotalAccountsReceivable();

    BigDecimal getTotalAccountsPayable();
//    BigDecimal getTotalGeneralLedgerEntries();
    public void addAccountsReceivableInvoice(AccountItem invoice);
    BigDecimal getNetBalance();
    public AccountItem createBill(String description, BigDecimal amount);
}

