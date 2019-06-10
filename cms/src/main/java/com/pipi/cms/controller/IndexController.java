package com.pipi.cms.controller;

import com.pipi.common.domain.Admin;
import com.pipi.common.service.inter.AdminService;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author lazyb
 * @create 2019/5/30
 * @desc
 **/
@Controller
@CommonsLog
public class IndexController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/index");
        view.addObject("now", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
        return view;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            return "redirect:/index";
        }
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam("name") String name, @RequestParam("password") String password) {
        log.info("register: name:" + name + ",password:" + password);
        Admin admin = adminService.register(name, password);
        if (admin == null) {
            return "redirect:/register";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Admin admin, RedirectAttributes redirectAttributes) {
        UsernamePasswordToken token = new UsernamePasswordToken(admin.getName(), admin.getPassword());
        Subject currentUser = SecurityUtils.getSubject();
        try {
            log.info("用户{}进行登录验证" + admin.getName());
            currentUser.login(token);
            log.info("用户{}验证通过" + admin.getName());
        } catch (AccountException e) {
            log.info("用户{}验证失败" + admin.getName());
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        if (currentUser.isAuthenticated()) {
            Session session = currentUser.getSession();
            session.setAttribute("username", currentUser.getPrincipal().toString());
            session.setTimeout(360000);
            return "redirect:/index";
        } else {
            token.clear();
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(RedirectAttributes redirectAttributes) {
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        return "redirect:/login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole() {
        return "403";
    }

    @RequestMapping(value = "/index/users", method = RequestMethod.GET)
    public String users() {
        return "users";
    }

}