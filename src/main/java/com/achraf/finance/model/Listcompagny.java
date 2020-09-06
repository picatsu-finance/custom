package com.achraf.finance.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Data
@AllArgsConstructor
public class Listcompagny {
    Map<String, String> list;
}
