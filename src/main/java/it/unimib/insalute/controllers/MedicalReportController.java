package it.unimib.insalute.controllers;

import it.unimib.insalute.models.MedicalReport;
import it.unimib.insalute.services.MedicalReportService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Spring MVC Controller.
 * Defines the REST controller for a web-based extension.
 */
@RestController
@RequestMapping("/api/medical_reports")
public class MedicalReportController {

    @Autowired
    private MedicalReportService medicalReportService;

    // CREATE

    /**
     * Add a medical report to the database.
     * @param medicalReport medical report to add.
     * @return persist the medical report.
     */
    @PostMapping
    public MedicalReport createMedicalReport(@RequestBody final MedicalReport medicalReport) {
        return medicalReportService.addMedicalReport(medicalReport);
    }

    // READ

    /**
     * Get all reports in the database.
     * @return list of all reports in the database.
     */
    @GetMapping
    public List<MedicalReport> list() {
        return medicalReportService.getMedicalReports();
    }

    /**
     * Get a medical report given the ID.
     * @param id ID of the medical report.
     * @return report.
     */
    @GetMapping
    @RequestMapping(value = "{id}")
    public MedicalReport getMedicalReportById(@PathVariable Long id) {
        return medicalReportService.getOneMedicalReportById(id);
    }

    // UPDATE

    /**
     * Update a medical report given the ID.
     * @param id ID of the medical report.
     * @param medicalReport new medical report.
     * @return persist the report updating.
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public MedicalReport updateMedicalReportById(@PathVariable Long id, @RequestBody MedicalReport medicalReport) {
        MedicalReport existingMedicalReport = medicalReportService.getOneMedicalReportById(id);
        BeanUtils.copyProperties(medicalReport, existingMedicalReport, "medical_report_id");
        return medicalReportService.addMedicalReport(existingMedicalReport);
    }

    // DELETE

    /**
     * Delete a medical report given the ID.
     * @param id ID of the medical report.
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteMedicalReportById(@PathVariable Long id) {
        medicalReportService.deleteMedicalReportById(id);
    }

}
