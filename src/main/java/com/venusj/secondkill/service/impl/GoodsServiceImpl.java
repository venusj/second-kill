package com.venusj.secondkill.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.venusj.secondkill.entity.Goods;
import com.venusj.secondkill.entity.MiaoshaGoods;
import com.venusj.secondkill.entity.MiaoshaUser;
import com.venusj.secondkill.entity.OrderInfo;
import com.venusj.secondkill.mapper.GoodsMapper;
import com.venusj.secondkill.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.venusj.secondkill.service.MiaoshaGoodsService;
import com.venusj.secondkill.vo.GoodsVo;
import net.bytebuddy.asm.Advice;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author venusj
 * @since 2020-08-26
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private MiaoshaGoodsService miaoshaGoodsService;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<GoodsVo> listGoodsVo() {
        return goodsMapper.listGoodsVo();
    }

    @Override
    public GoodsVo getGoodsVoById(Long goodsId) {
        GoodsVo goodsVo = goodsMapper.getGoodsVoById(goodsId);
        return goodsVo;
    }

    @Override
    public void reduceStock(GoodsVo goods) {
        miaoshaGoodsService.update(new UpdateWrapper<MiaoshaGoods>().setSql("stock_count = stock_count - 1")
                .eq("goods_id", goods.getId()));
    }
}
