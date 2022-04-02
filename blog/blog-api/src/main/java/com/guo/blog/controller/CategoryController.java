package com.guo.blog.controller;

import com.guo.blog.service.CategoryService;
import com.guo.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categorys")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    Result findAll(){
        return categoryService.findAll();
    }
    @GetMapping("detail")
    public Result categoriesDetail(){
        return categoryService.findAllDetail();
    }
    @GetMapping("detail/{id}")
    public Result categoryDetailById(@PathVariable("id")Long id){
        return categoryService.categoryDetailById(id);
    }

}
