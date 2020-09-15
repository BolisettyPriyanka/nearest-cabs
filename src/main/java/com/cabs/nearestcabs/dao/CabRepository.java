package com.cabs.nearestcabs.dao;

import com.cabs.nearestcabs.web.model.Cab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CabRepository extends JpaRepository<Cab, Long> {
}
