package com.shop.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.shop.domain.AttachDTO;
import com.shop.domain.ProductDTO;

public interface ProductService {

    // 상품등록, 수정
    public boolean registerProduct(ProductDTO params);

    // 파일 업로드
    public boolean registerProduct(ProductDTO params, MultipartFile[] files);

    // 상품상세보기
    public ProductDTO getProductDetail(Integer productNumber);

    // 상품삭제
    public boolean deleteProduct(Integer productNumber);

    // 상품리스트
    public List<ProductDTO> getProductList();

    // 첨부파일 상세보기
    public AttachDTO getAttachDetail(Integer productNumber);

    // 상품판매리스트
    public List<ProductDTO> getProductSellList();
}