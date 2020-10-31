package com.achraf.finance.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IndiceModel {
    private String value;
    private String mic;
    private String label;
    private String country;

    private List<Object> constituents;

}
