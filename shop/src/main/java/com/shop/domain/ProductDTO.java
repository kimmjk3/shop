package com.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    Integer product_Number; // 상품번호
    String product_Category; // 상품카테고리
    Integer product_State; // 상품상테(0:준비중 1:판매중)
    String product_Name; // 상품명
    Integer product_Price; // 상품가격
    Integer product_Stock; // 상품재고
    String product_Desc; // 상품상세내용
    Integer product_Hits; // 상품조회수
    String product_Date; // 상품등록일
    String product_Current; // 상품수정일
    String product_DeleteDate; // 상품삭제일
}