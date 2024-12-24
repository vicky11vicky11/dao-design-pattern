package com.dp.model;

public class Restaurant {

    private int userId;
    private int restaurantId;
    private String name;
    private String address;
    private String phone;
    private float rating;
    private String cuisineType;
    private boolean isActive;
    private int eta;
    private String imagePath;

    public Restaurant() {
    }

    public Restaurant(int userId, String name, String address, String phone, float rating, String cuisineType,
            boolean isActive, int eta, String imagePath) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.rating = rating;
        this.cuisineType = cuisineType;
        this.isActive = isActive;
        this.eta = eta;
        this.imagePath = imagePath;
    }

    public Restaurant(int userId, int restaurantId, String name, String address, String phone, float rating,
            String cuisineType, boolean isActive, int eta, String imagePath) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.rating = rating;
        this.cuisineType = cuisineType;
        this.isActive = isActive;
        this.eta = eta;
        this.imagePath = imagePath;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Restaurant [userId=" + userId + ", restaurantId=" + restaurantId + ", name=" + name + ", address="
                + address + ", phone=" + phone + ", rating=" + rating + ", cuisineType=" + cuisineType + ", isActive="
                + isActive + ", eta=" + eta + ", imagePath=" + imagePath + "]";
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

}
