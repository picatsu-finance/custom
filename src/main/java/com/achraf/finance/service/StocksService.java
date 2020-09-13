package com.achraf.finance.service;

import com.achraf.finance.config.CustomFunctions;
import com.achraf.finance.model.TickerModel;
import lombok.Getter;
import org.patriques.AlphaVantageConnector;
import org.patriques.input.timeseries.Interval;
import org.patriques.input.timeseries.OutputSize;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.timeseries.IntraDay;
import org.patriques.output.timeseries.data.StockData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Getter
public class StocksService {

    private org.patriques.TimeSeries stockTimeSeries;

    @Autowired
    private AlphaVantageConnector apiConnector;



    public StocksService() {
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

    public List<TickerModel> findTickers(String str) throws IOException {

        List<TickerModel> values = new ArrayList<>();
        for (TickerModel entry : CustomFunctions.loadData() ) {
            if(( (entry.getCode() + entry.getName() ).toLowerCase()).contains(str.toLowerCase()) ) {
                values.add (entry);
            }
        }

        return values ;
    }


}
