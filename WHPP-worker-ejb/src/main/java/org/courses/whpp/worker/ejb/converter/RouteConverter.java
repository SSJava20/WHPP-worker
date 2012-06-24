/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.worker.ejb.converter;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import org.courses.mobileentity.entity.RouteXML;
import org.courses.whpp.entity.Route;

/**
 *
 * @author NGAL
 */
@Stateless
@LocalBean
public class RouteConverter {

	public RouteXML toXMLEntity ( Route route )
	{
		RouteXML routeXML= new RouteXML();
	routeXML.set	route.getId();
		route.getName();
		route.getRoutepointList();
	}

	public Route toFullEntity ( RouteXML routeXML)
	{

	}

}
