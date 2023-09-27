package com.wujie.mapper;


import com.wujie.pojo.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberMapper {

    @Select("select * from member where id = #{memberId}")
    List<Member> findById(String memberId);
}
