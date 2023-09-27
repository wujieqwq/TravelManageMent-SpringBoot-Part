package com.wujie.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
//会员
public class Member implements Serializable {
    private String id;
    private String name;
    private String nickname;
    private String phoneNum;
    private String email;

}
