/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import config.MySQLConfig;
import entity.UserEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import entity.UserEntity;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
/**
 *
 * @author ACER
 */
@WebServlet(name = "loginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {
        
            //Bước 1 nhận tham số
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            //Bước 2 chuẩn bị câu truy vấn tương ứng với chức năng
            String query = "SELECT * FROM users u WHERE u.username = ? AND u.password = ?";
            ResultSet resultSet = null;
            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try { //Bước 3 : mở kết nối cơ sở dữ liệu và truyền câu truy vấn xuống CSDL để thực hiện
                connection = MySQLConfig.getConnection(); 
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                //excuteQuery : dành cho lấy dữ liệu --> SELECT
                //excuteUpdate : khác SELECT
                //preparedStatement.executeQuery(); --> trả về kiểu ResultSet
                //resultSet :  đại diện cho kết quả dữ liệu truy vấn được của bảng

                resultSet = preparedStatement.executeQuery();
                //tạo ra mảng rỗng đễ gán dữ liệu bằng từng dòng dữ liệu truy vấn được
                List<UserEntity> listUsers = new ArrayList<>();
                while (resultSet.next()) {
                    UserEntity userEntity = new UserEntity();
                    userEntity.setId(resultSet.getInt("id"));
                    System.out.println(userEntity.getId());
                    userEntity.setFirstName(resultSet.getString("first_name"));
                    listUsers.add(userEntity);
                }

                if (!listUsers.isEmpty()) {
                    
                    Cookie login = new Cookie("login", "true");
                    resp.addCookie(login);
                    resp.sendRedirect("user-add");
                    System.out.println("Đăng nhập thành công");
                } else {
                    System.out.println("Đăng nhập thất bại!!!");
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                }
            
        
        } catch (Exception e) {
            System.out.println("Lỗi login" + e.getMessage());
        } finally {
                try {
                    resultSet.close();
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }

    }


    
    
    
}
