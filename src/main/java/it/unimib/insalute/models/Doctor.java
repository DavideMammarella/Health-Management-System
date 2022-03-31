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
@Table(name = "doctor")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "relationClass"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "doctorId")
@Getter
@Setter
@NoArgsConstructor
public class Doctor {

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id", nullable = false)
    private @Id
    Long doctorId;

    @Column(name = "doctor_firstname", nullable = false)
    private String doctorFirstname;

    @Column(name = "doctor_lastname", nullable = false)
    private String doctorLastname;

    @Column(name = "doctor_specialization", nullable = false)
    private String doctorSpecialization;

    // RELATIONSHIPS

    @ManyToMany
    @JoinTable(name = "patient_doctor",
            joinColumns = {@JoinColumn(name = "doctor_id")},
            inverseJoinColumns = {@JoinColumn(name = "patient_id")})
    private Set<Patient> patients = new HashSet<>();

    // CONSTRUCTOR

    /**
     * Entity constructor.
     * This constructor is used in the persistence layer.
     * @param doctorId ID of the doctor.
     * @param doctorFirstname Firstname of the doctor.
     * @param doctorLastname Lastname of the doctor.
     * @param doctorSpecialization Specialization of the doctor.
     */
    public Doctor(Long doctorId, String doctorFirstname, String doctorLastname, String doctorSpecialization) {
        super();
        this.doctorId = doctorId;
        this.doctorFirstname = doctorFirstname;
        this.doctorLastname = doctorLastname;
        this.doctorSpecialization = doctorSpecialization;
    }
}
