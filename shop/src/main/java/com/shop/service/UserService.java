package com.shop.service;

import java.util.List;

import com.shop.domain.ProductDTO;
import com.shop.domain.UserDTO;

public interface UserService {

    // 회원가입

    public boolean registerUser(UserDTO params);

    public UserDTO getUserDetail(String userID);

    public boolean deleteUser(String userID);

    // 로그인
    public UserDTO loginUser(UserDTO params);

    // 관심상품 등록
    public boolean registerInterestItem(String userID, Integer productNumber);

    // 관심상품 리스트
    public List<ProductDTO> getInterestItemList(String userID);
}