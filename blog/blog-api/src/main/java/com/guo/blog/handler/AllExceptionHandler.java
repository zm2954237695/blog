package com.guo.blog.handler;

import com.guo.blog.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//对加了@Controller注解的方法进行拦截处理 Aop的实现
@RestControllerAdvice
public class AllExceptionHandler {
    //进行异常处理,处理Exception.class的异常
    @ExceptionHandler(Exception.class)
    //返回json数据
    //@ResponseBody
    public Result doException(Exception ex){
         ex.printStackTrace();
        return Result.fail(-999,"系统异常");
    }

}
