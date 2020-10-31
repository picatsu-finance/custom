package com.achraf.finance.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "selectedTickers")
public class SelectedTickers {
    private String name;
    private String code;
    private String type;
    private double maxThreshold;
    private double minThreshold;
    private double price;
    private String idUser;
}
