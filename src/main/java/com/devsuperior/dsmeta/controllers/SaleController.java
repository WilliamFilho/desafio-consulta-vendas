package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.entities.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.dto.SalesReportDTO;
import com.devsuperior.dsmeta.entities.dto.SalesSummaryDTO;
import com.devsuperior.dsmeta.entities.dto.SellerSalesDTO;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import com.devsuperior.dsmeta.services.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/sales")
@AllArgsConstructor
public class SaleController {
    private SaleRepository saleRepository;
    private SaleService saleService;

    /*
        GET /sales/1
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
        SaleMinDTO dto = saleService.findById(id);
        return ResponseEntity.ok(dto);
    }

    /*
        Sumário de vendas por vendedor (teste 1)
        GET /sales/summary?minDate=2022-01-01&maxDate=2022-06-30
     */
    @GetMapping("/summary")
    public List<SellerSalesDTO> getTotalSalesBySellerAndDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate minDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate maxDate) {
        return saleRepository.getTotalSalesBySellerAndDateRange(minDate, maxDate);
    }

    /*
        Sumário de vendas por vendedor (teste 2)
        GET /sales/summary
        Deverá retornar o sumário de vendas por vendedor dos últimos 12 meses.
     */
    @GetMapping("/sales-summary")
    public List<SalesSummaryDTO> getSalesSummary() {
        LocalDate date = LocalDate.now().minusMonths(15);
        return saleRepository.getSalesSummary(date);
    }

    /*
       Relatório de vendas (teste 1)
       GET /sales/report
    */


    /*
        Relatório de vendas (teste 2)
        GET /sales/report?minDate=2022-05-01&maxDate=2022-05-31&name=odinson (Agrupado por vendedor)
   */
    @GetMapping("/sales/report")
    public List<SalesReportDTO> getSalesReport(@RequestParam("minDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate minDate, @RequestParam("maxDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate maxDate, @RequestParam(required = false, defaultValue = "") String name) {
        return saleService.getSalesReport(minDate, maxDate, name);
    }



}
