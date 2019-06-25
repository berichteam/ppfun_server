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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author lazyb
 * @create 2019/6/11
 * @desc
 **/
@Order(1)
@WebFilter(filterName = "authFilter", urlPatterns = {"/article/*", "/upload/*", "/user/social/*", "/membership/*"})
@CommonsLog
public class AuthFilter implements Filter {

    @Autowired
    private UserService userService;

    /* 这里设置不被拦截的请求路径 */
    private static final List<String> unFilterUrlList = Arrays.asList("/article");

    /* 判断请求路径是否为不拦截的请求路径 */
    private boolean isFilter(String url) {
        for (String s : unFilterUrlList) {
            if (url.indexOf(s) > -1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("AuthFilter doFilter start.");
        boolean flag = false;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.debug("requestURL======================================" + request.getRequestURL().toString());
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //排除不需要过滤的
        if (isFilter(request.getRequestURL().toString()) && request.getMethod().equals("GET")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String token = request.getHeader("X-AUTH-TOKEN");
        log.info("token: " + token);
        if (token == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "no token");
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
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "auth error");
            return;
        }
    }

    @Override
    public void destroy() {

    }
}
