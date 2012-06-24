/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.WorkerApi;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlRootElement;
import org.courses.mobileentity.entity.RoutepointXML;

/**
 *
 * @author stvad
 */
public class stubs
{
    public static Boolean auth(Long userId, String userPassHash)
    {
        return true;//FIXME: replace with real realization
    }
    
    public static String getMapForRoute(String UserID)
    {
        return "wheeeee";//FIXME: replace with real realization
    }
    
    public static Boolean markPoint(RoutepointXML Point, Long UserId)
    {
        return true;//FIXME: replace with real realization
    }
    
    public static Boolean warning(String msg)
    {
        return true;//FIXME: replace with real realization
    }
    
    enum MessageType
    {
        MESSAGE_OK,
        MESSAGE_WARNING
    }
    
    @XmlRootElement
    class PointToSend
    {
        private Long id;
        private BigDecimal longitude;
        private BigDecimal latitude;
        private String message;
        private MessageType type;

        public Long getId()
        {
            return id;
        }

        public void setId(Long id)
        {
            this.id = id;
        }

        public BigDecimal getLatitude()
        {
            return latitude;
        }

        public void setLatitude(BigDecimal latitude)
        {
            this.latitude = latitude;
        }

        public BigDecimal getLongitude()
        {
            return longitude;
        }

        public void setLongitude(BigDecimal longitude)
        {
            this.longitude = longitude;
        }

        public String getMessage()
        {
            return message;
        }

        public void setMessage(String message)
        {
            this.message = message;
        }

        public MessageType getType()
        {
            return type;
        }

        public void setType(MessageType type)
        {
            this.type = type;
        }
        
    }
}
