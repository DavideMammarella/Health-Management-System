package it.unimib.insalute.controllers;

import it.unimib.insalute.models.HealthFacility;
import it.unimib.insalute.services.HealthFacilityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Spring MVC Controller.
 * Defines the REST controller for a web-based extension.
 */
@RestController
@RequestMapping("/api/health_facilities")
public class HealthFacilityController {

    @Autowired
    private HealthFacilityService healthFacilityService;

    // CREATE

    /**
     * Add an health facility to the database.
     * @param healthFacility health facility  to add.
     * @return persist the health facility.
     */
    @PostMapping
    public HealthFacility createHealthFacility(@RequestBody final HealthFacility healthFacility) {
        return healthFacilityService.addHealthFacility(healthFacility);
    }

    // READ

    /**
     * Get all health facilities in the database.
     * @return list of all health facilities in the database.
     */
    @GetMapping
    public List<HealthFacility> list() {
        return healthFacilityService.getHealthFacilities();
    }

    /**
     * Get an health facility given the ID.
     * @param id ID of the health facility.
     * @return health facility.
     */
    @GetMapping
    @RequestMapping(value = "{id}")
    public HealthFacility getHealthFacilityById(@PathVariable Long id) {
        return healthFacilityService.getOneHealthFacilityById(id);
    }

    // UPDATE

    /**
     * Update an health facility given the ID.
     * @param id ID of the health facility.
     * @param healthFacility new health facility.
     * @return persist the health facility updating.
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public HealthFacility updateHealthFacilityById(@PathVariable Long id, @RequestBody HealthFacility healthFacility) {
        HealthFacility existingHealthFacility = healthFacilityService.getOneHealthFacilityById(id);
        BeanUtils.copyProperties(healthFacility, existingHealthFacility, "health_facility_id");
        return healthFacilityService.addHealthFacility(healthFacility);
    }

    // DELETE

    /**
     * Delete an health facility given the ID.
     * @param id ID of the health facility.
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteHealthFacilityById(@PathVariable Long id) {
        healthFacilityService.deleteHealthFacilityById(id);
    }
}
