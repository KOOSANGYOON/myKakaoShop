package com.shopping.myKakaoShop.support;

import com.shopping.myKakaoShop.domain.User;
import com.shopping.myKakaoShop.exception.UnAutorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserRoleInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(UserRoleInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = HttpSessionUtils.getUserFromSession(request.getSession());
        log.debug("User Data : {}", user);
        if (user.isGuestUser()) {
            log.debug("User is Guest");
            throw new UnAutorizedException("로그인이 필요합니다.");
        }

        return true;
    }
}
