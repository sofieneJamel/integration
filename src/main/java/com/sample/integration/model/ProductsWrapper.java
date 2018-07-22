package com.sample.integration.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ProductsWrapper {
    private List<Product> products;
}