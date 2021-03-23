package com.tianxu.service;

import com.alibaba.fastjson.JSON;
import com.tianxu.dao.BlogMapper;
import com.tianxu.pojo.Blog;
import com.tianxu.pojo.Type;
import com.tianxu.vo.BlogQuery;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private JedisPool jedisPool;
    @Override
    public Blog getBlog(Long id) {
        return blogMapper.findById(id);
    }

    @Override
    public List<Blog> listBlogByTypeAndTags(BlogQuery blogQuery) {
        return blogMapper.findAllByTypeAndTag(blogQuery);
    }

    @Override
    public List<Blog> listBlog() {
        Jedis jedis = jedisPool.getResource();
        String blogs_redis = jedis.get("blogs");
        List<Blog> blogs;
        if (blogs_redis != null) {
            blogs = (List<Blog>) JSON.parseArray(blogs_redis,Blog.class);
            System.out.println("从缓存中读入");
        } else {
            blogs = blogMapper.findAll();
            String blogs_json = JSON.toJSONString(blogs);
            jedis.set("blogs",blogs_json);
            System.out.println("从数据库中读入");
        }
        jedis.close();//并不是真正关闭，而是把Jedis还回连接池
//        jedisPool.close();
        return blogs;
    }

    @Override
    public List<Blog> listBlogByContent(String query) {
        return blogMapper.findAllByContent(query);
    }


    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        //更新操作先删除缓存
        Jedis jedis = jedisPool.getResource();
        String blogs_redis = jedis.get("blogs");
        if (blogs_redis != null) {
            jedis.del("blogs");
        }
        jedis.close();
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        //还得存入t_blog_tag中映射关系啊
        blogMapper.save(blog);
        List<Long> idlist = new ArrayList<>();
        if (blog.getTagIds()!=null) {
            String[] idArray = blog.getTagIds().split(",");
            for (int i = 0; i < idArray.length; i++) {
                idlist.add(Long.valueOf(idArray[i]));
            }
            for (Long tag_id : idlist) {
                blogMapper.saveBlogtoTags(blog.getId(),tag_id);
            }
        }


        return 1;
    }

    @Transactional
    @Override
    public int updateBlog(Blog blog){
        //更新操作先删除缓存
        Jedis jedis = jedisPool.getResource();
        String blogs_redis = jedis.get("blogs");
        if (blogs_redis != null) {
            jedis.del("blogs");
        }
        jedis.close();
        blog.setUpdateTime(new Date());
        blogMapper.deleteBlogToTags(blog.getId());
        List<Long> idlist = new ArrayList<>();
        if (blog.getTagIds()!=null) {
            String[] idArray = blog.getTagIds().split(",");
            for (int i = 0; i < idArray.length; i++) {
                idlist.add(Long.valueOf(idArray[i]));
            }
        }
        System.out.println(idlist);
        for (Long tag_id : idlist) {
            blogMapper.saveBlogtoTags(blog.getId(),tag_id);
        }
        blogMapper.updateBlog(blog);
        return 1;
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        //更新操作先删除缓存
        Jedis jedis = jedisPool.getResource();
        String blogs_redis = jedis.get("blogs");
        if (blogs_redis != null) {
            jedis.del("blogs");
        }
        jedis.close();
        blogMapper.deleteById(id);
    }

    @Override
    public List<Blog> listBlogByType(Long type_id) {
        return blogMapper.findAllByType(type_id);
    }

    @Override
    public List<Blog> listBlogByTag(Long tag_id) {
        return blogMapper.findAllByTag(tag_id);
    }

    @Override
    public int updateView(Long id, int views) {
        //更新操作先删除缓存
        Jedis jedis = jedisPool.getResource();
        String blogs_redis = jedis.get("blogs");
        if (blogs_redis != null) {
            jedis.del("blogs");
            System.out.println("删除缓存");
        }
        jedis.close();

        return blogMapper.updateView(id,views);
    }

//    @Override
//    public List<Blog> listRecommendBlogTop(Integer size) {
//        Pageable pageable = PageRequest.of(0,size, Sort.Direction.DESC,"updateTime");
//        return blogMapper.findTop(pageable);
//    }
}
