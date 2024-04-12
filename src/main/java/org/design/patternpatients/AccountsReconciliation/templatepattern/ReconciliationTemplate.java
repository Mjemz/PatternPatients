package org.design.patternpatients.AccountsReconciliation.templatepattern;

import org.design.patternpatients.AccountsReconciliation.entities.AccountItem;
import org.springframework.ui.Model;
import java.math.BigDecimal;
import java.util.List;

// template interface
public interface ReconciliationTemplate {
    List<AccountItem> fetchAccounts();
    BigDecimal calculateTotal();
    void render(Model model);
}
