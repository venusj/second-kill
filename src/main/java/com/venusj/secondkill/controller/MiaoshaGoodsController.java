package com.venusj.secondkill.controller;


import com.venusj.secondkill.common.CodeMsg;
import com.venusj.secondkill.entity.MiaoshaOrder;
import com.venusj.secondkill.entity.MiaoshaUser;
import com.venusj.secondkill.entity.OrderInfo;
import com.venusj.secondkill.service.GoodsService;
import com.venusj.secondkill.service.MiaoshaGoodsService;
import com.venusj.secondkill.service.OrderInfoService;
import com.venusj.secondkill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品秒杀表 前端控制器
 * </p>
 *
 * @author venusj
 * @since 2020-08-26
 */
@Controller
@RequestMapping("/miaosha")
public class MiaoshaGoodsController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private MiaoshaGoodsService miaoshaGoodsService;

    @RequestMapping("do_miaosha")
    public String doMiaoSha(Model model, MiaoshaUser user,
                            @RequestParam("goodsId")long goodsId) {
        model.addAttribute("user", user);
        if(user == null) {
            return "login";
        }
        //判断库存
        GoodsVo goods = goodsService.getGoodsVoById(goodsId);
        int stock = goods.getStockCount();
        if(stock <= 0) {
            model.addAttribute("errmsg", CodeMsg.MIAO_SHA_OVER.getMessage());
            return "miaosha_fail";
        }
        //判断是否已经秒杀到了
        MiaoshaOrder order = orderInfoService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if(order != null) {
            model.addAttribute("errmsg", CodeMsg.REPEATE_MIAOSHA.getMessage());
            return "miaosha_fail";
        }
        //减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = miaoshaGoodsService.miaosha(user, goods);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goods);
        return "order_detail";
    }
}

