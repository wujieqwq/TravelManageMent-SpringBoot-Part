package com.wujie.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wujie.mapper.OrderMapper;
import com.wujie.pojo.Orders;
import com.wujie.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public PageInfo<Orders> findAll(int pageNum) {
        PageHelper.startPage(pageNum, 2);
        List<Orders> list = orderMapper.findAll();
        return new PageInfo<>(list);
    }

    @Override
    public Orders findById(String id) {
       return orderMapper.findById(id);
    }

    @Override
    public void updateOrders(Orders orders) {
        orderMapper.updateOrders(orders);
    }

    @Override
    public void delete(String id) {
        orderMapper.delete(id);
    }
}
