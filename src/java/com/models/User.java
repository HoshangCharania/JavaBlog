/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import java.io.Serializable;

/**
 *
 * @author hoshang
 */
public class User extends Object implements Serializable {
    
    private String username,password;
    /* User object */
    public User(){
        username=null;
        password=null;
    }
    
    /* Getters and Setters for User Object */ 
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
}
