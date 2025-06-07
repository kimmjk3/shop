package com.shop.product.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.shop.attach.vo.AttachVO;
import com.shop.product.vo.ProductVO;

public interface ProductService {

    // 상품등록, 수정
    public boolean registerProduct(ProductVO params);

    // 상품등록(파일 업로드)
    public boolean registerProduct(ProductVO params, MultipartFile[] files);

    // 상품상세보기
    public ProductVO getProductDetail(Integer productNumber);

    // 상품삭제
    public boolean deleteProduct(Integer productNumber);

    // 상품리스트
    public List<ProductVO> getProductList();

    // 첨부파일 상세보기
    public AttachVO getAttachDetail(Integer productNumber);

    // 상품판매리스트
    public List<ProductVO> getProductSellList();
    
	// 관심상품 등록
	public boolean registerInterestItem(String userID, Integer productNumber);

	// 관심상품 리스트
	public List<ProductVO> getInterestItemList(String userID);

	// 관심상품 제거
	public boolean deleteInterestItem(String userID, Integer productNumber);
}