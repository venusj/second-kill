package com.venusj.secondkill.service.impl;

import com.venusj.secondkill.entity.MiaoshaGoods;
import com.venusj.secondkill.entity.MiaoshaUser;
import com.venusj.secondkill.entity.OrderInfo;
import com.venusj.secondkill.mapper.MiaoshaGoodsMapper;
import com.venusj.secondkill.service.GoodsService;
import com.venusj.secondkill.service.MiaoshaGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.venusj.secondkill.service.OrderInfoService;
import com.venusj.secondkill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 商品秒杀表 服务实现类
 * </p>
 *
 * @author venusj
 * @since 2020-08-26
 */
@Service
public class MiaoshaGoodsServiceImpl extends ServiceImpl<MiaoshaGoodsMapper, MiaoshaGoods> implements MiaoshaGoodsService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderInfoService orderInfoService;

    @Override
    @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
        //减库存 下订单 写入秒杀订单
        goodsService.reduceStock(goods);
        //order_info maiosha_order
        return orderInfoService.createOrder(user, goods);
    }
}
