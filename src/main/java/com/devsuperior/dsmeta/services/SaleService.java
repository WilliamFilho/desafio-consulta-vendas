package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.dto.SalesReportDTO;
import com.devsuperior.dsmeta.entities.dto.SalesReportGeralDTO;
import com.devsuperior.dsmeta.entities.dto.SalesSummaryDTO;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    public SaleMinDTO findById(Long id) {
        Optional<Sale> result = repository.findById(id);
        Sale entity = result.get();
        return new SaleMinDTO(entity);
    }
    @Autowired
    private SaleRepository saleRepository;
    //recebendo a data como String...
    public List<SalesSummaryDTO> getSalesSummary() {
        //Date initial default
        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        //LocalDate endDate = LocalDate.parse(endDateStr != null ? endDateStr : today.toString());
        //LocalDate startDate = LocalDate.parse(startDateStr != null ? startDateStr : today.minusYears(1L).toString());
        LocalDate date = LocalDate.parse(today.minusYears(2L).toString());
        return saleRepository.getSalesSummary(date);
    }


    //recebendo como DataLocal
    public List<SalesReportDTO> getSalesReport(LocalDate minDate, LocalDate maxDate, String name) {
          LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        LocalDate minD = minDate != null ? minDate : today;
        LocalDate maxD = maxDate != null ? maxDate : today.minusYears(1L);
        return saleRepository.getSalesReport(minD, maxD, name != null ? name : "");
    }

    public List<SalesReportGeralDTO> getSalesReport() {
        LocalDate startDate = LocalDate.now().minusMonths(15);
        List<Sale> salesList = saleRepository.findByDateGreaterThanEqual(startDate);
        List<SalesReportGeralDTO> reportList = new ArrayList<>();
        for (Sale sales : salesList) {
            SalesReportGeralDTO report = new SalesReportGeralDTO();
            report.setAmount(sales.getAmount());
            report.setDate(sales.getDate());
            report.setDeals(sales.getDeals());
            report.setSellerName(sales.getSeller().getName());
            report.setVisited(sales.getVisited());
            reportList.add(report);
        }
        return reportList;
    }
}
