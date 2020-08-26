package com.venusj.secondkill.service.impl;

import com.venusj.secondkill.entity.MiaoshaOrder;
import com.venusj.secondkill.entity.MiaoshaUser;
import com.venusj.secondkill.entity.OrderInfo;
import com.venusj.secondkill.mapper.MiaoshaOrderMapper;
import com.venusj.secondkill.mapper.OrderInfoMapper;
import com.venusj.secondkill.service.GoodsService;
import com.venusj.secondkill.service.OrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.venusj.secondkill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author venusj
 * @since 2020-08-26
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private MiaoshaOrderMapper miaoshaOrderMapper;

    @Override
    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(Long userId, long goodsId) {
        return orderInfoMapper.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
    }

    @Override
    public OrderInfo createOrder(MiaoshaUser user, GoodsVo goods) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        orderInfoMapper.insert(orderInfo);
        long orderId = orderInfo.getId();
        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setGoodsId(goods.getId());
        miaoshaOrder.setOrderId(orderId);
        miaoshaOrder.setUserId(user.getId());
        miaoshaOrderMapper.insert(miaoshaOrder);
//        orderInfoMapper.insertMiaoshaOrder(miaoshaOrder);
        return orderInfo;
    }
}
