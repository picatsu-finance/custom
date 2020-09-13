package com.achraf.finance.config;


import com.achraf.finance.model.TickerModel;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.patriques.AlphaVantageConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.util.List;


@Configuration
public class CustomConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Swagger doc for the Finance-Ash project")
                        .version("0.0.1")
                        .description("Finance API Ash project")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

    @Bean
    public AlphaVantageConnector beanAlphaApiConnector() {
        String apiKey = "63NJUA45A97BF6OI";
        int timeout = 3000;
        AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);
        return apiConnector;
    }



    public static List<TickerModel> listcompagny() throws IOException {

        return  CustomFunctions.loadData();
    }



}
