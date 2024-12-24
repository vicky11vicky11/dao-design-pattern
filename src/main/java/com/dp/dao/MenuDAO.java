package com.dp.dao;

import java.util.List;

import com.dp.model.Menu;

public interface MenuDAO {
    int addMenu(Menu menu);

    Menu getMenu(int menuId);

    List<Menu> getAllMenus();

    int updateMenu( int menuId, String itemName, String description, float price, boolean isAvailable, String imagePath);

    int deleteMenu(int menuId);
}
