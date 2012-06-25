/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.worker.ejb;

import com.microsoft.windowsazure.services.core.storage.StorageException;
import com.microsoft.windowsazure.services.serviceBus.models.BrokeredMessage;
import java.io.IOException;
import javax.ejb.Local;

/**
 *
 * @author NGAL
 */
@Local
public interface MessageHandlerLocal {

	void handleMessage(BrokeredMessage message) throws IOException, ClassNotFoundException, StorageException;
}
