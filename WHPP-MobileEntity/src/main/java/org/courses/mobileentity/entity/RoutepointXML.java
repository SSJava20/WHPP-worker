/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.mobileentity.entity;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NGAL
 */
@XmlRootElement
public class RoutepointXML implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String name;

	private RouteXML routeId;

	private CoordsXML coordsId;

	private boolean isPassed;

	private Date passedTime;

	private String driverId;

	public RoutepointXML() {
	}

	public RoutepointXML(String id) {
		this.id = id;
	}

	public RoutepointXML(String id, String name) {
		this.id = id;
		this.name = name;
	}

	@XmlID
	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlIDREF
	public RouteXML getRouteId() {
		return routeId;
	}

	public void setRouteId(RouteXML routeId) {
		this.routeId = routeId;
	}

	@XmlElement
	public CoordsXML getCoordsId() {
		return coordsId;
	}

	public void setCoordsId(CoordsXML coordsId) {
		this.coordsId = coordsId;
	}

	public boolean isIsPassed() {
		return isPassed;
	}

	public void setIsPassed(boolean isPassed) {
		this.isPassed = isPassed;
	}

	public Date getPassedTime() {
		return passedTime;
	}

	public void setPassedTime(Date passedTime) {
		this.passedTime = passedTime;
	}

	public String getDriver() {
		return driverId;
	}

	public void setDriver(String driver) {
		this.driverId = driver;
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
		if (!(object instanceof RoutepointXML)) {
			return false;
		}
		RoutepointXML other = (RoutepointXML) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.courses.whpp.worker.entity.Routepoint[ id=" + id + " ]";
	}
}
