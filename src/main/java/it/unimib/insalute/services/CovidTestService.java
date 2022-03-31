package it.unimib.insalute.services;

import it.unimib.insalute.models.CovidTest;
import it.unimib.insalute.repositories.CovidTestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Spring JPA Service.
 * Defines advanced business logic.
 */
@Service
public class CovidTestService {

    private CovidTestRepository covidTestRepository;

    /**
     * Service constructor.
     * @param covidTestRepository repository layer of the covid test.
     */
    public CovidTestService(CovidTestRepository covidTestRepository) {
        this.covidTestRepository = covidTestRepository;
    }

    // CREATE-UPDATE

    /**
     * Add a covid test to the database.
     * @param covidTest Covid Test to add.
     * @return persist the covid test.
     */
    public CovidTest addCovidTest(CovidTest covidTest) {
        return covidTestRepository.save(covidTest);
    }

    // READ

    /**
     * Get all covid tests in the database.
     * @return list of all covid tests in the database.
     */
    public List<CovidTest> getCovidTests() {
        return covidTestRepository.findAll();
    }

    /**
     * Get a covid test given the ID.
     * @param id ID of the covid test.
     * @return covid test.
     */
    public CovidTest getOneCovidTestById(Long id) {
        return covidTestRepository.getOne(id);
    }

    // DELETE

    /**
     * Delete a covid test.
     * @param covidTest covid test to delete.
     */
    public void deleteCovidTest(CovidTest covidTest) {
        covidTestRepository.delete(covidTest);
    }

    /**
     * Delete a covid test given the ID.
     * @param id ID of the blood test.
     */
    public void deleteCovidTestById(Long id) {
        covidTestRepository.delete(covidTestRepository.getOne(id));
    }
}
