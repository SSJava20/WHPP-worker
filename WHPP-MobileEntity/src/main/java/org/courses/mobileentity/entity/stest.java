/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.courses.mobileentity.entity;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import org.courses.mobileentity.entity.CoordsXML;
import org.courses.mobileentity.entity.RouteXML;
import org.courses.mobileentity.entity.RoutepointXML;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 *
 * @author stvad
 */
public class stest
{
    public static void main (String[] o) throws Exception
    {
        CoordsXML coordsXML = new CoordsXML("10", "coords", 10, 10);
                CoordsXML coordsXML1 = new CoordsXML("11", "coords2", 11, 11);
                
                RouteXML routeXML = new RouteXML("56238", "route");
                
                RoutepointXML routepointXML = new RoutepointXML("10", "FUCK-POINT");
                routepointXML.setCoordsId(coordsXML);
                routepointXML.setRouteId(routeXML);
                
                RoutepointXML routepointXML1 = new RoutepointXML("11", "FUCK-POINT2");
                routepointXML1.setCoordsId(coordsXML1);
                routepointXML1.setRouteId(routeXML);
                
                ArrayList<RoutepointXML> list = new ArrayList<RoutepointXML>();
                list.add(routepointXML);
                list.add(routepointXML1);
                
                routeXML.setRoutepointList(list);
                
                 Serializer serializer = new Persister();
    //            File result = new File("example.xml");

                 OutputStream os = new ByteArrayOutputStream();
                 
                 serializer.write(routeXML, os);
                 serializer.write(routeXML, System.out);
                 
                 RouteXML read = serializer.read(RouteXML.class, os.toString());
                 
    }
}
