package com.guo.blog.service;

import com.guo.blog.dao.pojo.SysUser;
import com.guo.blog.vo.Result;
import com.guo.blog.vo.param.LoginParams;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface LoginService {

    Result login(LoginParams loginParams);

    SysUser checkToken(String token);

    Result register(LoginParams loginParams);

    Result logout(String token);
}
