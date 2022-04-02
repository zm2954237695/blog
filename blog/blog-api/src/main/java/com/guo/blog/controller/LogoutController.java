package com.guo.blog.controller;

import com.guo.blog.service.LoginService;
import com.guo.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("logout")
public class LogoutController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public Result logout(@RequestHeader("Authorization")String token){
        return loginService.logout(token);
    }
}
