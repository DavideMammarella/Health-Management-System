package it.unimib.insalute.services;

import it.unimib.insalute.models.HealthFacility;
import it.unimib.insalute.repositories.HealthFacilityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Spring JPA Service.
 * Defines advanced business logic.
 */
@Service
public class HealthFacilityService {
    private HealthFacilityRepository healthFacilityRepository;

    /**
     * Service constructor.
     * @param healthFacilityRepository repository layer of the health repository.
     */
    public HealthFacilityService(HealthFacilityRepository healthFacilityRepository) {
        this.healthFacilityRepository = healthFacilityRepository;
    }

    // CREATE-UPDATE

    /**
     * Add an health facility to the database.
     * @param healthFacility Health facility  to add.
     * @return persist the health facility.
     */
    public HealthFacility addHealthFacility(HealthFacility healthFacility) {
        return healthFacilityRepository.save(healthFacility);
    }

    // READ

    /**
     * Get all health facilities in the database.
     * @return list of all health facilities in the database.
     */
    public List<HealthFacility> getHealthFacilities() {
        return healthFacilityRepository.findAll();
    }

    /**
     * Get an health facility given the ID.
     * @param id ID of the health facility.
     * @return health facility.
     */
    public HealthFacility getOneHealthFacilityById(Long id) {
        return healthFacilityRepository.getOne(id);
    }

    /**
     * Get a list of health facilities given the name.
     * @param name name of the health facility.
     * @return health facility.
     */
    public Set<HealthFacility> getHealthFacilitiesByName(String name) {
        return healthFacilityRepository.findHealthFacilityByHealthFacilityName(name);
    }

    /**
     * Get a list of health facilities given the tipology.
     * @param tipology tipology of the health facility.
     * @return health facility.
     */
    public Set<HealthFacility> getHealthFacilitiesByTipology(String tipology) {
        return healthFacilityRepository.findHealthFacilityByHealthFacilityTipology(tipology);
    }

    /**
     * Get a list of health facilities given the region.
     * @param region region of the health facility.
     * @return health facility.
     */
    public Set<HealthFacility> getHealthFacilitiesByRegion(String region) {
        return healthFacilityRepository.findHealthFacilityByHealthFacilityRegion(region);
    }

    /**
     * Get a list of health facilities given the province.
     * @param province province of the health facility.
     * @return health facility.
     */
    public Set<HealthFacility> getHealthFacilitiesByProvince(String province) {
        return healthFacilityRepository.findHealthFacilityByHealthFacilityProvince(province);
    }

    /**
     * Get a list of health facilities given the address.
     * @param address address of the health facility.
     * @return health facility.
     */
    public Set<HealthFacility> getHealthFacilitiesByAddress(String address) {
        return healthFacilityRepository.findHealthFacilityByHealthFacilityAddress(address);
    }

    // DELETE

    /**
     * Delete an health facility .
     * @param healthFacility health facility to delete.
     */
    public void deleteHealthFacility(HealthFacility healthFacility) {
        healthFacilityRepository.delete(healthFacility);
    }

    /**
     * Delete an health facility given the ID.
     * @param id ID of the health facility.
     */
    public void deleteHealthFacilityById(Long id) {
        healthFacilityRepository.delete(healthFacilityRepository.getOne(id));
    }
}
