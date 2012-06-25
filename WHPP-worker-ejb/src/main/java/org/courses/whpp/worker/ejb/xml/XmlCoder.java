/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.worker.ejb.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Scanner;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 *
 * @author NGAL
 */
@Stateless
@LocalBean
public class XmlCoder {

	public String convertToXML(Object object) throws Exception {

		StringBuilder builder = new StringBuilder();

		Serializer serializer = new Persister();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		serializer.write(object, baos);

		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

		Scanner scanner = new Scanner(bais);

		while (scanner.hasNext()) {
			builder.append(scanner.nextLine()).append("\n");
		}

		return builder.toString();
	}
}
