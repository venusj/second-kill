package com.venusj.secondkill.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.venusj.secondkill.entity.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangjh
 * @date 2020/8/26
 * @desc
 */
@Data
@ToString
public class GoodsVo  extends Goods {
    private BigDecimal miaoshaPrice;
    private Integer stockCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private Date endDate;
}
