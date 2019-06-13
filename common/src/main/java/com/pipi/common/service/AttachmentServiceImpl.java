package com.pipi.common.service;

import com.pipi.common.domain.Attachment;
import com.pipi.common.persistence.mapper.AttachmentMapper;
import com.pipi.common.service.inter.AttachmentService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@CommonsLog
@Service("attachmentService")
public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    private AttachmentMapper attachmentMapper;
    @Override
    public int insert(Attachment record) {
        return attachmentMapper.insert(record);
    }
}
