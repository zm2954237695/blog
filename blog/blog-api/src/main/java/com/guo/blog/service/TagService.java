package com.guo.blog.service;

import com.guo.blog.vo.Result;
import com.guo.blog.vo.TagVo;

import java.util.List;

public interface TagService {
    List<TagVo> findTagsByArticleId(Long articleId);

    Result hots(long limit);

    Result findAll();

    Result findAllDetail();

    Result findDetailById(Long id);
}
