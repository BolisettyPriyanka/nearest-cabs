package com.cabs.nearestcabs.web.controllers;

import com.cabs.nearestcabs.dao.CabRepository;
import com.cabs.nearestcabs.services.CabSearchService;
import com.cabs.nearestcabs.web.model.Cab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cabs/")
public class CabController {

    @Autowired
    private CabRepository cabRepository;

    @Autowired
    private CabSearchService cabSearchService;

    private static final Logger log =
            LoggerFactory.getLogger(CabController.class);

    /*
    Get cab by Id
     */
    @GetMapping("/{cab_id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Cab> getCabById(@PathVariable("cab_id") Long cab_id){
        return cabRepository.findById(cab_id);
    }

    /*
    Update a cab
     */
    @PutMapping("/{cab_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Cab> handleCreateOrUpdate(@PathVariable("cab_id") Long cab_id,
                                                  @RequestBody Cab newCab){
        Optional<Cab> cabObject = Optional.of(cabRepository.findById(cab_id)
                .map(cab -> {
                    cab.setLatitude(newCab.getLatitude());
                    cab.setLongitude(newCab.getLongitude());
                    return cabRepository.save(cab);
                })
                .orElseGet(() -> {
                    newCab.setId(cab_id);
                    return cabRepository.save(newCab);
                }));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{cab_id}")
                .buildAndExpand(newCab.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    /*
    Delete a cab
     */
    @DeleteMapping("/{cab_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteCab(@PathVariable Long cab_id){
        cabRepository.deleteById(cab_id);
        return ResponseEntity.noContent().build();
    }

    /*
    Destroy all cabs
     */
    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteAllCab(){
        cabRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

    /*
    Get mapping to search nearest cabs
    Endpoint - http://localhost:8081/api/v1/cabs/getcabs?latitude=37.788654&longitude=-122.50748&radius=10000&limit=2
     */
    @GetMapping("/getcabs")
    public ResponseEntity<List<Cab>> searchNearestCabs(@RequestParam("latitude") float lat,
                                                       @RequestParam("longitude") float lon,
                                                       @RequestParam("radius") float radius,
                                                       @RequestParam("limit") int limit){
        log.info("Searching for cabs....");
        final ResponseEntity<List<Cab>> listResponseEntity = new ResponseEntity<List<Cab>>
                    (cabSearchService.searchNearestCabs(lat, lon, radius, limit), HttpStatus.OK);
        return listResponseEntity;
    }
}
