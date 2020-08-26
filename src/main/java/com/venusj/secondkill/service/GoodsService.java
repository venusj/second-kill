package com.venusj.secondkill.service;

import com.venusj.secondkill.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.venusj.secondkill.entity.MiaoshaUser;
import com.venusj.secondkill.entity.OrderInfo;
import com.venusj.secondkill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author venusj
 * @since 2020-08-26
 */
public interface GoodsService extends IService<Goods> {

    List<GoodsVo> listGoodsVo();

    GoodsVo getGoodsVoById(Long goodsId);

    void reduceStock(GoodsVo goods);
}
