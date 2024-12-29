package com.dp.controller;

import java.util.List;
import java.util.Scanner;

import com.dp.dao.OrderItemDAO;
import com.dp.daoimp.OrderItemDAOImp;
import com.dp.model.OrderItem;

public class OrderItemController {
    private final static Scanner sc = new Scanner(System.in);
    private static int orderItemId;
    private static int orderId;
    private static int menuId;
    private static int quantity;
    private static float totalAmount;
    private static int res;

    public static void main(String[] args) {
        OrderItemDAO orderItemDAO = new OrderItemDAOImp();

        System.out.println("Welcome to OrderItem Management System .....");
        System.out.println("To Start Using OrderItem Management System Select Below Option");
        System.out.println("""
                1. Add OrderItem
                2. Get Specific OrderItem
                3. Get All OrderItems
                4. Update OrderItem
                5. Delete OrderItem
                6. Exit
                """);
        int option = sc.nextInt();
        sc.nextLine();

        switch (option) {
            case 1 -> {
                System.out.println("Enter OrderItem Details to Add");
                System.out.println("Enter Order ID:");
                orderId = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Menu ID:");
                menuId = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Quantity:");
                quantity = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Total Amount:");
                totalAmount = sc.nextFloat();
                sc.nextLine();
                res = orderItemDAO.addOrderItem(new OrderItem(orderId, menuId, quantity, totalAmount));
                if (res > 0) {
                    System.out.println("OrderItem Added Successfully");
                } else {
                    System.out.println("Failed to Add OrderItem");
                }
            }
            case 2 -> {
                System.out.println("Enter OrderItem ID to Get");
                orderItemId = sc.nextInt();
                sc.nextLine();
                OrderItem orderItem = orderItemDAO.getOrderItem(orderItemId);
                if (orderItem != null) {
                    System.out.println(orderItem);
                } else {
                    System.out.println("OrderItem Not Found");
                }
            }
            case 3 -> {
                System.out.println("All OrderItems");
                List<OrderItem> orderItems = orderItemDAO.getOrderItems();
                if (orderItems != null) {
                    orderItems.forEach(System.out::println);
                } else {
                    System.out.println("No OrderItems Found");
                }
            }
            case 4 -> {
                System.out.println("Enter OrderItem ID to Update");
                orderItemId = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Quantity:");
                quantity = sc.nextInt();
                sc.nextLine();
                res = orderItemDAO.updateOrderItem(orderItemId, quantity);
                if (res > 0) {
                    System.out.println("OrderItem Updated Successfully");
                } else {
                    System.out.println("Failed to Update OrderItem");
                }
            }
            case 5 -> {
                System.out.println("Enter OrderItem ID to Delete");
                orderItemId = sc.nextInt();
                sc.nextLine();
                res = orderItemDAO.deleteOrderItem(orderItemId);
                if (res > 0) {
                    System.out.println("OrderItem Deleted Successfully");
                } else {
                    System.out.println("Failed to Delete OrderItem");
                }
            }
            case 6 -> System.exit(0);
            default -> System.out.println("Invalid Option");
        }

    }

}
