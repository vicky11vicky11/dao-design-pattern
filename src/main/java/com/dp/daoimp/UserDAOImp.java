package com.dp.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dp.dao.UserDAO;
import com.dp.dbconnect.ConnectDatabase;
import com.dp.model.User;

public class UserDAOImp implements UserDAO {
    private static final Connection con;
    private PreparedStatement pstmt;
    private ResultSet resultSet;
    private Statement stmt;
    private List<User> usersList = new ArrayList<>();

    static {
        con = ConnectDatabase.connectDatabase();
    }

    private final String ADD_USER_QUERY = "insert into user(name,username,email,password,phone,address,role) values(?,?,?,?,?,?,?)";
    private final String DELETE_USER_QUERY = "delete from user where username=?";
    private final String GET_USER_QUERY = "select * from user where username=?";
    private final String GET_ALL_USERS_QUERY = "select * from user";
    private final String UPDATE_USER_QUERY = "update user set name=?,password=?,phone=?,address=?,role=? where username=?";

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public int addUser(User user) {
        try {
            pstmt = con.prepareStatement(ADD_USER_QUERY);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getPhone());
            pstmt.setString(6, user.getAddress());
            pstmt.setString(7, user.getRole());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public User getUser(String username) {
        try {
            pstmt = con.prepareStatement(GET_USER_QUERY);
            pstmt.setString(1, username);
            resultSet = pstmt.executeQuery();
            usersList = extractUser(resultSet);
            return usersList.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public List<User> getAllUsers() {
        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(GET_ALL_USERS_QUERY);
            usersList = extractUser(resultSet);
            return usersList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public int updateUser(String username, String name, String password, String phone, String address, String role) {
        try {
            pstmt = con.prepareStatement(UPDATE_USER_QUERY);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            pstmt.setString(3, phone);
            pstmt.setString(4, address);
            pstmt.setString(5, role);
            pstmt.setString(6, username);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public int deleteUser(String username) {
        try {
            pstmt = con.prepareStatement(DELETE_USER_QUERY);
            pstmt.setString(1, username);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    private List<User> extractUser(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                usersList.add(
                        new User(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6),
                                resultSet.getString(7),
                                resultSet.getString(8)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }
}
