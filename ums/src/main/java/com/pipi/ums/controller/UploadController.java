package com.pipi.ums.controller;

import com.pipi.common.domain.Attachment;
import com.pipi.common.domain.FunImages;
import com.pipi.common.domain.Result;
import com.pipi.common.service.inter.AttachmentService;
import com.pipi.common.service.inter.FunImagesService;
import com.pipi.common.service.inter.UploadService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import static com.pipi.common.enums.ResultCode.PARAM_IS_BLANK;

/**
 * @author Ryan
 * @create 2019/6/4
 * @desc
 **/
@RestController
@CommonsLog
public class UploadController {
    @Autowired
    private UploadService uploadService;
    @Autowired
    private AttachmentService attachmentService;

    @PostMapping(value = "/upload/image")
    public Result fileUpload(@RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty()) {
            Result.failure(PARAM_IS_BLANK);
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String uuidName = UUID.randomUUID().toString();
        fileName = uuidName + suffixName; // 新文件名

        try {
            //将原文件上传到OSS
            uploadService.uploadFile2OSSPrivate(file.getInputStream(), fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Attachment attachment = new Attachment();
        attachment.setAttachmentName(uuidName);
        attachment.setAttachmentSuffixAme(suffixName);
        attachmentService.insert(attachment);
        return Result.success(attachment);
    }
}
