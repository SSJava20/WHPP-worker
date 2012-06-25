/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.worker.ejb.util;

import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import org.courses.mobileentity.entity.CoordsXML;
import org.courses.mobileentity.entity.RouteXML;
import org.courses.mobileentity.entity.RoutepointXML;
import org.courses.whpp.entity.Coords;
import org.courses.whpp.entity.Route;
import org.courses.whpp.entity.Routepoint;

/**
 *
 * @author NGAL
 */
@Stateless
@LocalBean
public class EntityXMLConverter {

	public RouteXML toXMLRoute(Route route) {
		RouteXML routeXML = new RouteXML();
		routeXML.setId(String.valueOf(route.getId()));
		routeXML.setName(route.getName());

		ArrayList<RoutepointXML> routepointXMLs = new ArrayList<RoutepointXML>();

		if (route.getRoutepointList() != null) {
			for (int i = 0; i < route.getRoutepointList().size(); i++) {

				Routepoint p = route.getRoutepointList().get(i);
				RoutepointXML pXML = toXMLRoutepoint(p);
				pXML.setRouteId(routeXML);
				routepointXMLs.add(pXML);
			}
		}

		routeXML.setRoutepointList(routepointXMLs);

		return routeXML;
	}

	public Route toFullRoute(RouteXML routeXML) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	private RoutepointXML toXMLRoutepoint(Routepoint routepoint) {
		RoutepointXML routepointXML = new RoutepointXML();

		routepointXML.setId(String.valueOf(routepoint.getId()));
		routepointXML.setName(routepoint.getName());
		routepointXML.setCoordsId(toXMLCoords(routepoint.getCoordsId()));

		return routepointXML;
	}

	private Routepoint toFullRoutepoint(RoutepointXML routepointXML) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	private CoordsXML toXMLCoords(Coords coords) {

		CoordsXML coordsXML = new CoordsXML();

		coordsXML.setId(String.valueOf(coords.getId()));
		coordsXML.setAddress(coords.getAddress());
		coordsXML.setLatitude(coords.getLatitude());
		coordsXML.setLongitude(coords.getLongitude());
		return coordsXML;
	}

	private Coords toFullCoords(CoordsXML coordsXML) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
}
