package com.javafiddle.core.DataModel;

import java.util.LinkedList;

/**
 *
 * @author Вадим
 */
public class Element {
    public String name;
    public String type;
    public Element parent;
    public Boolean canEdit;
    public String filePath;
    
    public Element(String n, String t, Boolean cEdit, String fp)
    {
        name = n;
        type = t;
        canEdit = cEdit;
        filePath = fp;
    }
}
