/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.worker.ejb;

import com.microsoft.windowsazure.services.serviceBus.models.BrokeredMessage;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Stateless;

/**
 *
 * @author NGAL
 */
@Stateless
public class MessageHandler{

//	@Override
	public void handleMessage(BrokeredMessage message) {

		System.out.println("I receive new message: " + message.getLabel());
		
	}
}
