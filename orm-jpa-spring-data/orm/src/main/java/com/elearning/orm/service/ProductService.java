package com.elearning.orm.service;

import com.elearning.orm.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();
    ProductDto findById(Long id);
}
