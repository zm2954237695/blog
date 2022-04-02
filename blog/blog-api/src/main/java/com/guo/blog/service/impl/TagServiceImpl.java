package com.guo.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.guo.blog.dao.mapper.TagMapper;
import com.guo.blog.dao.pojo.Tag;
import com.guo.blog.service.TagService;
import com.guo.blog.vo.Result;
import com.guo.blog.vo.TagVo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;
    @Override
    public List<TagVo> findTagsByArticleId(Long articleId) {
        //MybatisPlus无法多表查询
        List<Tag> tags = tagMapper.findTagsByArticleId(articleId);
        return copyList(tags);
    }

    @Override
    public Result hots(long limit) {
        /*
          1. 标签拥有的文章数量最多为最热标签
          2. 查询 根据tag_id 分组计数 ，从大到小取前limit个
         */
        List<Long> tagIds = tagMapper.findHotsTagIds(limit);
        if (CollectionUtils.isEmpty(tagIds)) {
            return Result.success(Collections.emptyList());
        }
        List<Tag> tags = tagMapper.findTagsByIds(tagIds);
        return Result.success(tags);
    }

    @Override
    public Result findAll() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Tag::getId,Tag::getTagName);
        List<Tag> tags = tagMapper.selectList(queryWrapper);
        return Result.success(copyList(tags));
    }

    @Override
    public Result findAllDetail() {
        List<Tag> tagList = tagMapper.selectList(new LambdaQueryWrapper<>());
        return Result.success(copyList(tagList));
    }

    @Override
    public Result findDetailById(Long id) {
        Tag tag = tagMapper.selectById(id);
        return Result.success(copy(tag));
    }

    private TagVo copy(Tag tag){
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag,tagVo);
        tagVo.setId(tag.getId());
        return tagVo;
    }
    private List<TagVo> copyList(List<Tag> tags) {
        List<TagVo> voList = new ArrayList<>();
        for(Tag tag:tags){
            voList.add(copy(tag));
        }
        return voList;
    }
}
