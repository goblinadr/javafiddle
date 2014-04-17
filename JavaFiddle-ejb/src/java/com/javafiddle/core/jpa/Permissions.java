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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author vitaly
 */
@Entity
@Table(name = "permissions", schema = "public" )
@NamedQuery(name = "Permissions.getAll", query = "SELECT c from Permissions c")
public class Permissions implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "permission_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "file_id", referencedColumnName = "file_id" )
    private Files file;
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id" )
    private User user;
    
    @Column(name = "permission")
    private String permission;
    
    public Permissions(){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Files getFile() {
        return file;
    }

    public void setFile(Files file) {
        this.file = file;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
    
}
