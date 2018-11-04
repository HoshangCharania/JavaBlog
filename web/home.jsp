<%-- 
    Document   : home.jsp
    Created on : Nov 1, 2018, 3:52:27 PM
    Author     : hoshang
--%>
<%@page import="com.models.User"%> 
<%@page import="com.models.Blog"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    </head>
    <body>
        <% 
         User current_bean_user=(User) request.getSession().getAttribute("User");  
         String current_user = null;
         String current_title = null;
         String current_blogText = null; 
         if(current_bean_user !=null){
         current_user = current_bean_user.getUsername();
         }
         Blog current_bean_blog= (Blog) request.getSession().getAttribute("Blog");
         String current_blog = null;
         if(current_bean_blog !=null){
         current_title = current_bean_blog.getTitle();
         current_blogText=current_bean_blog.getBlogText();
         }
        %>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Blogs</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
      <li><a href="Blogs">Your Blogs</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="Users?action=delete"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
    </ul>
  </div>
</nav>
<p class="bg-info"><%= current_user %></p>
        
        
        <div class="container-fluid">
            <div class="header">
                <h1>Your most recent blog post: <%= current_user %> </h1>
            </div>
            <div class="jumbotron">
                <div class="row">
                    <div class="col-sm-6">
                <h2><%= current_title  %></h2>
                    </div>
                </div>
                   
                <div class="row">
                    <div class="col-sm-6">
                <p><%= current_blogText %></p>
                    </div>
                    <div class="col-sm-6">
                        <img src="img/blog.jpg" class="rounded float-right" alt="Responsive image">
                    </div>
                </div>
                    
            </div>
        </div>
    </body>
</html>
