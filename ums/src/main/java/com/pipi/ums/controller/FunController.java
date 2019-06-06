package com.pipi.ums.controller;

import com.pipi.common.domain.Fun;
import com.pipi.common.domain.FunContent;
import com.pipi.common.domain.FunImages;
import com.pipi.common.domain.Result;
import com.pipi.common.repository.FunContentRepository;
import com.pipi.common.repository.FunImagesRepository;
import com.pipi.common.repository.FunRepository;
import com.pipi.common.service.inter.FunService;
import com.pipi.common.service.inter.UploadService;
import com.pipi.common.vo.FunImagesVo;
import com.pipi.common.vo.FunVo;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Ryan
 * @create 2019/6/6
 * @desc
 **/
@RestController
@CommonsLog
public class FunController {


    @Autowired
    private FunService funService;

    @PostMapping(value = "/fun/funPublish")
    public Result funPublish(@RequestBody FunVo funVo, HttpServletRequest request) {
        funService.funPublish(funVo);
        return Result.success(funVo);
    }
}
