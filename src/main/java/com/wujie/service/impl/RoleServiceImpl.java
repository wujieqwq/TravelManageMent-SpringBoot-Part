package com.wujie.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wujie.mapper.RoleMapper;
import com.wujie.pojo.Role;
import com.wujie.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo<Role> findAll(Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        List<Role> roles = roleMapper.selectAll();
        return new PageInfo<>(roles);
    }
}
