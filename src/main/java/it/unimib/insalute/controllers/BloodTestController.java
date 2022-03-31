package it.unimib.insalute.controllers;

import it.unimib.insalute.models.BloodTest;
import it.unimib.insalute.models.MedicalReport;
import it.unimib.insalute.services.BloodTestService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Spring MVC Controller.
 * Defines the REST controller for a web-based extension.
 */
@RestController
@RequestMapping("/api/medical_reports/blood_tests")
public class BloodTestController {

    @Autowired
    private BloodTestService bloodTestService;

    // CREATE

    /**
     * Add a blood test to the database.
     * @param bloodTest Blood Test to add.
     * @return persist the blood test.
     */
    @PostMapping
    public BloodTest createBloodTest(@RequestBody final BloodTest bloodTest) {
        return bloodTestService.addBloodTest(bloodTest);
    }

    // READ

    /**
     * Get all blood tests in the database.
     * @return list of all blood tests in the database.
     */
    @GetMapping
    public List<BloodTest> list() {
        return bloodTestService.getBloodTests();
    }

    /**
     * Get a blood test given the ID.
     * @param id ID of the blood test.
     * @return blood test.
     */
    @GetMapping
    @RequestMapping(value = "{id}")
    public BloodTest getBloodTestById(@PathVariable Long id) {
        return bloodTestService.getOneBloodTestById(id);
    }

    // UPDATE

    /**
     * Update a blood test given the ID.
     * @param id ID of the blood test.
     * @param medicalReport new blood test.
     * @return persist the blood test updating.
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public BloodTest updateBloodTestById(@PathVariable Long id, @RequestBody MedicalReport medicalReport) {
        BloodTest existingMedicalReport = bloodTestService.getOneBloodTestById(id);
        BeanUtils.copyProperties(medicalReport, existingMedicalReport, "medical_report_id");
        return bloodTestService.addBloodTest(existingMedicalReport);
    }

    // DELETE

    /**
     * Delete a blood test given the ID.
     * @param id ID of the blood test.
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteBloodTestById(@PathVariable Long id) {
        bloodTestService.deleteBloodTestById(id);
    }
}
