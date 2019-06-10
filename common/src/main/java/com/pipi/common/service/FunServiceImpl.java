package com.pipi.common.service;

import com.pipi.common.domain.Fun;
import com.pipi.common.domain.FunContent;
import com.pipi.common.repository.FunContentRepository;
import com.pipi.common.repository.FunImagesRepository;
import com.pipi.common.repository.FunRepository;
import com.pipi.common.service.inter.FunService;
import com.pipi.common.service.inter.UploadService;
import com.pipi.common.vo.FunImagesVo;
import com.pipi.common.vo.FunVo;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.util.Date;

/**
 * @author Ryan
 * @create 2019/6/10
 * @desc 用户接口实现
 **/
@CommonsLog
@Service("funService")
public class FunServiceImpl implements FunService {

    @Autowired
    private FunRepository funRepository;

    @Autowired
    private FunContentRepository funContentRepository;
    @Autowired
    private FunImagesRepository funImagesRepository;
    @Autowired
    private UploadService uploadService;

    @Override
    public Page<Fun> findAllByPage(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page - 1, size);
        return funRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void funPublish(FunVo funVo) {
        Fun fun = funRepository.save(new Fun(funVo.getAuthority(), funVo.getPassword(), funVo.getFee(), new Date(), new Date()));
        funContentRepository.save(new FunContent(fun.getId(), funVo.getTitle(), funVo.getContent(), new Date(), new Date()));
        for (FunImagesVo funImages : funVo.getImages()
        ) {
            //图片模糊处理
            if(funImages.getBlur()==1) {
                String imageUrl = funImagesRepository.getUrlById(funImages.getId());
                URL url =uploadService.getFileFromOSS(imageUrl);
                funImagesRepository.updateOneWithBlur(fun.getId(), funImages.getBlur(), funImages.getDesc(),url.toString(), funImages.getId());
            }else {
                funImagesRepository.updateOneWithOutBlur(fun.getId(), funImages.getBlur(), funImages.getDesc(), funImages.getId());
            }
        }
    }
}
