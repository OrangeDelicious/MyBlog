package com.tianxu.service;

import com.tianxu.dao.UserMapper;
import com.tianxu.pojo.User;
import com.tianxu.utils.MD5util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {
        User user = userMapper.findByUsernameAndPassword(username, MD5util.code(password));
        return user;
    }
}
