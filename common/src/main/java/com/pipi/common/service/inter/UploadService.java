package com.pipi.common.service.inter;

import java.io.InputStream;

/**
 * @author Ryan
 * @create 2019/6/4
 * @desc 用户上传接口
 **/
public interface UploadService {

    void uploadFileToOSS(InputStream inputStream,String fileName);

}
