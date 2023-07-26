package com.shirley.hxmall.dao;

import com.shirley.hxmall.entity.Orders;
import com.shirley.hxmall.entity.OrdersVO;
import com.shirley.hxmall.general.GeneralDAO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersMapper extends GeneralDAO<Orders> {
    public List<OrdersVO> selectOrders(@Param("userId") String userId,
                                       @Param("status") String status,
                                       @Param("start") int start,
                                       @Param("limit") int limit);

}