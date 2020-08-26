package com.venusj.secondkill.service.impl;

import cn.hutool.core.lang.UUID;
import com.venusj.secondkill.common.ApiResponse;
import com.venusj.secondkill.common.CodeMsg;
import com.venusj.secondkill.entity.MiaoshaUser;
import com.venusj.secondkill.mapper.MiaoshaUserMapper;
import com.venusj.secondkill.redis.MiaoshaUserKey;
import com.venusj.secondkill.service.MiaoshaUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.venusj.secondkill.utils.MD5Utils;
import com.venusj.secondkill.utils.RedisUtils;
import com.venusj.secondkill.utils.ValidatorUtil;
import com.venusj.secondkill.vo.LoginVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author venusj
 * @since 2020-08-25
 */
@Service
@Slf4j
public class MiaoshaUserServiceImpl extends ServiceImpl<MiaoshaUserMapper, MiaoshaUser> implements MiaoshaUserService {

    public final static String TOKEN_NAME = "token";

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public ApiResponse login(HttpServletResponse response, LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        /*if(StringUtils.isEmpty(mobile))
        {
            return ApiResponse.error(CodeMsg.MOBILE_EMPTY);
        }
        if(StringUtils.isEmpty(password))
        {
            return ApiResponse.error(CodeMsg.PASSWORD_EMPTY);
        }
        if(!ValidatorUtil.isMobile(mobile))
        {
            return ApiResponse.error(CodeMsg.MOBILE_ERROR);
        }*/
        MiaoshaUser miaoshaUser = this.getById(mobile);
        String saltDB = miaoshaUser.getSalt();
        // 验证密码
        String realPass = MD5Utils.toDbBymd5(password, saltDB);
        log.info("加密之后的密码：{}" , realPass);
        if(!realPass.equals(miaoshaUser.getPassword()))
        {
            return ApiResponse.error(CodeMsg.PASSWORD_ERROR);
        }

        String token = UUID.fastUUID().toString();
        addCookie(response,token, miaoshaUser);
        return ApiResponse.success(true);
    }

    @Override
    public MiaoshaUser getUserByToken(HttpServletResponse response,String token) {
        Object o = redisUtils.get(MiaoshaUserKey.token.getPrefix() + token);
        if(!(o instanceof MiaoshaUser))
        {
           return null;
        }
        // 延长用户有效时间
        MiaoshaUser miaoshaUser = (MiaoshaUser) o;
        addCookie(response, token, miaoshaUser);
        return miaoshaUser;
    }

    private void addCookie(HttpServletResponse response, String token, MiaoshaUser miaoshaUser)
    {
        redisUtils.setEx(MiaoshaUserKey.token.getPrefix() + token, miaoshaUser, Long.valueOf(MiaoshaUserKey.token.getExpireSeconds()));
        Cookie cookie = new Cookie(TOKEN_NAME, token);
        cookie.setMaxAge(MiaoshaUserKey.token.getExpireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
