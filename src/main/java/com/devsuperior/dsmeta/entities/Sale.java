package com.devsuperior.dsmeta.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private LocalDate date;
    private Integer deals;
    private String sellerName;
    private Integer visited;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;
    // constructor, getters and setters
    @Transient
    private LocalDate startDate;
    @Transient
    private LocalDate endDate;

    public boolean isWithinLast12Months() {
        return date.isAfter(LocalDate.now().minusMonths(12));
    }
    // constructor, getters and setters

    public Sale() {
    }

    public Sale(Long id, Double amount, LocalDate date, Integer deals, String sellerName, Integer visited, Seller seller, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.deals = deals;
        this.sellerName = sellerName;
        this.visited = visited;
        this.seller = seller;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getDeals() {
        return deals;
    }

    public void setDeals(Integer deals) {
        this.deals = deals;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Integer getVisited() {
        return visited;
    }

    public void setVisited(Integer visited) {
        this.visited = visited;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }


}