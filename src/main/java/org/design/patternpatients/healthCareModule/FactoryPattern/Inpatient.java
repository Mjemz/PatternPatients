package org.design.patternpatients.healthCareModule.FactoryPattern;
import lombok.Getter;
import org.design.patternpatients.healthCareModule.entities.Patients;

@Getter
public class Inpatient implements PatientInterface {
    private Patients patient;
    public Inpatient() {
        this.patient = new Patients();
    }
    @Override
    public void setPatientDetails(String patientName, Integer age, Integer weight, String treatment, String email, Integer amount, String patientType) {
        patient.setPatient_name(patientName);
        patient.setAge(age);
        patient.setWeight(weight);
        patient.setTreatment(treatment);
        patient.setEmail(email);
        patient.setAmount(amount);
        patient.setPatientType(patientType);
    }

}