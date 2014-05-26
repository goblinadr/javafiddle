/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.javafiddle.web.beans;

import com.javafiddle.core.DataModel.Element;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Вадим
 */
public interface UserProjectsBeanWebLocal extends Serializable {
    
    public LinkedList <Element> getList();
    
    public void setButtonId(Long id);
    public Long getButtonId();
}
