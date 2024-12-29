package com.dp.dao;

import java.util.List;

import com.dp.model.OrderItem;

public interface OrderItemDAO {
    int addOrderItem(OrderItem orderItem);

    OrderItem getOrderItem(int orderItemId);

    List<OrderItem> getOrderItems();

    int updateOrderItem(int orderItemId, int quantity);

    int deleteOrderItem(int orderItemId);
}
