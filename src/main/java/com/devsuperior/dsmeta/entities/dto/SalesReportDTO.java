package com.devsuperior.dsmeta.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
@AllArgsConstructor
@Data
public class SalesReportDTO {
    private String sellerName;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalSales;
}
