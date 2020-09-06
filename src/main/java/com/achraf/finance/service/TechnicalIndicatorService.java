package com.achraf.finance.service;

import org.patriques.AlphaVantageConnector;
import org.patriques.TechnicalIndicators;
import org.patriques.input.technicalindicators.Interval;
import org.patriques.input.technicalindicators.SeriesType;
import org.patriques.input.technicalindicators.TimePeriod;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.technicalindicators.MACD;
import org.patriques.output.technicalindicators.data.MACDData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class TechnicalIndicatorService {


    @Autowired
    public AlphaVantageConnector apiConnector;

    public MACD get(String symbol) {
        TechnicalIndicators technicalIndicators = new TechnicalIndicators(apiConnector);
        MACD response = null;
        try {
             response = technicalIndicators.macd(symbol,
                    Interval.DAILY,
                    TimePeriod.of(10),
                    SeriesType.CLOSE,
                    null,
                    null,
                    null);

        } catch (AlphaVantageException e) {
            System.out.println("something went wrong");
        }
        return response;
    }

}
