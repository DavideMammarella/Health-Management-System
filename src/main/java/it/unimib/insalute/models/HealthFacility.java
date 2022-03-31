package it.unimib.insalute.models;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Spring JPA Entity.
 * Includes a self-reference relationship (a health facility may have multiple medical laboratories, which results in identical fields except name).
 * @JsonIgnoreProperties avoids serialization issues.
 * @JsonIdentityInfo avoids serialization issues.
 * It uses Lombok to reduce verbosity.
 */
@Entity
@Table(name = "health_facility")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "relationClass"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "healthFacilityId")
@Getter
@Setter
@NoArgsConstructor
public class HealthFacility {

    @Column(name = "health_facility_id", nullable = false)
    private @Id
    Long healthFacilityId;

    @Column(name = "health_facility_name", nullable = false)
    private String healthFacilityName;

    @Column(name = "health_facility_tipology", nullable = false)
    private String healthFacilityTipology;

    @Column(name = "health_facility_region", nullable = false)
    private String healthFacilityRegion;

    @Column(name = "health_facility_province", nullable = false)
    private String healthFacilityProvince;

    @Column(name = "health_facility_address", nullable = false)
    private String healthFacilityAddress;

    // RELATIONSHIPS

    @ManyToMany(mappedBy = "healthFacilities")
    private Set<MedicalReport> medicalReports = new HashSet<>();

    // RELATIONSHIPS OPERATION

    /**
     * Adds an entity inside a ManyToMany relationship.
     * @param medicalReport Medical Report to add.
     */
    public void addMedicalReport(MedicalReport medicalReport) {
        this.medicalReports.add(medicalReport);
        medicalReport.getHealthFacilities().add(this);
    }

    /**
     * Remove an entity inside a ManyToMany relationship.
     * @param medicalReport Medical Report to remove.
     */
    public void removeMedicalReport(MedicalReport medicalReport) {
        this.medicalReports.remove(medicalReport);
        medicalReport.getHealthFacilities().remove(this);
    }

    // SELF-REFERENCE

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private HealthFacility healthFacility;

    /**
     * CascadeType.ALL will propagates all operations from a parent to a child entity.
     */
    @OneToMany(mappedBy = "healthFacility", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<HealthFacility> medicalLabs = new HashSet<>();

    // SELF-REFERENCE OPERATION

    /**
     * Adds an entity (child) inside a self-relationship.
     * @param medicalLab Medical Report to add.
     */
    public void addMedicalLab(HealthFacility medicalLab) {
        this.medicalLabs.add(medicalLab);
        medicalLab.setHealthFacility(this);
    }

    /**
     * Adds an entity (child) inside a self-relationship.
     * @param medicalLab Medical Report to remove.
     */
    public void removeMedicalLab(HealthFacility medicalLab) {
        this.medicalLabs.remove(medicalLab);
        medicalLab.getHealthFacility().getMedicalLabs().remove(this);
    }

    // CONSTRUCTOR

    /**
     * Entity constructor.
     * This constructor is used in the persistence layer.
     * @param healthFacilityId ID of the health facility.
     * @param healthFacilityName Name of the health facility.
     * @param healthFacilityTipology Tipology of the health facility.
     * @param healthFacilityRegion Region of the health facility.
     * @param healthFacilityProvince Province of the health facility.
     * @param healthFacilityAddress Address of the health facility.
     */
    public HealthFacility(Long healthFacilityId, String healthFacilityName, String healthFacilityTipology, String healthFacilityRegion, String healthFacilityProvince, String healthFacilityAddress) {
        super();
        this.healthFacilityId = healthFacilityId;
        this.healthFacilityName = healthFacilityName;
        this.healthFacilityTipology = healthFacilityTipology;
        this.healthFacilityRegion = healthFacilityRegion;
        this.healthFacilityProvince = healthFacilityProvince;
        this.healthFacilityAddress = healthFacilityAddress;
    }

}
