package com.javafiddle.web.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.javafiddle.web.tree.*;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("tree")
@SessionScoped
public class TreeService implements Serializable {
    Tree tree;
    IdList idList;
    
    public TreeService() {
        idList = new IdList();
        tree = new Tree();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTree(
            @Context HttpServletRequest request
            ) {
        if(tree.isEmpty()) {
            addExampleTree();
        }
        Gson gson = new GsonBuilder().create();
        return Response.ok(gson.toJson(tree), MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("filedata")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFileData(
            @Context HttpServletRequest request,
            @QueryParam("id") String element_id
            ) {
        Gson gson = new GsonBuilder().create();
        int start_pos = element_id.indexOf("_") + 1;
        int end_pos = element_id.indexOf('_', start_pos);
        Integer id = Integer.parseInt(element_id.substring(start_pos, end_pos));
        
        return Response.ok(gson.toJson(idList.getFile(id)), MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("addExampleTree")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void addExampleTree(
            @Context HttpServletRequest request
            ) {
        TreeProject tpr = tree.getProjectInstance(idList, "javafiddle");
        tpr.getPackageInstance(idList, "com.javafiddle.web.beans.death");
        TreePackage tp = tpr.getPackageInstance(idList, "com.javafiddle.web.projecttree.a.b.c.d.e.f.g.h.i");
        tp.addFile(idList, "class", "Reflections.java");
        tp = tpr.getPackageInstance(idList, "com.javafiddle.web.beans");
        tp.addFile(idList, "class", "CommonBean.java");
        tp.addFile(idList, "interface", "Example.txt");
        tp = tpr.getPackageInstance(idList, "com.javafiddle.web.codemirror"); 
        tp.addFile(idList, "class", "Dummy.java");
        tp.addFile(idList, "class", "FileEditions.java");
        tp = tpr.getPackageInstance(idList, "com.javafiddle.web.codemirror.gui.core");  
        tp.addFile(idList, "class", "ProjectEditions.java");
        tp = tpr.getPackageInstance(idList, "com.javafiddle.web.codemirror.gui.core.adding");  
        tp.addFile(idList, "class", "Tree.java");
        tpr.getPackageInstance(idList, "com.javafiddle.web.acore.appl");
        tpr.getPackageInstance(idList, "com.javafiddle.web.acore");
        tpr.getPackageInstance(idList, "com.javafiddle.web.acore.cpp");
        tpr.getPackageInstance(idList, "com.javafiddle.web.acore.cpp");
    }
    
    @POST
    @Path("addProject")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void addProject(
            @Context HttpServletRequest request,
            @QueryParam("name") String name
            ) {
        tree.addProject(idList, name);
    }
    
    @POST
    @Path("addPackage")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void addPackage(
            @Context HttpServletRequest request,
            @QueryParam("projectId") String idString,
            @QueryParam("name") String name
            ) {
        int id = parseId(idString);
        TreeProject tpr = idList.getProject(id);
        tpr.addPackage(idList, name);
    }
       
    @POST
    @Path("addFile")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void addFile(
            @Context HttpServletRequest request,
            @QueryParam("packageId") String idString,
            @QueryParam("name") String name,
            @QueryParam("type") String type
            ) {
        int id = parseId(idString);
        TreePackage tp = idList.getPackage(id);
        tp.addFile(idList, type, name);
     }
    
    @POST
    @Path("remove")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(
            @Context HttpServletRequest request,
            String idString
            ) {
        int id = parseId(idString);
        switch (idList.getType(id)) {
            case PROJECT:
                tree.deleteProject(idList, id);
                break;
            case PACKAGE:
                TreePackage tp = idList.getPackage(id);
                TreeProject tpr = idList.getProject(tp.getProjectId());
                tpr.deletePackage(idList, id);
                break;
            case FILE:
                TreeFile tf = idList.getFile(id);
                TreePackage tpack = idList.getPackage(tf.getPackageId());
                tpack.deleteFile(idList, id);
                break;
            default:
                break;
        }
     }
       
    private int parseId(String idString) {
        if (idString.indexOf("node_") == -1)
            return -1;
        return Integer.parseInt(idString.substring(idString.indexOf('_')+1));
    }

    private void addExampleTree() {
        TreeProject tpr = tree.getProjectInstance(idList, "javafiddle");
        tpr.getPackageInstance(idList, "com.javafiddle.web.beans.death");
        TreePackage tp = tpr.getPackageInstance(idList, "com.javafiddle.web.projecttree.a.b.c.d.e.f.g.h.i");
        tp.addFile(idList, "class", "Reflections.java");
        tp = tpr.getPackageInstance(idList, "com.javafiddle.web.beans");
        tp.addFile(idList, "class", "CommonBean.java");
        tp.addFile(idList, "interface", "Example.txt");
        tp = tpr.getPackageInstance(idList, "com.javafiddle.web.codemirror"); 
        tp.addFile(idList, "class", "Dummy.java");
        tp.addFile(idList, "class", "FileEditions.java");
        tp = tpr.getPackageInstance(idList, "com.javafiddle.web.codemirror.gui.core");  
        tp.addFile(idList, "class", "ProjectEditions.java");
        tp = tpr.getPackageInstance(idList, "com.javafiddle.web.codemirror.gui.core.adding");  
        tp.addFile(idList, "class", "Tree.java");
        tpr.getPackageInstance(idList, "com.javafiddle.web.acore.appl");
        tpr.getPackageInstance(idList, "com.javafiddle.web.acore");
        tpr.getPackageInstance(idList, "com.javafiddle.web.acore.cpp");
        tpr.getPackageInstance(idList, "com.javafiddle.web.acore.cpp");
    }
}
