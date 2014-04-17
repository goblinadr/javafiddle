/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.javafiddle.core.data;

import com.javafiddle.core.jpa.User;
import java.util.Date;

/**
 *
 * @author vitaly
 */
public class Files {
    private Long id;
    private String name;
    private String filePath;
    private Date date;
    private String type;
    private com.javafiddle.core.jpa.User creator;
    private com.javafiddle.core.jpa.Files parent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public com.javafiddle.core.jpa.Files getParent() {
        return parent;
    }

    public void setParent(com.javafiddle.core.jpa.Files parent) {
        this.parent = parent;
    }

}
