package com.guo.blog.controller;

import com.guo.blog.service.LoginService;
import com.guo.blog.vo.Result;
import com.guo.blog.vo.param.LoginParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @PostMapping
    public Result login(@RequestBody  LoginParams loginParams){
        return loginService.login(loginParams);
    }


}
