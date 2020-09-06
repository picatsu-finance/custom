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
                = BASE_URL+"function=DIGITAL_CURRENCY_DAILY&symbol="+symbol+"&market="+market+"&apikey=63NJUA45A97BF6OI";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl + "/1", String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        return root;


    }



}
