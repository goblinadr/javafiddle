/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.javafiddle.core.ejb;

import com.javafiddle.core.jpa.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vitaly
 */
@Stateless
public class LoginBean implements LoginBeanLocal {
    @PersistenceContext
    private EntityManager em;

    private String message;
    private boolean tryLogin(String nickname, String pwd) {
       User currentUser = null;
       Long userId = null;
       List<User> pass = em.createQuery("select u from User u where u.nickname =:nickname and u.password=:password")
                    .setParameter("nickname", nickname)
                    .setParameter("password", pwd).getResultList();
       if(pass == null || pass.isEmpty()){
            
           return false;
       }
      return true;
    }

    @Override
    public User performLogin(String nickname, String password) {
         User user = null;
         List<User> users = em.createQuery("select u from User u where u.nickname =:nickname")
                    .setParameter("nickname", nickname).getResultList();
       if(users == null || users.size() != 1){
           System.out.println("Login is not unique or no user with this login exists!");
          return user;
       }
       user = users.get(0);
     
       String pwd = password;
       password = null;       
        if(!tryLogin(nickname, pwd)) {
            return null;
        }
        return user;
    }
}
