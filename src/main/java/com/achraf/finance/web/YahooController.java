package com.achraf.finance.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.bind.annotation.*;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.fx.FxQuote;
import yahoofinance.quotes.fx.FxSymbols;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/api/v1/yahoo")
@RestController("YahooStocksController")
@Slf4j

public class YahooController {

    @GetMapping(value = "stock/{code}")
    // https://financequotes-api.com/
    public BigDecimal yahooGetByCode(@PathVariable String code) throws IOException {
        return YahooFinance.get(code).getQuote().getPrice();
    }

    @GetMapping(value = "/stock/all-infos/{code}")
    // https://financequotes-api.com/
    public Stock yahooGetByCodeAll(@PathVariable String code) throws IOException {
        return YahooFinance.get(code) ;
    }

    @RequestMapping(value = "/stock/multiple", method = RequestMethod.POST )
    // https://financequotes-api.com/
    public Map<String, Stock> yahooGetByCodeMultiple(@RequestBody List<String> code) throws IOException {
       return YahooFinance.get(code.toArray(new String[0])); // single request
    }

    @GetMapping(value = "/fx-quote")
    // https://financequotes-api.com/
    public List<ImmutablePair<String, FxQuote>> yahooGetFx() throws IOException {

        List<ImmutablePair<String, FxQuote>> res = new ArrayList<>();
        res.add(new ImmutablePair<>("usd->eur", YahooFinance.getFx(FxSymbols.USDEUR)));
        res.add(new ImmutablePair<>("usd->gbp", YahooFinance.getFx("USDGBP=X")));

        return res;

    }

}
