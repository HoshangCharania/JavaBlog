/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author hoshang
 */
public class connectionDatabase {
    /*
    Basic connection to database.
    
    @returns Connection
    */
    public Connection connect(){
    try {
        	String driver = "org.apache.derby.jdbc.ClientDriver";
                Class.forName("org.apache.derby.jdbc.ClientDriver");   
                String url = "jdbc:derby://localhost:1527/blogger";
                Connection c = DriverManager.getConnection(url,"blogger","blogger");
                return c;
        }
        catch(ClassNotFoundException e){
                    System.out.println(e);
        }
        catch (SQLException ex) {
                    System.out.println("Connect failed ! "+ex);
        }
    return null;
    }
}
