package com.wujie.mapper;

import com.wujie.pojo.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysLogMapper {

    @Select("select * from syslog order by visitTime desc")
    List<SysLog> list();

    @Insert("insert into syslog(id, visitTime, username, ip, url, executionTime, method) " +
            "values(#{id},#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void addLog(SysLog sysLog);
}
