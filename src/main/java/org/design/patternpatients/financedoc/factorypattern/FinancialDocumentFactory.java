package org.design.patternpatients.financedoc.factorypattern;
import org.design.patternpatients.financedoc.factorypattern.FinancialDocument;
import org.design.patternpatients.financedoc.factorypattern.concrete.Bill;
import org.design.patternpatients.financedoc.factorypattern.concrete.Invoice;
import org.design.patternpatients.financedoc.factorypattern.concrete.Receipt;

public class FinancialDocumentFactory {
    public static FinancialDocument getFinancialDocument(String type, String documentContent) {
        if ("Receipt".equalsIgnoreCase(type)) {
            return new Receipt(type, documentContent);
        } else if ("Bill".equalsIgnoreCase(type)) {
            return new Bill(type, documentContent);
        } else if ("Invoice".equalsIgnoreCase(type)) {
            return new Invoice(type, documentContent);
        }
        return null;
    }
}
