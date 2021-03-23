package com.tianxu.shejimoshi.Prototype;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProtoType implements Cloneable{

    private Integer classes;
    private Integer grade;
    private Integer num;
    private ArrayList<Integer> s = new ArrayList<>();

    @Override
    protected ProtoType clone() throws CloneNotSupportedException {
        ProtoType protoType = (ProtoType) super.clone();
        protoType.s = (ArrayList<Integer>) this.s.clone();
        return protoType;
    }
}
