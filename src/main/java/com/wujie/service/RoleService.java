package com.wujie.service;

import com.github.pagehelper.PageInfo;
import com.wujie.pojo.Role;

import java.util.List;

public interface RoleService {
    PageInfo<Role> findAll(Integer pageNum);
}
