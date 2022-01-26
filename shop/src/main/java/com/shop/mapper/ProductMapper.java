package com.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shop.domain.ProductDTO;

@Mapper
public interface ProductMapper {

    public int insertProduct(ProductDTO params);

    public ProductDTO selectProductDetail(Integer productNumber);

    public int updateProduct(ProductDTO params);

    public int deleteProduct(Integer productNumber);

    public List<ProductDTO> selectProductList();

    public int selectProductTotalCount();

}
