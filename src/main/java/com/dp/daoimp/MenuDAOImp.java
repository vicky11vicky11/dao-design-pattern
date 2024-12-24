package com.dp.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dp.dao.MenuDAO;
import com.dp.dbconnect.ConnectDatabase;
import com.dp.model.Menu;

@SuppressWarnings("CallToPrintStackTrace")
public class MenuDAOImp implements MenuDAO {

    private static Connection con;
    private PreparedStatement pstmt;
    private ResultSet resultSet;
    private Statement stmt;
    private List<Menu> menusList = new ArrayList<>();
    private final String ADD_MENU_QUERY = "insert into menu(restaurantId, itemName, description, price, rating, isAvailable, imagePath) values(?,?,?,?,?,?,?)";
    private final String DELETE_MENU_QUERY = "delete from menu where menuId=?";
    private final String GET_MENU_QUERY = "select * from menu where menuId=?";
    private final String GET_ALL_MENUS_QUERY = "select * from menu";
    private final String UPDATE_MENU_QUERY = "update menu set itemName=?, description=?, price=?, isAvailable=?, imagePath=? where menuId=?";

    static {
        try {
            con = ConnectDatabase.connectDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public int addMenu(Menu menu) {

        try {
            pstmt = con.prepareStatement(ADD_MENU_QUERY);
            pstmt.setInt(1, menu.getRestaurantId());
            pstmt.setString(2, menu.getItemName());
            pstmt.setString(3, menu.getDescription());
            pstmt.setFloat(4, menu.getPrice());
            pstmt.setFloat(5, menu.getRating());
            pstmt.setBoolean(6, menu.getIsAvailable());
            pstmt.setString(7, menu.getImagePath());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public Menu getMenu(int menuId) {

        try {
            pstmt = con.prepareStatement(GET_MENU_QUERY);
            pstmt.setInt(1, menuId);
            resultSet = pstmt.executeQuery();
            menusList = extractMenuItems(resultSet);
            return menusList.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public List<Menu> getAllMenus() {

        try {
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(GET_ALL_MENUS_QUERY);
            return extractMenuItems(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public int updateMenu(int menuId, String itemName, String description, float price, boolean isAvailable,
            String imagePath) {

        try {
            pstmt = con.prepareStatement(UPDATE_MENU_QUERY);
            pstmt.setString(1, itemName);
            pstmt.setString(2, description);
            pstmt.setFloat(3, price);
            pstmt.setBoolean(4, isAvailable);
            pstmt.setString(5, imagePath);
            pstmt.setInt(6, menuId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public int deleteMenu(int menuId) {

        try {
            pstmt = con.prepareStatement(DELETE_MENU_QUERY);
            pstmt.setInt(1, menuId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    private List<Menu> extractMenuItems(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                menusList.add(
                        new Menu(
                                resultSet.getInt(1),
                                resultSet.getInt(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getFloat(5),
                                resultSet.getFloat(6),
                                resultSet.getBoolean(7),
                                resultSet.getString(8)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menusList;
    }

}
