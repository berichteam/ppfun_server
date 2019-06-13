package com.pipi.common.persistence.mapper;

import com.pipi.common.domain.Attachment;
import java.util.List;

/**
* Created by Mybatis Generator 2019/06/13
*/
public interface AttachmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Attachment record);

    Attachment selectByPrimaryKey(Long id);

    List<Attachment> selectAll();

    int updateByPrimaryKey(Attachment record);
}