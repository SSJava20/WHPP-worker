/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.WorkerApi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author stvad
 */
@Path("/mobile")
public class api
{

    @GET
    @Produces(MediaType.TEXT_XML)
    public String sayXMLHello()
    {
        return "<?xml version=\"1.0\"?>" + "<hello> Hello" + "</hello>";
    }
    

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello()
    {
        return "<html> " + "<title>" + "Hello " + "</title>"
                + "<body><h1>" + "Hello " + "</body></h1>" + "</html> ";
    }
    
    @GET
    @Path("get_route")
    @Produces(MediaType.TEXT_XML)
    public Response getRoute(@HeaderParam("user_login") String id, @HeaderParam("user_pass") String passHash)
    {
//        Response response = new ResponseImpl();
        if(stubs.auth(id, passHash))
            return Response.status(200).entity(stubs.getMapForRoute(id)).build();   
        else 
            return Response.status(403).build();
    }
}
