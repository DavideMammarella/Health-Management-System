package it.unimib.insalute.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;

/**
 * Spring JPA Entity.
 * This entity is part of an IS-A relationship (it is parent of BloodTest and CovidTest classes).
 * @JsonIgnoreProperties avoids serialization issues.
 * @JsonIdentityInfo avoids serialization issues.
 * It uses Lombok to reduce verbosity.
 */
@Entity
@Table(name = "medical_report")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "relationClass"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "medicalReportId")
@Getter
@Setter
@NoArgsConstructor
public class MedicalReport {

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_report_id", nullable = false)
    private @Id
    Long medicalReportId;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "medical_report_date", nullable = false)
    private Date medicalReportDate;

    // RELATIONSHIPS

    @ManyToMany
    @JoinTable(name = "patient_medical_report",
            joinColumns = {@JoinColumn(name = "medical_report_id")},
            inverseJoinColumns = {@JoinColumn(name = "patient_id")})
    private Set<Patient> patients = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "health_facility_medical_report",
            joinColumns = {@JoinColumn(name = "medical_report_id")},
            inverseJoinColumns = {@JoinColumn(name = "health_facility_id")})
    private Set<HealthFacility> healthFacilities = new HashSet<>();

    // CONSTRUCTOR

    /**
     * Entity constructor.
     * This constructor is used in the persistence layer.
     * @param medicalReportId ID of the medical report.
     * @param medicalReportDate Date of the medical report.
     */
    public MedicalReport(Long medicalReportId, Date medicalReportDate) {
        this.medicalReportId = medicalReportId;
        this.medicalReportDate = medicalReportDate;
    }
}
