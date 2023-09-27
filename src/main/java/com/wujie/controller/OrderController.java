package com.wujie.controller;


import com.github.pagehelper.PageInfo;
import com.wujie.mapper.TravellerMapper;
import com.wujie.pojo.Orders;
import com.wujie.pojo.Traveller;
import com.wujie.service.OrderService;
import com.wujie.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private TravelService travelService;

    @RequestMapping("/list")
    public PageInfo<Orders> list(@RequestParam(defaultValue = "1") Integer pageNum){
        return orderService.findAll(pageNum);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public Orders detail(@PathVariable String id){
        return orderService.findById(id);
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public String update(@RequestBody Orders orders){
        travelService.updateTraveller(orders.getTravellers());
        orderService.updateOrders(orders);
        return "true";
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public String deleteOrder(@PathVariable String id){
        orderService.delete(id);
        return "true";
    }

    @RequestMapping(value = "/deleteOrders",method = RequestMethod.DELETE)
    public String deleteOrders(String[] orderIds){
        for (String orderId : orderIds) {
            orderService.delete(orderId);
        }
        return "true";
    }
}
