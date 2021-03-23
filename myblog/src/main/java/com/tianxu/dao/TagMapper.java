package com.tianxu.dao;

import com.tianxu.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TagMapper {
    Tag getTagByName(String name);

//    @Query("select t from t_tag t")
//    public List<Tag> findTop(Pageable pageable);
    int save(Tag tag);
    Tag findById(Long id);
    List<Tag> findAll();
    List<Tag> findAllById(List<Long> idlist);
    int deleteById(Long id);
    int updateTag(Tag tag);
}
