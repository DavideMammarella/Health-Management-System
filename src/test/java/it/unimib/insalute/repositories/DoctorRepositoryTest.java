package it.unimib.insalute.repositories;

import it.unimib.insalute.models.Doctor;
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
public class DoctorRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private DoctorRepository doctorRepository;


    @Test
    public void testCreateReadDoctor_checkOnFirstname() {
        Doctor doctor1 = new Doctor(1L, "Luigi", "Sacco", "Medico");
        em.persist(doctor1);
        em.flush();

        // Check (on Firstname)
        Doctor doctorExpected = doctorRepository.findById(1L).get();
        assertEquals("Firstname incorrect!", "Luigi", doctorExpected.getDoctorFirstname());
    }

    @Test
    public void testUpdateDoctor_checkOnFirstnameLastnameSpecialization() {
        Doctor doctor1 = new Doctor(1L, "Luigi", "Sacco", "Medico");
        em.persist(doctor1);
        em.flush();

        doctor1.setDoctorFirstname("Mario");
        doctor1.setDoctorLastname("Rossi");
        doctor1.setDoctorSpecialization("Ematologo");
        em.persist(doctor1);
        em.flush();

        // Check (on Firstname, Lastname, Specialization)
        Doctor doctorUpdated = doctorRepository.findById(1L).get();
        assertEquals("Firstname incorrect!", "Mario", doctorUpdated.getDoctorFirstname());
        assertEquals("Lastname incorrect!", "Rossi", doctorUpdated.getDoctorLastname());
        assertEquals("Specialization incorrect!", "Ematologo", doctorUpdated.getDoctorSpecialization());
    }

    @Test
    public void testDeleteDoctor_tripleCheck() throws NotFoundException {
        Doctor doctor1 = new Doctor(1L, "Luigi", "Sacco", "Medico");
        em.persist(doctor1);
        em.flush();

        // Check
        try {
            doctorRepository.delete(doctorRepository.findById(1L).get());
            em.flush();
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException("Can't delete non existent doctor!");
        }

        // Double Check
        assertEquals("Patient not deleted!", false, doctorRepository.existsById(1L));

        // Triple Check (on size)
        assertEquals("Patient not deleted, size is wrong!", 0, doctorRepository.findAll().size());
    }
}
