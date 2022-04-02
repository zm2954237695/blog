package com.guo.blog.service;

import com.guo.blog.vo.Result;
import com.guo.blog.vo.param.CommentParam;

public interface CommentsService {

    Result commentsByArticleId(Long id);

    Result comment(CommentParam commentParam);
}
