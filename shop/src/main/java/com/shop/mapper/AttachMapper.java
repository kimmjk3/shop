package com.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shop.domain.AttachDTO;

@Mapper
public interface AttachMapper {

    public int insertAttach(List<AttachDTO> attachList);

    public AttachDTO selectAttachDetail(Long attachNumber);

    public int deleteAttach(Long productNumber);

    public List<AttachDTO> selectAttachList(Long productNumber);

    public int selectAttachTotalCount(Long productNumber);
}