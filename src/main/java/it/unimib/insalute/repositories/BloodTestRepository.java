package it.unimib.insalute.repositories;

import it.unimib.insalute.models.BloodTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring JPA Repository.
 * Interface delegated to communicate with the data source.
 * Extends JpaRepository to implement basic CRUD operations.
 * Uses Spring Data's Query Method to define custom queries.
 */
@Repository
public interface BloodTestRepository extends JpaRepository<BloodTest, Long> {
}
