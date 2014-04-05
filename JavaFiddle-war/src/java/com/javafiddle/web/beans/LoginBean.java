/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.javafiddle.web.beans;
import com.javafiddle.core.ejb.LoginBeanLocal;
import com.javafiddle.core.jpa.User;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author vitaly
 */
@Named
@SessionScoped
public class LoginBean implements Serializable{
    @Inject
    private LoginBeanLocal ejbLoginBean;
    /**
     * Creates a new instance of LoginBean
     */
    private User user;
    private String nickname;
    private String password;
    
    public LoginBean() {
    }
    
    public void login(){
        boolean error = false;
        FacesContext context = FacesContext.getCurrentInstance();
        if (this.nickname.isEmpty() || this.password.isEmpty()){
            context.addMessage("loginErrors", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Some field is empty", "Some field input failed"));
            error = true;           
        }                
        if (!error){
            this.user = ejbLoginBean.performLogin(nickname, password);
            if (this.user != null){               
              FacesContext fc = FacesContext.getCurrentInstance();
  NavigationHandler nh = fc.getApplication().getNavigationHandler();
  nh.handleNavigation(fc, null, "index");   
            //context.addMessage("loginErrors", new FacesMessage(FacesMessage.SEVERITY_ERROR, "GOOOD", "good"));
            }
            else    context.addMessage("loginErrors", new FacesMessage(FacesMessage.SEVERITY_ERROR, "incorrect username or password", "incorrect username or password"));


        }
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
