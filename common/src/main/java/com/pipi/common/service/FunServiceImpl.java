package com.pipi.common.service;

import com.google.common.collect.Lists;
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
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.net.URL;
import java.util.Date;
import java.util.List;

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

//    @Override
//    public Page<Fun> findAllByPageAndAuthority(Integer page, Integer size,Integer authority) {
//        Pageable pageable = new PageRequest(page - 1, size);
//        return funRepository.findAll(pageable);
//    }

    @Override
    public Page<Fun> findAllByPageAndAuthority(Integer authority, Integer page, Integer size){
        //这里可在 Pageable 里构建 Sort 用来排序
        Pageable pageable = new PageRequest(page-1, size);
        Page<Fun> housings = funRepository.findAll((root, criteriaQuery, criteriaBuilder)
                -> getPredicate(authority, root, criteriaBuilder), pageable);
        return housings;
    }
    private Predicate getPredicate(Integer authority, Root<Fun> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> list = Lists.newArrayList();
        if (authority!=0) {
            list.add(criteriaBuilder.equal(root.get("authority").as(String.class), "=" + authority));
        }

        Predicate[] p = new Predicate[list.size()];
        return criteriaBuilder.and(list.toArray(p));
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
