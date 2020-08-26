package com.venusj.secondkill.service;

import com.venusj.secondkill.entity.MiaoshaGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.venusj.secondkill.entity.MiaoshaUser;
import com.venusj.secondkill.entity.OrderInfo;
import com.venusj.secondkill.vo.GoodsVo;

/**
 * <p>
 * 商品秒杀表 服务类
 * </p>
 *
 * @author venusj
 * @since 2020-08-26
 */
public interface MiaoshaGoodsService extends IService<MiaoshaGoods> {

    OrderInfo miaosha(MiaoshaUser user, GoodsVo goods);
}
