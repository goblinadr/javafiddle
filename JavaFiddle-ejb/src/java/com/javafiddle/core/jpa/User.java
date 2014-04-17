/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.javafiddle.core.jpa;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 *
 * @author vitaly
 */
@Entity
@Table(name = "users", schema = "public" )
@NamedQuery(name = "User.getAll", query = "SELECT c from User c")
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nickname")
    private String nickname;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "email")
    private String email;
    
    public User(){
        
    }
    public User(String nickname, String password, String email){
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String name) {
        this.nickname = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }
        
     public String getEmail(){
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if (!this.nickname.equals(other)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testsjdk7.jpa.Users[ id=" + id + " ]";
    }
}
