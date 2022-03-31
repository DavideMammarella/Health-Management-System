package it.unimib.insalute.controllers;

import it.unimib.insalute.models.Doctor;
import it.unimib.insalute.models.Patient;
import it.unimib.insalute.services.PatientService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Spring MVC Controller.
 * Defines the REST controller for a web-based extension.
 */
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // CREATE

    /**
     * Add a patient to the database.
     * @param patient Patient to add.
     * @return persist the medical report.
     */
    @PostMapping
    public Patient createPatient(@RequestBody final Patient patient) {
        return patientService.addPatient(patient);
    }

    // READ

    /**
     * Get all patients in the database.
     * @return list of all patients in the database.
     */
    @GetMapping
    public List<Patient> list() {
        return patientService.getPatients();
    }

    /**
     * Get a patient given the ID.
     * @param id ID of the patient.
     * @return patient.
     */
    @GetMapping
    @RequestMapping("{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.getOnePatientById(id);
    }

    // UPDATE

    /**
     * Update a patient given the ID.
     * @param id ID of the patient.
     * @param patient new patient.
     * @return persist the patient updating.
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Patient updatePatientById(@PathVariable Long id, @RequestBody Patient patient) {
        Patient existingPatient = patientService.getOnePatientById(id);
        BeanUtils.copyProperties(patient, existingPatient, "patient_id");
        return patientService.addPatient(existingPatient);
    }

    // DELETE

    /**
     * Delete a patient given the ID.
     * @param id ID of the patient.
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deletePatientById(@PathVariable Long id) {
        patientService.deletePatientById(id);
    }

    // SEARCH

    /**
     * Search doctors associated to a patient.
     * @param id ID of the patient.
     * @return list of doctors.
     */
    @GetMapping
    @RequestMapping("/doctors_patient/{id}")
    public Set<Doctor> searchDoctorsByPatient(@PathVariable Long id) {
        return patientService.searchDoctorsByPatientId(id);
    }
}
