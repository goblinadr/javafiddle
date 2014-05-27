package com.javafiddle.core.ejb;

import com.javafiddle.core.DataModel.Element;
import com.javafiddle.core.jpa.User;
import java.util.LinkedList;
import javax.ejb.Local;


@Local
public interface UserProjectsBeanLocal  {
    public LinkedList <  Element > getProjects(Long userId);
    public void addElement(String name, String type, String hash, Long parentId, Long creatorId);
    public void deleteElementFromDatabase(Long id);
    public void shareElement(Long fileId, String nickname );
            
}
