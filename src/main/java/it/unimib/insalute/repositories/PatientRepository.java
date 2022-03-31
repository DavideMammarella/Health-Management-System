package it.unimib.insalute.repositories;

import it.unimib.insalute.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Spring JPA Repository.
 * Interface delegated to communicate with the data source.
 * Extends JpaRepository to implement basic CRUD operations.
 * Uses Spring Data's Query Method to define custom queries.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Set<Patient> findPatientsByPatientIFC(String ifc);
    Set<Patient> findPatientsByPatientFirstname(String firstname);
    Set<Patient> findPatientsByPatientLastname(String lastname);
}
