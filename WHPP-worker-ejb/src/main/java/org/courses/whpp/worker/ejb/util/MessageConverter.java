/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.worker.ejb.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import org.courses.whpp.message.Message;

/**
 *
 * @author NGAL
 */
@Stateless
public class MessageConverter {

	/**
	 * Retrieve Message object from stream
	 *
	 * @param stream
	 * @return Message object
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Message convert(InputStream stream) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(stream);
		return (Message) ois.readObject();
	}
}
