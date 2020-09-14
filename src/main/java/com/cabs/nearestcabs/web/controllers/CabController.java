package com.cabs.nearestcabs.web.controllers;

import com.cabs.nearestcabs.dao.CabRepository;
import com.cabs.nearestcabs.web.model.Cab;
import com.cabs.nearestcabs.web.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cabs/")
public class CabController {

    @Autowired
    private CabRepository cabRepository;

    @GetMapping("/{cab_id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Cab> getCabById(@PathVariable("cab_id") Long cab_id){
        return cabRepository.findById(cab_id);
    }

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

    @DeleteMapping("/{cab_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteCab(@PathVariable Long cab_id){
        cabRepository.deleteById(cab_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteAllCab(){
        cabRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<Cab>> searchNearestCabs(@RequestParam("latitude") float lat,
                                                       @RequestParam("longitude") float lon,
                                                       @RequestParam("radius") float radius,
                                                       @RequestParam("limit") int limit){
        try {
            List<Cab> cabs = new ArrayList<Cab>();
            if (cabs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cabs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
