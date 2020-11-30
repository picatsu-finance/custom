package com.achraf.finance.web;


import com.achraf.finance.service.TechnicalIndicatorService;
import lombok.extern.slf4j.Slf4j;
import org.patriques.output.technicalindicators.MACD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;


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

    @GetMapping(value = "/details")
    public Mono<Object> getDetails(){


        String uri
                = "https://api.nasdaq.com/api/quote/indices?chartFor=COMP&chartFor=NYA&chartFor=SPX&chartFor=RUT&chartFor=NDX&symbol=COMP&symbol=NYA&symbol=SPX&symbol=RUT&symbol=NDX&symbol=CAC40";


        WebClient webClient = WebClient.create();

        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Object.class);
    }

}
