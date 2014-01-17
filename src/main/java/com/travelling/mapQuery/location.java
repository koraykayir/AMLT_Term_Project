/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.travelling.mapQuery;

/**
 *
 * @author Koray
 */
public class location {
    private double lon;
    private double lat;

    void location(){
        this.lon=0;
        this.lat=0;
    }

    public void setLat(double lati){
        this.lat=lati;
    }

    public void setLon(double longi){
        this.lon=longi;
    }

    public double getLon(){
        return this.lon;
    }


    public double getLat(){
            return this.lat;
        }
        
};
