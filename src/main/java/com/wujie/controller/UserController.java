package com.wujie.controller;


import com.github.pagehelper.PageInfo;
import com.wujie.pojo.Role;
import com.wujie.pojo.UserInfo;
import com.wujie.service.UserInfoService;
import com.wujie.utils.QueryVo;
import com.wujie.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserInfoService userInfoService;


    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public UserInfo findOne(@PathVariable String id){
        return userInfoService.findById(id);
    }

    @RequestMapping("/findNoRole")
    public List<Role> findNoRole(String id){
        return userInfoService.findNoRoleById(id);
    }


    @RequestMapping("/findAll")
    public PageInfo<UserInfo> findAll(@RequestParam(defaultValue = "1") Integer pageNum){
        return userInfoService.findAll(pageNum);
    }

//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/updateRoles",method = RequestMethod.PUT)
    public String updateRoles(@RequestBody QueryVo queryVo){
        userInfoService.updateRoles(queryVo.getRoleIds(),queryVo.getId());
        return "true";
    }

    @RequestMapping("/add")
    public String addUser(@RequestBody UserInfo user){
        userInfoService.addUser(user);
        return "true";
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultInfo login(@RequestBody UserInfo userInfo, HttpSession session) throws LoginException {
        UserInfo user = userInfoService.login(userInfo.getUsername(), userInfo.getPassword());
        UserDetails userDetails = userInfoService.loadUserByUsername(userInfo.getUsername());
        session.setAttribute("user", user);
        session.setMaxInactiveInterval(30*60);
        ResultInfo info = new ResultInfo();
        info.setFlag(true);
        return info;
    }

    @RequestMapping("removeById")
    public ResultInfo removeById(String id){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setFlag(true);
        userInfoService.removeById(id);
        return resultInfo;
    }
}
