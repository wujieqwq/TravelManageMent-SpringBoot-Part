package com.wujie.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wujie.mapper.UserMapper;
import com.wujie.pojo.Role;
import com.wujie.pojo.SecurityUser;
import com.wujie.pojo.UserInfo;
import com.wujie.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserInfoServiceImpl implements UserInfoService, UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserInfo findById(String id) {
        return userMapper.findById(id);
    }

    @Override
    public PageInfo<UserInfo> findAll(Integer pageNum) {
        PageHelper.startPage(pageNum, 2);
        List<UserInfo> list = userMapper.findAll();
        return new PageInfo<>(list);
    }

    @Override
    public List<Role> findNoRoleById(String id) {
        return userMapper.findNoRoleById(id);
    }

    @Override
    public void updateRoles(String[] roleIds, String uid) {
        userMapper.deleteRoleById(uid);
        for (String rid : roleIds) {
            userMapper.insertRoleById(rid,uid);
        }
    }

    @Override
    public void addUser(UserInfo user) {
        PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encodePassword = delegatingPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        user.setId("u"+userMapper.countUser());
        userMapper.addUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userMapper.findByUsername(username);
        if (user==null){
            throw  new UsernameNotFoundException("用户名不存在");
        }
        SecurityUser securityUser = new SecurityUser(user);
        return new SecurityUser(user);
    }

    public List<GrantedAuthority> getList(List<Role> roles){
        List<GrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    @Override
    public UserInfo login(String username, String password) throws LoginException {
        Example e = new Example(UserInfo.class);
        e.createCriteria().andEqualTo("username",username);
        PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserInfo userInfo = userMapper.selectOneByExample(e);
        if (userInfo==null){
            throw new LoginException("用户名不存在");
        }else if (!delegatingPasswordEncoder.matches(password,userInfo.getPassword())){
            throw new LoginException("用户名或密码错误");
        } else if (userInfo.getStatus()==0) {
            throw new LoginException("用户未激活");
        } else {
            return userInfo;
        }
    }

    @Override
    public void removeById(String id) {
        userMapper.deleteByPrimaryKey(id);
    }
}
