package com.dp.dao;

import java.util.List;

import com.dp.model.Restaurant;

public interface RestaurantDAO {

    int addRestaurant(Restaurant restaurant);

    Restaurant getRestaurant(int restaurantId);

    List<Restaurant> getAllRestaurants();

    int updateRestaurant(int restaurantId,String name, String address, String phone, String cuisineType, boolean isActive, int eta, String imagePath);

    int deleteRestaurant(int restaurantId);
}
