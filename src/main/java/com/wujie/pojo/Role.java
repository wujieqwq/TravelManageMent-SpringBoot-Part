package com.wujie.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Data
@Table(name = "role")
public class Role implements Serializable {
    @Id
    private String id;
    @Column(name = "roleName")
    private String roleName;
    @Column(name = "roleDesc")
    private String roleDesc;
    private List<Permission> permissions;
    private List<UserInfo> users;

}
