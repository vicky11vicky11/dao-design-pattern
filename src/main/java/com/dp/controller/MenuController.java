package com.dp.controller;

import java.util.Scanner;

import com.dp.dao.MenuDAO;
import com.dp.daoimp.MenuDAOImp;
import com.dp.model.Menu;

public class MenuController {
    private final static Scanner sc = new Scanner(System.in);

    private static int menuId;
    private static int restaurantId;
    private static String itemName;
    private static String description;
    private static float price;
    private static float rating;
    private static boolean isAvailable;
    private static String imagePath;

    public static void main(String[] args) {
        MenuDAO menuDAO = new MenuDAOImp();
        System.out.println("Welcome to Menu Management System .....");
        System.out.println("To Start Using Menu Management System Select Below Option");
        System.out.println("""
                1. Add Menu
                2. Get Specific Menu
                3. Get All Menus
                4. Update Menu
                5. Delete Menu
                6. Exit
                """);

        int option = sc.nextInt();
        sc.nextLine();

        switch (option) {
            case 1 -> {
                System.out.println("Enter Menu Details to Add");
                System.out.println("Enter Restaurant ID:");
                restaurantId = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Item Name:");
                itemName = sc.nextLine();
                System.out.println("Enter Menu Description:");
                description = sc.nextLine();
                System.out.println("Enter Item Price:");
                price = sc.nextFloat();
                sc.nextLine();
                System.out.println("Enter Item Rating:");
                rating = sc.nextFloat();
                sc.nextLine();
                System.out.println("Is the Menu Available? (true/false):");
                isAvailable = sc.nextBoolean();
                sc.nextLine();
                System.out.println("Enter Image Path:");
                imagePath = sc.nextLine();

                int res = menuDAO.addMenu(
                        new Menu(restaurantId, itemName, description, price, rating, isAvailable, imagePath));
                if (res > 0) {
                    System.out.println("Menu Added Successfully");
                } else {
                    System.out.println("Failed to Add Menu");
                }
            }

            case 2 -> {
                System.out.println("Enter Menu ID to Get Details:");
                menuId = sc.nextInt();
                Menu menu = menuDAO.getMenu(menuId);
                if (menu != null) {
                    System.out.println(menu);
                } else {
                    System.out.println("Menu Not Found");
                }
            }

            case 3 -> {
                System.out.println("List of All Menus:");
                menuDAO.getAllMenus().forEach(System.out::println);
            }

            case 4 -> {
                System.out.println("Enter Menu ID to Update:");
                menuId = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Item Name:");
                itemName = sc.nextLine();
                System.out.println("Enter Updated Description:");
                description = sc.nextLine();
                System.out.println("Enter Updated Price:");
                price = sc.nextFloat();
                sc.nextLine();
                System.out.println("Is the Menu Active? (true/false):");
                isAvailable = sc.nextBoolean();
                sc.nextLine();
                System.out.println("Enter Updated Image Path:");
                imagePath = sc.nextLine();

                int res = menuDAO.updateMenu(menuId, itemName, description, price, isAvailable, imagePath);
                if (res > 0) {
                    System.out.println("Menu Updated Successfully");
                } else {
                    System.out.println("Failed to Update Menu");
                }
            }

            case 5 -> {
                System.out.println("Enter Menu ID to Delete:");
                menuId = sc.nextInt();
                int res = menuDAO.deleteMenu(menuId);
                if (res > 0) {
                    System.out.println("Menu Deleted Successfully");
                } else {
                    System.out.println("Failed to Delete Menu");
                }
            }

            case 6 -> {
                System.out.println("Thanks for Using Menu Management System!");
                System.exit(0);
            }

            default -> System.out.println("Invalid Option");
        }
    }
}
