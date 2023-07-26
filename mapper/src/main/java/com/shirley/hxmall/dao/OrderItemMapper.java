package com.shirley.hxmall.dao;

import com.shirley.hxmall.entity.OrderItem;
import com.shirley.hxmall.general.GeneralDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemMapper extends GeneralDAO<OrderItem> {
    public List<OrderItem> listOrderItemsByOrderId(String orderId);
}