package org.design.patternpatients.financedoc.factorypattern.concrete;


import org.design.patternpatients.financedoc.factorypattern.FinancialDocument;
public class Invoice extends FinancialDocument {
    private String documentType;
    private String documentContent;

    public Invoice(String documentType, String documentContent) {
        this.documentType = documentType;
        this.documentContent = documentContent;
    }
    @Override
    public String getDocumentType() {
        return documentType;
    }
    @Override
    public String generateDocument() {
        return documentContent;
    }
}

