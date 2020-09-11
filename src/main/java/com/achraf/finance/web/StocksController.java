package com.achraf.finance.web;

import com.achraf.finance.service.StocksService;
import lombok.extern.slf4j.Slf4j;
import org.patriques.output.timeseries.data.StockData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/api/v1/stocks")
@RestController("StocksController")
@Slf4j
public class StocksController {

    @Autowired
    public StocksService stocksService;


    @GetMapping(value = "/{code}")
    public List<StockData> getByCode(@PathVariable String code){
        log.info("time serie for code "+code);
        return stocksService.getTS(code);
    }


    @GetMapping(value = "/search-ticker/{str}")
    public List<String> findCode(@PathVariable String str){
       return stocksService.findTickers(str);
    }

    @GetMapping(value = "/tickers")
    public Map<String, String> LoadCompagnyList() {
        return stocksService.getListcompagny().getList();
    }
}
