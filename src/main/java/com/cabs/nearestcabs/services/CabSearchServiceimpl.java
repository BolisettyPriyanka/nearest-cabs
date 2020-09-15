package com.cabs.nearestcabs.services;

import com.cabs.nearestcabs.dao.CabDAOCommandLineRunner;
import com.cabs.nearestcabs.dao.CabRepository;
import com.cabs.nearestcabs.web.mapper.CabMapper;
import com.cabs.nearestcabs.web.model.Cab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CabSearchServiceimpl implements CabSearchService{

    @Autowired
    private CabRepository cabRepository;

    private static final Logger log =
            LoggerFactory.getLogger(CabSearchServiceimpl.class);

    @Override
    public List<Cab> searchNearestCabs(float srLatitude, float srLongitude,
                                       float distance, int limit){
        List<Cab> cabList = cabRepository.findAll();
        List<Cab> nearestCabs = new ArrayList<>();

        for(Cab cab: cabList) {
            float destLatitude = cab.getLatitude();
            float destLongitude = cab.getLongitude();

            float dLat = (float) Math.toRadians(srLatitude - destLatitude);
            float dLon = (float) Math.toRadians(srLongitude - destLongitude);

            // Convert to radians
            srLatitude = (float) Math.toRadians(srLatitude);
            destLatitude = (float) Math.toRadians(destLatitude);

            // calculate distance
            double a = Math.pow(Math.sin(destLatitude / 2), 2) +
                    Math.pow(Math.sin(destLongitude / 2), 2) *
                            Math.cos(srLatitude) *
                            Math.cos(destLatitude);
            double rad = 6371;
            double c = 2 * Math.asin(Math.sqrt(a));
            double calculatedDistance = rad * c;

            log.info("Calculated distance.." + calculatedDistance);

            if(calculatedDistance <= distance ){
                if(nearestCabs.size() < limit) {
                    log.info("Found a near by cab.." + cab);
                    nearestCabs.add(cab);
                }
            }
        }
        return nearestCabs;
    }
}
