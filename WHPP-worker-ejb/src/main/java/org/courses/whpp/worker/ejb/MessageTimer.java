/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.worker.ejb;

import com.microsoft.windowsazure.services.core.ServiceException;
import com.microsoft.windowsazure.services.serviceBus.models.BrokeredMessage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author NGAL
 */
@Singleton
@Startup
public class MessageTimer implements MessageTimerLocal {

	@EJB
	public MessageReceiver messageReceiver;

	@EJB
	public MessageHandler messageHandler;

	@PostConstruct
	public void createTimer() {
		Timer t = new Timer();

		TimerTask task = new TimerTaskExt(messageHandler);

		t.schedule(task, 0, 1000);
	}

	class TimerTaskExt extends TimerTask {

		private MessageHandler m_handler;

		public TimerTaskExt(MessageHandler handler) {
			this.m_handler = handler;
		}

		@Override
		public void run() {
			try {
				BrokeredMessage message = messageReceiver.getMessage();
				if (message != null) {
					m_handler.handleMessage(message);
				}
			} catch (ServiceException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
