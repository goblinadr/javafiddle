package com.javafiddle.core.DataModel;

import java.util.LinkedList;

/**
 *
 * @author Вадим
 */
public class Element {
    public String name;
    public String type;
    //public Element parent;
    public Boolean canEdit;
    public String filePath;
    public LinkedList <Element> child;
    public Long id;
    
    public Element(Long d, String n, String t, Boolean cEdit, String fp)
    {
        id = d;
        name = n;
        type = t;
        canEdit = cEdit;
        filePath = fp;
        child = new LinkedList <>();
    }
}
