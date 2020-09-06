package com.achraf.finance.service;

import org.patriques.AlphaVantageConnector;
import org.patriques.input.timeseries.Interval;
import org.patriques.input.timeseries.OutputSize;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.timeseries.IntraDay;
import org.patriques.output.timeseries.data.StockData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.List;
import java.util.Map;

@Component
public class TimeSeries {

    private org.patriques.TimeSeries stockTimeSeries;
    @Autowired
    private AlphaVantageConnector apiConnector;


    public TimeSeries() {
        stockTimeSeries = new org.patriques.TimeSeries(apiConnector);
    }

    public List<StockData> getTS(String code ) {

        try {
            IntraDay response = stockTimeSeries.intraDay(code, Interval.ONE_MIN, OutputSize.COMPACT);
            Map<String, String> metaData = response.getMetaData();
            System.out.println("Information: " + metaData.get("1. Information"));
            System.out.println("Stock: " + metaData.get("2. Symbol"));

            List<StockData> stockData = response.getStockData();
            return stockData;

        } catch (AlphaVantageException e) {
            System.out.println("something went wrong");
        }
        return null;
    }
}
