package com.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shop.domain.AttachDTO;

@Mapper
public interface AttachMapper {

    // 상품 첨부
    public int insertAttach(List<AttachDTO> attachList);

    public AttachDTO selectAttachDetail(Integer attachNumber);

    public int deleteAttach(Integer productNumber);

    public List<AttachDTO> selectAttachList(Integer productNumber);

    public int selectAttachTotalCount(Integer productNumber);

    // 게시글 첨부
    public int insertPostAttach(List<AttachDTO> attachList);

    public AttachDTO selectPostAttachDetail(Integer attachNumber);

    public int deletePostAttach(Integer postNumber);

    public List<AttachDTO> selectPostAttachList(Integer postNumber);

    public int selectPostAttachTotalCount(Integer postNumber);

}