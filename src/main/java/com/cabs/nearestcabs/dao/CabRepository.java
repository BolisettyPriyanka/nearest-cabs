package com.cabs.nearestcabs.dao;

import com.cabs.nearestcabs.web.model.Cab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CabRepository extends JpaRepository<Cab, Long> {
    List<Cab> searchNearestCabs(float latitude,
                                float longitude,
                                int limit,
                                float radius);
}
