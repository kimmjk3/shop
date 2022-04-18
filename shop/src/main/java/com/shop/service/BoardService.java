package com.shop.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.shop.domain.AttachDTO;
import com.shop.domain.BoardDTO;

public interface BoardService {

    // 게시글 등록,수정
    public boolean registerBoard(BoardDTO params);

    // 게시글 등록(파일 업로드)
    public boolean registerBoard(BoardDTO params, MultipartFile[] files);

    // 게시글 상세보기
    public BoardDTO getBoardDetail(Integer postNumber);

    // 게시글 삭제
    public boolean deleteBoard(Integer postNumber);

    // 게시글리스트
    public List<BoardDTO> getBoardList();

    // 첨부파일 상세보기
    public AttachDTO getAttachDetail(Integer postNumber);

}