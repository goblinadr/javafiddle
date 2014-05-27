package com.javafiddle.web.tree;

import com.google.gson.Gson;
import com.javafiddle.core.DataModel.Element;
import com.javafiddle.core.ejb.UserProjectsBeanLocal;
import com.javafiddle.web.beans.UserProjectsBeanWebLocal;
import com.javafiddle.web.utils.JSFHelper;
import java.util.LinkedList;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Александр
 */
@Path("profileTree")
@RequestScoped
public class ProfileTreeWS {

    @Inject
    private UserProjectsBeanWebLocal upwb;

    @GET
    @Path("treeElements")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTree(
            @Context HttpServletRequest request
    ) {
        //   System.out.println("projects: "+adapter.getProjects().size()+" Instance: "+this);       
        Gson gson = new Gson();
        return Response.ok(gson.toJson(upwb.getList()), MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("nodeId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setNode(@Context HttpServletRequest request) {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            System.out.println("Click on tree node: "+id);
            upwb.setButtonId(id);
        } catch (NumberFormatException e) {
        }
        return null;
    }

}
