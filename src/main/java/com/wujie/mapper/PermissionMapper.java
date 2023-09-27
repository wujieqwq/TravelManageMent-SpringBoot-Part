package com.wujie.mapper;

import com.wujie.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{rid})")
    List<Permission> findByRid(String rid);
}
