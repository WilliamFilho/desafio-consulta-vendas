package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.dto.SalesReportDTO;
import com.devsuperior.dsmeta.entities.dto.SalesReportGeralDTO;
import com.devsuperior.dsmeta.entities.dto.SalesSummaryDTO;
import com.devsuperior.dsmeta.entities.dto.SellerSalesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {


    @Query("SELECT new com.devsuperior.dsmeta.entities.dto.SellerSalesDTO(s.seller.name, SUM(s.amount), :startDate, :endDate) " +
            "FROM Sale s " +
            "WHERE s.date BETWEEN :startDate AND :endDate " +
            "GROUP BY s.seller.name")
    List<SellerSalesDTO> getTotalSalesBySellerAndDateRange(LocalDate startDate, LocalDate endDate);

    @Query("SELECT new com.devsuperior.dsmeta.entities.dto.SalesSummaryDTO(s.name, SUM(sales.amount)) FROM Sale sales INNER JOIN sales.seller s WHERE sales.date >= :date GROUP BY s.name")
    List<SalesSummaryDTO> getSalesSummary(@Param("date") LocalDate date);

    @Query("SELECT new com.devsuperior.dsmeta.entities.dto.SalesSummaryDTO(s.name, SUM(sales.amount)) FROM Sale sales INNER JOIN sales.seller s WHERE sales.date >= :startDate AND sales.date <= :endDate AND s.name LIKE %:name% GROUP BY s.name")
    List<SalesSummaryDTO> getSalesSummary1(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("name") String name);

    @Query("SELECT new com.devsuperior.dsmeta.entities.dto.SalesReportDTO(s.name, MIN(sales.date), MAX(sales.date), SUM(sales.amount)) FROM Sale sales INNER JOIN sales.seller s WHERE sales.date >= :minDate AND sales.date <= :maxDate AND LOWER(s.name) LIKE LOWER(CONCAT ('%',:name,'%')) GROUP BY s.name")
    List<SalesReportDTO> getSalesReport(@Param("minDate") LocalDate minDate, @Param("maxDate") LocalDate maxDate, @Param("name") String name);

    List<Sale> findByDateGreaterThanEqual(LocalDate startDate);
}