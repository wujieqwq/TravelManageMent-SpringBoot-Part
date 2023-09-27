package com.wujie.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Data
//订单
public class Orders implements Serializable {
    private String id; // ID
    private String orderNum; // 订单编号
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date orderTime; // 下单时间
    private Integer orderStatus; // 订单状态 0未支付 1已支付
    private Integer peopleCount; // 出行人数
    private Product product; // 订单所关联的产品
    private List<Traveller> travellers;
    private Member member;
    private Integer payType; // 支付方式 0支付宝 1微信 2其他
    private String orderDesc; // 订单描述

}
