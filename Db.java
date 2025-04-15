package Employ_management_system;

import java.sql.Connection;
import java.sql.DriverManager;

public class Db {
    static  Connection conn;

    public static Connection getConnection() {
    	
        try {
            if (conn == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/subham", "root", "Subham@12345");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
