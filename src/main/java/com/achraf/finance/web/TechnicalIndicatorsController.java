package com.achraf.finance.web;


import com.achraf.finance.service.TechnicalIndicatorService;
import lombok.extern.slf4j.Slf4j;
import org.patriques.output.technicalindicators.MACD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping(value = "/api/v1/custom/indicator")
@RestController("TechnicalIndicatorsController")
@Slf4j
@CrossOrigin
public class TechnicalIndicatorsController {

    @Autowired
    public TechnicalIndicatorService technicalIndicatorService;

    @GetMapping(value = "/{symbol}")
    public MACD getByCode(@PathVariable String symbol){

        return technicalIndicatorService.get(symbol);
    }

}
