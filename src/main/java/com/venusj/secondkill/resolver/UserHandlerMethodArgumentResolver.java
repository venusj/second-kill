package com.venusj.secondkill.resolver;

import com.venusj.secondkill.entity.MiaoshaUser;
import com.venusj.secondkill.service.MiaoshaUserService;
import com.venusj.secondkill.service.impl.MiaoshaUserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Parameter;

/**
 * @author zhangjh
 * @date 2020/8/26
 * @desc
 */
@Component
public class UserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private MiaoshaUserService miaoshaUserService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> clazz = methodParameter.getParameterType();
        return clazz == MiaoshaUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        String tokenParam = request.getParameter(MiaoshaUserServiceImpl.TOKEN_NAME);
        String cookieValue = getCookieValue(request, MiaoshaUserServiceImpl.TOKEN_NAME);
        if(StringUtils.isEmpty(tokenParam) && StringUtils.isEmpty(cookieValue))
        {
            return "login";
        }
        String token = StringUtils.isEmpty(tokenParam) ? cookieValue : tokenParam;
        return miaoshaUserService.getUserByToken(response,token);
    }

    private String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies)
        {
            if(cookie.getName().equals(cookieName))
            {
                return cookie.getValue();
            }
        }
        return null;
    }
}
