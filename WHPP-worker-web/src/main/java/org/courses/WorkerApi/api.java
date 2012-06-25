/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.WorkerApi;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.courses.whpp.worker.ejb.WorkerBl;
import org.courses.whpp.worker.ejb.security.Authenticator;

/**
 *
 * @author stvad
 */
@Path("/mobile")
@Stateless
public class api {

	@EJB
	Authenticator authenticator;

	@EJB
	WorkerBl workerBl;

	public static String OK_MESSAGE = "OK";

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
	public Response getRoute(@HeaderParam("user_login") String id, @HeaderParam("user_pass") String passHash) {
		try {
			if (authenticator.isDriverExists(id, passHash)) {

				String answer = workerBl.getRouteForDriverId(id);

				return Response.status(200).entity(answer).build();

			} else {
				return Response.status(401).build();
			}
		} catch (Exception ex) {
			Logger.getLogger(api.class.getName()).log(Level.SEVERE, null, ex);
		}
		return Response.status(400).build();
	}

	@POST
	@Path("/put_point")
	public Response putPoint(@HeaderParam("user_login") String id, @HeaderParam("user_pass") String passHash, String point) {
		try {
			if (authenticator.isDriverExists(id, passHash)) {

				workerBl.checkPoint(id, point);

				return Response.status(200).entity(OK_MESSAGE).build();

			} else {
				return Response.status(401).build();
			}
		} catch (Exception ex) {
			Logger.getLogger(api.class.getName()).log(Level.SEVERE, null, ex);
		}
		return Response.status(400).build();
	}

	@POST
	@Path("/put_warning_msg")
	public Response putWarningMessage(@HeaderParam("user_login") String id, @HeaderParam("user_pass") String passHash, String msgText) {
		try {
			if (authenticator.isDriverExists(id, passHash)) {

				workerBl.receiveWarningMessage(msgText, id);

				return Response.status(200).entity(OK_MESSAGE).build();

			} else {
				return Response.status(401).build();
			}
		} catch (Exception ex) {
			Logger.getLogger(api.class.getName()).log(Level.SEVERE, null, ex);
		}
		return Response.status(400).build();
	}
}
