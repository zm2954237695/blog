package com.guo.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.guo.blog.dao.mapper.ArticleMapper;
import com.guo.blog.dao.pojo.Article;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ThreadService {

    //期望此操作在线程池执行， 不会影响原有的主线程
    @Async("taskExecutor")
    public void updateArticleViewCount(ArticleMapper articleMapper, Article article){
           int viewCounts = article.getViewCounts();
           Article articleUpdate = new Article();
           articleUpdate.setViewCounts(viewCounts+1);
           LambdaQueryWrapper<Article> updateWrapper = new LambdaQueryWrapper<>();
           updateWrapper.eq(Article::getId,article.getId());
           updateWrapper.eq(Article::getViewCounts,viewCounts);
           articleMapper.update(articleUpdate,updateWrapper);
    }
}
