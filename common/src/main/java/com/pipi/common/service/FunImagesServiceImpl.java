package com.pipi.common.service;

import com.pipi.common.domain.FunImages;
import com.pipi.common.persistence.mapper.FunImagesMapper;
import com.pipi.common.service.inter.FunImagesService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@CommonsLog
@Service("funImagesService")
public class FunImagesServiceImpl implements FunImagesService {

    @Autowired
    private FunImagesMapper funImagesMapper;
    @Override
    public int insert(FunImages record) {
        return funImagesMapper.insert(record);
    }
}
