package it.unimib.insalute.repositories;

import it.unimib.insalute.models.BloodTest;
import it.unimib.insalute.models.CovidTest;
import it.unimib.insalute.models.HealthFacility;
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
 */
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-integration-test.properties")
@DataJpaTest
public class HealthFacilityRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private HealthFacilityRepository healthFacilityRepository;

    @Test
    public void testCreateHealthFacility_checkOnRegionProvinceAddress() {
        HealthFacility healthFacility1 = new HealthFacility(1L, "Ospedale di Milano", "Struttura Pubblica", "Lombardia", "Milano", "Via Francesco Sforza, 35");
        em.persist(healthFacility1);
        em.flush();

        // Check (on Region, Province and Address)
        HealthFacility healthFacilityExpected = healthFacilityRepository.findById(1L).get();
        assertEquals("Region Incorrect!", "Lombardia", healthFacilityExpected.getHealthFacilityRegion());
        assertEquals("Province Incorrect!", "Milano", healthFacilityExpected.getHealthFacilityProvince());
        assertEquals("Address Incorrect!", "Via Francesco Sforza, 35", healthFacilityExpected.getHealthFacilityAddress());
    }

    @Test
    public void testUpdateHealthFacility_checkOnRegionProvinceAddress() throws Exception {
        HealthFacility healthFacility1 = new HealthFacility(1L, "Ospedale di Milano", "Struttura Pubblica", "Lombardia", "Milano", "Via Donizetti, 13");
        em.persist(healthFacility1);
        em.flush();

        try {
            healthFacility1.setHealthFacilityName("Ospedale di Bologna");
            healthFacility1.setHealthFacilityRegion("Emilia Romagna");
            healthFacility1.setHealthFacilityProvince("Bologna");
            healthFacility1.setHealthFacilityAddress("Via Altura, 3");
            em.persist(healthFacility1);
            em.flush();
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("Can't delete non existent health facility!");
        } catch (JpaSystemException ex) {
            throw new HibernateException("Cascade problem: parent-child relationship must be corrected!");
        }

        // Check (on Region, Province and Address)
        HealthFacility healthFacilityExpected = healthFacilityRepository.findById(1L).get();
        assertEquals("Region Incorrect!", "Emilia Romagna", healthFacilityExpected.getHealthFacilityRegion());
        assertEquals("Province Incorrect!", "Bologna", healthFacilityExpected.getHealthFacilityProvince());
        assertEquals("Address Incorrect!", "Via Altura, 3", healthFacilityExpected.getHealthFacilityAddress());
    }

    @Test
    public void testDeleteHealthFacility_doubleCheck() throws Exception {
        HealthFacility healthFacility1 = new HealthFacility(1L, "Ospedale di Milano", "Struttura Pubblica", "Lombardia", "Milano", "Via Donizetti, 13");
        em.persist(healthFacility1);
        em.flush();

        // Check
        try {
            healthFacilityRepository.delete(healthFacilityRepository.findById(1L).get());
            em.flush();
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("Can't delete non existent health facility!");
        } catch (JpaSystemException ex) {
            throw new HibernateException("Cascade problem: parent-child relation must be corrected");
        }

        // Double Check
        assertEquals("Health Facility not deleted!", false, healthFacilityRepository.existsById(1L));

        // Triple Check (on size)
        assertEquals("Patient not deleted, size is wrong!", 0, healthFacilityRepository.findAll().size());
    }

    @Test
    public void testRelationship_MedicalReport() {
        HealthFacility healthFacility1 = new HealthFacility(1L, "Ospedale di Milano", "Struttura Pubblica", "Lombardia", "Milano", "Via Donizetti, 13");
        em.persist(healthFacility1);
        em.flush();
        BloodTest bloodTest1 = new BloodTest(1L, java.sql.Date.valueOf("2020-01-01"), 1.2, 20.3, 400.5, 10, 20, 30, 40, 1.2, 100, 100.2, "OK");
        em.persist(bloodTest1);
        em.flush();
        CovidTest covidTest1 = new CovidTest(2L, java.sql.Date.valueOf("2020-02-02"), "Negativo");
        em.persist(covidTest1);
        em.flush();

        // INSERT
        healthFacility1.addMedicalReport(bloodTest1);
        healthFacility1.addMedicalReport(covidTest1);
        em.persist(healthFacility1);
        em.flush();

        HealthFacility healthFacilityAfterAddition = healthFacilityRepository.findById(1L).get();
        assertEquals("Size do not match!", 2, healthFacilityAfterAddition.getMedicalReports().size());

        // DELETE
        // First remove
        healthFacility1.removeMedicalReport(bloodTest1);
        em.persist(healthFacility1);
        em.flush();

        HealthFacility healthFacilityAfterDelete = healthFacilityRepository.findById(1L).get();
        assertEquals("Size do not match!", 1, healthFacilityAfterDelete.getMedicalReports().size());

        // Second remove
        healthFacility1.removeMedicalReport(covidTest1);
        em.persist(healthFacility1);
        em.flush();

        assertEquals("Size do not match!", 0, healthFacilityAfterDelete.getMedicalReports().size());
    }

    @Test
    public void testRelationship_SelfReference() throws Exception{
        HealthFacility healthFacility1 = new HealthFacility(1L, "Ospedale di Milano", "Struttura Pubblica", "Lombardia", "Milano", "Via Donizetti, 13");
        em.persist(healthFacility1);
        em.flush();
        HealthFacility medicalLab1 = new HealthFacility(2L, "Laboratorio Esami del Sangue Milano", "Struttura Pubblica", "Lombardia", "Milano", "Via Donizetti, 13");
        em.persist(medicalLab1);
        em.flush();
        HealthFacility medicalLab2 = new HealthFacility(3L, "Laboratorio Esami Covid Milano", "Struttura Pubblica", "Lombardia", "Milano", "Via Donizetti, 13");
        em.persist(medicalLab2);
        em.flush();

        // Add 2 child
        healthFacility1.addMedicalLab(medicalLab1);
        healthFacility1.addMedicalLab(medicalLab2);
        em.persist(healthFacility1);
        em.flush();

        // Check
        HealthFacility healthFacilityAfterAddition = healthFacilityRepository.findById(1L).get();
        assertEquals("Size do not match!", 2, healthFacilityAfterAddition.getMedicalLabs().size());

        // Remove 1 child (first one)
        healthFacility1.removeMedicalLab(medicalLab1);
        em.persist(healthFacility1);
        em.flush();

        // Check
        HealthFacility healthFacilityAfterDelete = healthFacilityRepository.findById(1L).get();
        assertEquals("Size do not match!", 1, healthFacilityAfterDelete.getMedicalLabs().size());

        // Remove parent
        try {
            healthFacilityRepository.delete(healthFacilityRepository.findById(1L).get());
            em.flush();
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("Can't delete non existent health facility!");
        } catch (JpaSystemException ex) {
            throw new HibernateException("Cascade problem: parent-child relation must be corrected");
        }

        // Check
        assertEquals("Health Facility not deleted!", false, healthFacilityRepository.existsById(1L));

        // Double Check (on second child)
        assertEquals("Medical Lab still exists!", false, healthFacilityRepository.existsById(3L));
    }
}
