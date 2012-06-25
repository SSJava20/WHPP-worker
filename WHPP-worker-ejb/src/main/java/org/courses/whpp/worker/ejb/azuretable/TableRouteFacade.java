/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.worker.ejb.azuretable;

import com.microsoft.windowsazure.services.core.storage.StorageException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.ejb.EJB;
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
public class TableRouteFacade {

	public static String ROUTE_PARTITION_KEY = "Route";

	@EJB
	TableStorage tableStorage;

	private TableStoragedClass prepareRouteToStore(RouteXML r, String driverId) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(r);

		TableStoragedClass tsc = new TableStoragedClass("Route", driverId, baos.toByteArray());
		return tsc;

	}

	public RouteXML getRouteForDriver(String driverId) throws IOException, ClassNotFoundException, StorageException {
		TableStoragedClass tsc = tableStorage.get(ROUTE_PARTITION_KEY, driverId);

		ByteArrayInputStream bais = new ByteArrayInputStream(tsc.getDecodedStoredString());
		ObjectInputStream ois = new ObjectInputStream(bais);
		return (RouteXML) ois.readObject();

	}

	public void create(RouteXML routeXML, String driverId) throws StorageException, IOException {
		tableStorage.save(prepareRouteToStore(routeXML, driverId));
	}
}
