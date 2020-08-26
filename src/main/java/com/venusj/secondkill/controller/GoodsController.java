package com.venusj.secondkill.controller;

import com.venusj.secondkill.entity.MiaoshaUser;
import com.venusj.secondkill.service.MiaoshaUserService;
import com.venusj.secondkill.service.impl.MiaoshaUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangjh
 * @date 2020/8/25
 * @desc 商品管理
 */
@Controller
@RequestMapping("/goods")
@Slf4j
public class GoodsController {

    @Autowired
    private MiaoshaUserService miaoshaUserService;

    @RequestMapping("/to_list")
    public String list(Model model, MiaoshaUser miaoshaUser) {
        model.addAttribute("user", miaoshaUser);
        return "goods_list";
    }

    @RequestMapping("/to_detail")
    public String detail(Model model, MiaoshaUser miaoshaUser) {
        model.addAttribute("user", miaoshaUser);
        return "goods_list";
    }

}
