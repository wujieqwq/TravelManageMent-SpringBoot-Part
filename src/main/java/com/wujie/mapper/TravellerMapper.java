package com.wujie.mapper;


import com.wujie.pojo.Traveller;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TravellerMapper {

    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId = #{id})")
    List<Traveller> findByOid(String id);

    @Update("update traveller set name = #{name},sex = #{sex},phoneNum = #{phoneNum},credentialsNum=#{credentialsNum},credentialsType=#{credentialsType} where id = #{id}")
    void updateTraveller(Traveller traveller);
}
