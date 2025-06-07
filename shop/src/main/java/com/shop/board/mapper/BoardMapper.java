package com.shop.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shop.board.vo.BoardVO;

@Mapper
public interface BoardMapper {

    public int insertBoard(BoardVO params);

    public BoardVO selectBoardDetail(Integer postNumber);

    public int updateBoard(BoardVO params);

    public int deleteBoard(Integer postNumber);

    public List<BoardVO> selectReviewList();

}
