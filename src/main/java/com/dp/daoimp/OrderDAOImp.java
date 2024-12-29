package com.dp.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dp.dao.OrderDAO;
import com.dp.dbconnect.ConnectDatabase;
import com.dp.model.Order;

@SuppressWarnings("CallToPrintStackTrace")
public class OrderDAOImp implements OrderDAO {

    private static Connection con;
    private PreparedStatement pstmt;
    private ResultSet resultSet;
    private Statement stmt;
    private final List<Order> ordersList = new ArrayList<>();
    private final String ADD_ORDER_QUERY = "insert into orders(userId, restaurantId, totalAmount, status, paymentMode) values(?,?,?,?,?)";
    private final String GET_ORDER_QUERY = "select * from orders where orderId=?";
    private final String GET_ALL_ORDERS_QUERY = "select * from orders";
    private final String UPDATE_ORDER_QUERY = "update orders set status=?, paymentMode=? where orderId=?";
    private final String DELETE_ORDER_QUERY = "delete from orders where orderId=?";

    static {
        try {
            con = ConnectDatabase.connectDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addOrder(Order order) {

        try {
            pstmt = con.prepareStatement(ADD_ORDER_QUERY);
            pstmt.setInt(1, order.getUserId());
            pstmt.setInt(2, order.getRestaurantId());
            pstmt.setFloat(3, order.getTotalAmount());
            pstmt.setString(4, order.getStatus());
            pstmt.setString(5, order.getPaymentMode());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Order getOrder(int orderId) {

        try {
            pstmt = con.prepareStatement(GET_ORDER_QUERY);
            pstmt.setInt(1, orderId);
            resultSet = pstmt.executeQuery();
            return extractOrderItems(resultSet).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Order> getOrders() {

        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(GET_ALL_ORDERS_QUERY);
            return extractOrderItems(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    // private final String UPDATE_ORDER_QUERY = "update orders set status=?, paymentMode=? where orderId=?";

    @Override
    public int updateOrder(int orderId, String status, String paymentMode) {
        try {
            pstmt = con.prepareStatement(UPDATE_ORDER_QUERY);
            pstmt.setString(1, status);
            pstmt.setString(2, paymentMode);
            pstmt.setInt(3, orderId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteOrder(int orderId) {
        try {
            pstmt = con.prepareStatement(DELETE_ORDER_QUERY);
            pstmt.setInt(1, orderId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private List<Order> extractOrderItems(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                ordersList.add(new Order(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getFloat(5),
                        resultSet.getString(6),
                        resultSet.getString(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;

    }
}