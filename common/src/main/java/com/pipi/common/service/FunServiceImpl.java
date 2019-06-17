package com.pipi.common.service;

import com.pipi.common.domain.Attachment;
import com.pipi.common.domain.Fun;
import com.pipi.common.domain.FunImages;
import com.pipi.common.persistence.dto.FunDTO;
import com.pipi.common.persistence.mapper.AttachmentMapper;
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
    private FunImagesMapper funImagesMapper;
    @Autowired
    private AttachmentMapper attachmentMapper;
    @Autowired
    private UploadService uploadService;


    @Override
    public List<FunDTO> selectAllByPageAndAuthority(Integer authority) {
        //这里可在 Pageable 里构建 Sort 用来排序
        List<FunDTO> funList = funMapper.selectFunByAuthority(authority);
        return funList;
    }

    @Override
    @Transactional
    public void createFun(FunVo funVo) {
        //持久化Fun
        Fun fun = new Fun();
        fun.setUserId(funVo.getUserId());
        fun.setTitle(funVo.getTitle());
        fun.setContent(funVo.getContent());
        fun.setAuthority(funVo.getAuthority());
        fun.setCreatedAt(new Date());
        if (funVo.getAuthority() == 3) {
            fun.setFee(funVo.getFee());
        } else if (funVo.getAuthority() == 4) {
            fun.setPassword(funVo.getPassword());
        }
        funMapper.insert(fun);
        //持久化FunImages
        for (FunImagesVo funImagesVo : funVo.getImages()
        ) {
            Attachment attachment = attachmentMapper.selectByPrimaryKey(Long.valueOf(funImagesVo.getId() + ""));
            //原始图片对应OSS的key
            String originalImageName = attachment.getAttachmentName() + attachment.getAttachmentSuffixAme();
            //操作完成后对应的OSS的key
            String blurImageName = System.currentTimeMillis() + attachment.getAttachmentSuffixAme();

            //图片模糊处理
            FunImages funImages = new FunImages();
            funImages.setFunId(fun.getId());
            funImages.setAttachmentId(attachment.getId());
            funImages.setBlur(funImagesVo.getBlur());
            funImages.setDescription(funImagesVo.getDesc());
            funImages.setImageUrl(originalImageName);
            if (funImagesVo.getBlur() == 1) {
                //将文件复制到公告bucket中
                uploadService.handleFileInOSSByBlur(originalImageName, blurImageName);
            } else {
                uploadService.handleFileInOSSByCopy(originalImageName, blurImageName);
            }
            funImagesMapper.insert(funImages);
        }
    }

    @Override
    public void editFun(FunVo funVo) {
        //持久化Fun
        Fun fun = new Fun();
        fun.setUserId(funVo.getUserId());
        fun.setTitle(funVo.getTitle());
        fun.setContent(funVo.getContent());
        fun.setAuthority(funVo.getAuthority());
        fun.setUpdatedAt(new Date());
        if (funVo.getAuthority() == 3) {
            fun.setFee(funVo.getFee());
        } else if (funVo.getAuthority() == 4) {
            fun.setPassword(funVo.getPassword());
        }
        funMapper.updateByPrimaryKey(fun);
        //持久化FunImages
        for (FunImagesVo funImagesVo : funVo.getImages()
        ) {
            Attachment attachment = attachmentMapper.selectByPrimaryKey(Long.valueOf(funImagesVo.getId() + ""));
            //原始图片对应OSS的key
            String originalImageName = attachment.getAttachmentName() + attachment.getAttachmentSuffixAme();
            //操作完成后对应的OSS的key
            String blurImageName = System.currentTimeMillis() + attachment.getAttachmentSuffixAme();
            //图片模糊处理
            FunImages funImages = new FunImages();
            funImages.setFunId(fun.getId());
            funImages.setAttachmentId(attachment.getId());
            funImages.setBlur(funImagesVo.getBlur());
            funImages.setDescription(funImagesVo.getDesc());
            funImages.setImageUrl(originalImageName);
            if (funImagesVo.getBlur() == 1) {
                //将文件复制到公告bucket中
                uploadService.handleFileInOSSByBlur(originalImageName, blurImageName);
            } else {
                uploadService.handleFileInOSSByCopy(originalImageName, blurImageName);
            }
            funImagesMapper.updateByPrimaryKey(funImages);
        }
    }

    @Override
    public int deleteFun(Integer funId) {
        return funMapper.updateByPrimaryKeyForDelete(funId);
    }

    @Override
    public FunDTO selectFunByFunId(Long funId) {
        return funMapper.selectFunByFunId(funId);
    }

    @Override
    public List<FunDTO> selectAllFunByPage() {
        return funMapper.selectAllFunByPage();
    }
}
