package com.achraf.finance.web;

import com.achraf.finance.service.SectorPerformanceService;
import com.achraf.finance.service.TechnicalIndicatorService;
import lombok.extern.slf4j.Slf4j;
import org.patriques.output.sectorperformances.Sectors;
import org.patriques.output.technicalindicators.MACD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/custom/perf")
@RestController("SectorPerformancesController")
@Slf4j
@CrossOrigin
public class SectorPerformancesController {

    @Autowired
    public SectorPerformanceService sectorPerformanceService;

    @GetMapping(value = "/")
    public Sectors getByCode( ){

        return sectorPerformanceService.get();
    }
}
