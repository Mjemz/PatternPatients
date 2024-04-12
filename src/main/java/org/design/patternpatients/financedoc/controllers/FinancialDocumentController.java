package org.design.patternpatients.financedoc.controllers;

import org.design.patternpatients.financedoc.factorypattern.FinancialDocument;
import org.design.patternpatients.financedoc.factorypattern.FinancialDocumentFactory;
import org.design.patternpatients.healthCareModule.entities.Patients;
import org.design.patternpatients.healthCareModule.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class FinancialDocumentController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/generate/bill/{patientId}")
    public String generateBill(@PathVariable Long patientId, Model model) {
        // Fetch the patient's details
        Optional<Patients> patientoptional = patientService.getPatientById(patientId);
        if (patientoptional.isPresent()) {
            Patients patient = patientoptional.get();
            // Generate the bill content based on the patient's details
            String documentContent = "Amount: " + patient.getAmount() + ", Description: " + patient.getTreatment();

            // Create the bill document using the factory
            FinancialDocument bill = FinancialDocumentFactory.getFinancialDocument("Bill", documentContent);

            // Add the generated document to the model
            model.addAttribute("document", bill.generateDocument());

            // Return the view name corresponding to the bill document type
            return bill.getDocumentType().toLowerCase();
        }
        return "error";
    }
}
