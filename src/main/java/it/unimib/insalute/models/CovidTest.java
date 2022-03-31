package it.unimib.insalute.models;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Spring JPA Entity.
 * This entity is part of an IS-A relationship (it is child of the MedicalReport class, then extends it).
 * It uses Lombok to reduce verbosity.
 */
@Entity
@Table(name = "covid_test")
@Getter
@Setter
@NoArgsConstructor
public class CovidTest extends MedicalReport {

    @Column(name = "ct_result", nullable = false)
    private @NotNull
    String covidTestResult;

    /**
     * Entity constructor.
     * It inherits variables from the MedicalReport superclass.
     * This constructor is used in the persistence layer.
     * @param medicalReportId ID of the covid test.
     * @param medicalReportDate Date of the covid test.
     * @param covidTestResult Result of the covid test.
     */
    public CovidTest(Long medicalReportId, Date medicalReportDate, String covidTestResult) {
        super(medicalReportId, medicalReportDate);
        this.covidTestResult = covidTestResult;
    }
}
