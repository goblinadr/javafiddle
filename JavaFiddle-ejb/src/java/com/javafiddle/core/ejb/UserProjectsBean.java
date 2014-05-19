package com.javafiddle.core.ejb;

import com.javafiddle.core.DataModel.Element;
import com.javafiddle.core.jpa.Files;
import com.javafiddle.core.jpa.Permissions;
import com.javafiddle.core.jpa.User;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Вадим
 */
@Stateful
public class UserProjectsBean implements UserProjectsBeanLocal, Serializable {

    @PersistenceContext
    private EntityManager em;
    private LinkedList< Element> projects;

    @Override
    public LinkedList< Element> getProjects(Long userId) {
        projects = new LinkedList<>();
        List<User> myUser = em.createQuery("select u from User u where u.id =:UsId").setParameter("UsId", userId).getResultList();
        List<Files> projs = em.createQuery("select u from Files u where u.creator =:id AND u.type = 'Project'")
                .setParameter("id", myUser.get(0)).getResultList();
        if (projs != null) {
            for (int i = 0; i < projs.size(); i++) {
                Element pr = new Element(projs.get(i).getId(), projs.get(i).getName(), "Project", true, projs.get(i).getFilePath());
                projects.add(pr);
                getChildren(pr, projs.get(i), i, -1L);
            }
        }
        List <Permissions> pers = em.createQuery("select u from Permissions u where u.user =:id")
                .setParameter("id", myUser.get(0)).getResultList();
        if (pers != null) {
            for (int i = 0; i < pers.size(); i++) {
                Long fileId = pers.get(i).getId();
                em.flush();
                Files fls = em.find(Files.class, fileId);               
                while (!fls.getType().equals("Project")) {
                    List <Files> buf = em.createQuery("select u from Files u where u.id =: id ").setParameter("id", fls.getParent()).getResultList();
                    fls = buf.get(0);
                }
                Element pr = new Element(fls.getId(), fls.getName(), "Project", false, fls.getFilePath());
                if (fileId == fls.getId()) {
                    pr.canEdit = true;
                }
                projects.add(pr);
                getChildren(pr, fls, projects.size() - 1, fileId);

            }
        }
        return projects;
    }

    private void getChildren(Element parentE, Files parentF, int numOfProject, long AllowElId) {
        //
        List<Files> listOfFiles = em.createQuery("select u from Files u where u.parent =:id")
                .setParameter("id", parentF).getResultList();
        for (int i = 0; i < listOfFiles.size(); i++) {
            Element newEl = new Element(listOfFiles.get(i).getId(), listOfFiles.get(i).getName(), listOfFiles.get(i).getType(), parentE.canEdit, listOfFiles.get(i).getFilePath());
            if (listOfFiles.get(i).getId() == AllowElId) {
                newEl.canEdit = true;
            }
            //newEl.parent = parentE;
            parentE.child.add(newEl);
            if (listOfFiles.get(i).getType().equals("Folder")) {
                getChildren(newEl, listOfFiles.get(i), numOfProject, AllowElId);
            }
        }
    }
}
