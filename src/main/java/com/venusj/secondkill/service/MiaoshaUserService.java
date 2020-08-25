package com.venusj.secondkill.service;

import com.venusj.secondkill.common.ApiResponse;
import com.venusj.secondkill.entity.MiaoshaUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.venusj.secondkill.vo.LoginVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author venusj
 * @since 2020-08-25
 */
public interface MiaoshaUserService extends IService<MiaoshaUser> {

    ApiResponse login(LoginVo loginVo);
}
