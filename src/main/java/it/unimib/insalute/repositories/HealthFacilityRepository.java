package it.unimib.insalute.repositories;

import it.unimib.insalute.models.HealthFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Spring JPA Repository.
 * Interface delegated to communicate with the data source.
 * Extends JpaRepository to implement basic CRUD operations.
 * Uses Spring Data's Query Method to define custom queries.
 */
@Repository
public interface HealthFacilityRepository extends JpaRepository<HealthFacility, Long> {
    Set<HealthFacility> findHealthFacilityByHealthFacilityName(String name);
    Set<HealthFacility> findHealthFacilityByHealthFacilityTipology(String tipology);
    Set<HealthFacility> findHealthFacilityByHealthFacilityRegion(String region);
    Set<HealthFacility> findHealthFacilityByHealthFacilityProvince(String province);
    Set<HealthFacility> findHealthFacilityByHealthFacilityAddress(String address);
}
