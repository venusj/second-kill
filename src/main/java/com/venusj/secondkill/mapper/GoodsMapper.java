package com.venusj.secondkill.mapper;

import com.venusj.secondkill.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.venusj.secondkill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author venusj
 * @since 2020-08-26
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    List<GoodsVo> listGoodsVo();
}
