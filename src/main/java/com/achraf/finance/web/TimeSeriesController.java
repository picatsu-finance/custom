package com.achraf.finance.web;

import com.achraf.finance.model.Listcompagny;
import com.achraf.finance.service.TimeSeries;
import lombok.extern.slf4j.Slf4j;
import org.patriques.output.timeseries.data.StockData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping(value = "/api/v1/time-series")
@RestController("TimeSeriesController")
@Slf4j
public class TimeSeriesController {

    @Autowired
    public TimeSeries timeSeries;

    @Autowired
    public Listcompagny listcompagny;


    @GetMapping(value = "/{code}")
    public List<StockData> test(@PathVariable String code){
        log.info("tim serie for code "+code);
        return timeSeries.getTS(code);
    }


    @GetMapping(value = "/tickers")
    public Map<String, String> LoadCompagnyList() {
        return listcompagny.getList();
    }
}
