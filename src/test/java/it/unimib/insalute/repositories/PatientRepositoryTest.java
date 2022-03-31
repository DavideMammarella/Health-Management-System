package it.unimib.insalute.repositories;

import it.unimib.insalute.models.BloodTest;
import it.unimib.insalute.models.CovidTest;
import it.unimib.insalute.models.Doctor;
import it.unimib.insalute.models.Patient;
import javassist.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * H2 in-memory database is used.
 */
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-integration-test.properties")
@DataJpaTest
public class PatientRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testCreateReadPatient_checkOnCF() {
        Patient patient1 = new Patient(1L, "QWE123RTY456UIO7", "Paziente", "Uno");
        em.persist(patient1);
        em.flush();

        // Check (on CF)
        Patient patientExpected = patientRepository.findById(1L).get();
        assertEquals("CF incorrect!", "QWE123RTY456UIO7", patientExpected.getPatientIFC());
    }

    @Test
    public void testUpdatePatient_checkOnFirstnameAndLastname() {
        Patient patient1 = new Patient(1L, "QWE123RTY456UIO7", "Paziente", "Uno");
        em.persist(patient1);
        em.flush();

        patient1.setPatientIFC("ERT345SDF123ERT5");
        patient1.setPatientFirstname("Mario");
        patient1.setPatientLastname("Rossi");
        em.persist(patient1);
        em.flush();

        // Check (on Firstname and Lastname)
        Patient patientUpdated = patientRepository.findById(1L).get();
        assertEquals("Firstname incorrect!", "Mario", patientUpdated.getPatientFirstname());
        assertEquals("Lastname incorrect!", "Rossi", patientUpdated.getPatientLastname());
    }

    @Test
    public void testDeletePatient_tripleCheck() throws Exception {
        Patient patient1 = new Patient(1L, "QWE123RTY456UIO7", "Paziente", "Uno");
        em.persist(patient1);
        em.flush();

        // Check
        try {
            patientRepository.delete(patientRepository.findById(1L).get());
            em.flush();
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("Can't delete non existent patient!");
        }

        // Double Check
        assertEquals("Patient not deleted!", false, patientRepository.existsById(1L));

        // Triple Check (on size)
        assertEquals("Patient not deleted, size is wrong!", 0, patientRepository.findAll().size());
    }

    @Test
    public void testSearch_DoctorsByPatient() {
        Patient patient1 = new Patient(1L, "QWE123RTY456UIO7", "Paziente", "Uno");
        em.persist(patient1);
        em.flush();
        Doctor doctor1 = new Doctor(1L, "Luigi", "Sacco", "Medico");
        em.persist(doctor1);
        em.flush();
        Doctor doctor2 = new Doctor(2L, "Ernestina", "Paper", "Ematologo");
        em.persist(doctor2);
        em.flush();

        // FILL THE RELATIONSHIP
        patient1.addDoctor(doctor1);
        patient1.addDoctor(doctor2);
        em.persist(patient1);
        em.flush();

        // CHECK
        Patient patientWithDoctors = patientRepository.findById(1L).get();
        assertEquals("Size do not match!", 2, patientWithDoctors.getDoctors().size());
    }

    @Test
    public void testRelationship_Doctor() {
        Patient patient1 = new Patient(1L, "QWE123RTY456UIO7", "Paziente", "Uno");
        em.persist(patient1);
        em.flush();
        Doctor doctor1 = new Doctor(1L, "Luigi", "Sacco", "Medico");
        em.persist(doctor1);
        em.flush();
        Doctor doctor2 = new Doctor(2L, "Ernestina", "Paper", "Ematologo");
        em.persist(doctor2);
        em.flush();

        // INSERT
        patient1.addDoctor(doctor1);
        patient1.addDoctor(doctor2);
        em.persist(patient1);
        em.flush();

        Patient patientAfterAddition = patientRepository.findById(1L).get();
        assertEquals("Size do not match!", 2, patientAfterAddition.getDoctors().size());

        // DELETE
        patient1.removeDoctor(doctor1);
        em.persist(patient1);
        em.flush();

        Patient patientAfterDelete = patientRepository.findById(1L).get();
        assertEquals("Size do not match!", 1, patientAfterDelete.getDoctors().size());
    }

    @Test
    public void testRelationship_MedicalReport() {
        Patient patient1 = new Patient(1L, "QWE123RTY456UIO7", "Paziente", "Uno");
        em.persist(patient1);
        em.flush();
        BloodTest bloodTest1 = new BloodTest(1L, java.sql.Date.valueOf("2020-01-01"), 1.2, 20.3, 400.5, 10, 20, 30, 40, 1.2, 100, 100.2, "OK");
        em.persist(bloodTest1);
        em.flush();
        CovidTest covidTest1 = new CovidTest(2L, java.sql.Date.valueOf("2020-02-02"), "Negativo");
        em.persist(covidTest1);
        em.flush();

        // INSERT
        patient1.addMedicalReport(bloodTest1);
        patient1.addMedicalReport(covidTest1);
        em.persist(patient1);
        em.flush();

        Patient patientAfterAddition = patientRepository.findById(1L).get();
        assertEquals("Size do not match!", 2, patientAfterAddition.getMedicalReports().size());

        // DELETE
        // First remove
        patient1.removeMedicalReport(bloodTest1);
        em.persist(patient1);
        em.flush();

        Patient patientAfterDelete = patientRepository.findById(1L).get();
        assertEquals("Size do not match!", 1, patientAfterDelete.getMedicalReports().size());

        // Second remove
        patient1.removeMedicalReport(covidTest1);
        em.persist(patient1);
        em.flush();

        assertEquals("Size do not match!", 0, patientAfterDelete.getMedicalReports().size());

        patientRepository.deleteById(1L);
        assertEquals("Size do not match!", 0, patientRepository.findAll().size());
    }
}
