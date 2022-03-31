package it.unimib.insalute.services;

import it.unimib.insalute.models.BloodTest;
import it.unimib.insalute.repositories.BloodTestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Spring JPA Service.
 * Defines advanced business logic.
 */
@Service
public class BloodTestService {

    private BloodTestRepository bloodTestRepository;

    /**
     * Service constructor.
     * @param bloodTestRepository repository layer of the blood test.
     */
    public BloodTestService(BloodTestRepository bloodTestRepository) {
        this.bloodTestRepository = bloodTestRepository;
    }

    // CREATE-UPDATE

    /**
     * Add a blood test to the database.
     * @param bloodTest Blood Test to add.
     * @return persist the blood test.
     */
    public BloodTest addBloodTest(BloodTest bloodTest) {
        return bloodTestRepository.save(bloodTest);
    }

    // READ

    /**
     * Get all blood tests in the database.
     * @return list of all blood tests in the database.
     */
    public List<BloodTest> getBloodTests() {
        return bloodTestRepository.findAll();
    }

    /**
     * Get a blood test given the ID.
     * @param id ID of the blood test.
     * @return blood test.
     */
    public BloodTest getOneBloodTestById(Long id) {
        return bloodTestRepository.getOne(id);
    }

    // DELETE

    /**
     * Delete a blood test.
     * @param bloodTest blood test to delete.
     */
    public void deleteBloodTest(BloodTest bloodTest) {
        bloodTestRepository.delete(bloodTest);
    }

    /**
     * Delete a blood test given the ID.
     * @param id ID of the blood test.
     */
    public void deleteBloodTestById(Long id) {
        bloodTestRepository.delete(bloodTestRepository.getOne(id));
    }

}
