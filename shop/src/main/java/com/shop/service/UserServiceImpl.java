package com.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.domain.UserDTO;
import com.shop.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean registerUser(UserDTO params) {
        int queryResult = 0;
        System.out.println(params);

        queryResult = userMapper.insertUser(params);

        return (queryResult == 1) ? true : false;
    }

    @Override
    public UserDTO getUserDetail(String userID) {
        return userMapper.selectUserDetail(userID);
    }

    @Override
    public boolean deleteUser(String userID) {
        int queryResult = 0;

        UserDTO user = userMapper.selectUserDetail(userID);
        if (user != null && user.getUserSecessionDate() == null) {
            queryResult = userMapper.deleteUser(userID);
        }

        return (queryResult == 1) ? true : false;
    }

    @Override
    public UserDTO loginUser(UserDTO params) {

        return userMapper.userLogin(params);

    }

    // 관심품목 등록
    public boolean registerInterestItem(String userID, Integer productNumber) {
        int queryResult = 0;
        int queryResult2 = 0;

        queryResult2 = userMapper.selectInterestItemDetail(userID, productNumber);
        if (queryResult2 == 1) {
            return false;
        } else
            queryResult = userMapper.insertInterestItem(userID, productNumber);
        return (queryResult == 1) ? true : false;
    }
}