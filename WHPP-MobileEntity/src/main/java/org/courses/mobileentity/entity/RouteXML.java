/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.mobileentity.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
//import javax.xml.bind.annotation.XmlAttribute;
//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlElementWrapper;
//import javax.xml.bind.annotation.XmlID;
//import javax.xml.bind.annotation.XmlRootElement;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 *
 * @author NGAL
 */
//@XmlRootElement
@Root
public class RouteXML implements Serializable {

	private static final long serialVersionUID = 1L;

	@Attribute(name="id")
        private String id;

	@Element
        private String name;

        @ElementList
	private List<RoutepointXML> routepointList;

	public RouteXML() {
	}

	public RouteXML(String id) {
		this.id = id;
	}

	public RouteXML(String id, String name) {
		this.id = id;
		this.name = name;
	}

//	@XmlID
//	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

//	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

//	@XmlElementWrapper(name = "points")
//	@XmlElement
	public List<RoutepointXML> getRoutepointList() {
		return routepointList;
	}

	public void setRoutepointList(List<RoutepointXML> routepointList) {
		this.routepointList = routepointList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof RouteXML)) {
			return false;
		}
		RouteXML other = (RouteXML) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.courses.whpp.worker.entity.Route[ id=" + id + " ]";
	}
}
