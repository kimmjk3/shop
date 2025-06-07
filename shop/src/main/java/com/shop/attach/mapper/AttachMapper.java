package com.shop.attach.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shop.attach.vo.AttachVO;

@Mapper
public interface AttachMapper {

    // 상품 첨부
    public int insertAttach(List<AttachVO> attachList);

    public AttachVO selectAttachDetail(Integer attachNumber);

    public int deleteAttach(Integer productNumber);

    public List<AttachVO> selectAttachList(Integer productNumber);

    public int selectAttachTotalCount(Integer productNumber);

    // 게시글 첨부
    public int insertPostAttach(List<AttachVO> attachList);

    public AttachVO selectPostAttachDetail(Integer attachNumber);

    public int deletePostAttach(Integer postNumber);

    public List<AttachVO> selectPostAttachList(Integer postNumber);

    public int selectPostAttachTotalCount(Integer postNumber);

}