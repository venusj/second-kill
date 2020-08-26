package com.venusj.secondkill.service.impl;

import com.venusj.secondkill.entity.Goods;
import com.venusj.secondkill.mapper.GoodsMapper;
import com.venusj.secondkill.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.venusj.secondkill.service.MiaoshaGoodsService;
import com.venusj.secondkill.vo.GoodsVo;
import net.bytebuddy.asm.Advice;
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
}
