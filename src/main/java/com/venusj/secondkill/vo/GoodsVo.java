package com.venusj.secondkill.vo;

import com.venusj.secondkill.entity.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author zhangjh
 * @date 2020/8/26
 * @desc
 */
@Data
@ToString
public class GoodsVo  extends Goods {
    private Double miaoshaPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
