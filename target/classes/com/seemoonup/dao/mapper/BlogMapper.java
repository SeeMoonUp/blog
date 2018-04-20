package com.seemoonup.dao.mapper;

import com.seemoonup.common.datasource.DataSourceSelector;
import com.seemoonup.model.entity.Blog;
import com.seemoonup.model.entity.page.PageInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogMapper {
    @DataSourceSelector("master")
    int saveBlog(Blog blog);

    @DataSourceSelector("slave")
    Blog getBlog(int blogId);

    @DataSourceSelector("slave")
    List<Blog> listBlog(PageInfo pageInfo);
}
