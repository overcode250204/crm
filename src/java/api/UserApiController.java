/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import response.BaseReponse;
import service.I_UserService;
import service.UserService;

/**
 *
 * @author ACER
 */
@WebServlet(urlPatterns = {"/api/user"})
public class UserApiController extends HttpServlet{
    private I_UserService userService = new UserService();
    //Thư viện hỗ trợ trả JSON Gson, JackSon
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUser =  Integer.parseInt(req.getParameter("id"));
        
        BaseReponse baseReponse = new BaseReponse();
        baseReponse.setStatusCode(200);
        baseReponse.setMessage("");
        baseReponse.setData(userService.deleteUser(idUser));
        
        Gson gson = new Gson();
        String jsonData = gson.toJson(baseReponse);
        
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.append(jsonData);
        writer.close();
        
    }
    
    
}
