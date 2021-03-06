package com.tianxu.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer type;

//    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
//    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Transient
    private List<Blog> blogs = new ArrayList<>();
}
