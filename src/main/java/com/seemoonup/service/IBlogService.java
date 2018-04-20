package com.seemoonup.service;

import com.seemoonup.model.entity.Blog;
import com.seemoonup.model.entity.page.PageInfo;

import java.util.List;

public interface IBlogService {

    int saveBlog(Blog blog);

    Blog getBlog(int blogId);

    List<Blog> listBlog(PageInfo pageInfo);
}
