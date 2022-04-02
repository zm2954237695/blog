package com.guo.blog.controller;

import com.guo.blog.common.aop.LogAnnotation;
import com.guo.blog.common.cache.Cache;
import com.guo.blog.service.ArticleService;
import com.guo.blog.vo.Result;
import com.guo.blog.vo.param.ArticleParam;
import com.guo.blog.vo.param.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/articles")
public class ArticleController {

     /*
        首页 文章列表
        @Param pageParams
        @return
      */
    @Resource
    ArticleService articleService;
    @PostMapping
    @LogAnnotation(module = "文章",operator = "获取文章列表")
    public Result listArticle(@RequestBody PageParams pageParams){
          return articleService.listArticle(pageParams);
    }

    @PostMapping("hot")
    @Cache(expire = 5*60*1000,name="hot_article")
    public Result hotArticle(){
        int limit = 5;
        return articleService.hotArticle(limit);
    }
    @PostMapping("new")
    @Cache(expire = 5*60*1000,name="new_article")
    public Result newArticles(){
        int limit = 5;
        return articleService.newArticle(limit);
    }

    @PostMapping("listArchives")
    public Result listArchives(){
        return articleService.listArchives();
    }

    //查看文章详情》
    @PostMapping  ("view/{id}")
    public Result findArticleById(@PathVariable("id") Long articleId){
        return articleService.findArticleById(articleId);
    }

    @PostMapping("publish")
    public Result publish(@RequestBody ArticleParam articleParam) {
        return articleService.publish(articleParam);
    }
}
