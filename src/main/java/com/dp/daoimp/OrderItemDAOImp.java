package com.dp.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dp.dao.OrderItemDAO;
import com.dp.dbconnect.ConnectDatabase;
import com.dp.model.OrderItem;

@SuppressWarnings("CallToPrintStackTrace")
public class OrderItemDAOImp implements OrderItemDAO {

    private static Connection con;
    private PreparedStatement pstmt;
    private ResultSet resultSet;
    private Statement stmt;
    private List<OrderItem> orderItemsList = new ArrayList<>();

    private final String ADD_ORDER_ITEM_QUERY = "insert into orderItem(orderId, menuId, quantity, totalAmount) values(?,?,?,?)";
    private final String GET_ORDER_ITEM_QUERY = "select * from orderItem where orderItemId=?";
    private final String GET_ALL_ORDER_ITEMS_QUERY = "select * from orderItem";
    private final String UPDATE_ORDER_ITEM_QUERY = "update orderItem set quantity=?  where orderItemId=?";
    private final String DELETE_ORDER_ITEM_QUERY = "delete from orderItem where orderItemId=?";

    static {
        try {
            con = ConnectDatabase.connectDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addOrderItem(OrderItem orderItem) {
        try {
            pstmt = con.prepareStatement(ADD_ORDER_ITEM_QUERY);
            pstmt.setInt(1, orderItem.getOrderId());
            pstmt.setInt(2, orderItem.getMenuId());
            pstmt.setInt(3, orderItem.getQuantity());
            pstmt.setFloat(4, orderItem.getTotalAmount());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public OrderItem getOrderItem(int orderItemId) {
        try {
            pstmt = con.prepareStatement(GET_ORDER_ITEM_QUERY);
            pstmt.setInt(1, orderItemId);
            resultSet = pstmt.executeQuery();
            orderItemsList = extractOrderItems(resultSet);
            return orderItemsList.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<OrderItem> getOrderItems() {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(GET_ALL_ORDER_ITEMS_QUERY);
            orderItemsList = extractOrderItems(resultSet);
            return orderItemsList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int updateOrderItem(int orderItemId, int quantity) {
        try {
            pstmt = con.prepareStatement(UPDATE_ORDER_ITEM_QUERY);
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, orderItemId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int deleteOrderItem(int orderItemId) {
        try {
            pstmt = con.prepareStatement(DELETE_ORDER_ITEM_QUERY);
            pstmt.setInt(1, orderItemId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private List<OrderItem> extractOrderItems(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                orderItemsList.add(new OrderItem(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4),
                        resultSet.getFloat(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemsList;
    }

}
