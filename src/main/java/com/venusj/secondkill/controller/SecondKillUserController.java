package com.venusj.secondkill.controller;


import com.venusj.secondkill.entity.SecondKillUser;
import com.venusj.secondkill.service.SecondKillUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author venusj
 * @since 2020-08-20
 */
@RestController
@RequestMapping("/user")
public class SecondKillUserController {

    @Autowired
    private SecondKillUserService secondKillUserService;


    @GetMapping("/get/{id}")
    public SecondKillUser get(@PathVariable(value = "id") Integer id)
    {
        SecondKillUser byId = secondKillUserService.getById(id);
        return byId;
    }

}

