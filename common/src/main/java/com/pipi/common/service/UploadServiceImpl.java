package com.pipi.common.service;

import com.aliyun.oss.OSSClient;
import com.pipi.common.properties.OSSProperties;
import com.pipi.common.service.inter.UploadService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@CommonsLog
@Service("UploadService")
public class UploadServiceImpl implements UploadService {

    @Autowired
    private OSSProperties ossProperties;

    @Override
    public void uploadFileToOSS(InputStream inputStream, String fileName) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ossProperties.getAccessKeyId();
        String accessKeySecret = ossProperties.getAccessKeySecret();

// 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

// 上传文件流。
        ossClient.putObject(ossProperties.getBucketName(), fileName, inputStream);

// 关闭OSSClient。
        ossClient.shutdown();
    }
}
