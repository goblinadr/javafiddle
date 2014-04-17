/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.javafiddle.core.data;

import com.javafiddle.core.jpa.Files;
import com.javafiddle.core.jpa.User;

/**
 *
 * @author vitaly
 */
public class Permissions {
    private Long id;
    private com.javafiddle.core.jpa.Files file;
    private com.javafiddle.core.jpa.User user;
    private String permission;

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
