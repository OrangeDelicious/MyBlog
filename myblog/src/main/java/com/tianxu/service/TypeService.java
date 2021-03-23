package com.tianxu.service;

import com.tianxu.pojo.Type;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {

    int saveType(Type type);
    Type getType(Long id);
    List<Type> listType();
    int updateType(Type type);

    void deleteType(Long id);

    Type getTypeByName(String name);

    List<Type> listTypeTop(Integer size);

}
