/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.worker.ejb;

import com.microsoft.windowsazure.services.core.storage.StorageException;
import com.microsoft.windowsazure.services.serviceBus.models.BrokeredMessage;
import java.io.IOException;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import org.courses.mobileentity.entity.RouteXML;
import org.courses.whpp.entity.Route;
import org.courses.whpp.message.Message;
import org.courses.whpp.session.RouteFacade;
import org.courses.whpp.azuretable.TableRouteFacade;
import org.courses.whpp.worker.ejb.util.EntityXMLConverter;
import org.courses.whpp.worker.ejb.util.MessageConverter;

/**
 *
 * @author NGAL
 */
@Stateless
@Local
public class MessageHandler implements MessageHandlerLocal {

	@EJB
	MessageConverter messageConverter;

	@EJB
	EntityXMLConverter entityXMLConverter;

	@EJB
	TableRouteFacade tableRouteFacade;

	@EJB
	RouteFacade routeFacade;

	@Override
	public void handleMessage(BrokeredMessage brokeredMessage) throws StorageException, IOException, ClassNotFoundException {
		System.out.println("I receive new message: " + brokeredMessage.getLabel());
		Message message = messageConverter.convert(brokeredMessage.getBody());

		Route r = routeFacade.find(message.getRouteId());

		RouteXML routeXML = entityXMLConverter.toXMLRoute(r);

		tableRouteFacade.create(routeXML, message.getDriverId());
	}
}
