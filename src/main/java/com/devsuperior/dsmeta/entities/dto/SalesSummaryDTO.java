package com.devsuperior.dsmeta.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SalesSummaryDTO {
    private String sellerName;
    private double totalSales;
}
