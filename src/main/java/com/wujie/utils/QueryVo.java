package com.wujie.utils;

import java.util.Arrays;

public class QueryVo {

    private String[] roleIds;
    private String id;

    public QueryVo() {
    }

    public String[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "QueryVo{" +
                "roleIds=" + Arrays.toString(roleIds) +
                ", id='" + id + '\'' +
                '}';
    }
}
