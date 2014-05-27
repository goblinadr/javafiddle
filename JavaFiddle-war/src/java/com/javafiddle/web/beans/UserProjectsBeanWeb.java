package com.javafiddle.web.beans;

import com.javafiddle.core.DataModel.Element;
import com.javafiddle.core.ejb.UserProjectsBeanLocal;
import com.javafiddle.web.utils.JSFHelper;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.LinkedList;
import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class UserProjectsBeanWeb implements UserProjectsBeanWebLocal {

    @Inject
    private UserProjectsBeanLocal upbl;

    @Inject
    private LoginBean lb;

    private Long pressedButtonId;
    
    @Override
    public void share() {
        //Todo
        
    }

    @Override
    public LinkedList<Element> getList() {
        Long userId = lb.getId();
        return upbl.getProjects(userId);
    }
    
   private String getHash(String str) {
       
        MessageDigest md5 ;        
        StringBuilder  hexString = new StringBuilder();
       
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
    
    private String type;
    private String nameFile;
    
    
    @Override
    public void createFile(){
        System.out.println("Call create file: "+ nameFile+" type: "+type);
        upbl.addElement(nameFile, type, getHash(nameFile + new Long(new Date().getTime()).toString()), pressedButtonId, lb.getId());
    }
    

    public UserProjectsBeanLocal getUpbl() {
        return upbl;
    }

    public void setUpbl(UserProjectsBeanLocal upbl) {
        this.upbl = upbl;
    }

    public LoginBean getLb() {
        return lb;
    }

    public void setLb(LoginBean lb) {
        this.lb = lb;
    }

    public Long getPressedButtonId() {
        return pressedButtonId;
    }

    public void setPressedButtonId(Long pressedButtonId) {
        System.out.println(pressedButtonId);
        this.pressedButtonId = pressedButtonId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }
    @Override
    public void setButtonId(Long id) {
        pressedButtonId = id;
    }
    
    @Override
    public Long getButtonId(){
        return pressedButtonId;
    }
    
    @Override
    public void delete(){
        System.out.println("Call delete method, pressed tree node = "+pressedButtonId);
        if(pressedButtonId != null)
          upbl.deleteElementFromDatabase(pressedButtonId); //upbw.getButtonId() - это должно быть вместо константы
    }
    
    /*public String getParent(){
        return pressedButtonId.toString();
    }*/
}
