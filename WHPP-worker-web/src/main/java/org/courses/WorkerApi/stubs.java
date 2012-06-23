/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.WorkerApi;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlRootElement;

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
    
    public static Boolean markPoint(PointToSend Point, Long UserId)
    {
        return true;//FIXME: replace with real realization
    }
    
    @XmlRootElement
    class PointToSend
    {
        private Long id;
        private BigDecimal longitude;
        private BigDecimal latitude;
        
    }
}
