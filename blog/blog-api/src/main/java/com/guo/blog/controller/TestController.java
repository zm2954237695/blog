package com.guo.blog.controller;


import com.guo.blog.dao.pojo.SysUser;
import com.guo.blog.utils.UserThreadLocal;
import com.guo.blog.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping
    public Result test(){
        SysUser sysUser = UserThreadLocal.get();
        System.out.println(sysUser);
        return Result.success(null);
    }
}