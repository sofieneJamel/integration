package com.sample.integration.business;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product implements Serializable {
    private String code;
    private String label;
    private Integer price;
    private Integer quantity;
    private String zone;
}
