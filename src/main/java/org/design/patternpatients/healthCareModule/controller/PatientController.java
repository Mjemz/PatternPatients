package org.design.patternpatients.healthCareModule.controller;

import org.design.patternpatients.AccountsReconciliation.entities.AccountItem;
import org.design.patternpatients.AccountsReconciliation.services.AccountServiceImpl;
import org.design.patternpatients.financedoc.factorypattern.FinancialDocument;
import org.design.patternpatients.financedoc.factorypattern.FinancialDocumentFactory;
import org.design.patternpatients.healthCareModule.FactoryPattern.PatientFactory;
import org.design.patternpatients.healthCareModule.FactoryPattern.PatientInterface;
import org.design.patternpatients.healthCareModule.entities.Patients;
import org.design.patternpatients.healthCareModule.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

// The factory client
@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private AccountServiceImpl accountService;
    //private PatientRepository repository;
    @Autowired
    private PatientFactory patientFactory;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/patients")
    public String listPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patients";
    }

    @GetMapping("/patients/new")
    public String createPatientForm(Model model) {

        // Create patient object to hold patient form data
        Patients patient = new Patients();
        model.addAttribute("patient", patient);
        return "addPatient";
    }

    @PostMapping("/patients")
    public String savePatient(@ModelAttribute("patient") Patients patient,@RequestParam("patientType") String patientType) {
        PatientInterface createdPatient = patientFactory.createPatient(patientType);
        createdPatient.setPatientDetails(patient.getPatient_name(), patient.getAge(), patient.getWeight(), patient.getTreatment(), patient.getEmail(), patient.getAmount(), patient.getPatientType());
        // Adds a new accounts receivable to the system
        AccountItem receivable = accountService.createBill("Bill generated " + patient.getPatient_name(), new BigDecimal(patient.getAmount()));
        accountService.addAccountsReceivableInvoice(receivable);
        patient.setBill(receivable);

        // generates a bill for a patient
        String documentContent = "Amount: " + patient.getAmount() + ", Description: " + patient.getTreatment();
        FinancialDocument bill = FinancialDocumentFactory.getFinancialDocument("Bill", documentContent);

        // saves patient to the database
        patientService.savePatient(patient);
        return "redirect:/patients";
    }

}
