package org.design.patternpatients.healthCareModule.service;

import org.design.patternpatients.healthCareModule.entities.Patients;
import org.design.patternpatients.healthCareModule.repos.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImp implements PatientService {

    private final PatientRepository repository;

    @Autowired
    public PatientServiceImp(PatientRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Patients> getAllPatients()
    {
        return repository.findAll();
    }
    @Override
    public Patients savePatient(Patients patient) {
        return repository.save(patient);
    }

    @Override
    public Optional<Patients> getPatientById(Long patientId) {
        return repository.findById(patientId);
    }
}
