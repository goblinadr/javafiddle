/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.javafiddle.web.beans;

import com.javafiddle.core.ejb.RegistrationBeanLocal;
import com.javafiddle.core.jpa.User;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

/**
 *
 * @author vitaly
 */
@Named
@RequestScoped
public class RegistrationBean {
    @EJB
    private RegistrationBeanLocal ejbRegistrationBean;
   
    private User user;
    private String nickname;
    private String password;
    private String email;
    private String repeatPassword;

    public void addNewUser(AjaxBehaviorEvent event){
        boolean error = false;
        FacesContext context = FacesContext.getCurrentInstance();
        if (this.nickname.isEmpty()){    
            context.addMessage("registerErrors", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username field is not filled", "Some field input failed"));
            error = true;
        }
        if (this.email.isEmpty()){
            context.addMessage("registerErrors", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email field is not filled", "Some field input failed"));
            error = true;
        }
        if (this.password.isEmpty() || this.repeatPassword.isEmpty()){
            context.addMessage("registerErrors", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password field is not filled", "Some field input failed"));
            error = true;
        }
        if (this.password.equals(this.repeatPassword)){
            user = ejbRegistrationBean.addNewUser(this.nickname, this.password, this.email);
            if (user == null) 
                context.addMessage("registerErrors", new FacesMessage(FacesMessage.SEVERITY_ERROR, ejbRegistrationBean.getMessage(), ejbRegistrationBean.getMessage()));

        }
        else    
            context.addMessage("registerErrors", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords are not equal", "Some field input failed"));
    
        //System.out.println("ADD NEW USER");
    }
    
    
    public RegistrationBean() {
    }
    
    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }
    
    
}
