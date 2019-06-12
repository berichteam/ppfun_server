package com.pipi.ums.controller;

import com.pipi.common.domain.FunImages;
import com.pipi.common.domain.Result;
import com.pipi.common.repository.FunImagesRepository;
import com.pipi.common.service.inter.FunImagesService;
import com.pipi.common.service.inter.UploadService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

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
    private FunImagesService funImagesService;
    @PostMapping(value = "/upload/fileUpload")
    public Result fileUpload(@RequestParam(value = "file") MultipartFile file,@RequestParam("mark") Integer mark, HttpServletRequest request) {
        if (file.isEmpty()) {
            System.out.println("文件为空空");
        }
        System.out.print("是否模糊处理:"+mark);
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        log.debug("服务器地址:"+ request.getServletContext().getRealPath(""));
        System.out.print(request.getServletContext().getRealPath(""));
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        try {
            uploadService.uploadFileToOSS(file.getInputStream(),fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
       URL url= uploadService.getFileFromOSSBlur(fileName);
        System.out.print("URl："+url);
        int funImages =funImagesService.insert(new FunImages(fileName,new Date(),new Date()));
        return  Result.success(funImages);
    }
}
