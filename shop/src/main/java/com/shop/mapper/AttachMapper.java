package com.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shop.domain.AttachDTO;

@Mapper
public interface AttachMapper {

    public int insertAttach(List<AttachDTO> attachList);

    public AttachDTO selectAttachDetail(Integer attachNumber);

    public int deleteAttach(Integer productNumber);

    public List<AttachDTO> selectAttachList(Integer productNumber);

    public int selectAttachTotalCount(Integer productNumber);

}