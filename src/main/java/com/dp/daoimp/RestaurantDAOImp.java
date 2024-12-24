package com.dp.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dp.dao.RestaurantDAO;
import com.dp.dbconnect.ConnectDatabase;
import com.dp.model.Restaurant;

public class RestaurantDAOImp implements RestaurantDAO {
    private static final Connection con;
    private PreparedStatement pstmt;
    private ResultSet resultSet;
    private Statement stmt;

    static {
        con = ConnectDatabase.connectDatabase();
    }

    private final String ADD_RESTAURANT_QUERY = "INSERT INTO restaurant(userId, name, address, phone, rating, cuisineType, isActive, eta, imagePath) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String GET_RESTAURANT_QUERY = "SELECT * FROM restaurant WHERE restaurantId=?";
    private final String GET_ALL_RESTAURANTS_QUERY = "SELECT * FROM restaurant";
    private final String UPDATE_RESTAURANT_QUERY = "UPDATE restaurant SET name=?, address=?, phone=?, cuisineType=?, isActive=?, eta=?, imagePath=? WHERE restaurantId=?";
    private final String DELETE_RESTAURANT_QUERY = "DELETE FROM restaurant WHERE restaurantId=?";

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public int addRestaurant(Restaurant restaurant) {
        try {
            pstmt = con.prepareStatement(ADD_RESTAURANT_QUERY);
            pstmt.setInt(1, restaurant.getUserId());
            pstmt.setString(2, restaurant.getName());
            pstmt.setString(3, restaurant.getAddress());
            pstmt.setString(4, restaurant.getPhone());
            pstmt.setFloat(5, restaurant.getRating());
            pstmt.setString(6, restaurant.getCuisineType());
            pstmt.setBoolean(7, restaurant.getIsActive());
            pstmt.setInt(8, restaurant.getEta());
            pstmt.setString(9, restaurant.getImagePath());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public Restaurant getRestaurant (int restaurantId) {
        try {
            pstmt = con.prepareStatement(GET_RESTAURANT_QUERY);
            pstmt.setInt(1, restaurantId);
            resultSet = pstmt.executeQuery();
            List<Restaurant> restaurants = extractRestaurant(resultSet);
            if (!restaurants.isEmpty()) {
                return restaurants.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public List<Restaurant> getAllRestaurants() {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(GET_ALL_RESTAURANTS_QUERY);
            return extractRestaurant(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public int updateRestaurant(int restaurantId, String name, String address, String phone, String cuisineType,
            boolean isActive, int eta, String imagePath) {
        try {
            pstmt = con.prepareStatement(UPDATE_RESTAURANT_QUERY);
            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.setString(3, phone);
            pstmt.setString(4, cuisineType);
            pstmt.setBoolean(5, isActive);
            pstmt.setInt(6, eta);
            pstmt.setString(7, imagePath);
            pstmt.setInt(8, restaurantId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public int deleteRestaurant(int restaurantId) {
        try {
            pstmt = con.prepareStatement(DELETE_RESTAURANT_QUERY);
            pstmt.setInt(1, restaurantId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    private List<Restaurant> extractRestaurant(ResultSet resultSet) {
        List<Restaurant> restaurants = new ArrayList<>();
        try {
            while (resultSet.next()) {
                restaurants.add(new Restaurant(
                        resultSet.getInt("userId"),
                        resultSet.getInt("restaurantId"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("phone"),
                        resultSet.getFloat("rating"),
                        resultSet.getString("cuisineType"),
                        resultSet.getBoolean("isActive"),
                        resultSet.getInt("eta"),
                        resultSet.getString("imagePath")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurants;
    }
}
