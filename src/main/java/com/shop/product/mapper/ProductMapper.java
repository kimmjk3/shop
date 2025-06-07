package com.shop.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shop.product.vo.ProductVO;

@Mapper
public interface ProductMapper {

    // 상품등록
    public int insertProduct(ProductVO params);

    // 상품상세보기
    public ProductVO selectProductDetail(Integer productNumber);

    // 상품수정
    public int updateProduct(ProductVO params);

    // 상품삭제
    public int deleteProduct(Integer productNumber);

    // 상품리스트(관리)
    public List<ProductVO> selectProductList();

    // 전체상품카운트
    public int selectProductTotalCount();

    // 상품 판매리스트 조회(productlist.html)
    public List<ProductVO> selectProductSellList();

    // 관심물품 등록
    public int insertInterestItem(@Param("userID") String userID, @Param("productNumber") Integer productNumber);

    // 관심품목 상세보기
    public int selectInterestItemCount(@Param("userID") String userID, @Param("productNumber") Integer productNumber);

    // 전체 관심상품 카운트
    public int selectInterestItemTotalCount(@Param("userID") String userID);

    // 관심상품 리스트 조회
    public List<ProductVO> selectInterestItemList(@Param("userID") String userID);

    // 관심상품 삭제
    public int deleteInterestItem(@Param("userID") String userID, @Param("productNumber") Integer productNumber);
}
