/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.worker.ejb;

import com.microsoft.windowsazure.services.serviceBus.models.BrokeredMessage;
import javax.ejb.Local;

/**
 *
 * @author NGAL
 */
@Local
public interface MessageHandlerLocal {

	void handleMessage(BrokeredMessage message);

}
