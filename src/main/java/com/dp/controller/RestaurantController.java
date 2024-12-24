package com.dp.controller;

import java.util.Scanner;

import com.dp.dao.RestaurantDAO;
import com.dp.daoimp.RestaurantDAOImp;
import com.dp.model.Restaurant;

public class RestaurantController {
    private final static Scanner sc = new Scanner(System.in);

    private static int restaurantId;
    private static String name;
    private static String address;
    private static String phone;
    private static float rating;
    private static String cuisineType;
    private static boolean isActive;
    private static int eta;
    private static String imagePath;
    private static int userId;

    public static void main(String[] args) {
        RestaurantDAO restaurantDAO = new RestaurantDAOImp();
        System.out.println("Welcome to Restaurant Management System .....");
        System.out.println("To Start Using Restaurant Management System Select Below Option");
        System.out.println("""
                1. Add Restaurant
                2. Get Specific Restaurant
                3. Get All Restaurants
                4. Update Restaurant
                5. Delete Restaurant
                6. Exit
                """);

        int option = sc.nextInt();
        sc.nextLine();

        switch (option) {
            case 1 -> {
                System.out.println("Enter Restaurant Details to Add");
                System.out.println("Enter User ID:");
                userId = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Restaurant Name:");
                name = sc.nextLine();
                System.out.println("Enter Restaurant Address:");
                address = sc.nextLine();
                System.out.println("Enter Restaurant Phone:");
                phone = sc.nextLine();
                System.out.println("Enter Restaurant Rating:");
                rating = sc.nextFloat();
                sc.nextLine();
                System.out.println("Enter Cuisine Type:");
                cuisineType = sc.nextLine();
                System.out.println("Is the Restaurant Active? (true/false):");
                isActive = sc.nextBoolean();
                System.out.println("Enter Estimated Time of Arrival (ETA):");
                eta = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Image Path:");
                imagePath = sc.nextLine();

                int res = restaurantDAO.addRestaurant(
                        new Restaurant(userId, name, address, phone, rating, cuisineType, isActive, eta, imagePath));
                if (res > 0) {
                    System.out.println("Restaurant Added Successfully");
                } else {
                    System.out.println("Failed to Add Restaurant");
                }
            }

            case 2 -> {
                System.out.println("Enter Restaurant ID to Get Details:");
                restaurantId = sc.nextInt();
                Restaurant restaurant = restaurantDAO.getRestaurant(restaurantId);
                if (restaurant != null) {
                    System.out.println(restaurant);
                } else {
                    System.out.println("Restaurant Not Found");
                }
            }

            case 3 -> {
                System.out.println("List of All Restaurants:");
                restaurantDAO.getAllRestaurants().forEach(System.out::println);
            }

            case 4 -> {
                System.out.println("Enter Restaurant ID to Update:");
                restaurantId = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Updated Name:");
                name = sc.nextLine();
                System.out.println("Enter Updated Address:");
                address = sc.nextLine();
                System.out.println("Enter Updated Phone:");
                phone = sc.nextLine();
                System.out.println("Enter Updated Cuisine Type:");
                cuisineType = sc.nextLine();
                System.out.println("Is the Restaurant Active? (true/false):");
                isActive = sc.nextBoolean();
                System.out.println("Enter Updated ETA:");
                eta = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Updated Image Path:");
                imagePath = sc.nextLine();

                int res = restaurantDAO.updateRestaurant(restaurantId, name, address, phone, cuisineType, isActive, eta,
                        imagePath);
                if (res > 0) {
                    System.out.println("Restaurant Updated Successfully");
                } else {
                    System.out.println("Failed to Update Restaurant");
                }
            }

            case 5 -> {
                System.out.println("Enter Restaurant ID to Delete:");
                restaurantId = sc.nextInt();
                int res = restaurantDAO.deleteRestaurant(restaurantId);
                if (res > 0) {
                    System.out.println("Restaurant Deleted Successfully");
                } else {
                    System.out.println("Failed to Delete Restaurant");
                }
            }

            case 6 -> {
                System.out.println("Thanks for Using Restaurant Management System!");
                System.exit(0);
            }

            default -> System.out.println("Invalid Option");
        }
    }
}
