/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.WorkerApi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.courses.mobileentity.entity.CoordsXML;
import org.courses.mobileentity.entity.RouteXML;
import org.courses.mobileentity.entity.RoutepointXML;

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
        return "<html> " + "<title>" + "Hello anonymous" + "</title>"
                + "<body><h1>" + "Hello " + "</body></h1>" + "</html> ";
    }

    @GET
    @Path("/get_route")
    @Produces(MediaType.TEXT_XML)
    public Response getRoute(@HeaderParam("user_login") String id, @HeaderParam("user_pass") String passHash) throws JAXBException
    {
        Long uid = null;
        try
        {
            uid = Long.parseLong(id);
        } catch (NumberFormatException ex)
        {
            return Response.status(400).build();
        }

        if (stubs.auth(uid, passHash))
        {

//            CoordsXML coordsXML = new CoordsXML("10", "coords", 10, 10);
//            CoordsXML coordsXML1 = new CoordsXML("11", "coords2", 11, 11);
//            
//            RouteXML routeXML = new RouteXML("56238", "route");
//            
//            RoutepointXML routepointXML = new RoutepointXML("10", "FUCK-POINT");
//            routepointXML.setCoordsId(coordsXML);
//            routepointXML.setRouteId(routeXML);
//            
//            RoutepointXML routepointXML1 = new RoutepointXML("11", "FUCK-POINT2");
//            routepointXML1.setCoordsId(coordsXML1);
//            routepointXML1.setRouteId(routeXML);
//            
//            ArrayList<RoutepointXML> list = new ArrayList<RoutepointXML>();
//            list.add(routepointXML);
//            list.add(routepointXML1);
//            
//            routeXML.setRoutepointList(list);
//            
//            JAXBContext context = JAXBContext.newInstance(RouteXML.class);
//            Marshaller m = context.createMarshaller();
//            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            
//            m.marshal(routeXML, baos);
//            m.marshal(routeXML, System.out);
//            
//            Unmarshaller handler = context.createUnmarshaller();
//// Get the object
//
//            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
//            RouteXML msg = (RouteXML) handler.unmarshal(bais);
//            
//            System.out.println(msg);
//            return Response.status(200).entity(baos.toString()).build();
            return Response.status(200).entity(stubs.getMapForRoute(id)).build();

        } else
        {
            return Response.status(401).build();
        }
    }

    @POST
    @Path("/put_point")
    @Consumes(MediaType.APPLICATION_XML)
    public Response putPoint(@HeaderParam("user_login") String id, @HeaderParam("user_pass") String passHash,
            JAXBElement<RoutepointXML> point)
    {
        Long uid = null;
        try
        {
            uid = Long.parseLong(id);
        } catch (NumberFormatException ex)
        {
            return Response.status(400).build();
        }

        if (stubs.auth(uid, passHash))
        {
            if (stubs.markPoint(point.getValue(), uid))
            {
                return Response.status(201).build();
            } else
            {
                return Response.status(403).build();
            }
        } else
        {
            return Response.status(401).build();
        }
    }

    @POST
    @Path("/put_warning_msg")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response putWarningMessage(@HeaderParam("user_login") String id, @HeaderParam("user_pass") String passHash,
            String msgText)
    {
        Long uid = null;
        try
        {
            uid = Long.parseLong(id);
        } catch (NumberFormatException ex)
        {
            return Response.status(400).build();
        }

        if (stubs.auth(uid, passHash))
        {
            if (stubs.warning(msgText))
            {
                return Response.status(201).build();
            } else
            {
                return Response.status(403).build();
            }
        } else
        {
            return Response.status(401).build();
        }
    }
}
