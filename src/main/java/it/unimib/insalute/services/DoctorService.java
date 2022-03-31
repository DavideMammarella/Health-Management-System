package it.unimib.insalute.services;

import it.unimib.insalute.models.Doctor;
import it.unimib.insalute.repositories.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Spring JPA Service.
 * Defines advanced business logic.
 */
@Service
public class DoctorService {

    private DoctorRepository doctorRepository;

    /**
     * Service constructor.
     * @param doctorRepository repository layer of the doctor.
     */
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // CREATE-UPDATE

    /**
     * Add a doctor to the database.
     * @param doctor doctor to add.
     * @return persist the doctor.
     */
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // READ

    /**
     * Get all doctors in the database.
     * @return list of all doctors in the database.
     */
    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    /**
     * Get a doctor given the ID.
     * @param id ID of the doctor.
     * @return doctor.
     */
    public Doctor getOneDoctorById(Long id) {
        return doctorRepository.getOne(id);
    }

    /**
     * Get a doctor given the firstname.
     * @param firstname firstname of the doctor.
     * @return doctor.
     */
    public Set<Doctor> getDoctorsByFirstname(String firstname) {
        return doctorRepository.findDoctorByDoctorFirstname(firstname);
    }

    /**
     * Get a doctor given the lastname.
     * @param lastname lastname of the doctor.
     * @return doctor.
     */
    public Set<Doctor> getDoctorsByLastname(String lastname) {
        return doctorRepository.findDoctorByDoctorLastname(lastname);
    }

    /**
     * Get a doctor given the specialization.
     * @param specialization specialization of the doctor.
     * @return doctor.
     */
    public Set<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.findDoctorByDoctorSpecialization(specialization);
    }

    // DELETE

    /**
     * Delete a doctor given the ID.
     * @param doctor doctor to delete.
     */
    public void deleteDoctor(Doctor doctor) {
        doctorRepository.delete(doctor);
    }

    /**
     * Delete a doctor given the ID.
     * @param id ID of the doctor.
     */
    public void deleteDoctorById(Long id) {
        doctorRepository.delete(doctorRepository.getOne(id));
    }
}
