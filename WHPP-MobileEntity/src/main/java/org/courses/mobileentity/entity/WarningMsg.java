/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.mobileentity.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author NGAL
 */
//@Root
public class WarningMsg implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	private String message;

	private String driverId;

	private Date createdDate;

	public WarningMsg() {
		this.id = System.currentTimeMillis();
		this.createdDate = new Date();
	}

	@Override
	public int hashCode() {
		int hash = 5;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final WarningMsg other = (WarningMsg) obj;
		if ((this.message == null) ? (other.message != null) : !this.message.equals(other.message)) {
			return false;
		}
		if ((this.driverId == null) ? (other.driverId != null) : !this.driverId.equals(other.driverId)) {
			return false;
		}
		if (this.createdDate != other.createdDate && (this.createdDate == null || !this.createdDate.equals(other.createdDate))) {
			return false;
		}
		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
