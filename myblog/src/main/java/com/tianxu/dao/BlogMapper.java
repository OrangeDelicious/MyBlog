package com.tianxu.dao;

import com.tianxu.pojo.Blog;
import com.tianxu.vo.BlogQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlogMapper{

    Blog findById(Long id);

    List<Blog> findAll();//按更新时间顺序

    List<Blog> findAllByTypeAndTag(BlogQuery blogQuery);//按更新时间顺序

    List<Blog> findAllByContent(String text);

    int save(Blog blog);

    int updateBlog(Blog blog);
    int deleteById(Long id);

    int saveBlogtoTags(@Param("blog_id") Long blog_id, @Param("tag_id") Long tag_id);

    int deleteBlogToTags(Long blog_id);

    List<Blog> findAllByType(Long type_id);
    List<Blog> findAllByTag(Long tag_id);

    int updateView(@Param("id") Long id, @Param("views") int views);

}
