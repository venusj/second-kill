package com.venusj.secondkill.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *      秒杀用户实体类
 * </p>
 *
 * @author venusj
 * @since 2020-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("second_kill_user")
@ApiModel(value="SecondKillUser对象", description="")
public class SecondKillUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String nickname;

    private String password;

    private String salt;

    private String head;

    @TableField(value = "registerDate", fill = FieldFill.INSERT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date registerDate;

    @TableField(value = "lastLoginDate", fill = FieldFill.UPDATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastLoginDate;

    @TableField("loginCount")
    private Integer loginCount;

    @TableLogic
    private Integer deleted;


}
