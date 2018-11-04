<%-- 
    Document   : index
    Created on : Nov 1, 2018, 3:35:25 PM
    Author     : hoshang
--%>
<%@page import="com.models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    </head>
    <body>
       
        
       <% // String username = (String) request.getSession().getAttribute("Username"); %>
       <% 
          User bean=(User) request.getSession().getAttribute("User"); 
          String username= null;
          if(bean!=null){
          username = bean.getUsername();
          }
          Boolean err=false;
          if(request.getAttribute("err")!= null){
         err= (Boolean) request.getAttribute("err");
          }
          
         %>
         
        <% if(username==null){ %>
        
        <div class="container-fluid">
       <div class="jumbotron text-center">
           <h2>Please enter your login details:</h2><br>
           <% if(err){ %>
        <p><a href="#" class="text-danger">Incorrect Username/ Password</a></p>
        <% } %>
        <form action="Users" method="post" id="myForm">  
        <div class="row">
            <div class="col-sm-16">
                Username:<input type="text" onchange="onsubmit()" name="name" id="name"><br> <br>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-16">
                Password:<input type="password" name="password"><br><br>
            </div>
        </div>
            
        <div class="row">
            <div class="col-sm-16 mt-10">
        <button onclick="submit()" class="btn btn-primary" type="submit"> Submit</button>
        
            </div>
            
        </div>
        </form> 
        
        <div class="row">
            <div class="col-sm-6">
                Database Usernames and Passwords:
            </div>
            </div>
            <div class="row">
            <div class="col-sm-6">
                Username: Hoshang Password: test
            </div>
            </div>
            <div class="row">
            <div class="col-sm-6">
                Username: Yixin Password:  not
            </div>
            </div>
            <div class="row">
            <div class="col-sm-6">
                Username: John Password: Doe
            </div>
            </div>
       </div>
       </div>
       <% } else { %>
        <%@include file="home.jsp" %>
       <% } %>
       
</body>
</html>
