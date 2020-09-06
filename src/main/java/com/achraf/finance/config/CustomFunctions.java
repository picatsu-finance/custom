package com.achraf.finance.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Data
@Component
@AllArgsConstructor
public class CustomFunctions {

    public static Map<String, String> loadData() throws IOException {

        Map<String, String> liste = new HashMap<>();
        String[] fileNames = new String[]{"listing_status.csv", "wilshire_5000_stocks.csv"};

        String myLine = "";
        BufferedReader reader = null;

        for(String name : fileNames) {

            File file = new ClassPathResource(name).getFile();
            try {
                reader = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            while ((myLine = reader.readLine()) != null ) {
                addToArray(liste, myLine);
            }
        }

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return liste;
    }



    private static void addToArray(Map<String, String> liste, String myLine) {
        String[] infos = myLine.split(",");

        if(! liste.containsKey(infos[0]) && infos.length > 1 && infos[0] != "Ticker") {
            liste.put(infos[0], infos[1]);
        }

    }
}
