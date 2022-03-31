package it.unimib.insalute.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Spring JPA Entity.
 * @JsonIgnoreProperties avoids serialization issues.
 * @JsonIdentityInfo avoids serialization issues.
 * It uses Lombok to reduce verbosity.
 */
@Entity
@Table(name = "patient")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "relationClass"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "patientId")
@Getter
@Setter
@NoArgsConstructor
public class Patient {

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id", nullable = false)
    private @Id
    Long patientId;

    @Column(name = "patient_ifc", nullable = false)
    private String patientIFC;

    @Column(name = "patient_firstname", nullable = false)
    private String patientFirstname;

    @Column(name = "patient_lastname", nullable = false)
    private String patientLastname;

    // RELATIONSHIPS

    @ManyToMany(mappedBy = "patients")
    private Set<Doctor> doctors = new HashSet<>();

    @ManyToMany(mappedBy = "patients")
    private Set<MedicalReport> medicalReports = new HashSet<>();

    // RELATIONSHIPS OPERATION

    /**
     * Adds an entity inside a ManyToMany relationship.
     * @param doctor Doctor to add.
     */
    public void addDoctor(Doctor doctor) {
        this.doctors.add(doctor);
        doctor.getPatients().add(this);
    }

    /**
     * Remove an entity inside a ManyToMany relationship.
     * @param doctor Doctor to remove.
     */
    public void removeDoctor(Doctor doctor) {
        this.doctors.remove(doctor);
        doctor.getPatients().remove(this);
    }

    /**
     * Adds an entity inside a ManyToMany relationship.
     * @param medicalReport Medical Report to add.
     */
    public void addMedicalReport(MedicalReport medicalReport) {
        this.medicalReports.add(medicalReport);
        medicalReport.getPatients().add(this);
    }

    /**
     * Remove an entity inside a ManyToMany relationship.
     * @param medicalReport Medical Report to add.
     */
    public void removeMedicalReport(MedicalReport medicalReport) {
        this.medicalReports.remove(medicalReport);
        medicalReport.getPatients().remove(this);
    }

    // CONSTRUCTOR

    /**
     * Entity constructor.
     * This constructor is used in the persistence layer.
     *
     * @param patientId ID of the patient.
     * @param patientIFC Italian Fiscal Code of the patient.
     * @param patientFirstname Firstname of the patient.
     * @param patientLastname Lastname of the patient.
     */
    public Patient(Long patientId, String patientIFC, String patientFirstname, String patientLastname) {
        super();
        this.patientId = patientId;
        this.patientIFC = patientIFC;
        this.patientFirstname = patientFirstname;
        this.patientLastname = patientLastname;
    }
}
