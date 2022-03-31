package it.unimib.insalute.services;

import it.unimib.insalute.models.MedicalReport;
import it.unimib.insalute.repositories.MedicalReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Spring JPA Service.
 * Defines advanced business logic.
 */
@Service
public class MedicalReportService {

    private MedicalReportRepository medicalReportRepository;

    /**
     * Service constructor.
     * @param medicalReportRepository repository layer of the medical report.
     */
    public MedicalReportService(MedicalReportRepository medicalReportRepository) {
        this.medicalReportRepository = medicalReportRepository;
    }

    // CREATE-UPDATE

    /**
     * Add a medical report to the database.
     * @param medicalReport medical report to add.
     * @return persist the medical report.
     */
    public MedicalReport addMedicalReport(MedicalReport medicalReport) {
        return medicalReportRepository.save(medicalReport);
    }

    // READ

    /**
     * Get all reports in the database.
     * @return list of all reports in the database.
     */
    public List<MedicalReport> getMedicalReports() {
        return medicalReportRepository.findAll();
    }

    /**
     * Get a medical report given the ID.
     * @param id ID of the medical report.
     * @return report.
     */
    public MedicalReport getOneMedicalReportById(Long id) {
        return medicalReportRepository.getOne(id);
    }

    // DELETE

    /**
     * Delete a report.
     * @param medicalReport medical report to delete.
     */
    public void deleteMedicalReport(MedicalReport medicalReport) {
        medicalReportRepository.delete(medicalReport);
    }

    /**
     * Delete a report given the ID.
     * @param id ID of the medical report.
     */
    public void deleteMedicalReportById(Long id) {
        medicalReportRepository.delete(medicalReportRepository.getOne(id));
    }
}
