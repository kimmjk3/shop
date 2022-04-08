package com.shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shop.domain.UserDTO;

@Mapper
public interface UserMapper {

    // 회원가입값 입력
    public int insertUser(UserDTO params);

    public UserDTO selectUserDetail(String userID);

    public int updateUser(UserDTO params);

    public int deleteUser(String userID);

    // 로그인
    public UserDTO userLogin(UserDTO params);

    // 관심물품 등록
    public int insertInterestItem(@Param("userID") String userID, @Param("productNumber") Integer productNumber);

    // 관심품목 상세보기
    public int selectInterestItemDetail(@Param("userID") String userID, @Param("productNumber") Integer productNumber);
}
