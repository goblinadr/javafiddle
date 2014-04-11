/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.javafiddle.core.ejb;

import com.javafiddle.core.jpa.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vitaly
 */
@Stateful
public class RegistrationBean implements RegistrationBeanLocal {
    @PersistenceContext
    private EntityManager em;
    
    private String message;
    @Override
    public User addNewUser(String nickname, String password, String email) {
        User user= null;
            if (tryAddNewUser(nickname, email)){                 
                user = new User();
                user.setNickname(nickname);
                user.setPassword(getHash(password));
                user.setEmail(email);
                em.persist(user);
                System.out.println("User has been created: " + user);
                System.out.println("Change status of user[" + user.getId() + "], current status: " + user.getNickname());
            }
            else message = "Nickname or email is not empty";
        return user;        
    }
    
    private boolean tryAddNewUser(String nickname, String email){
        List<User> users = em.createQuery("select u from User u where u.nickname =:nickname or u.email=:email")
                    .setParameter("nickname", nickname)
                    .setParameter("email", email).getResultList();
        if (users.isEmpty()) 
            return true;
        else
            return false;
    }
     private String getHash(String str) {
        
        MessageDigest md5 ;        
        StringBuffer  hexString = new StringBuffer();
        
        try {
                                    
            md5 = MessageDigest.getInstance("md5");
            
            md5.reset();
            md5.update(str.getBytes()); 
                        
                        
            byte messageDigest[] = md5.digest();
                        
            for (int i = 0; i < messageDigest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            }
                                                                                        
        } 
        catch (NoSuchAlgorithmException e) {                        
            return e.toString();
        }
        
        return hexString.toString();
    }
     
    @Override
    public String getMessage(){
        return this.message;
    }
}