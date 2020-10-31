package com.achraf.finance.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@RequestMapping(value = "/api/v1/custom/auth")
@RestController("LoginController")
@Slf4j
@CrossOrigin
public class LoginController {
    private static final String URL = "http://94.239.109.172:8080/auth/realms/Finance/protocol/openid-connect/token";
    private static final  RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();

    public LoginController(){
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    }

    @GetMapping(value = "/login/{btoa}")
    public Object getByCode(@PathVariable String btoa ){

        String[] decoded = new String(Base64.getDecoder().decode(btoa)).split(":");
        System.out.println(new String(Base64.getDecoder().decode(btoa)));
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", "finance-client");
        map.add("grant_type","password");
        map.add("client_secret","e2d93343-885b-4620-b9d2-147c4e82f852");
        map.add("username", decoded[0]);
        map.add("password", decoded[1]);

        ResponseEntity<Object> response =
                restTemplate.exchange(
                        URL,
                        HttpMethod.POST,
                        new HttpEntity<>(map, headers),
                        Object.class );

        return response.getBody();
    }
}
