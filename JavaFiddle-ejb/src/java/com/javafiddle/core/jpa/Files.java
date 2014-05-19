/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.javafiddle.core.jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vitaly
 */
@Entity
@Table(name = "files", schema = "public" )
@NamedQuery(name = "Files.getAll", query = "SELECT c from Files c")
public class Files implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "file_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fileId;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "file_path")
    private String filePath;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "last_modification_date")
    private Date date;
    
    @Column(name = "type")
    private String type;
    
    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "user_id" )
    private User creator;
    
    @ManyToOne
    @JoinColumn(name = "parent_id", columnDefinition = "integer")
    private Files parent;
    
   /* @OneToMany(mappedBy = "parent", cascade = {CascadeType.ALL} )
    private List<Files> children;*/

   /* public List<Files> getChildren() {
        return children;
    }

    public void setChildren(List<Files> children) {
        this.children = children;
    }*/
    
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    public Files getParent() {
        return parent;
    }

    public void setParent(Files parent) {
        this.parent = parent;
    }
  
    public Files(){
        
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return fileId;
    }

    public String getName() {
        return name;
    }

   
    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public User getCreator() {
        return creator;
    }

    public void setId(Long id) {
        this.fileId = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
    
    
    
    
    
}
