package com.achraf.finance.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "selectedTickers")
@AllArgsConstructor
@Builder
public class SelectedTickers {
    private String userId;
    private String name;
    private String code;
    private String type;
    private double maxThreshold;
    private double minThreshold;
    private double price;
    private double buyPrice;
    private double quantity;
    private String description;
}
