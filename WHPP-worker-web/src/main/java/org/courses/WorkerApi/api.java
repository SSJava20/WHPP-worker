/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.WorkerApi;

import com.microsoft.windowsazure.services.core.storage.StorageException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
import org.courses.whpp.entity.User;
import org.courses.whpp.session.UserFacade;
import org.courses.whpp.worker.ejb.MessageHandler;
import org.courses.whpp.worker.ejb.MessageHandlerLocal;
import org.courses.whpp.worker.ejb.azuretable.TableRouteFacade;
import org.courses.whpp.worker.ejb.security.Authenticator;
import org.courses.whpp.worker.ejb.xml.XmlCoder;

/**
 *
 * @author stvad
 */
@Path("/mobile")
@Singleton
public class api {

	@EJB
	Authenticator authenticator;

	@EJB
	TableRouteFacade routeFacade;

	@EJB
	XmlCoder coder;

	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayXMLHello() {
		return "<?xml version=\"1.0\"?>" + "<hello> Hello" + "</hello>";
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		return "<html> " + "<title>" + "Hello anonymous" + "</title>"
				+ "<body><h1>" + "Hello " + "</body></h1>" + "</html> ";
	}

	@GET
	@Path("/get_route")
	@Produces(MediaType.TEXT_XML)
	public Response getRoute(@HeaderParam("user_login") String id, @HeaderParam("user_pass") String passHash) throws JAXBException, IOException, ClassNotFoundException, StorageException {

		if (authenticator.isDriverExists(id, passHash)) {

			String answer = coder.convertToXML(routeFacade.getRouteForDriver(id));

			return Response.status(200).entity(answer).build();

		} else {
			return Response.status(401).build();
		}
	}

	@POST
	@Path("/put_point")
	@Consumes(MediaType.APPLICATION_XML)
	public Response putPoint(@HeaderParam("user_login") String id, @HeaderParam("user_pass") String passHash,
			JAXBElement<stubs.PointToSend> point) {
		Long uid = null;
		try {
			uid = Long.parseLong(id);
		} catch (NumberFormatException ex) {
			return Response.status(400).build();
		}

		if (stubs.auth(uid, passHash)) {
			if (stubs.markPoint(point.getValue(), uid)) {
				return Response.status(201).build();
			} else {
				return Response.status(403).build();
			}
		} else {
			return Response.status(401).build();
		}
	}
}
