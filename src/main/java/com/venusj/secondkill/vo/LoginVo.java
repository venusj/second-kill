package com.venusj.secondkill.vo;

import com.venusj.secondkill.validator.IsMobile;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author zhangjh
 * @date 2020/8/25
 * @desc
 */
@Data
@ToString
public class LoginVo {
    @NotNull
    @IsMobile
    private String mobile;
    @NotNull
    @Length(min=32)
    private String password;

}
