package com.pipi.common.service;

import com.google.common.collect.Lists;
import com.pipi.common.domain.Attachment;
import com.pipi.common.domain.Fun;
import com.pipi.common.domain.FunContent;
import com.pipi.common.persistence.dto.FunDTO;
import com.pipi.common.persistence.mapper.AttachmentMapper;
import com.pipi.common.persistence.mapper.FunContentMapper;
import com.pipi.common.persistence.mapper.FunImagesMapper;
import com.pipi.common.persistence.mapper.FunMapper;
import com.pipi.common.service.inter.FunService;
import com.pipi.common.service.inter.UploadService;
import com.pipi.common.vo.FunImagesVo;
import com.pipi.common.vo.FunVo;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.net.URL;
import java.sql.Timestamp;
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
    private AttachmentMapper attachmentMapper;
    @Autowired
    private UploadService uploadService;

//    @Override
//    public Page<Fun> findAllByPageAndAuthority(Integer page, Integer size,Integer authority) {
//        Pageable pageable = new PageRequest(page - 1, size);
//        return funRepository.findAll(pageable);
//    }

    @Override
    public List<FunDTO> findAllByPageAndAuthority(Integer authority){
        //这里可在 Pageable 里构建 Sort 用来排序

        List<FunDTO> funList =funMapper.selectFunByAuthority(authority);
//        PageInfo<Fun> pageInfo = new PageInfo<Fun>(funList);
        return funList;
    }

    @Override
    @Transactional
    public void funPublish(FunVo funVo) {
        //持久化Fun
        Fun fun =new Fun(funVo.getAuthority(), funVo.getPassword(), funVo.getFee(), new Date(), new Date());
        funMapper.insert(fun);
        //持久化FunContent
        FunContent funContent=new FunContent(fun.getId(), funVo.getTitle(), funVo.getContent(), new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));
        funContentMapper.insert(funContent);
        //持久化FunImages
        for (FunImagesVo funImages : funVo.getImages()
        ) {
            //图片模糊处理
            if(funImages.getBlur()==1) {
                Attachment attachment = attachmentMapper.selectByPrimaryKey(Long.valueOf(funImages.getId()+""));
                //将文件复制到公告bucket中
                URL url =uploadService.handleFileInOSS(attachment.getAttachmentName()+attachment.getAttachmentSuffixAme());
//                funImagesRepository.updateOneWithBlur(fun.getId(), funImages.getBlur(), funImages.getDesc(),url.toString(), funImages.getId());
            }else {
//                funImagesRepository.updateOneWithOutBlur(fun.getId(), funImages.getBlur(), funImages.getDesc(), funImages.getId());
            }
        }
    }
}
