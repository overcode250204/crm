/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import entity.RoleEntity;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import service.I_UserService;
import service.RoleService;
import service.UserService;
/**
 *
 * @author ACER
 */
@WebServlet(urlPatterns = {"/user-add", "/user"})
public class UserController extends HttpServlet{
    private RoleService roleService = new RoleService();
    private I_UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //khai báo cookie
//        Cookie cookie = new Cookie("name", "cybersoft");
//        Cookie cookie2 = new Cookie("demo2", "saesaesae");
//        //tạo cookie ở client
//        resp.addCookie(cookie2);
//        resp.addCookie(cookie);
//        //lấy cookie
//        Cookie[] cookies = req.getCookies();
//        for (Cookie c : cookies) {
//            //lấy tên cookie
//            String nameCookie = c.getName();
//            //lấy value cookie
//            String valueCookie = c.getValue();
//            if (nameCookie.equals("demo2")) {
//                System.out.println("Giá trị cookie: " + valueCookie);
//                break;
//            }
//            System.out.println("ksad" + valueCookie);
//        }

//        HttpSession httpSession = req.getSession();//khởi tạo session
//        httpSession.setAttribute("abc", "hellosession");// set giá trị cho session

        String path = req.getServletPath();
        switch (path) {
            case "/user-add":
                userAdd(req, resp);
                break;
            case "/user":
                getUser(req, resp);
                break;
            default:
                System.out.println("ERROR!!!");
                
        }


        
        
    }
    public void userAdd(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<RoleEntity> roleServiceList = roleService.getAllRoles();
            req.setAttribute("roles", roleServiceList);
            req.getRequestDispatcher("user-add.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println("Error!!!" + e.getMessage());
        }
        
    }
    public void getUser(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setAttribute("users", userService.getUser());
            req.getRequestDispatcher("user-table.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println("Error!!!" + e.getMessage());
        }
        
        
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("example-email");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        String role = req.getParameter("roles");
        int roleInt = Integer.parseInt(role);
        int result = userService.addUsers(password, fullname, email, phone, roleInt);
        
        if (result > 0) {
            System.out.println("Success!!!");
        } else {
            System.out.println("Fail to add!!!");
            req.getRequestDispatcher("user-add.jsp").forward(req, resp);
        }
    
    
    }
}
