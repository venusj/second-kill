package com.venusj.secondkill.service;

import com.venusj.secondkill.entity.MiaoshaOrder;
import com.venusj.secondkill.entity.MiaoshaUser;
import com.venusj.secondkill.entity.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.venusj.secondkill.vo.GoodsVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author venusj
 * @since 2020-08-26
 */
public interface OrderInfoService extends IService<OrderInfo> {

    MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(Long id, long goodsId);

    OrderInfo createOrder(MiaoshaUser user, GoodsVo goods);
}
