package com.pipi.ums.controller;

import com.pipi.common.util.QrCodeUtils;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

import static com.pipi.common.util.QrCodeUtils.zxingCodeCreate;
import static com.pipi.common.util.QrCodeUtils.zxingCodeCreateImage;

/**
 * @author Ryan
 * @create 2019/6/19
 * @desc
 **/
@RestController
@CommonsLog
@RequestMapping("/qrcode")
public class QrcodeController {

    /**
     * 二维码
     *
     * @param request
     * @param response
     */
    @GetMapping
    public void qrcode(HttpServletRequest request, HttpServletResponse response) {
        StringBuffer url = request.getRequestURL();
        // 域名
        String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();

        // 再加上请求链接
        String requestUrl = tempContextUrl + "/index";
        try {
            OutputStream os = response.getOutputStream();
            QrCodeUtils.encode(requestUrl, "", os, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        zxingCodeCreate("https://www.baidu.com", 348, 348,"C:\\Users\\Administrator\\Desktop\\picture\\", "test", "jpg");
        zxingCodeCreateImage("https://www.baidu.com", 348, 348,"C:\\Users\\Administrator\\Desktop\\picture\\2.jpg", "824", "1790");
    }

}
