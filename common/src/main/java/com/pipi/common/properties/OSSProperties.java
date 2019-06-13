package com.pipi.common.properties;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value={"classpath:oss.properties"},name="oss.properties")
@Data
public class OSSProperties {

    @Value("${oss.endpoint}")
    private String endpoint;
    @Value("${oss.accessKeyId}")
    private String accessKeyId;
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${oss.bucketName.public}")
    private String bucketNamePublic;
    @Value("${oss.bucketName.private}")
    private String bucketNamePrivate;

}
