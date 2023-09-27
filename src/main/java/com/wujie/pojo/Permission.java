package com.wujie.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Permission implements Serializable {
    private String id;
    private String permissionName;
    private String url;
    private List<Role> roles;

}
