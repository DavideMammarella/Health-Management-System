package it.unimib.insalute.services;

import it.unimib.insalute.models.Doctor;
import it.unimib.insalute.models.Patient;
import it.unimib.insalute.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Spring JPA Service.
 * Defines advanced business logic.
 */
@Service
public class PatientService {
    private PatientRepository patientRepository;

    /**
     * Service constructor.
     * @param patientRepository repository layer of the patient.
     */
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // CREATE-UPDATE

    /**
     * Add a patient to the database.
     * @param patient Patient to add.
     * @return persist the medical report.
     */
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // READ

    /**
     * Get all patients in the database.
     * @return list of all patients in the database.
     */
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    /**
     * Get a patient given the ID.
     * @param id ID of the patient.
     * @return patient.
     */
    public Patient getOnePatientById(Long id) {
        return patientRepository.getOne(id);
    }

    /**
     * Get patients given the ifc.
     * @param ifc ifc of the patient.
     * @return set of patients.
     */
    public Set<Patient> getPatientByIFC(String ifc) {
        return patientRepository.findPatientsByPatientIFC(ifc);
    }

    /**
     * Get patients given the firstname.
     * @param firstname firstname of the patient.
     * @return set of patients.
     */
    public Set<Patient> getPatientsByFirstname(String firstname) {
        return patientRepository.findPatientsByPatientFirstname(firstname);
    }

    /**
     * Get patients given the firstname.
     * @param lastname lastname of the patient.
     * @return set of patients.
     */
    public Set<Patient> getPatientsByLastname(String lastname) {
        return patientRepository.findPatientsByPatientLastname(lastname);
    }

    // DELETE

    /**
     * Delete a patient.
     * @param patient patient to delete.
     */
    public void deletePatient(Patient patient) {
        patientRepository.delete(patient);
    }

    /**
     * Delete a patient given the ID.
     * @param id ID of the patient.
     */
    public void deletePatientById(Long id) {
        patientRepository.delete(patientRepository.getOne(id));
    }

    // SEARCH

    /**
     * Search doctors associated to a patient.
     * @param id ID of the patient.
     * @return list of doctors.
     */
    public Set<Doctor> searchDoctorsByPatientId(Long id) {
        Patient patientWithDoctors = patientRepository.findById(1L).get();
        return patientWithDoctors.getDoctors();
    }
}
