package com.achraf.finance.service;


import org.patriques.AlphaVantageConnector;
import org.patriques.SectorPerformances;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.sectorperformances.Sectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SectorPerformanceService {


    @Autowired
    public AlphaVantageConnector apiConnector;

    public Sectors get() {
        SectorPerformances sectorPerformances = new SectorPerformances(apiConnector);
        Sectors response = null;
        try {
            response = sectorPerformances.sector();
        } catch (AlphaVantageException e) {
            System.out.println("something went wrong");
        }
        return response;
    }


}
