package com.guo.blog.service;

import com.guo.blog.vo.CategoryVo;
import com.guo.blog.vo.Result;

public interface CategoryService {
    CategoryVo findCategoryById(Long categoryId);

    Result findAll();

    Result findAllDetail();

    Result categoryDetailById(Long id);
}
