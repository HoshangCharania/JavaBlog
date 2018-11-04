/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;
import com.models.Blog;
import com.models.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.connection.connectionDatabase;
/**
 *
 * @author hoshang
 */
public class BlogAccess {
    
    /*
    @param ResultSet rs
    @returns Blog
    
    Given a blog as an output from a SQL query it creates a Blog object.
    */
     public Blog createBlog(ResultSet rs){
        Blog blog=new Blog();
        try{
        if (rs.next()) {
                System.out.println("Queried output username:"+rs.getString("username"));
                String blogText=rs.getString("Blog_Text");
                blog.setBlogText(blogText);
                String title=rs.getString("title");
                blog.setTitle(title);
                int id=rs.getInt("id");
                blog.setId(id);
                String blogUsername=rs.getString("username");
                blog.setUsername(blogUsername);
            }
        }
        catch (SQLException ex) {
                    System.out.println("Connect failed ! "+ex);
        }
        return blog;
    }
     
     /*
     @param username
     @returns Blog
     
     Given a username, the SQL statement finds the most recent Blog.
     */
    public Blog checkRecentBlog(String username){
        Blog blog=new Blog();
        connectionDatabase c=new connectionDatabase();
        Connection connection=c.connect();
        try {
                System.out.println("Connection Successful");
                PreparedStatement preparedStatement = connection.prepareStatement("select * from blogs where username='"+username+"' ORDER BY id DESC fetch first 1 rows only");
                System.out.println("select * from blogs where username='"+username+"' ORDER BY id DESC fetch first 1 rows only;");
                ResultSet rs = preparedStatement.executeQuery();
                blog=createBlog(rs);
                connection.close();
        }
        catch (SQLException ex) {
                    System.out.println("Connect failed ! "+ex);
        }
        return blog;
    }
    /*
    @param username
    @returns int 
    
    Given a username it gives you the number of Blogs posted by that user.
    
    */
    public int checkNumberBlogs(String username){
        connectionDatabase c=new connectionDatabase();
        Connection connection=c.connect();
        try{
        PreparedStatement preparedStatement = connection.prepareStatement("select count(*) AS count from blogs where username='"+username+"'");
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            int number=rs.getInt("count");
            return number;
        }
        } catch (SQLException ex) {
                    System.out.println("Connect failed ! "+ex);
        }
        return 0;
    }
    /*
    
    @param ResultSet
    @returns username
    
    Returns a ResultSet of all the Blogs posted by that user.
    */
    public ResultSet userBlogs(String username){
        connectionDatabase c=new connectionDatabase();
        Connection connection=c.connect();
        ResultSet rs=null;
        try{
        PreparedStatement preparedStatement = connection.prepareStatement("select Blog_Text,title from blogs where username='"+username+"'");
        System.out.println("select Blog_Text,title from blogs where username='"+username+"'");
        rs = preparedStatement.executeQuery();
        return rs;
        } catch (SQLException ex) {
                    System.out.println("Connect failed ! "+ex);
        }
        return rs;
    }
}
