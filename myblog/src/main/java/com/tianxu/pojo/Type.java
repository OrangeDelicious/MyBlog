package com.tianxu.pojo;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class Type {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Transient
    private List<Blog> blogs = new ArrayList<>();
}
