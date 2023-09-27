package com.wujie.service;

import com.github.pagehelper.PageInfo;
import com.wujie.pojo.Orders;

public interface OrderService {
    PageInfo<Orders> findAll(int pageNum);

    Orders findById(String id);

    void updateOrders(Orders orders);

    void delete(String id);
}
