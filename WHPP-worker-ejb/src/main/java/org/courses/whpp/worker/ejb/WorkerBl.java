/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.worker.ejb;

import com.microsoft.windowsazure.services.core.storage.StorageException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import org.courses.mobileentity.entity.RouteXML;
import org.courses.mobileentity.entity.RoutepointXML;
import org.courses.whpp.worker.ejb.azuretable.TableRouteFacade;
import org.courses.whpp.worker.ejb.xml.XmlCoder;

/**
 *
 * @author NGAL
 */
@Stateless
@LocalBean
public class WorkerBl {

	@EJB
	TableRouteFacade routeFacade;

	@EJB
	XmlCoder coder;

	public String getRouteForDriverId(String driverId) throws Exception {
		return coder.convertToXML(routeFacade.getRouteForDriver(driverId));
	}

	public void checkPoint(String driverId, String point) throws Exception {
		RoutepointXML routepointXML = (RoutepointXML) coder.convertFromXML(point, RoutepointXML.class);

		RouteXML routeXML = routeFacade.getRouteForDriver(driverId);

		List<RoutepointXML> list = routeXML.getRoutepointList();

		for (int i = 0; i < list.size(); i++) {
			RoutepointXML p = list.get(i);
			if (p.equals(routepointXML)) {

				p.setIsPassed(true);
				p.setPassedTime(new Date());

				routeFacade.createOrReplace(routeXML, driverId);

				Logger.getLogger(WorkerBl.class.getName()).log(Level.INFO, "Driver {0} checked point {1}", new Object[]{driverId, routepointXML});
			}
		}
	}
}
