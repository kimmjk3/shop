package com.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shop.domain.ProductDTO;

@Mapper
public interface ProductMapper {

    // 상품등록
    public int insertProduct(ProductDTO params);

    // 상품상세보기
    public ProductDTO selectProductDetail(Integer productNumber);

    // 상품수정
    public int updateProduct(ProductDTO params);

    // 상품삭제
    public int deleteProduct(Integer productNumber);

    // 상품리스트(관리)
    public List<ProductDTO> selectProductList();

    // 전체상품카운트
    public int selectProductTotalCount();

    // 상품 판매리스트 조회(productlist.html)
    public List<ProductDTO> selectProductSellList();

}
