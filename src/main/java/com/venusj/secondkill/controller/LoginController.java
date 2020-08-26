package com.venusj.secondkill.controller;

import com.venusj.secondkill.common.ApiResponse;
import com.venusj.secondkill.service.MiaoshaUserService;
import com.venusj.secondkill.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author zhangjh
 * @date 2020/8/25
 * @desc
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private MiaoshaUserService miaoshaUserService;

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @PostMapping("/do_login")
    @ResponseBody
    public ApiResponse doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        return miaoshaUserService.login(response, loginVo);
    }

}
