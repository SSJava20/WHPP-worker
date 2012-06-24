/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.worker.ejb;

import com.microsoft.windowsazure.services.core.Configuration;
import com.microsoft.windowsazure.services.core.ServiceException;
import com.microsoft.windowsazure.services.serviceBus.ServiceBusConfiguration;
import com.microsoft.windowsazure.services.serviceBus.ServiceBusContract;
import com.microsoft.windowsazure.services.serviceBus.ServiceBusService;
import com.microsoft.windowsazure.services.serviceBus.models.BrokeredMessage;
import com.microsoft.windowsazure.services.serviceBus.models.ReceiveMessageOptions;
import com.microsoft.windowsazure.services.serviceBus.models.ReceiveMode;
import com.microsoft.windowsazure.services.serviceBus.models.ReceiveSubscriptionMessageResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

/**
 *
 * @author GAL
 */
@Singleton
public class MessageReceiver {

	final static String TOPIC_PATH = "web_receive";

	final static String SUB_NAME = "web";

	final static String SERVICEBUS_NAME = "WHPPServiceBus";

	final static String OWNER = "owner";

	final static String KEY = "OTUSJ7fJBhEqWGwFdunLLbZA/45AeMxeDcSkH+4O418=";

	static Configuration config;

	static ServiceBusContract serviceBusContract;

	@PostConstruct
	public void configureBus() {
		config = ServiceBusConfiguration.configureWithWrapAuthentication(SERVICEBUS_NAME, OWNER,
				KEY);

		serviceBusContract = ServiceBusService.create(config);
	}

	/**
	 *
	 * Receive message from servicebus and return it. If servicebus empty -
	 * return null
	 *
	 * @return received message
	 * @throws ServiceException
	 * @throws IOException
	 */
//	@Override
	public BrokeredMessage getMessage() throws ServiceException, IOException {

		if (isContainMessage()) {
			ReceiveMessageOptions options = new ReceiveMessageOptions();
			options.setReceiveMode(ReceiveMode.RECEIVE_AND_DELETE);

			ReceiveSubscriptionMessageResult result = serviceBusContract.receiveSubscriptionMessage(TOPIC_PATH, SUB_NAME, options);
			return result.getValue();
		}

		return null;

	}

	/**
	 * Is servicebus contain at least 1 message
	 *
	 * @return
	 * @throws ServiceException
	 */
	private boolean isContainMessage() throws ServiceException {
		if (serviceBusContract.getSubscription(TOPIC_PATH, SUB_NAME).getValue().getMessageCount() == 0) {
			return false;
		}
		return true;
	}
}
