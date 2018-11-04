<%-- 
    Document   : blog
    Created on : Nov 3, 2018, 12:38:08 PM
    Author     : hoshang
--%>
<%@page import="com.models.User"%> 
<%@page import="com.models.Blog"%> 
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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <title>Your blogs</title>
    </head>
    <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Blogs</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="Users">Home</a></li>
      <li  class="active"><a href="#">Your Blogs</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="Users?action=delete"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
    </ul>
  </div>
</nav>
<p class="bg-info"><%= current_user %></p>
        
        
        <div class="container-fluid">
            <div class="jumbotron">
                <h1>Your Total Blogs: <%= request.getAttribute("totalBlogs") %></h1>
                <% String[] titles=(String[]) request.getAttribute("titles");
                   String[] blogTexts=(String[]) request.getAttribute("blogTexts");
                   int totalBlogs= (int) request.getAttribute("totalBlogs");
                %>
                
                <% for(int i=0;i<=totalBlogs-1;i++) {  %>
                <div class="row">
                    <div class="col-sm-6">
                        <h3><%= titles[i] %></h3>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <p><%= blogTexts[i] %></p>
                    </div>
                </div>
                <% }%>
            </div>
        </div>
    </body>
</html>
