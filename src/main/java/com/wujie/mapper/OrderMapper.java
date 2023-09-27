package com.wujie.mapper;

import com.wujie.pojo.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface OrderMapper {




    @Select("select * from orders")
    @Results(id = "orderMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderStatus",property = "orderStatus"),

            @Result(column = "productId",property = "product",
                    one = @One(select="com.wujie.mapper.ProductMapper.findById")),
            @Result(column = "id",property = "travellers",
                    many = @Many(select = "com.wujie.mapper.TravellerMapper.findByOid")),
            @Result(column = "memberId",property = "member",
                    one = @One(select = "com.wujie.mapper.MemberMapper.findById"))

    })
    List<Orders> findAll();

    @Select("select * from orders where id = #{id}")
    @ResultMap("orderMap")
    Orders findById(String id);


    @Update("update orders set orderTime = #{orderTime},orderDesc = #{orderDesc} where id=#{id}")
    void updateOrders(Orders orders);

    @Delete("delete from orders where id=#{id}")
    void delete(String id);
}
