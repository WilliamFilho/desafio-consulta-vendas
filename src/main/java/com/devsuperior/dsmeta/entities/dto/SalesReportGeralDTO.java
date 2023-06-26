package com.devsuperior.dsmeta.entities.dto;

import com.devsuperior.dsmeta.entities.Seller;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.hibernate.property.access.spi.Setter;

import java.time.LocalDate;
@Data
public class SalesReportGeralDTO {
    private Double amount;
    private LocalDate date;
    private Integer deals;
    private String sellerName;
    private Integer visited;
    @Getter(AccessLevel.NONE)
    private Seller seller;

    //evitar q carrega null(nome do vendedor)
    public String getName(Seller seller){
        return this.sellerName = seller.getName();
    }
}
