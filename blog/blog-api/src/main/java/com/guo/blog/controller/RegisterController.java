package com.guo.blog.controller;

import com.guo.blog.service.LoginService;
import com.guo.blog.vo.Result;
import com.guo.blog.vo.param.LoginParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private LoginService loginService;
    @PostMapping
    public Result register(@RequestBody LoginParams loginParams){

        return loginService.register(loginParams);

    }
}
