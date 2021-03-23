package com.tianxu.service;

import com.tianxu.pojo.Tag;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {
    int saveTag(Tag tag);
    Tag getTag(Long id);
//    Page<Tag> listTag(Pageable pageable);

    int updateTag(Tag tag);

    void deleteTag(Long id);

    Tag getTagByName(String name);
    List<Tag> listTag();
    List<Tag> listTag(String ids);
    List<Tag> listTagTop(Integer size);
}
