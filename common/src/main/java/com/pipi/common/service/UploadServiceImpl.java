package com.pipi.common.service;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.common.utils.IOUtils;
import com.aliyun.oss.model.CopyObjectResult;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.GenericResult;
import com.aliyun.oss.model.ProcessObjectRequest;
import com.pipi.common.domain.Attachment;
import com.pipi.common.properties.OSSProperties;
import com.pipi.common.service.inter.UploadService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Formatter;

@CommonsLog
@Service("UploadService")
public class UploadServiceImpl implements UploadService {

    @Autowired
    private OSSProperties ossProperties;

    @Override
    public void uploadFile2OSSPublic(InputStream inputStream, String fileName) {
// 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(ossProperties.getEndpoint(), ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
// 上传文件流。
        ossClient.putObject(ossProperties.getBucketNamePublic(), fileName, inputStream);
// 关闭OSSClient。
        ossClient.shutdown();
    }
    @Override
    public void uploadFile2OSSPrivate(InputStream inputStream, String fileName) {
// 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(ossProperties.getEndpoint(), ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
// 上传文件流。
        ossClient.putObject(ossProperties.getBucketNamePrivate(), fileName, inputStream);
// 关闭OSSClient。
        ossClient.shutdown();
    }

    @Override
    public URL getFileFromOSSBlur(String fileName) {
        // 设置图片处理样式。
//        String style = "image/auto-orient,1/quality,q_90/blur,r_50,s_50";
//        OSSClient ossClient = new OSSClient(ossProperties.getEndpoint(), ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
//        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
//        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(ossProperties.getBucketName(), fileName, HttpMethod.GET);
//        req.setExpiration(expiration);
//        req.setProcess(style);
//        //模糊处理后的返回
//        URL signedUrl = ossClient.generatePresignedUrl(req);
//        ossClient.shutdown();
        return null;
    }

    @Override
    public String handleFileInOSSByBlur(String originalImageName,String blurImageName) {
        // 开启ossclient
        OSSClient ossClient = new OSSClient(ossProperties.getEndpoint(), ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
        // 拷贝文件
       CopyObjectResult result = ossClient.copyObject(ossProperties.getBucketNamePrivate(), originalImageName, ossProperties.getBucketNamePublic(),blurImageName);
        // 图片处理持久化 : 缩放
        StringBuilder sbStyle = new StringBuilder();
        Formatter styleFormatter = new Formatter(sbStyle);
        String styleType = "image/auto-orient,1/quality,q_90/blur,r_50,s_50";
//        String targetImage = "test.png";
        styleFormatter.format("%s|sys/saveas,o_%s,b_%s", styleType,
                BinaryUtil.toBase64String(blurImageName.getBytes()),
                BinaryUtil.toBase64String(ossProperties.getBucketNamePublic().getBytes()));
        System.out.println(sbStyle.toString());
        ProcessObjectRequest request = new ProcessObjectRequest(ossProperties.getBucketNamePublic(),blurImageName, sbStyle.toString());
        GenericResult processResult = ossClient.processObject(request);
        String json = null;
        try {
            json = IOUtils.readStreamAsString(processResult.getResponse().getContent(), "UTF-8");
            processResult.getResponse().getContent().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String finalUrl=ossProperties.getBucketNamePublic()+"."+ossProperties.getEndpoint()+"/"+blurImageName;
//        // 删除文件。
//        ossClient.deleteObject(ossProperties.getBucketNamePublic(), fileName+timeName);
        ossClient.shutdown();
        return finalUrl;
    }

    @Override
    public String handleFileInOSSByCopy(String originalImageName,String blurImageName) {
        // 开启ossclient
        OSSClient ossClient = new OSSClient(ossProperties.getEndpoint(), ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
        // 拷贝文件
        CopyObjectResult result = ossClient.copyObject(ossProperties.getBucketNamePrivate(), originalImageName, ossProperties.getBucketNamePublic(),blurImageName);
        ossClient.shutdown();
        String finalUrl=ossProperties.getBucketNamePublic()+"."+ossProperties.getEndpoint()+"/"+blurImageName;
        return finalUrl;
    }

}
