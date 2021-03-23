package com.tianxu.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Blog {

    @Id
    @GeneratedValue

    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;
    private String description;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentabled;
    private boolean published;
    private boolean recommend;
    private String tagIds;//以字符串形式存储多个tagId,1,2,3
    private Long typeId_my;//这个先不改了吧
    private Long userId;

//    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
//    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Transient
    private Type type;
    @Transient
    private List<Tag> tags = new ArrayList<>();
    @Transient
    private User user;
    @Transient
    private List<Comment> comments = new ArrayList<>();
}
