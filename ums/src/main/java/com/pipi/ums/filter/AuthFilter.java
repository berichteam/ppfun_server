package com.pipi.ums.filter;

import com.auth0.jwt.interfaces.Claim;
import com.pipi.common.domain.Users;
import com.pipi.common.service.inter.UserService;
import com.pipi.ums.utils.JwtTokenUtils;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author lazyb
 * @create 2019/6/11
 * @desc
 **/
@Order(1)
@WebFilter(filterName = "authFilter", urlPatterns = {"/fun/*", "/upload/*"})
@CommonsLog
public class AuthFilter implements Filter {

    @Autowired
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("AuthFilter doFilter start.");
        boolean flag = false;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getHeader("AUTH-TOKEN");
        log.info("token: " + token);
        if (token == null) {
            response.sendRedirect("/auth_error");
            return;
        }
        try {
            Map<String, Claim> map = JwtTokenUtils.vertifyToken(token);
            String name = map.get("username").asString();
            Users user = userService.findByName(name);
            if (user != null) {
                servletRequest.setAttribute("user", user);
                flag = true;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (flag) {
            log.info("auth success");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect("/auth_error");
            return;
        }
    }

    @Override
    public void destroy() {

    }
}
