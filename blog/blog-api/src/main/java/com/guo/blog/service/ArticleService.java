package com.guo.blog.service;

import com.guo.blog.vo.Result;
import com.guo.blog.vo.param.ArticleParam;
import com.guo.blog.vo.param.PageParams;
import org.springframework.stereotype.Service;

@Service
public interface ArticleService {

    Result listArticle(PageParams pageParams);
    Result hotArticle(int limit);

    Result newArticle(int limit);
    //文章归档
    Result listArchives();

    Result findArticleById(Long articleId);

    Result publish(ArticleParam articleParam);
}
