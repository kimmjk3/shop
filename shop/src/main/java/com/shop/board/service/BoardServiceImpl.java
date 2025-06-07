package com.shop.board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.shop.attach.mapper.AttachMapper;
import com.shop.attach.vo.AttachVO;
import com.shop.board.mapper.BoardMapper;
import com.shop.board.vo.BoardVO;
import com.shop.util.FileUtils;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private AttachMapper attachMapper;

    @Autowired
    private FileUtils fileUtils;
    
    // 리뷰목록
    @Override
    public List<BoardVO> selectReviewList() {
        List<BoardVO> reviewList = Collections.emptyList();
        reviewList = boardMapper.selectReviewList();
        return reviewList;
    }

    // 게시글 등록,수정
    @Override
    public boolean registerBoard(BoardVO params) {
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
    public boolean registerBoard(BoardVO params, MultipartFile[] files) {
        int queryResult = 1;

        if (registerBoard(params) == false) {
            return false;
        }
        
        List<AttachVO> fileList = fileUtils.uploadFiles(files, params.getPostNumber());
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
    public BoardVO getBoardDetail(Integer postNumber) {
        return boardMapper.selectBoardDetail(postNumber);
    }

    // 게시글 삭제
    @Override
    public boolean deleteBoard(Integer postNumber) {
        int queryResult = 0;

        BoardVO board = boardMapper.selectBoardDetail(postNumber);

        if (board != null && board.getPostDeleteDate() == null) {
            queryResult = boardMapper.deleteBoard(postNumber);
        }

        return (queryResult == 1) ? true : false;
    }

    // 첨부파일 상세보기
    @Override
    public AttachVO getAttachDetail(Integer postNumber) {
        return attachMapper.selectPostAttachDetail(postNumber);
    }

}