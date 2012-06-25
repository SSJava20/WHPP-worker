/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.mobileentity.entity;

import java.io.Serializable;
import java.util.List;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 *
 * @author NGAL
 */
//@Root
public class CoordsXML implements Serializable
{

    private static final long serialVersionUID = 1L;
    
    @Attribute(name="id")
    private String id;
    
    @Element
    private String address;
    
    @Element
    private double latitude;
    
    @Element
    private double longitude;
    
//    @ElementList
    private List<RoutepointXML> routepointList;

    public CoordsXML()
    {
    }

    public CoordsXML(String id)
    {
        this.id = id;
    }

    public CoordsXML(String id, String address, int latitude, int longitude)
    {
        this.id = id;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

//	@XmlID
//	@XmlAttribute
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

//	@XmlElement
    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

//	@XmlElementWrapper(name = "points")
//	@XmlElement
    public List<RoutepointXML> getRoutepointList()
    {
        return routepointList;
    }

    public void setRoutepointList(List<RoutepointXML> routepointList)
    {
        this.routepointList = routepointList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoordsXML))
        {
            return false;
        }
        CoordsXML other = (CoordsXML) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "org.courses.whpp.worker.entity.Coords[ id=" + id + " ]";
    }
}
