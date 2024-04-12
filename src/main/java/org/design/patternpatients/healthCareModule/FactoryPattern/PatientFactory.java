package org.design.patternpatients.healthCareModule.FactoryPattern;

import org.springframework.stereotype.Component;

@Component

public interface PatientFactory {
    PatientInterface createPatient(String type);
}
