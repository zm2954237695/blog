package com.guo.blog.controller;

import com.guo.blog.utils.QiniuUtils;
import com.guo.blog.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


@RestController
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private QiniuUtils qiniuUtils;
    @PostMapping
    public Result upload(@RequestParam("image")MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        //substringAfterLast 获得.后面的所有字符 也就是图片后缀类型
        String filename = UUID.randomUUID().toString()+"."+ StringUtils.substringAfterLast(originalFilename,".");
        boolean upload = qiniuUtils.upload(file,filename);
        if ( upload ) {
            return Result.success(QiniuUtils.url+filename);
        }

        return Result.fail(20001,"上传失败");



    }
}
