package com.wujie.service;

import com.github.pagehelper.PageInfo;
import com.wujie.pojo.Role;
import com.wujie.pojo.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;


import javax.security.auth.login.LoginException;
import java.util.List;


public interface UserInfoService extends UserDetailsService {

    UserInfo findById(String id);

    PageInfo<UserInfo> findAll(Integer pageNum);

    List<Role> findNoRoleById(String id);

    void updateRoles(String[] roleIds, String uid);

    void addUser(UserInfo user);

    UserInfo login(String username, String password) throws LoginException;

    void removeById(String id);
}
