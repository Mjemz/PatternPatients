package org.design.patternpatients.healthCareModule.repos;
import org.design.patternpatients.healthCareModule.entities.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patients, Long>{
}
