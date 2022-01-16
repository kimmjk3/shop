package com.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shop.domain.BoardDTO;

@Mapper
public interface BoardMapper {

    public int insertBoard(BoardDTO params);

    public BoardDTO selectBoardDetail(Integer postNumber);

    public int updateBoard(BoardDTO params);

    public int deleteBoard(Integer postNumber);

    public List<BoardDTO> selectBoardList();

    public int selectBoardTotalCount();

}
