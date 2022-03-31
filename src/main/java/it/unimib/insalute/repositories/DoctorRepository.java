package it.unimib.insalute.repositories;

import it.unimib.insalute.models.Doctor;
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
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Set<Doctor> findDoctorByDoctorFirstname(String firstname);
    Set<Doctor> findDoctorByDoctorLastname(String lastname);
    Set<Doctor> findDoctorByDoctorSpecialization(String specialization);
}
