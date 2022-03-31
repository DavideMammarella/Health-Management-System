package it.unimib.insalute.models;

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
@Table(name = "blood_test")
@Getter
@Setter
@NoArgsConstructor
public class BloodTest extends MedicalReport {

    @Column(name = "bt_wbc", nullable = false)
    private double bloodTestWBC;

    @Column(name = "bt_rbc", nullable = false)
    private double bloodTestRBC;

    @Column(name = "bt_hb", nullable = false)
    private double bloodTestHB;

    @Column(name = "bt_ht", nullable = false)
    private int bloodTestHT;

    @Column(name = "bt_mcv", nullable = false)
    private int bloodTestMCV;

    @Column(name = "bt_mch", nullable = false)
    private int bloodTestMCH;

    @Column(name = "bt_mchc", nullable = false)
    private int bloodTestMCHC;

    @Column(name = "bt_rdw", nullable = false)
    private double bloodTestRDW;

    @Column(name = "bt_plts", nullable = false)
    private int bloodTestPLTS;

    @Column(name = "bt_mpv", nullable = false)
    private double bloodTestMPV;

    @Column(name = "bt_descr", nullable = false)
    private String bloodTestDescription;

    /**
     * Entity constructor.
     * It inherits variables from the MedicalReport superclass.
     * This constructor is used in the persistence layer.
     * @param medicalReportId ID of the blood test.
     * @param medicalReportDate Date of the blood test.
     * @param bloodTestWBC The level of white blood cells.
     * @param bloodTestRBC The level of red blood cells.
     * @param bloodTestHB Level of hemoglobin molecules.
     * @param bloodTestHT Similar to RBC but in percentage.
     * @param bloodTestMCV The average volume of red blood cells.
     * @param bloodTestMCH The average amount of hemoglobin in each red blood cell.
     * @param bloodTestMCHC The average concentration of hemoglobin in a red blood cell.
     * @param bloodTestRDW The magnitude of the GR volume distribution around its mean value.
     * @param bloodTestPLTS Platelets levels in the blood.
     * @param bloodTestMPV The mean platelet volume.
     * @param bloodTestDescription Description of the blood test.
     */
    public BloodTest(Long medicalReportId, Date medicalReportDate, double bloodTestWBC, double bloodTestRBC, double bloodTestHB, int bloodTestHT, int bloodTestMCV, int bloodTestMCH, int bloodTestMCHC, double bloodTestRDW, int bloodTestPLTS, double bloodTestMPV, String bloodTestDescription) {
        super(medicalReportId, medicalReportDate);
        this.bloodTestWBC = bloodTestWBC;
        this.bloodTestRBC = bloodTestRBC;
        this.bloodTestHB = bloodTestHB;
        this.bloodTestHT = bloodTestHT;
        this.bloodTestMCV = bloodTestMCV;
        this.bloodTestMCH = bloodTestMCH;
        this.bloodTestMCHC = bloodTestMCHC;
        this.bloodTestRDW = bloodTestRDW;
        this.bloodTestPLTS = bloodTestPLTS;
        this.bloodTestMPV = bloodTestMPV;
        this.bloodTestDescription = bloodTestDescription;
    }
}
