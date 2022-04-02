package com.guo.blog.service;

import com.guo.blog.dao.pojo.SysUser;
import com.guo.blog.vo.Result;
import com.guo.blog.vo.UserVo;

public interface SysUserService {

    SysUser findUserById(Long id);

    SysUser findUser(String account, String password);

    Result findUserByToken(String token);

    SysUser findUserByAccount(String account);

    void save(SysUser sysUser);

    UserVo findUserVoById(Long id);
}
