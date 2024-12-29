package com.dp.controller;

import java.util.Scanner;

import com.dp.dao.OrderDAO;
import com.dp.daoimp.OrderDAOImp;
import com.dp.model.Order;

public class OrderController {
    private final static Scanner sc = new Scanner(System.in);

    private static int orderId;
    private static int userId;
    private static int restaurantId;
    private static float totalAmount;
    private static String status;
    private static String paymentMode;
    // private static String orderDate;
    private static int res;

    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAOImp();
        System.out.println("Welcome to Order Management System .....");
        System.out.println("To Start Using Order Management System Select Below Option");
        System.out.println("""
                1. Add Order
                2. Get Specific Order
                3. Get All Orders
                4. Update Order
                5. Delete Order
                6. Exit
                """);

        int option = sc.nextInt();
        sc.nextLine();

        switch (option) {
            case 1 -> {
                System.out.println("Enter Order Details to Add");
                System.out.println("Enter User ID:");
                userId = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Restaurant ID:");
                restaurantId = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Total Amount:");
                totalAmount = sc.nextFloat();
                sc.nextLine();
                System.out.println("Enter Order Status [ confirmed | delivered | cancelled  ] :");
                status = sc.nextLine();
                System.out.println("Enter Payment Mode [ cash | upi | card | netbanking ] :");
                paymentMode = sc.nextLine();
                res = orderDAO.addOrder(new Order(userId, restaurantId, totalAmount, status, paymentMode));
                if (res > 0) {
                    System.out.println("Order Added Successfully");
                } else {
                    System.out.println("Order Not Added");
                }

            }
            case 2 -> {
                System.out.println("Enter Order ID to Get Specific Order:");
                orderId = sc.nextInt();
                sc.nextLine();
                Order order = orderDAO.getOrder(orderId);
                if (order != null) {
                    System.out.println(order);
                } else {
                    System.out.println("Order Not Found");
                }
            }
            case 3 -> {
                orderDAO.getOrders().forEach(orderList -> System.out.println(orderList));

            }
            case 4 -> {
                System.out.println("Enter Order ID to Update:");
                orderId = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Order Status [ confirmed | delivered | cancelled ] :");
                status = sc.nextLine();
                System.out.println("Enter Payment Mode [ cash | upi | card | netbanking ] :");
                paymentMode = sc.nextLine();
                res = orderDAO.updateOrder(orderId, status, paymentMode);
                if (res > 0) {
                    System.out.println("Order Updated Successfully");
                } else {
                    System.out.println("Order Not Updated");
                }
            }
            case 5 -> {
                System.out.println("Enter Order ID to Delete:");
                orderId = sc.nextInt();
                sc.nextLine();
                res = orderDAO.deleteOrder(orderId);
                if (res > 0) {
                    System.out.println("Order Deleted Successfully");
                } else {
                    System.out.println("Order Not Deleted");
                }
            }
            case 6 -> {
                System.out.println("Good Bye");
            }
            default -> {
                System.out.println("Please Enter Valid Option");
            }
        }

    }

}
