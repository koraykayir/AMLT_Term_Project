/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.travelling.mapQuery;

import com.travelling.dao.AttractionDAO;
import com.travelling.entity.CbrAttraction;
import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;




/**
 *
 * @author Koray
 */
       
public class mapSearch{
    

    private location mapLoc1;
    private location mapLoc2;
    
    public mapSearch(){
        mapLoc1=null;
        mapLoc2=null;
    }
    
    public String search(location loc1, location loc2){
        
            this.mapLoc1 = loc1;
            this.mapLoc2 = loc2;
            
            
            String urlString = "https://maps.googleapis.com/maps/api/directions/xml?sensor=false&origin="+loc1.getLon()+","+loc1.getLat()+"&destination="+loc2.getLon()+","+loc2.getLat()+"&mode=transit"+"&departure_time=1200";
            
            System.out.println(urlString);
           
            try{
                Document doc;
                doc = Jsoup.connect(urlString).get();
                Element duration = doc.select("leg > duration text").first();
                if (duration == null)
                {
                    System.out.println(doc);
                }
                String time = duration.text();
                return time; 
            }
            catch(IOException e)
            {
                System.out.println(e);
                return null;
            }
        }
    
    public void setLoc1(float lon, float lat){
        this.mapLoc1.setLon(lon);
        this.mapLoc1.setLat(lat);
    }
    
    public void setLoc2(float lon, float lat){
        this.mapLoc1.setLon(lon);
        this.mapLoc1.setLat(lat);
    }
    
    public location getLoc1(){
        return this.mapLoc1;
    }
    
    public location getLoc2(){
        return this.mapLoc1;
    }
}

