package com.dp.controller;

import java.util.Scanner;

import com.dp.dao.UserDAO;
import com.dp.daoimp.UserDAOImp;
import com.dp.model.User;

public class UserController {
    private final static Scanner sc = new Scanner(System.in);

    private static String name;
    private static String username;
    private static String email;
    private static String password;
    private static String phone;
    private static String address;
    private static String role;

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAOImp();
        System.out.println("Welcome to User Management System .....");
        System.out.println("To Start Using User Management System Select Below Option");
        System.out.println("""
                1. Add User
                2. Get Specific User
                3. Get All Users
                4. Update User
                5. Delete User
                6. Exit
                """);
        int option = sc.nextInt();
        sc.nextLine();
        switch (option) {
            case 1 -> {
                System.out.println("Enter User Details to Add User");
                System.out.println("Enter User Name");
                name = sc.nextLine();
                System.out.println("Enter User Username");
                username = sc.nextLine();
                System.out.println("Enter User Email");
                email = sc.nextLine();
                System.out.println("Enter User Password");
                password = sc.nextLine();
                System.out.println("Enter User Phone");
                phone = sc.nextLine();
                System.out.println("Enter User Address");
                address = sc.nextLine();
                System.out.println("Enter User Role");
                role = sc.nextLine();
                int res = userDAO.addUser(new User(name, username, email, password, phone,
                        address, role));
                if (res > 0) {
                    System.out.println("User Added Successfully");
                } else {
                    System.out.println("User Not Added");
                }
            }

            case 2 -> {
                System.out.println("Enter Username to Get User");
                username = sc.nextLine();
                User user = userDAO.getUser(username);
                if (user != null) {
                    System.out.println(user);
                } else {
                    System.out.println("User Not Found");
                }
            }

            case 3 -> {
                System.out.println("List of All Users");
                userDAO.getAllUsers().forEach(System.out::println);
            }

            case 4 -> {
                System.out.println("Enter User's Username to Update User");
                username = sc.nextLine();
                System.out.println("Enter User's Name");
                name = sc.nextLine();
                System.out.println("Enter User's Password");
                password = sc.nextLine();
                System.out.println("Enter User's Phone");
                phone = sc.nextLine();
                System.out.println("Enter User's Address");
                address = sc.nextLine();
                System.out.println("Enter User's Role");
                role = sc.nextLine();
                int res = userDAO.updateUser(username, name, password, phone, address, role);
                if (res > 0) {
                    System.out.println("User Updated Successfully");
                } else {
                    System.out.println("User Not Updated");
                }
            }

            case 5 -> {
                System.out.println("Enter User's Username to Delete User");
                username = sc.nextLine();
                int res = userDAO.deleteUser(username);
                if (res > 0) {
                    System.out.println("User Deleted Successfully");
                } else {
                    System.out.println("User Not Deleted");
                }
            }

            case 6 -> {
                System.out.println("Thanks for Using User Management System");
                System.exit(0);
            }

            default -> System.out.println("Invalid Option");
        }
    }
}
