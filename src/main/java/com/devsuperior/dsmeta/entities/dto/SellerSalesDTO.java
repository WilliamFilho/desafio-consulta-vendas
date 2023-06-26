package com.devsuperior.dsmeta.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SellerSalesDTO {
    private String sellerName;
    private Double totalSales;
}
