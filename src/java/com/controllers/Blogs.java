/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.models.Blog;
import com.models.User;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author hoshang
 */
@WebServlet(name = "Blogs", urlPatterns = {"/Blogs"})
public class Blogs extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("delete")){
            
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Blogs</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Blogs at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
        }
    }
   
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     * 
     * Handles the Get Request by /Blogs , to display the information.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
          User bean=(User) request.getSession().getAttribute("User"); 
          String username= null;
          if(bean==null){
            response.setContentType("text/html");  
            PrintWriter out=response.getWriter();
            RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
            rd.forward(request, response); 
          }
          username = bean.getUsername();
          BlogAccess blogAccess= new BlogAccess();
          int totalBlogs=blogAccess.checkNumberBlogs(username);
          request.setAttribute("totalBlogs", totalBlogs);
          ResultSet rs=blogAccess.userBlogs(username);
          /*
          Not the greatest of ways to do it, but the below method 
          could be used in an ideal scenario, if I had more time, I 
          could implement the system in a better way.
          Blog blog = null;
          List <Blog> Blogs = new ArrayList<>();
          */
          String[] titles = new String[totalBlogs];
          System.out.println("Inside Blogs"+titles[1]);
          String[] blogTexts = new String[totalBlogs];
          int i=0;
          try{
            while (rs.next()) {
                String blogText=(String) rs.getString("Blog_Text");
                String title=(String) rs.getString("title");
                titles[i]=title;
                blogTexts[i]=blogText;
                i++;
                }
          }
          catch(SQLException e){
              System.out.println(e);
          }
          request.setAttribute("titles", titles);
          request.setAttribute("blogTexts", blogTexts);
          RequestDispatcher rd=request.getRequestDispatcher("blogs.jsp"); 
          rd.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
