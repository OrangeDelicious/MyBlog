package com.tianxu.service;

import com.tianxu.dao.TagMapper;
import com.tianxu.pojo.Tag;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class TagServiceImpl implements TagService{



    @Autowired
    private TagMapper tagMapper;

    @Transactional
    @Override
    public int saveTag(Tag tag) {
//        int id = UUID.randomUUID().toString().hashCode();
//        tag.setId((long) id);
        return tagMapper.save(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagMapper.findById(id);
    }


    @Transactional
    @Override
    public int updateTag(Tag tag){

        return tagMapper.updateTag(tag);
    }



    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagMapper.deleteById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }

    @Override
    public List<Tag> listTag() {
        return tagMapper.findAll();
    }

    @Override
    public List<Tag> listTag(String ids) {

        List<Long> idlist = new ArrayList<>();
        if (ids!=null ) {
            String[] idArray = ids.split(",");
            for (int i = 0; i < idArray.length; i++) {
                idlist.add(Long.valueOf(idArray[i]));
            }
        }
        return tagMapper.findAllById(idlist);
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        List<Tag> tags = tagMapper.findAll();
        tags.sort(new Comparator<Tag>() {
            @Override
            public int compare(Tag o1, Tag o2) {
                return Integer.compare(o2.getBlogs().size(),o1.getBlogs().size());
            }
        });
        size = Math.min(size,tags.size());
        return tags.subList(0,size);
    }
}
