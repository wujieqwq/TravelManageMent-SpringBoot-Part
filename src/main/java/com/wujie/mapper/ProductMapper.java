package com.wujie.mapper;

import com.wujie.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProductMapper {

    @Select("select * from product where id = #{id}")
    Product findById(String id);
}
