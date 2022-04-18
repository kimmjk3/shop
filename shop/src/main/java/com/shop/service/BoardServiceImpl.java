package com.shop.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.shop.domain.AttachDTO;
import com.shop.domain.BoardDTO;
import com.shop.mapper.AttachMapper;
import com.shop.mapper.BoardMapper;
import com.shop.util.FileUtils;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private AttachMapper attachMapper;

    @Autowired
    private FileUtils fileUtils;

    // 게시글 등록,수정
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

    // 게시글 등록(파일)
    @Override
    public boolean registerBoard(BoardDTO params, MultipartFile[] files) {
        int queryResult = 1;

        if (registerBoard(params) == false) {
            return false;
        }

        System.out.println(params.getPostNumber());
        List<AttachDTO> fileList = fileUtils.uploadFiles(files, params.getPostNumber());
        if (CollectionUtils.isEmpty(fileList) == false) {
            queryResult = attachMapper.insertPostAttach(fileList);
            if (queryResult < 1) {
                queryResult = 0;
            }
        }

        return (queryResult > 0);
    }

    // 게시글 상세보기
    @Override
    public BoardDTO getBoardDetail(Integer postNumber) {
        return boardMapper.selectBoardDetail(postNumber);
    }

    // 게시글 삭제
    @Override
    public boolean deleteBoard(Integer postNumber) {
        int queryResult = 0;

        BoardDTO board = boardMapper.selectBoardDetail(postNumber);

        if (board != null && board.getPostDeleteDate() == null) {
            queryResult = boardMapper.deleteBoard(postNumber);
        }

        return (queryResult == 1) ? true : false;
    }

    // 게시글 리스트
    @Override
    public List<BoardDTO> getBoardList() {
        List<BoardDTO> boardList = Collections.emptyList();

        int boardTotalCount = boardMapper.selectBoardTotalCount();

        if (boardTotalCount > 0) {
            boardList = boardMapper.selectBoardList();
        }

        return boardList;
    }

    // 첨부파일 상세보기
    @Override
    public AttachDTO getAttachDetail(Integer postNumber) {
        return attachMapper.selectPostAttachDetail(postNumber);
    }

}