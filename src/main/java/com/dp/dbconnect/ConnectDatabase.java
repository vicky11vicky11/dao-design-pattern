package com.dp.dbconnect;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectDatabase {
    private static Connection con;

    @SuppressWarnings("CallToPrintStackTrace")
    public static Connection connectDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/maven_crud", "root",
                    "Vignesh@2003");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
