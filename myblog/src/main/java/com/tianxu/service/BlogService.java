package com.tianxu.service;

import com.tianxu.pojo.Blog;
import com.tianxu.vo.BlogQuery;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {

    Blog getBlog(Long id);
    List<Blog> listBlogByTypeAndTags(BlogQuery blogQuery);
    List<Blog> listBlog();
    List<Blog> listBlogByContent(String query);

//    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);
//    Page<Blog> listBlog(Pageable pageable);
//    Page<Blog> listBlog(Pageable pageable,String query);

    int saveBlog(Blog blog);

    int updateBlog(Blog bog);

    void deleteBlog(Long id);

//    List<Blog> listRecommendBlogTop(Integer size);

    List<Blog> listBlogByType(Long type_id);
    List<Blog> listBlogByTag(Long tag_id);
    int updateView(Long id, int views);
}
