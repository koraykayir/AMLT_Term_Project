/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelling.reuse;

import com.travelling.entity.CbrAttraction;
import com.travelling.pojo.Day;
import com.travelling.pojo.TravellingCase;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Filip
 */
public class ScoredCase implements Constants, Comparable<ScoredCase> {
    
    TravellingCase tCase;
    double fitness;

    public ScoredCase() {
        tCase = new TravellingCase();
        fitness = 0;
    }

    public ScoredCase(TravellingCase tCase, double score) {
        this.tCase = tCase;
        this.fitness = score;
    }

    public TravellingCase gettCase() {
        return tCase;
    }

    public double getFitness() {
        return fitness;
    }

    public void settCase(TravellingCase tCase) {
        this.tCase = tCase;
    }

    public void setFitness(double score) {
        this.fitness = score;
    }

    @Override
    public int compareTo(ScoredCase t) {
        if ( fitness > t.fitness ) {
            return 1;
        }
        if ( fitness == t.fitness ) {
            return 0;
        }
        return -1;
    }
    
    public List<Day> getDays() {
        return tCase.getDays();
    }
    
    
    public ScoredCase copyScoredCase(ScoredCase s) {
        ScoredCase newCase = new ScoredCase();
        TravellingCase oldTCase, newTCase;
        newCase.setFitness(s.getFitness());
        
        oldTCase = s.gettCase();
        newTCase = new TravellingCase();
        
        /*newTCase.setEndTime(oldTCase.getEndTime());
        newTCase.setStartTime(oldTCase.getStartTime());
        newTCase.setId(oldTCase.getId());
        newTCase.setMoney(oldTCase.getMoney());
        newTCase.setNumberOfDays(oldTCase.getNumberOfDays());
        newTCase.setSuccessRatio(oldTCase.getSuccessRatio());
        newTCase.s*/
        
        List<Day> oldDays, newDays;
        oldDays = oldTCase.getDays();
        newDays = new LinkedList<>();
        Day newDay = new Day();
        List<CbrAttraction> oldAttractions, newAttractions;
        
        for (Day oldDay:oldDays) {
            newDay.setStartTime(oldDay.getStartTime());
            oldAttractions = oldDay.getAttractions();
            newAttractions = new LinkedList<>();
            for (CbrAttraction oldA:oldAttractions) {
                newAttractions.add(oldA);
            }
            newDay.setAttractions(newAttractions);
            newDays.add(newDay);
        }
        
        newTCase.setDays(newDays);
        newCase.settCase(newTCase);
        return newCase;
    }
    
    
    public void crossoverDays(ScoredCase sc1, ScoredCase sc2, int pivot1, 
            int pivot2) {
        List<Day> days, c1Days, c2Days;
        ScoredCase c1 = copyScoredCase(sc1), c2 = copyScoredCase(sc2);
        int i;
        
        days = new LinkedList<>();
        c1Days = c1.getDays();
        c2Days = c2.getDays();
        
        for (i = 0; i < pivot1; i++) {
            days.add(c1Days.get(i));
        }
        
        for (i = pivot2; i < c2Days.size(); i++) {
            days.add(c2Days.get(i));
        }
        
        tCase.setDays(days);
    }
    
   
    public void crossoverDay(ScoredCase sc1, ScoredCase sc2, int d1, int d2, 
            int pivot1, int pivot2) {
        List<Day> days, c1Days, c2Days;
        List<CbrAttraction> attractions, attractions1, attractions2;
        ScoredCase c1 = copyScoredCase(sc1), c2 = copyScoredCase(sc2);
        Day day;
        int i;
        
        days = new LinkedList<>();
        c1Days = c1.getDays();
        c2Days = c2.getDays();
        
        // Add previous days
        for (i = 0; i < d1; i++) {
            days.add(c1Days.get(i));
        }
        
        attractions1 = c1Days.get(d1).getAttractions();
        attractions2 = c2Days.get(d2).getAttractions();
        attractions = new LinkedList<>();
        
        for (i = 0; i < pivot1; i++) {
            attractions.add(attractions1.get(i));
        }
        for (i = pivot2; i < attractions2.size(); i++) {
            attractions.add(attractions2.get(i));
        }
        
        day = c1Days.get(d1);
        day.setAttractions(attractions);
        days.add(day);
        
        
        // Add following days
        for (i = d1 + 1; i < c1Days.size(); i++) {
            days.add(c1Days.get(i));
        }
        
        tCase.setDays(days);
    }
    
    
    public void mutate(ScoredCase c, CbrAttraction newAttraction, 
            int dayIndex, int attractionIndex) {
        int i;
        List<Day> parentDays, days = new LinkedList<>();
        Day newDay;
        ScoredCase parent = copyScoredCase(c);
        List<CbrAttraction> parentAttractions, attractions = new LinkedList<>();
                               
        parentDays = parent.getDays();
        parentAttractions = parentDays.get(dayIndex).getAttractions();
        
        // Add previous days
        for (i = 0; i < dayIndex; i++) {
           days.add(parentDays.get(i));
        }
        
        // Add previous attractions
        for (i = 0; i < attractionIndex; i++) {
            attractions.add(parentAttractions.get(i));
        }
        
        // Add new attraction
        attractions.add(newAttraction);
        
        // Add following attractions
        for (i = attractionIndex + 1; i < parentAttractions.size(); i++) {
            attractions.add(parentAttractions.get(i));
        }
        
        // Add new day
        newDay = parentDays.get(dayIndex);
        newDay.setAttractions(attractions);
        days.add(newDay);
        
        
        // Add following days
        for (i = dayIndex + 1; i < parentDays.size(); i++) {
           days.add(parentDays.get(i));
        }
        
        tCase.setDays(days);
    }
}
