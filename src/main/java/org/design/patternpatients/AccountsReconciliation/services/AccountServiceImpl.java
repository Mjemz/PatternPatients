package org.design.patternpatients.AccountsReconciliation.services;
import org.design.patternpatients.AccountsReconciliation.entities.AccountItem;
import org.design.patternpatients.AccountsReconciliation.templatepattern.AccountService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private List<AccountItem> accountsReceivableInvoices = new ArrayList<>();
    @Override
    public void addAccountsReceivableInvoice(AccountItem invoice) {
        accountsReceivableInvoices.add(invoice);
    }
@Override
public List<AccountItem> getAccountsReceivableInvoices() {
    return accountsReceivableInvoices;
}
    @Override
    public List<AccountItem> getAccountsPayableBills() {
        return Arrays.asList(
                new AccountItem(1L, new BigDecimal("500.00"), LocalDate.now(), "Money for pharmacy drugs"),
                new AccountItem(2L, new BigDecimal("1500.00"), LocalDate.now(), "Money for medical equipment")
        );
    }
    public AccountItem createBill(String description, BigDecimal amount) {
        return new AccountItem(null, amount, LocalDate.now(), description);
    }

    @Override
    public BigDecimal getTotalAccountsReceivable() {
        return getAccountsReceivableInvoices().stream()
                .map(AccountItem::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal getTotalAccountsPayable() {
        return getAccountsPayableBills().stream()
                .map(AccountItem::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal getNetBalance() {

        return getTotalAccountsReceivable().subtract(getTotalAccountsPayable());
    }
}

