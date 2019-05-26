package com.pipi.ums.controller;

import com.pipi.common.domain.Users;
import com.pipi.common.service.ModuleService;
import com.pipi.common.service.inter.UserService;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author lazyb
 * @create 2019/5/20
 * @desc
 **/
@RestController
@CommonsLog
public class IndexController {

    @Autowired
    private ModuleService moduleService;
    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public ModelAndView index() {
        String module = moduleService.umsModule();
        ModelAndView view = new ModelAndView("index");
        view.addObject("now", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
        view.addObject("module", module);
        return view;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerForm() {
        return new ModelAndView("register");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(Users user, RedirectAttributes redirectAttributes) {
        try {
            log.info("用户{}进行注册" + user.getPhone());
            userService.register(user.getPhone(), user.getPassword());
        } catch (Exception e) {
            log.info("用户{}注册失败" + user.getPhone());
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return new ModelAndView("redirect:/register");
        }
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginForm() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            return new ModelAndView("redirect:/index");
        }
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(Users user, RedirectAttributes redirectAttributes) {
        UsernamePasswordToken token = new UsernamePasswordToken(user.getPhone(), user.getPassword());
        Subject currentUser = SecurityUtils.getSubject();
        try {
            log.info("用户{}进行登录验证" + user.getPhone());
            currentUser.login(token);
            log.info("用户{}验证通过" + user.getPhone());
        } catch (AccountException e) {
            log.info("用户{}验证失败" + user.getPhone());
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        if (currentUser.isAuthenticated()) {
            return new ModelAndView("redirect:/index");
        } else {
            token.clear();
            return new ModelAndView("redirect:/login");
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(RedirectAttributes redirectAttributes) {
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping("/403")
    public ModelAndView unauthorizedRole() {
        return new ModelAndView("403");
    }

}
