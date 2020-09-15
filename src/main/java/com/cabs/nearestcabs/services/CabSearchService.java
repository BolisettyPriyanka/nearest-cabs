package com.cabs.nearestcabs.services;

import com.cabs.nearestcabs.web.model.Cab;

import java.util.List;

public interface CabSearchService {
    List<Cab> searchNearestCabs(float srLatitude, float srLongitude,
                                float distance, int limit);
}
