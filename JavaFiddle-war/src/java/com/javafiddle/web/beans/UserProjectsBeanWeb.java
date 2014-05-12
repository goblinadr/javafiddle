package com.javafiddle.web.beans;

import com.javafiddle.core.DataModel.Element;
import com.javafiddle.core.ejb.UserProjectsBeanLocal;
import com.javafiddle.web.utils.JSFHelper;
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

    public LinkedList <Element> getList() {
        Long userId = lb.getId();
        return upbl.getProjects(userId);
    }

}
