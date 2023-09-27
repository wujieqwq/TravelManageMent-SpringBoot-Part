package com.wujie.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

//与数据库中users对应
@Data
@Table(name = "users")
public class UserInfo implements Serializable {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    @Column(name = "phoneNum")
    private String phoneNum;
    private Integer status;
    private List<Role> roles;
}
