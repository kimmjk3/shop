package com.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    // Product(상품)
    Integer productNumber; // 상품번호
    String productCategory; // 상품카테고리
    Integer productState; // 상품상테(0:준비중 1:판매중)
    String productName; // 상품명
    Integer productPrice; // 상품가격
    Integer productStock; // 상품재고
    String productDesc; // 상품상세내용
    Integer productHits; // 상품조회수
    String productInputDate; // 상품등록일
    String productCurrent; // 상품수정일
    String productDeleteDate; // 상품삭제일
    String userID; // 상품 등록인

    // Attach(첨부)
    Integer attachNumber; // 파일 번호
    String attachOriginalName; // 원본 파일명
    String attachSaveName; // 저장 파일명
    long attachSize; // 파일 크기
    String attachLocation; // 경로
    String attachInsertDate; // 등록일
    String attachDeleteDate; // 삭제일
}