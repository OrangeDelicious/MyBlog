package com.tianxu.dao;

import com.tianxu.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TypeMapper{

    Type getTypeByName(String name);


    int save(Type type);

    Type findById(Long id);

    List<Type> findAll();

    int deleteById(Long id);

    int updateType(Type type);

}
