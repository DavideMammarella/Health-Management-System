package it.unimib.insalute.repositories;

import it.unimib.insalute.models.BloodTest;
import it.unimib.insalute.models.CovidTest;
import it.unimib.insalute.models.MedicalReport;
import javassist.NotFoundException;
import org.hibernate.HibernateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * H2 in-memory database is used.
 * Every class test Inheritance of MedicalReport (Super) with BloodTest and CovidTest.
 */
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-integration-test.properties")
@DataJpaTest
public class MedicalReportRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    MedicalReportRepository medicalReportRepository;

    @Autowired
    BloodTestRepository bloodTestRepository;

    @Autowired
    CovidTestRepository covidTestRepository;

    @Test
    public void testCreate_MedicalReports_BloodTest_CovidTest_inheritance() {
        BloodTest bloodTest1 = new BloodTest(1L, java.sql.Date.valueOf("2020-01-01"), 1.2, 20.3, 400.5, 10, 20, 30, 40, 1.2, 100, 100.2, "OK");
        em.persist(bloodTest1);
        em.flush();
        CovidTest covidTest1 = new CovidTest(2L, java.sql.Date.valueOf("2020-02-02"), "Negativo");
        em.persist(covidTest1);
        em.flush();

        // Check (on BloodTest description and CovidTest result)
        BloodTest bloodTestExpected = bloodTestRepository.findById(1L).get();
        assertEquals("Description Incorrect!", "OK", bloodTestExpected.getBloodTestDescription());
        CovidTest covidTestExpected = covidTestRepository.findById(2L).get();
        assertEquals("Result Incorrect!", "Negativo", covidTestExpected.getCovidTestResult());

        // Double Check (Inheritance, on Date)

        // BloodTest Inheritance
        MedicalReport medicalReportBloodTest = medicalReportRepository.findById(1L).get();
        assertEquals("Date Incorrect!", java.sql.Date.valueOf("2020-01-01"), medicalReportBloodTest.getMedicalReportDate());

        // CovidTest Inheritance
        MedicalReport medicalReportCovidTest = medicalReportRepository.findById(2L).get();
        assertEquals("Date Incorrect!", java.sql.Date.valueOf("2020-02-02"), medicalReportCovidTest.getMedicalReportDate());
    }

    @Test
    public void testUpdate_MedicalReports_BloodTest_CovidTest_inheritance() throws Exception {
        BloodTest bloodTest1 = new BloodTest(1L, java.sql.Date.valueOf("2020-01-01"), 1.2, 20.3, 400.5, 10, 20, 30, 40, 1.2, 100, 100.2, "OK");
        em.persist(bloodTest1);
        em.flush();
        CovidTest covidTest1 = new CovidTest(2L, java.sql.Date.valueOf("2020-02-02"), "Negativo");
        em.persist(covidTest1);
        em.flush();

        try {
            bloodTest1.setBloodTestWBC(15);
            bloodTest1.setBloodTestDescription("A seguito dell'aggiornamento i globuli bianchi risultano alti.");
            em.persist(bloodTest1);
            em.flush();

            covidTest1.setCovidTestResult("Negativo");
            em.persist(covidTest1);
            em.flush();
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("Can't delete non existent health facility!");
        } catch (JpaSystemException ex) {
            throw new HibernateException("Cascade problem: relationship must be corrected!");
        }

        // Check (on BloodTest description and CovidTest result)
        BloodTest bloodTestExpected = bloodTestRepository.findById(1L).get();
        assertEquals("Description Incorrect!", "A seguito dell'aggiornamento i globuli bianchi risultano alti.", bloodTestExpected.getBloodTestDescription());
        CovidTest covidTestExpected = covidTestRepository.findById(2L).get();
        assertEquals("Result Incorrect!", "Negativo", covidTestExpected.getCovidTestResult());

        // BloodTest Inheritance
        MedicalReport medicalReportBloodTest = medicalReportRepository.findById(1L).get();
        assertEquals("Date Incorrect!", java.sql.Date.valueOf("2020-01-01"), medicalReportBloodTest.getMedicalReportDate());

        // CovidTest Inheritance
        MedicalReport medicalReportCovidTest = medicalReportRepository.findById(2L).get();
        assertEquals("Date Incorrect!", java.sql.Date.valueOf("2020-02-02"), medicalReportCovidTest.getMedicalReportDate());

    }

    @Test
    public void testDelete_MedicalReports_BloodTest_CovidTest_inheritance() throws Exception {
        BloodTest bloodTest1 = new BloodTest(1L, java.sql.Date.valueOf("2020-01-01"), 1.2, 20.3, 400.5, 10, 20, 30, 40, 1.2, 100, 100.2, "OK");
        em.persist(bloodTest1);
        em.flush();
        CovidTest covidTest1 = new CovidTest(2L, java.sql.Date.valueOf("2020-02-02"), "Negativo");
        em.persist(covidTest1);
        em.flush();

        // Check (on BloodTest)
        try {
            bloodTestRepository.delete(bloodTestRepository.findById(1L).get());
            em.flush();
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("Can't delete non existent health facility!");
        } catch (JpaSystemException ex) {
            throw new HibernateException("Cascade problem: relationship must be corrected!");
        }

        // Check (on CovidTest)
        try {
            covidTestRepository.delete(covidTestRepository.findById(2L).get());
            em.flush();
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("Can't delete non existent health facility!");
        } catch (JpaSystemException ex) {
            throw new HibernateException("Cascade problem: relationship must be corrected!");
        }

        // Double Check
        assertEquals("Blood Test not deleted!", false, bloodTestRepository.existsById(1L));
        assertEquals("Covid Test not deleted!", false, covidTestRepository.existsById(2L));

        // Triple Check (Inheritance)
        assertEquals("Medical Report not deleted!", false, medicalReportRepository.existsById(1L));
        assertEquals("Medical Report deleted!", false, medicalReportRepository.existsById(2L));

        // Quadruple Check (on size)
        assertEquals("Patient not deleted, size is wrong!", 0, bloodTestRepository.findAll().size());
        assertEquals("Patient not deleted, size is wrong!", 0, covidTestRepository.findAll().size());
        assertEquals("Patient not deleted, size is wrong!", 0, medicalReportRepository.findAll().size());
    }
}
