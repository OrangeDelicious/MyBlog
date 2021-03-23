package com.tianxu.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Comment {

    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;

//    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Transient
    private Blog blog;
    @Transient
    private List<Comment> replyComments = new ArrayList<>();
    @Transient
    private Comment parentComment;
}
