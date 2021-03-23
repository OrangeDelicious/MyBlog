package com.tianxu.service;

import com.tianxu.dao.TypeMapper;
import com.tianxu.pojo.Type;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class TypeServiceImpl implements TypeService{

    @Autowired
    private TypeMapper typeMapper;

    @Transactional
    @Override
    public int saveType(Type type) {
//        int id = UUID.randomUUID().toString().hashCode();
//        type.setId((long) id);
        return typeMapper.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeMapper.findById(id);
    }

    @Transactional
    @Override
    public List<Type> listType() {
        return typeMapper.findAll();
    }

    @Transactional
    @Override
    public int updateType(Type type){

        return typeMapper.updateType(type);
    }



    @Transactional
    @Override
    public void deleteType(Long id) {
        Type t = typeMapper.findById(id);
        if (t.getBlogs().size() != 0) {
            typeMapper.deleteById(id);
        } else {
            //不能删除
        }

    }

    @Override
    public Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
    }


    @Override
    public List<Type> listTypeTop(Integer size) {
        List<Type> types = typeMapper.findAll();
        types.sort(new Comparator<Type>() {
            @Override
            public int compare(Type o1, Type o2) {
                return Integer.compare(o2.getBlogs().size(),o1.getBlogs().size());
            }
        });
        size = Math.min(size,types.size());
        return types.subList(0,size);
    }
}
