package com.seemoonup.blog.web.controller;

import com.seemoonup.model.entity.Blog;
import com.seemoonup.model.entity.page.PageInfo;
import com.seemoonup.service.IBlogService;
import org.apache.commons.lang.math.NumberUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("mgr")
public class IndexController {
    @Resource
    IBlogService blogService;

    @RequestMapping("sql")
    public ModelAndView sql() {
        return new ModelAndView("admin/sql");
    }

    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("admin/index");
    }

    @RequestMapping("bloglist")
    public ModelAndView blogList(ModelMap modelMap) {
        PageInfo pageInfo = new PageInfo();
        List<Blog> blogList = blogService.listBlog(pageInfo);
        modelMap.put("blogList", blogList);
        return new ModelAndView("admin/blogList");
    }

    @RequestMapping("blogdetail")
    public ModelAndView blogDetail(HttpServletRequest request, ModelMap modelMap) {
        int blogId = NumberUtils.toInt(request.getParameter("blogId"));
        if (blogId > 0) {
            Blog blog = blogService.getBlog(blogId);
            modelMap.put("blog", blog);
        }
        return new ModelAndView("admin/blogDetail");
    }

    @RequestMapping("marked")
    public ModelAndView marked() {
        return new ModelAndView("admin/marked");
    }

    @RequestMapping("addarticle")
    public ModelAndView addArticle() {
        Blog blog = new Blog();
        blog.setCreateTime(new Date());
        blog.setHtmlContent("htmlContent");
        blog.setMarkdownContent("markdownContent");
        blog.setTitle("title");
        int res = blogService.saveBlog(blog);
        System.out.println("res:" + res);
        return new ModelAndView("admin/marked");
    }

    @RequestMapping("savearticle")
    @ResponseBody
    public Map<String, Object> saveArticle(Blog blog) {
        Map<String, Object> result = new HashMap<String, Object>();
        blog.setCreateTime(DateTime.now().toDate());
        int res = blogService.saveBlog(blog);
        System.out.println("res:" + res);
        if (res == 1) {
            result.put("success", true);
            return result;
        } else {
            result.put("success", false);
            result.put("message", "程序出现问题");
            return result;
        }
    }
}
