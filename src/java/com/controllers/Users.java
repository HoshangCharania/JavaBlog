/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

//import org.apache.derby.jdbc.ClientDriver;
import com.connection.connectionDatabase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;  
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 

import com.models.Blog;
import com.models.User;
import com.connection.connectionDatabase;
/**
 *
 * @author hoshang
 */
@WebServlet(name = "Users", urlPatterns = {"/Users"})
public class Users extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /*protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      *      throws ServletException, IOException {
      *  response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
      *      /* TODO output your page here. You may use following sample code. */
    /*
      *      out.println("<!DOCTYPE html>");
      *      out.println("<html>");
      *      out.println("<head>");
      *      out.println("<title>Servlet Users</title>");            
      *      out.println("</head>");
      *      out.println("<body>");
      *      out.println("<h1>Servlet Users at " + request.getContextPath() + "</h1>");
      *      out.println("</body>");
      *      out.println("</html>");
      *  }
      * } 
*/
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method. Also handles the delete method, 
     * which ends a users session.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Here");   
        String action = request.getParameter("action");
        if(action==null){
            action="index";
        }
        System.out.println(action);
        if (action.equalsIgnoreCase("delete"))
        {
            response.setContentType("text/html");  
            PrintWriter out=response.getWriter(); 
            HttpSession session = request.getSession(true);
            session.invalidate();
            RequestDispatcher rd=request.getRequestDispatcher("logout.jsp");  
            rd.forward(request, response);
        }
        else{
            response.setContentType("text/html");  
            PrintWriter out=response.getWriter(); 
            HttpSession session = request.getSession(true);
            RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
            rd.forward(request, response); 
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * Login is authenticated and stored through this method.
     * 
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        String username=(String) request.getParameter("name");
        String password=(String) request.getParameter("password");
        System.out.println("Request Username: "+request.getParameter("name"));
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        System.out.println("Here");
        connectionDatabase connection=new connectionDatabase();
        Connection c=connection.connect();
 //ConnectionURL, username and password should be specified in getConnection()
        User bean=new User();
        Blog blog= new Blog();
       try {
                PreparedStatement preparedStatement = c.prepareStatement("select * from users where username='"+username+"' AND password='"+password+"'");
                System.out.println("select * from users where username='"+username+"' AND password='"+password+"'");
                ResultSet rs = preparedStatement.executeQuery();
                HttpSession session = request.getSession(true);
                Boolean err=true;
                if (rs.next()) {
                    System.out.println("Queried output username:"+rs.getString("username"));
                    bean.setUsername(username);
                    request.setAttribute("bean",bean); 
                    session.setAttribute("User",bean);
                    err=false;
                }
        c.close();
        request.setAttribute("err",err);
        BlogAccess BA=new BlogAccess();
        System.out.println(blog.getBlogText());
        blog=BA.checkRecentBlog(username);
        session.setAttribute("Blog",blog);
        System.out.println("Title in Blog(Users controller): "+blog.getTitle());
        Blog current_bean_blog= (Blog) request.getSession().getAttribute("Blog");
        String sessionTitle=(String) current_bean_blog.getTitle();
        System.out.println("Title in Blog-sessions(Users controller): "+sessionTitle);
        RequestDispatcher rd=request.getRequestDispatcher("index.jsp"); 
        rd.forward(request, response);
        }
        catch (SQLException ex) {
                    System.out.println("Connect failed ! "+ex);
        }
         /*String val=(String) session.getAttribute("Username");
         if(session.isNew()){
         System.out.println("New user"+val);
         }
         else{
         System.out.println("Old user"+val);
         }*/
            //User val=(User) session.getAttribute("User");
            //System.out.println("Old user"+val.getUsername());

        
    }

    /**
     * Has not been used, as html doesn't support delete requests. 
     */
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           HttpSession session = request.getSession(true);
           session.invalidate();
           RequestDispatcher rd=request.getRequestDispatcher("logout.jsp");  
           rd.forward(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
