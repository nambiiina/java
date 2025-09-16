package com.elearning.orm.mapper;

import com.elearning.orm.dto.ProductDto;
import com.elearning.orm.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(Product entity);
    Product toEntity(ProductDto dto);
}
