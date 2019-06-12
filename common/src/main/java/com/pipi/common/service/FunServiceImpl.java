package com.pipi.common.service;

import com.google.common.collect.Lists;
import com.pipi.common.domain.Fun;
import com.pipi.common.domain.FunContent;
import com.pipi.common.persistence.mapper.FunContentMapper;
import com.pipi.common.persistence.mapper.FunImagesMapper;
import com.pipi.common.persistence.mapper.FunMapper;
import com.pipi.common.service.inter.FunService;
import com.pipi.common.service.inter.UploadService;
import com.pipi.common.vo.FunImagesVo;
import com.pipi.common.vo.FunVo;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
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
    private FunMapper funMapper;
    @Autowired
    private FunContentMapper funContentMapper;
    @Autowired
    private FunImagesMapper funImagesMapper;
    @Autowired
    private UploadService uploadService;

//    @Override
//    public Page<Fun> findAllByPageAndAuthority(Integer page, Integer size,Integer authority) {
//        Pageable pageable = new PageRequest(page - 1, size);
//        return funRepository.findAll(pageable);
//    }

    @Override
    public List<Fun> findAllByPageAndAuthority(Integer authority, Integer page, Integer size){
        //这里可在 Pageable 里构建 Sort 用来排序
        Pageable pageable = new PageRequest(page-1, size);

        return funMapper.selectAll();
    }
    private Predicate getPredicate(Integer authority, Root<Fun> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> list = Lists.newArrayList();
        if (authority!=0) {
            list.add(criteriaBuilder.equal(root.get("authority").as(String.class), "=" + authority));
            Join<Fun, FunContent> join = root.join("fun_content", JoinType.LEFT);
            list.add(criteriaBuilder.equal(join.get("fun_id"), "=" + authority));
        }

        Predicate[] p = new Predicate[list.size()];
        return criteriaBuilder.and(list.toArray(p));
    }

    @Override
    @Transactional
    public void funPublish(FunVo funVo) {
        Integer funId = funMapper.insert(new Fun(funVo.getAuthority(), funVo.getPassword(), funVo.getFee(), new Date(), new Date()));
        funContentMapper.insert(new FunContent(Long.parseLong(funId+""), funVo.getTitle(), funVo.getContent(), new Date(), new Date()));
        for (FunImagesVo funImages : funVo.getImages()
        ) {
            //图片模糊处理
//            if(funImages.getBlur()==1) {
//                String imageUrl = funContentMapper.getUrlById(funImages.getId());
//                URL url =uploadService.getFileFromOSS(imageUrl);
//                funImagesRepository.updateOneWithBlur(fun.getId(), funImages.getBlur(), funImages.getDesc(),url.toString(), funImages.getId());
//            }else {
//                funImagesRepository.updateOneWithOutBlur(fun.getId(), funImages.getBlur(), funImages.getDesc(), funImages.getId());
//            }
        }
    }
}
