package com.seemoonup.dao.impl;

import com.seemoonup.dao.mapper.BlogMapper;
import com.seemoonup.model.entity.Blog;
import com.seemoonup.model.entity.page.PageInfo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lemon(lemon @ laowantong.cc)
 * @date 2018-04-07
 * @desc
 */

@Repository
public class BlogDao {
    @Resource
    BlogMapper blogMapper;

    public int saveBlog(Blog blog) {
        return blogMapper.saveBlog(blog);
    }

    public Blog getBlog(int blogId) {
        return blogMapper.getBlog(blogId);
    }

    public List<Blog> listBlog(PageInfo pageInfo) {
        return blogMapper.listBlog(pageInfo);
    }
}
