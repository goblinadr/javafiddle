package com.javafiddle.web.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Tree implements Serializable {
    private String projectHash;
    private List<TreeProject> projects = new ArrayList<>();

    public String getProjectHash() {
        return projectHash;
    }

    public void setProjectHash(String projectHash) {
        this.projectHash = projectHash;
    }
    
    public boolean isEmpty() {
        return projects.isEmpty();
    }

    public List<TreeProject> getProjects() {
        return projects;
    }
    
    public TreeProject addProject(IdList idList, String projectName) {
        for (TreeProject temp : projects)
            if (projectName.equals(temp.getName()))
                return null;
        TreeProject tpr = new TreeProject(projectName);
        projects.add(tpr);
        tpr.setId(idList.add(tpr));
        Collections.sort(projects);
        
        return tpr;
    }
        
    public TreeProject getProjectInstance(IdList idList, String projectName) {
        for (TreeProject temp : projects)
            if (projectName.equals(temp.getName()))
                return temp;
        TreeProject tpr = new TreeProject(projectName);
        projects.add(tpr);
        tpr.setId(idList.add(tpr));
        
        return tpr;
    }
    
    public void deleteProject(IdList idList, int projectId) {
        TreeProject tpr = idList.getProject(projectId);
        for (TreePackage temp : tpr.getPackages()) {
            for (TreeFile tempFile : temp.getFiles())
                idList.remove(tempFile.getId());
            idList.remove(temp.getId());
        }
        
        projects.remove(tpr);
        idList.remove(projectId);
    }

    public static ArrayList<String> getPackagesNames(List packages) {
        HashSet<String> set = new HashSet<>();
        Iterator<TreePackage> iterator = packages.iterator();
	while (iterator.hasNext()) {
            set.addAll(Tree.getAllPossiblePackages(iterator.next().getName()));
	}
        return new ArrayList<>(set);
    }
    
    public static HashSet<String> getAllPossiblePackages(String name) {
        HashSet<String> list = new HashSet<>();
        String[] possiblepacks = name.split("\\.");
        String current = "";
        for(String pack : possiblepacks) {
            current += current.isEmpty() ? pack : "." + pack;
            list.add(current);
        }
        return list;
    }

    public String toJSON() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");  
        sb.append("\"projects\"").append(":").append("[");
        for (TreeProject entry : projects)
            sb.append(entry.toJSON()).append(", ");
        sb.delete(sb.length()-2, sb.length());
        sb.append("]").append("}");
        return sb.toString();
    }
}
