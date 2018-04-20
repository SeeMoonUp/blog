package com.seemoonup.service.impl;

import com.seemoonup.dao.impl.BlogDao;
import com.seemoonup.model.entity.Blog;
import com.seemoonup.model.entity.page.PageInfo;
import com.seemoonup.service.IBlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lemon(lemon @ laowantong.cc)
 * @date 2018-04-07
 * @desc
 */

@Service
public class BlogService implements IBlogService{

    @Resource(name = "blogDao")
    private BlogDao blogDao;

    @Override
    public int saveBlog(Blog blog) {
        return blogDao.saveBlog(blog);
    }

    @Override
    public Blog getBlog(int blogId) {
        return blogDao.getBlog(blogId);
    }

    @Override
    public List<Blog> listBlog(PageInfo pageInfo) {
        return blogDao.listBlog(pageInfo);
    }
}
