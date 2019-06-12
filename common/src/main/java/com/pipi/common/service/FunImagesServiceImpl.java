package com.pipi.common.service;

import com.aliyun.oss.OSSClient;
import com.pipi.common.domain.FunImages;
import com.pipi.common.persistence.mapper.FunImagesMapper;
import com.pipi.common.properties.OSSProperties;
import com.pipi.common.service.inter.FunImagesService;
import com.pipi.common.service.inter.UploadService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@CommonsLog
@Service("funImagesService")
public class FunImagesServiceImpl implements FunImagesService {

    private FunImagesMapper funImagesMapper;
    @Override
    public int insert(FunImages record) {
        return funImagesMapper.insert(record);
    }
}
