/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.worker.ejb;

import com.microsoft.windowsazure.services.core.ServiceException;
import com.microsoft.windowsazure.services.core.storage.StorageException;
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
public class MessageTimer {

	@EJB
	private MessageHandlerLocal messageHandler;

	@EJB
	public MessageReceiver messageReceiver;

	@PostConstruct
	public void createTimer() {
		Timer t = new Timer();

		TimerTask task = new TimerTaskExt(messageHandler);

		t.schedule(task, 0, 5000);
	}

	class TimerTaskExt extends TimerTask {

		private MessageHandlerLocal m_handler;

		public TimerTaskExt(MessageHandlerLocal handler) {
			this.m_handler = handler;
		}

		@Override
		public void run() {
			try {
				BrokeredMessage message = messageReceiver.getMessage();
				if (message != null) {
					m_handler.handleMessage(message);
				}
			} catch (StorageException ex) {
				Logger.getLogger(MessageTimer.class.getName()).log(Level.SEVERE, null, ex);
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(MessageTimer.class.getName()).log(Level.SEVERE, null, ex);
			} catch (ServiceException ex) {
				Logger.getLogger(MessageTimer.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IOException ex) {
				Logger.getLogger(MessageTimer.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}
