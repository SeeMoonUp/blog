package com.seemoonup.blog.web.controller;

import com.seemoonup.model.entity.Blog;
import com.seemoonup.model.entity.page.PageInfo;
import com.seemoonup.service.IBlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("blog")
public class BlogController {
    @Resource
    IBlogService blogService;

    @RequestMapping("index")
    public ModelAndView index(ModelMap modelMap) {

        PageInfo pageInfo = new PageInfo();
        List<Blog> blogList = blogService.listBlog(pageInfo);
        modelMap.put("blogList", blogList);

        return new ModelAndView("blog/index");
    }

    @RequestMapping("detail")
    public ModelAndView detail(int blogId, ModelMap modelMap) {
        Blog blog = blogService.getBlog(blogId);
        modelMap.put("blog", blog);
        return new ModelAndView("blog/detail");
    }

    @RequestMapping("about")
    public ModelAndView about(ModelMap modelMap) {
        return new ModelAndView("blog/about");
    }

    @RequestMapping("contact")
    public ModelAndView contact(ModelMap modelMap) {
        return new ModelAndView("blog/contact");
    }
}
