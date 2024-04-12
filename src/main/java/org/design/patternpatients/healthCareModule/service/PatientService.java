package org.design.patternpatients.healthCareModule.service;
import org.design.patternpatients.healthCareModule.entities.Patients;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    Patients savePatient(Patients patient);
    List<Patients> getAllPatients();
    Optional<Patients> getPatientById(Long patientId);
}
