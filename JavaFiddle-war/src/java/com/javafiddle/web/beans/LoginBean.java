/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.javafiddle.web.beans;
import com.javafiddle.core.ejb.LoginBeanLocal;
import com.javafiddle.core.jpa.User;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author vitaly
 */
@Named(value = "LoginBean")
@SessionScoped
public class LoginBean implements Serializable{
    @EJB
    private LoginBeanLocal ejbLoginBean;
    /**
     * Creates a new instance of LoginBean
     */
    private User user;
    private String nickname;
    private String password;
    
    public LoginBean() {
    }
    
    public void login(String nickname, String password){
        this.user = ejbLoginBean.performLogin(nickname, password);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public Long getId(){
        return user.getId();
    }
    
}
