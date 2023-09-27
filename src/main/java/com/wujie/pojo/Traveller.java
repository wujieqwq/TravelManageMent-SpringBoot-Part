package com.wujie.pojo;

import lombok.Data;

import java.io.Serializable;

//旅客
@Data
public class Traveller implements Serializable {
    private String id;
    private String name;
    private String sex;
    private String phoneNum;
    private Integer credentialsType; // 证件类型 0身份证 1护照 2军官证
    private String credentialsNum;
    private Integer travellerType; // 游客类型 0成人  1儿童
}
