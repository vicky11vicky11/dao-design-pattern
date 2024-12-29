package com.dp.dao;

import java.util.List;

import com.dp.model.Order;

public interface OrderDAO {

    int addOrder(Order order);

    Order getOrder(int orderId);

    List<Order> getOrders();

    int updateOrder(int orderId, String status, String paymentMode);

    int deleteOrder(int orderId);

}
