package com.tianxu;

import com.alibaba.fastjson.JSON;
import com.tianxu.dao.BlogMapper;
import com.tianxu.dao.TagMapper;
import com.tianxu.dao.TypeMapper;
import com.tianxu.pojo.Blog;
import com.tianxu.pojo.Tag;
import com.tianxu.pojo.Type;
import com.tianxu.service.TagService;
import com.tianxu.service.TypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.util.List;

@SpringBootTest
class MyblogMybatisProjectApplicationTests {

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private TypeService typeServiceImpl;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private TagService tagServiceImpl;

    @Autowired
    private BlogMapper blogMapper;


    @Test
    void contextLoads() {

    }

}
