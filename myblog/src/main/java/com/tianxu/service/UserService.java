package com.tianxu.service;

import com.tianxu.pojo.User;

public interface UserService {
    User checkUser(String username, String password);
}
