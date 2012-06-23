/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.WorkerApi;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

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
    @Path("/get_route")
    @Produces(MediaType.TEXT_XML)
    public Response getRoute(@HeaderParam("user_login") String id, @HeaderParam("user_pass") String passHash)
    {
        if (stubs.auth(Long.parseLong(id), passHash))
        {
            return Response.status(200).entity(stubs.getMapForRoute(id)).build();
        } else
        {
            return Response.status(401).build();
        }
    }

    @PUT
    @Path("/put_point")
    @Consumes(MediaType.APPLICATION_XML)
    public Response putPoint(@HeaderParam("user_login") String id, @HeaderParam("user_pass") String passHash,
            JAXBElement<stubs.PointToSend> point)
    {
        Long uid = Long.parseLong(id);
        
        if (stubs.auth(uid, passHash))
        {
            if(stubs.markPoint(point.getValue(), uid))
                return Response.status(201).build();
            else
                return Response.status(403).build();            
        } else
        {
            return Response.status(401).build();
        }
    }
}
