package com.guo.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.guo.blog.dao.mapper.CommentMapper;
import com.guo.blog.dao.pojo.Comment;
import com.guo.blog.dao.pojo.SysUser;
import com.guo.blog.service.CommentsService;
import com.guo.blog.service.SysUserService;
import com.guo.blog.utils.UserThreadLocal;
import com.guo.blog.vo.CommentVo;
import com.guo.blog.vo.Result;
import com.guo.blog.vo.UserVo;
import com.guo.blog.vo.param.CommentParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private SysUserService sysUserService;
    @Override
    public Result commentsByArticleId(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId,id);
        queryWrapper.eq(Comment::getLevel,1);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        List<CommentVo> commentVoList = copyList(comments);
        return Result.success(commentVoList);
    }

    @Override
    public Result comment(CommentParam commentParam) {
        SysUser sysUser = UserThreadLocal.get();
        Comment comment = new Comment();
        comment.setArticleId(commentParam.getArticleId());
        comment.setAuthorId(sysUser.getId());
        comment.setContent(commentParam.getContent());
        comment.setCreateDate(System.currentTimeMillis());
        Long parent = commentParam.getParent();
        if ( parent == null || parent == 0 ) {
            comment.setLevel(1);
        } else {
            comment.setLevel(2);
        }
        comment.setParentId( parent == null ? 0 : parent );
        Long toUserId =  commentParam.getToUserId();
        comment.setToUid(toUserId == null ? 0 :toUserId);
        commentMapper.insert(comment);
        return Result.success(null);

    }

    private List<CommentVo> copyList(List<Comment> comments) {
        List<CommentVo>commentVoList = new ArrayList<>();
        for (Comment comment : comments) {
            commentVoList.add(copy(comment));
        }
        return commentVoList;
    }
    private CommentVo copy(Comment comment){
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment,commentVo);
        Long authorId = comment.getAuthorId();
        UserVo userVo = sysUserService.findUserVoById(authorId);
        commentVo.setAuthor(userVo);
        //子评论

        Integer level  = comment.getLevel();
        if ( 1 == level ) {
          Long id = comment.getId();
          List<CommentVo> commentVoList = findCommentsByParentId(id);
          commentVo.setChildrens(commentVoList);
        }
        if ( level > 1 ){
           Long toUid = comment.getToUid();
           UserVo toUserVo = sysUserService.findUserVoById(toUid);
           commentVo.setToUser(toUserVo);
        }
        return commentVo;
    }

    private List<CommentVo> findCommentsByParentId(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getId,id);
        queryWrapper.eq(Comment::getLevel,2);
        return copyList(commentMapper.selectList(queryWrapper));
    }
}
