package com.venusj.secondkill.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author venusj
 * @since 2020-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MiaoshaUser对象", description="")
public class MiaoshaUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id 手机号码")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String nickname;

    @ApiModelProperty(value = "MD5(MD5（密码明文+salt）+ salt)")
    private String password;

    private String salt;

    @ApiModelProperty(value = "头像")
    private String head;

    @ApiModelProperty(value = "注册时间")
    @TableField("registerDate")
    private Date registerDate;

    @ApiModelProperty(value = "上次登录时间")
    @TableField("lastLoginDate")
    private Date lastLoginDate;

    @ApiModelProperty(value = "登录次数")
    @TableField("loginCount")
    private Integer loginCount;


}
