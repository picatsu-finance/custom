package com.achraf.finance.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class IndicesService {

    String url = "https://api.nasdaq.com/api/quote/indices?chartFor=COMP&chartFor=NYA&chartFor=SPX&chartFor=RUT&chartFor=NDX&symbol=COMP&symbol=NYA&symbol=SPX&symbol=RUT&symbol=NDX";


    public Object loadIndices() {

        RestTemplate rest = new RestTemplate();
        Object aa = rest.getForEntity(url, Object.class).getBody();
        return aa;
    }

}
