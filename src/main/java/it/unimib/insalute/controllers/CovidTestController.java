package it.unimib.insalute.controllers;

import it.unimib.insalute.models.CovidTest;
import it.unimib.insalute.models.MedicalReport;
import it.unimib.insalute.services.CovidTestService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Spring MVC Controller.
 * Defines the REST controller for a web-based extension.
 */
@RestController
@RequestMapping("/api/medical_reports/covid_tests")
public class CovidTestController {

    @Autowired
    private CovidTestService covidTestService;

    // CREATE

    /**
     * Add a covid test to the database.
     * @param covidTest Covid Test to add.
     * @return persist the covid test.
     */
    @PostMapping
    public CovidTest createMedicalReport(@RequestBody final CovidTest covidTest) {
        return covidTestService.addCovidTest(covidTest);
    }

    // READ

    /**
     * Get all covid tests in the database.
     * @return list of all covid tests in the database.
     */
    @GetMapping
    public List<CovidTest> list() {
        return covidTestService.getCovidTests();
    }

    /**
     * Get a covid test given the ID.
     * @param id ID of the covid test.
     * @return covid test.
     */
    @GetMapping
    @RequestMapping(value = "{id}")
    public CovidTest getMedicalReportById(@PathVariable Long id) {
        return covidTestService.getOneCovidTestById(id);
    }

    // UPDATE

    /**
     * Update a covid test given the ID.
     * @param id ID of the covid test.
     * @param medicalReport new covid test.
     * @return persist the covid test updating.
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public CovidTest updateCovidTestById(@PathVariable Long id, @RequestBody MedicalReport medicalReport) {
        CovidTest existingMedicalReport = covidTestService.getOneCovidTestById(id);
        BeanUtils.copyProperties(medicalReport, existingMedicalReport, "medical_report_id");
        return covidTestService.addCovidTest(existingMedicalReport);
    }

    // DELETE

    /**
     * Delete a covid test given the ID.
     * @param id ID of the covid test.
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteCovidTestById(@PathVariable Long id) {
        covidTestService.deleteCovidTestById(id);
    }
}
