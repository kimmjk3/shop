package com.shop.service;

import java.util.List;

import com.shop.domain.ProductDTO;

public interface ProductService {

    // 상품등록, 수정
    public boolean registerProduct(ProductDTO params);

    // 상품상세보기
    public ProductDTO getProductDetail(Integer productNumber);

    // 상품삭제
    public boolean deleteProduct(Integer productNumber);

    // 상품리스트
    public List<ProductDTO> getProductList();

}