package com.wujie.controller;


import com.github.pagehelper.PageInfo;
import com.wujie.pojo.Role;
import com.wujie.service.RoleService;
import com.wujie.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
@CrossOrigin
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAll")
    public ResultInfo findAll(@RequestParam(defaultValue = "1") Integer pageNum){
        ResultInfo resultInfo = new ResultInfo();
        PageInfo<Role> page = roleService.findAll(pageNum);
        resultInfo.setData(page);
        resultInfo.setFlag(true);
        return resultInfo;
    }
}
