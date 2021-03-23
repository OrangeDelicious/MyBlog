package com.tianxu.dao;

import com.tianxu.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper{

    User findByUsernameAndPassword(String username, String password);
}
