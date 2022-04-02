package com.guo.blog.controller;

import com.guo.blog.service.CommentsService;
import com.guo.blog.vo.Result;
import com.guo.blog.vo.param.CommentParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.soap.Addressing;
//评论列表
@RestController
@RequestMapping("comments")
public class CommentController {
    @Autowired
    private CommentsService commentsService;

   @GetMapping("article/{id}")
    public Result comments(@PathVariable("id")Long id){
        return commentsService.commentsByArticleId(id);
    }

    @PostMapping("/create/change")
    public Result Comment(@RequestBody CommentParam commentParam){
       return commentsService.comment(commentParam);
    }
}
