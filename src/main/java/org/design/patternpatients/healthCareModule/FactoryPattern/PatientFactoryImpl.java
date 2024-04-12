package org.design.patternpatients.healthCareModule.FactoryPattern;

import org.springframework.stereotype.Component;
@Component
public class PatientFactoryImpl implements PatientFactory {
    @Override
    public PatientInterface createPatient(String type) {
        if ("inpatient".equalsIgnoreCase(type)) {
            return new Inpatient();
        } else if ("outpatient".equalsIgnoreCase(type)) {
            return new Outpatient();
        } else {
            throw new IllegalArgumentException("Invalid patient type");
        }
    }
}

