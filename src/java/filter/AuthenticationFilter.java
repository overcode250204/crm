/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ACER
 */
////urlPartterns là đường dẫn client gọi sẽ kích hoạt filter
@WebFilter(filterName = "authenFilter", urlPatterns = {"/user-add", "/login"})
public class AuthenticationFilter implements Filter{

   @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Filter kích hoạt");
        
        //vì ServletRequest là cha của thằng HttpServletRequest mà hàm getServletPath nằm trong thằng con HttpServletRequest nên ép kiểu
        //lấy link servlet mà client đang gọi
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        boolean isExist = false;
        String path = req.getServletPath();
        Cookie[] cookies = req.getCookies();
        for(Cookie cookie : cookies) {
            String nameCookie = cookie.getName();
            if (nameCookie.equals("login")) {
                isExist = true;
                break;
            }
        }
        
        switch (path) {
            case "/user-add":
                if (isExist) {
                    chain.doFilter(request, response);
                } else {
                    resp.sendRedirect("login");//ép kiểu về con để có sendRedirect!!!
                }
                break;
            case "/login":
                if (isExist) {
                    resp.sendRedirect("user-add");
                } else {
                    chain.doFilter(request, response);
                }
                break;
            default:
                System.out.println("Kiemtra khong tìm thấy link!!!");
        }
        //cho phép đi tiếp
//       chain.doFilter(request, response);

   
    }

    
    @Override
    public void destroy() {
        
    }

    
    
}
