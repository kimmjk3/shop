package com.shop.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.domain.ProductDTO;
import com.shop.domain.UserDTO;
import com.shop.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    // 회원가입
    @Override
    public boolean registerUser(UserDTO params) {
        int queryResult = 0;
        System.out.println(params);

        queryResult = userMapper.insertUser(params);

        return (queryResult == 1) ? true : false;
    }

    // 회원 상세보기
    @Override
    public UserDTO getUserDetail(String userID) {
        return userMapper.selectUserDetail(userID);
    }

    // 회원삭제
    @Override
    public boolean deleteUser(String userID) {
        int queryResult = 0;

        UserDTO user = userMapper.selectUserDetail(userID);
        if (user != null && user.getUserSecessionDate() == null) {
            queryResult = userMapper.deleteUser(userID);
        }

        return (queryResult == 1) ? true : false;
    }

    // 로그인
    @Override
    public UserDTO loginUser(UserDTO params) {

        return userMapper.userLogin(params);

    }

    // 관심품목 등록
    public boolean registerInterestItem(String userID, Integer productNumber) {
        int queryResult1 = 0;
        int queryResult2 = 0;

        // 등록된 관심상품이 이미 있다면 false반환(이미 등록된 상품입니다.)
        queryResult1 = userMapper.selectInterestItemDetail(userID, productNumber);
        if (queryResult1 == 1) {
            return false;
        } else
            queryResult2 = userMapper.insertInterestItem(userID, productNumber);
        return (queryResult2 == 1) ? true : false;
    }

    // 관심상품 리스트
    @Override
    public List<ProductDTO> getInterestItemList(String userID) {
        List<ProductDTO> interestItemList = Collections.emptyList();

        int interestItemTotalCount = userMapper.selectInterestItemTotalCount(userID);

        if (interestItemTotalCount > 0) {
            interestItemList = userMapper.selectInterestItemList(userID);
        }
        return interestItemList;
    }

    // 관심상품 상제
    @Override
    public boolean deleteInterestItem(String userID, Integer productNumber) {
        int queryResult1 = 0;
        int queryResult2 = 0;

        // 등록된 관심상품이 있다면 해상당품 삭제
        queryResult1 = userMapper.selectInterestItemDetail(userID, productNumber);
        if (queryResult1 == 1) {
            queryResult2 = userMapper.deleteInterestItem(userID, productNumber);
            System.out.println("queryResult : " + queryResult2);
        }

        return (queryResult2 > 0) ? true : false;
    }
}