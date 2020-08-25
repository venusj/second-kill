package com.venusj.secondkill.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhangjh
 * @date 2020/8/20
 * @desc 通用API接口返回
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "通用PI接口返回", description = "Common Api Response")
public class ApiResponse<T> implements Serializable {
    private static final long serialVersionUID = -8987146499044811208L;
    /**
     * 通用返回状态
     */
    @ApiModelProperty(value = "通用返回状态", required = true)
    private Integer code;
    /**
     * 通用返回信息
     */
    @ApiModelProperty(value = "通用返回信息", required = true)
    private String message;
    /**
     * 通用返回数据
     */
    @ApiModelProperty(value = "通用返回数据", required = true)
    private T data;

    public static ApiResponse success(Object data) {
        ApiResponse result = new ApiResponse<>();
        result.setCode(0);
        result.setMessage("成功");
        result.setData(data);
        return result;
    }

    public static ApiResponse success(CodeMsg codeMsg, Object data) {
        ApiResponse result = new ApiResponse<>();
        result.setCode(codeMsg.getCode());
        result.setMessage(codeMsg.getMessage());
        result.setData(data);
        return result;
    }

    public static ApiResponse error(CodeMsg codeMsg) {
        ApiResponse result = new ApiResponse<>();
        result.setCode(codeMsg.getCode());
        result.setMessage(codeMsg.getMessage());
        return result;
    }

    public static ApiResponse error() {
        ApiResponse result = new ApiResponse<>();
        result.setCode(-1);
        result.setMessage("错误");
        return result;
    }

    public static ApiResponse error(Object data) {
        ApiResponse result = new ApiResponse<>();
        result.setCode(-1);
        result.setMessage("错误");
        result.setData(data);
        return result;
    }
}
