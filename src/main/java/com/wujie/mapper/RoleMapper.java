package com.wujie.mapper;

import com.wujie.pojo.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper extends tk.mybatis.mapper.common.Mapper<Role> {

    @Select("select * from role where id in (select roleId from users_role where userId = #{id})")
    @Results(value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permissions",many = @Many(select = "com.wujie.mapper.PermissionMapper.findByRid"))
    })
    List<Role> findByUid(String id);
}
