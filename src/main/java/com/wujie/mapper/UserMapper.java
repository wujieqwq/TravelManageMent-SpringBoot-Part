package com.wujie.mapper;


import com.wujie.pojo.Role;
import com.wujie.pojo.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<UserInfo> {


    @Select("select * from users where id = #{id}")
    @Results(id = "userMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "email",property = "email"),
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password"),
            @Result(column = "phoneNum",property = "phoneNum"),
            @Result(column = "status",property = "status"),
            @Result(column = "id",property = "roles",many = @Many(select = "com.wujie.mapper.RoleMapper.findByUid"))

    })
    UserInfo findById(String id);


    @Select("select * from users")
    List<UserInfo> findAll();

    @Select("select * from role where id not in (select roleId from users_role where userId = #{id})")
    List<Role> findNoRoleById(String id);

    @Delete("delete from users_role where userId = #{uid}")
    void deleteRoleById(String uid);


    @Insert("insert into users_role (userId, roleId) values (#{uid},#{rid});")
    void insertRoleById(@Param("rid") String rid, @Param("uid") String uid);

    @Select("select * from users where username = #{username}")
    @ResultMap("userMap")
    UserInfo findByUsername(String username);

    @Insert("insert users (id,email, username, PASSWORD, phoneNum, STATUS) values (#{id},#{email},#{username},#{password},#{phoneNum},#{status})")
    void addUser(UserInfo user);

    @Select("select count(*) from users")
    int countUser();
}
