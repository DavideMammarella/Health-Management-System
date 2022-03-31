package it.unimib.insalute.controllers;

import it.unimib.insalute.models.Doctor;
import it.unimib.insalute.services.DoctorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Spring MVC Controller.
 * Defines the REST controller for a web-based extension.
 */
@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // CREATE

    /**
     * Add a doctor to the database.
     * @param doctor doctor to add.
     * @return persist the doctor.
     */
    @PostMapping
    public Doctor createDoctor(@RequestBody final Doctor doctor) {
        return doctorService.addDoctor(doctor);
    }

    // READ

    /**
     * Get all doctors in the database.
     * @return list of all doctors in the database.
     */
    @GetMapping
    public List<Doctor> list() {
        return doctorService.getDoctors();
    }

    /**
     * Get a doctor given the ID.
     * @param id ID of the doctor.
     * @return doctor.
     */
    @GetMapping
    @RequestMapping(value = "{id}")
    public Doctor getDoctorById(@PathVariable Long id) {
        return doctorService.getOneDoctorById(id);
    }

    // UPDATE

    /**
     * Update a doctor given the ID.
     * @param id ID of the doctor.
     * @param doctor new Doctor.
     * @return persist the doctor updating.
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Doctor updateDoctorById(@PathVariable Long id, @RequestBody Doctor doctor) {
        Doctor existingDoctor = doctorService.getOneDoctorById(id);
        BeanUtils.copyProperties(doctor, existingDoctor, "doctor_id");
        return doctorService.addDoctor(existingDoctor);
    }

    // DELETE

    /**
     * Delete a doctor given the ID.
     * @param id ID of the doctor.
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteDoctorById(@PathVariable Long id) {
        doctorService.deleteDoctorById(id);
    }

}
