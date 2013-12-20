/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelling.pojo;

import com.travelling.dao.DayXAttractionDAO;
import com.travelling.entity.CbrAttraction;
import com.travelling.entity.CbrDay;
import com.travelling.entity.CbrDayAttraction;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Stefan
 */
public class Day {
    private Date startTime;
    private List<CbrAttraction> attractions = new LinkedList<CbrAttraction>();
    
    public Day() {
        
    }
    
    public Day(CbrDay day) {
        startTime = day.getStartingTime();
        for (CbrDayAttraction dxa : DayXAttractionDAO.instance.findByDay(day)) {
            attractions.add(dxa.getFkAttraction());
        }
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the attractions
     */
    public List<CbrAttraction> getAttractions() {
        return attractions;
    }

    /**
     * @param attractions the attractions to set
     */
    public void setAttractions(List<CbrAttraction> attractions) {
        this.attractions = attractions;
    }
}
