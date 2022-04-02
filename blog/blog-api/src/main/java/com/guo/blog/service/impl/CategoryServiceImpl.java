package com.guo.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.guo.blog.dao.mapper.CategoryMapper;
import com.guo.blog.dao.pojo.Category;
import com.guo.blog.service.CategoryService;
import com.guo.blog.vo.CategoryVo;
import com.guo.blog.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public CategoryVo findCategoryById(Long categoryId) {
        Category category = categoryMapper.selectById(categoryId);
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category,categoryVo);
        return categoryVo;
    }

    @Override
    public Result findAll() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Category::getId,Category::getCategoryName);
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        return Result.success(copyList(categories));

    }

    @Override
    public Result categoryDetailById(Long id) {
        Category category  = categoryMapper.selectById(id);
        return Result.success(copy(category));
    }

    @Override
    public Result findAllDetail() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        return Result.success(copyList(categories));
    }

    private CategoryVo copy(Category category){
         CategoryVo categoryVo = new CategoryVo();
         BeanUtils.copyProperties(category,categoryVo);
         return categoryVo;
    }
    private List<CategoryVo> copyList(List<Category> categories) {
        List<CategoryVo> categoryVoList = new ArrayList<>();
        for(Category category:categories){
            categoryVoList.add(copy(category));
        }
        return categoryVoList;
    }
}
