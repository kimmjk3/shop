package com.shop.service;

import java.util.List;

import com.shop.domain.BoardDTO;

public interface BoardService {

    //
    public boolean registerBoard(BoardDTO params);

    public BoardDTO getBoardDetail(Integer postNumber);

    public boolean deleteBoard(Integer postNumber);

    public List<BoardDTO> getBoardList();

}