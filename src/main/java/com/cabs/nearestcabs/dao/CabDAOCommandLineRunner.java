package com.cabs.nearestcabs.dao;

import com.cabs.nearestcabs.web.model.Cab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CabDAOCommandLineRunner implements CommandLineRunner {

    @Autowired
    private CabRepository cabRepository;

    private static final Logger log =
            LoggerFactory.getLogger(CabDAOCommandLineRunner.class);

    @Override
    public void run(String... args) throws Exception {
        Cab cab = new Cab();
        cab.setLatitude((float) 37.788654783559);
        cab.setLongitude((float) -122.50747748978);
        cabRepository.save(cab);
        log.info("New cab is created.." + cab);

        cab = new Cab();
        cab.setLatitude((float) 57.778952285851);
        cab.setLongitude((float) -12.3333333333);
        cabRepository.save(cab);
        log.info("New cab is created.." + cab);

        cab = new Cab();
        cab.setLatitude((float) 37.099895225851);
        cab.setLongitude((float) -42.3333333333);
        cabRepository.save(cab);
        log.info("New cab is created.." + cab);
    }
}
