package com.wujie.utils;

import com.wujie.pojo.Traveller;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

public class TravellerSqlProvider {
    public String getCountSql(Traveller traveller) {
        SQL sql = new SQL().SELECT("count(0)");
        addFrom(sql);
        addWhere(sql, traveller);
        return sql.toString();
    }

    //拿到查询集合对象的SQL语句
    public String getLimitSql(Traveller traveller) {
        SQL sql = new SQL().SELECT("id, name, age");
        addFrom(sql);
        addWhere(sql, traveller);
        return sql.toString();
    }

    //抽取拼接的from语句的方法
    private void addFrom(SQL sql) {
        sql.FROM("employee"); //往SQL对象中添加from语句
    }

    //抽取拼接的where语句的方法
    private void addWhere(SQL sql, Traveller qo) {
        //往SQL对象中动态的添加添加
        if (StringUtils.hasLength(qo.getName())) {
            sql.WHERE("e.name like #{name}");
        }

    }
}
