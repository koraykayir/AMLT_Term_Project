/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.travelling.security;

import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import com.google.api.gwt.oauth2.client.*;
import com.google.api.gwt.oauth2.script.client.ScriptEntryPoint;
import com.travelling.App;
import com.travelling.dao.AttractionDAO;
import com.travelling.dao.AttractionXAttractionDAO;
import com.travelling.entity.CbrAttraction;
import com.travelling.entity.CbrAttractionXAttraction;
import com.travelling.mapQuery.location;
import com.travelling.mapQuery.mapSearch;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mortbay.jetty.security.Credential;



/**
 *
 * @author Koray
 */
public class getSecure {
    
    public void getSecure(){
        
    }
    /**
     *
     * @param i
     * @return
     * @throws IOException
     */
    public int secure(int i) throws IOException, InterruptedException{
        
//        String auth_url = "https://accounts.google.com/o/oauth2/auth";
//        String client_id = "95583169402-9s9kucauu55dtvl4hkv4to2gpm808rfa.apps.googleusercontent.com";
//        String scope = "http://maps.google.com/maps/api/staticmap";
//        
//        AuthRequest ar = new AuthRequest(auth_url, client_id).withScopes(scope);
//        System.out.println(ar.toString());
//        Auth.get().login(ar , new Callback<String, Throwable>() {
//        @Override
//        
//        public void onSuccess(String token) {
           List<CbrAttraction> attractions = AttractionDAO.instance.findAll();
            for(CbrAttraction att1: attractions){
                for(CbrAttraction att2: attractions){
                    if(att1!=att2){
                        List<CbrAttractionXAttraction> axaTester = AttractionXAttractionDAO.instance.findAll();
                        
                        location l1=new location();
                        location l2=new location();
                        l1.setLat(att1.getLatitude());
                        l1.setLon(att1.getLongitude());
                        l2.setLat(att2.getLatitude());
                        l2.setLon(att2.getLongitude());
                        
                        CbrAttractionXAttraction attdist = new CbrAttractionXAttraction();
                        attdist.setFkAttraction1(att1);
                        attdist.setFkAttraction2(att2);
                        boolean mark=true;
                        for (CbrAttractionXAttraction axa: axaTester){
                             if (axa.getFkAttraction1().getId() == att1.getId() && axa.getFkAttraction2().getId() == att2.getId())
                                 mark=false;
                             if (axa.getFkAttraction1().getId() == att2.getId() && axa.getFkAttraction2().getId() == att1.getId())
                                 mark=false;
                        }
                        if (mark){
                            try{
                                Thread.sleep(3000);
                            }
                            catch(InterruptedException e){
                                
                            }
                            mapSearch ms=new mapSearch(); 
                            String z=ms.search(l1, l2);
                            String[] parsed=z.split(" ");
                            int dist=999999;
                            switch(parsed.length){
                                case 2:
                                    dist=Integer.parseInt(parsed[0]);
                                    break;
                                case 4:
                                    dist=Integer.parseInt(parsed[0])*60+Integer.parseInt(parsed[2]);
                                    break;
                                case 6:
                                    dist=Integer.parseInt(parsed[0])*24*60+Integer.parseInt(parsed[2])*60+Integer.parseInt(parsed[4]);
                                    break;
                            }
    //                        CbrAttractionXAttraction axa = new CbrAttractionXAttraction();
    //                        axa.setFkAttraction1(att1);
    //                        axa.setFkAttraction2(att2);
                            attdist.setBusTime(dist);
                            AttractionXAttractionDAO.instance.update(attdist);
                        }
                    }
                }
            
        
//        @Override
//        public void onFailure(Throwable caught) {
//         System.out.println(caught.getMessage());
//        }
    }
//            );
     return i;
    }
    
}
