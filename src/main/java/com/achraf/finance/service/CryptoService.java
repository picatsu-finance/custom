package com.achraf.finance.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.patriques.AlphaVantageConnector;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.digitalcurrencies.IntraDay;
import org.patriques.output.digitalcurrencies.data.SimpelDigitalCurrencyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
public class CryptoService {
    private static final String BASE_URL = "https://www.alphavantage.co/query?";
    @Autowired
    public AlphaVantageConnector apiConnector;

    public JsonNode find(String symbol, String market) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "https://www.alphavantage.co/query?function=DIGITAL_CURRENCY_DAILY&symbol="+symbol+"&market="+market+"&apikey=63NJUA45A97BF6OI";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl + "/1", String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        return root;


    }


    public void find2(String symbol, String market) {
        String test = "https://www.alphavantage.co/query?function=DIGITAL_CURRENCY_DAILY&symbol=BTC&market=CNY&apikey=63NJUA45A97BF6OI";
        WebClient web = WebClient.create("https://www.alphavantage.co");


        try {
            Mono<IntraDay> response = web.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/query")
                            .queryParam("function", "DIGITAL_CURRENCY_DAILY" )
                            .queryParam("symbol", symbol)
                            .queryParam("market", market)
                            .queryParam("apikey", "63NJUA45A97BF6OI" )
                            .build()
                    ).retrieve().bodyToMono(IntraDay.class);


            Map<String, String> metaData = response.block().getMetaData();
            System.out.println("Information: " + metaData.get("1. Information"));
            System.out.println("Digital Currency Code: " + metaData.get("2. Digital Currency Code"));

            List<SimpelDigitalCurrencyData> digitalData =  response.block().getDigitalData();
            digitalData.forEach(data -> {
                System.out.println("date:       " + data.getDateTime());
                System.out.println("price A:    " + data.getPriceA());
                System.out.println("price B:    " + data.getPriceB());
                System.out.println("volume:     " + data.getVolume());
                System.out.println("market cap: " + data.getMarketCap());
            });
        } catch (AlphaVantageException e) {
            System.out.println("something went wrong");
        }
    }
}
