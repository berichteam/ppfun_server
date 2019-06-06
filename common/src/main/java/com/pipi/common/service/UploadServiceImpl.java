package com.pipi.common.service;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.pipi.common.properties.OSSProperties;
import com.pipi.common.service.inter.UploadService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;

@CommonsLog
@Service("UploadService")
public class UploadServiceImpl implements UploadService {

    @Autowired
    private OSSProperties ossProperties;

    @Override
    public void uploadFileToOSS(InputStream inputStream, String fileName) {
// 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(ossProperties.getEndpoint(), ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
// 上传文件流。
        ossClient.putObject(ossProperties.getBucketName(), fileName, inputStream);
// 关闭OSSClient。
        ossClient.shutdown();
    }

    @Override
    public URL getFileFromOSSBlur(String fileName) {
        // 设置图片处理样式。
        String style = "image/auto-orient,1/quality,q_90/blur,r_50,s_50";
        OSSClient ossClient = new OSSClient(ossProperties.getEndpoint(), ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(ossProperties.getBucketName(), fileName, HttpMethod.GET);
        req.setExpiration(expiration);
        req.setProcess(style);
        //模糊处理后的返回
        URL signedUrl = ossClient.generatePresignedUrl(req);
        ossClient.shutdown();
        return signedUrl;
    }

    @Override
    public URL getFileFromOSS(String fileName) {
        // 设置图片处理样式。
        OSSClient ossClient = new OSSClient(ossProperties.getEndpoint(), ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        // 设置URL过期时间为10年 3600l* 1000*24*365*10
        //未模糊处理的返回
        URL url =ossClient.generatePresignedUrl(ossProperties.getBucketName(),fileName,expiration);
        ossClient.shutdown();
        return url;
    }
}
