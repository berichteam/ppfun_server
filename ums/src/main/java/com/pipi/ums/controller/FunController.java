package com.pipi.ums.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pipi.common.domain.*;
import com.pipi.common.persistence.dto.FunDTO;
import com.pipi.common.service.inter.FunService;
import com.pipi.common.vo.FunVo;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author Ryan
 * @create 2019/6/6
 * @desc
 **/
@RestController
@CommonsLog
@RequestMapping("/article")
public class FunController {


    @Autowired
    private FunService funService;


    /**
     * 新增接口
     *
     * @param funVo
     * @param request
     * @return
     */
    @PostMapping
    public Result createFun(@RequestBody FunVo funVo, HttpServletRequest request) {
//        Users user = (Users) request.getAttribute("user");
//        funVo.setUserId(user.getId());
        funService.createFun(funVo);
        return Result.success(funVo);
    }

    /**
     * 编辑接口
     *
     * @param funVo
     *
     * @param request
     * @return
     */
    @PatchMapping(value = {"/id"})
    public Result editFun(@PathVariable String id, @RequestBody FunVo funVo, HttpServletRequest request) {
        funService.editFun(funVo);
        return Result.success(funVo);
    }

    /**
     * 删除接口
     *
     * @param funVo
     * @param request
     * @return
     */
    @DeleteMapping(value = {"/id"})
    public Result deleteFun(@PathVariable String id, @RequestBody FunVo funVo, HttpServletRequest request) {
        funService.deleteFun(Long.parseLong(id));
        return Result.success(funVo);
    }


    /**
     * 查询接口
     *
     * @param id
     * @param request
     * @return
     */
    @GetMapping
    public Result queryFun(@RequestParam(required = false) String id, @RequestParam(required = false) String q, HttpServletRequest request) {

        if (null != q && "" != q) {
            PageHelper.startPage(1, 10, q);
        } else {
            PageHelper.startPage(1, 10, "created_at desc");
        }

        if (null != id) {
            FunDTO funDTO = funService.selectFunByFunId(Long.parseLong(id));
            return Result.success(funDTO);
        } else {
            List<FunDTO> list = funService.selectAllFunByPage();
            PageInfo<FunDTO> pageInfo = new PageInfo<>(list);
            return Result.success(pageInfo);
        }

    }

    @PostMapping(value = "/{id}/star")
    public Result funStar(@PathVariable String id, HttpServletRequest request) {
//        Users user = (Users) request.getAttribute("user");
        FunStar funStar =new FunStar();
        funStar.setFunId(Long.parseLong(id));
//        funStar.setUserId(user.getId());
        funStar.setCreatedAt(new Date());
        funService.funStar(funStar);
        return Result.success(funStar);
    }

    @PostMapping(value = "/{id}/like")
    public Result funLike(@PathVariable String id,@RequestBody FunGift funGift, HttpServletRequest request) {
//        Users user = (Users) request.getAttribute("user");
        funGift.setFunId(Long.parseLong(id));
//        funStar.setUserId(user.getId());
        funGift.setCreatedAt(new Date());
        funService.funGift(funGift);
        return Result.success(funGift);
    }

    @GetMapping(value = "/{id}/viewed")
    public Result funView(@PathVariable String id, HttpServletRequest request) {
//        Users user = (Users) request.getAttribute("user");
        FunView funView =new FunView();
        funView.setFunId(Long.parseLong(id));
//        funView.setUserId(user.getId());
        funService.funView(funView);
        return Result.success(funView);
    }

}
