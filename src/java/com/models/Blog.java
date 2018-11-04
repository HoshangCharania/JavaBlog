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
public class Blog extends Object implements Serializable  {
     private String title,blogText,username;
     private int id;
     /* Blog Object */
     public Blog(){
            title=null;
            blogText=null;
            username=null;
            id=0;
        }
     /* Getters and Setters for Blog Object */
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getBlogText(){
        return this.blogText;
    }
    public void setBlogText(String blogText){
        this.blogText = blogText;
    }
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    
}
