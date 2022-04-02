package com.guo.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guo.blog.dao.pojo.ArticleTag;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleTapMapper extends BaseMapper<ArticleTag> {
}
