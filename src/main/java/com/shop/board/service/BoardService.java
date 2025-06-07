package com.shop.board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.shop.attach.vo.AttachVO;
import com.shop.board.vo.BoardVO;

public interface BoardService {
	
    // 리뷰목록
    public List<BoardVO> selectReviewList();

    // 게시글 등록,수정
    public boolean registerBoard(BoardVO params);

    // 게시글 등록(파일 업로드)
    public boolean registerBoard(BoardVO params, MultipartFile[] files);

    // 게시글 상세보기
    public BoardVO getBoardDetail(Integer postNumber);

    // 게시글 삭제
    public boolean deleteBoard(Integer postNumber);

    // 첨부파일 상세보기
    public AttachVO getAttachDetail(Integer postNumber);

}