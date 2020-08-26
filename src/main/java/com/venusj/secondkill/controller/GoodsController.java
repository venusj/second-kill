package com.venusj.secondkill.controller;

import com.venusj.secondkill.entity.MiaoshaUser;
import com.venusj.secondkill.service.GoodsService;
import com.venusj.secondkill.service.MiaoshaGoodsService;
import com.venusj.secondkill.service.MiaoshaUserService;
import com.venusj.secondkill.service.impl.MiaoshaUserServiceImpl;
import com.venusj.secondkill.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
    private GoodsService goodsService;


    @RequestMapping("/to_list")
    public String list(Model model, MiaoshaUser miaoshaUser) {
        model.addAttribute("user", miaoshaUser);
        //查询商品列表
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }

    @RequestMapping("/to_detail/{goodsId}")
    public String detail(Model model, MiaoshaUser miaoshaUser, @PathVariable("goodsId") Long goodsId) {
        model.addAttribute("user", miaoshaUser);
        GoodsVo goodsVo = goodsService.getGoodsVoById(goodsId);
        model.addAttribute("goods", goodsVo);
        long endAt = goodsVo.getEndDate().getTime();
        long startAt = goodsVo.getStartDate().getTime();
        long now = System.currentTimeMillis();
        int miaoshaStatus = 0;
        int remainSeconds = 0;
        if(now < startAt ) {//秒杀还没开始，倒计时
            miaoshaStatus = 0;
            remainSeconds = (int)((startAt - now )/1000);
        }else  if(now > endAt){//秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        }else {//秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }

}
