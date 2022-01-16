package com.shop.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.domain.BoardDTO;
import com.shop.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public boolean registerBoard(BoardDTO params) {
        int queryResult = 0;

        if (params.getPostNumber() == null) {
            queryResult = boardMapper.insertBoard(params);
        } else {
            queryResult = boardMapper.updateBoard(params);
        }
        return (queryResult == 1) ? true : false;
    }

    @Override
    public BoardDTO getBoardDetail(Integer postNumber) {
        return boardMapper.selectBoardDetail(postNumber);
    }

    @Override
    public boolean deleteBoard(Integer postNumber) {
        int queryResult = 0;

        BoardDTO board = boardMapper.selectBoardDetail(postNumber);

        if (board != null && board.getPostDeleteDate() == null) {
            queryResult = boardMapper.deleteBoard(postNumber);
        }

        return (queryResult == 1) ? true : false;
    }

    @Override
    public List<BoardDTO> getBoardList() {
        List<BoardDTO> boardList = Collections.emptyList();

        int boardTotalCount = boardMapper.selectBoardTotalCount();

        if (boardTotalCount > 0) {
            boardList = boardMapper.selectBoardList();
        }

        return boardList;
    }

}