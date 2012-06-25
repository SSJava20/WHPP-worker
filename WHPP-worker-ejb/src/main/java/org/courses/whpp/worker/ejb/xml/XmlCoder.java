/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.whpp.worker.ejb.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Scanner;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.courses.mobileentity.entity.RouteXML;

/**
 *
 * @author NGAL
 */
@Stateless
@LocalBean
public class XmlCoder {

	public String convertToXML(Object object) throws JAXBException {

		StringBuilder builder = new StringBuilder();

		JAXBContext context = JAXBContext.newInstance(object.getClass());
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		m.marshal(object, baos);

		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		Scanner scanner = new Scanner(bais);

		while (scanner.hasNext()) {
			builder.append(scanner.nextLine()).append("\n");
		}

		return builder.toString();
	}
}
