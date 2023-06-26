package com.devsuperior.dsmeta.assembler;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.dto.SellerSalesDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class SaleAssembler {

    private ModelMapper modelMapper;

    //Qdo precisar de uma EntityDTO
    public SellerSalesDTO toModel(Sale sale){
        return modelMapper.map(sale, SellerSalesDTO.class);
    }


    //Qdo precisar de uma List<EntityDTO> normal...

    //Qdo precisar de um Page<EntityDTO> paginada...
    public Page<SellerSalesDTO> toColletionModelPage(Page<Sale> sales) {
        return sales.map(this::toModel);
    }

    //de DTO para Entity
    public Sale toEntity(SellerSalesDTO dto) {
        return modelMapper.map(dto, Sale.class);
    }

    public List<SellerSalesDTO> toCollectionModel(List<Sale> sales) {
        return sales.stream()
                .map(sale -> toModel(sale))
                .collect(Collectors.toList());
    }
}
