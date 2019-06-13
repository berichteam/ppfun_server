package com.pipi.common.service.inter;

import java.io.InputStream;
import java.net.URL;

/**
 * @author Ryan
 * @create 2019/6/4
 * @desc 用户上传接口
 **/
public interface UploadService {

    void uploadFile2OSSPublic(InputStream inputStream, String fileName);

    void uploadFile2OSSPrivate(InputStream inputStream, String fileName);

    URL getFileFromOSSBlur(String fileName);

    /**
     * 1.将原图复制到公共的bucket中
     * 2.将公共的bucket中的图片模糊处理并持久化
     * 3.将公共的bucket中的原图删除
     *
     * @param fileName
     * @return
     */
    URL handleFileInOSS(String fileName);
}
