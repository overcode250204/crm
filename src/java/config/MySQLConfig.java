/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ACER
 */
public class MySQLConfig {
    //Mở kết nối tới CSDL
    public static Connection getConnection() {
        Connection connection = null;
        try {
            //khai báo driver sử dụng cho jdbc(Lên mạng search)
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/crmapp", "root", "admin123");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Lỗi không tìm thấy Driver" + e.getMessage());
        }
        return connection;
        
    }
}
