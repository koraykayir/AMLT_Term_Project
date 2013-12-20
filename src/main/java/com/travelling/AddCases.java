/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelling;

import com.travelling.dao.AttractionDAO;
import com.travelling.dao.CaseDAO;
import com.travelling.dao.CategoryDAO;
import com.travelling.entity.CbrAttraction;
import com.travelling.entity.CbrCase;
import com.travelling.entity.CbrCategory;
import com.travelling.pojo.Case;
import com.travelling.pojo.Day;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Stefan
 */
public class AddCases {
    
    private static final SimpleDateFormat sdf = new SimpleDateFormat("H:m");
    
    public static void main(String[] args) throws ParseException {
        Case c = new Case();
        c.setStartTime(sdf.parse("9:00"));
        c.setEndTime(sdf.parse("21:00"));
        c.setMoney(50);
        //System.out.println(c.getId());
        Map<CbrCategory, Double> preferences = new HashMap<CbrCategory, Double>();
        preferences.put(CategoryDAO.instance.find(8), 0.); //Art
        preferences.put(CategoryDAO.instance.find(9), 0.); //History
        preferences.put(CategoryDAO.instance.find(10), 1.); //Science
        preferences.put(CategoryDAO.instance.find(11), 1.); //Special
        preferences.put(CategoryDAO.instance.find(12), 1.); //Zoo
        preferences.put(CategoryDAO.instance.find(13), 1.); //Aquarium
        preferences.put(CategoryDAO.instance.find(19), 1.); //Amusement park
        preferences.put(CategoryDAO.instance.find(5), 0.); //Sports
        preferences.put(CategoryDAO.instance.find(14), 1.); //Monuments
        preferences.put(CategoryDAO.instance.find(15), 1.); //Squares
        preferences.put(CategoryDAO.instance.find(16), 1.); //Markets
        preferences.put(CategoryDAO.instance.find(17), 1.); //Beach
        preferences.put(CategoryDAO.instance.find(18), 1.); //Neighbourhood
        preferences.put(CategoryDAO.instance.find(23), 1.); //Fountains
        preferences.put(CategoryDAO.instance.find(6), 1.); //Parks
        preferences.put(CategoryDAO.instance.find(20), 1.); //Religious
        preferences.put(CategoryDAO.instance.find(21), 1.); //Historical
        preferences.put(CategoryDAO.instance.find(22), 1.); //Architectural
        
        c.setPreferences(preferences);
        
        List<Day> days = new LinkedList<Day>();
        List<CbrAttraction> attractions;
        Day day;
        
        day = new Day();
        day.setStartTime(sdf.parse("9:00"));
        attractions = new LinkedList<CbrAttraction>();
        attractions.add(AttractionDAO.instance.find(5));
        attractions.add(AttractionDAO.instance.find(17));
        attractions.add(AttractionDAO.instance.find(35));
        attractions.add(AttractionDAO.instance.find(20));
        attractions.add(AttractionDAO.instance.find(31));
        attractions.add(AttractionDAO.instance.find(37));
        attractions.add(AttractionDAO.instance.find(32));
        day.setAttractions(attractions);
        days.add(day);
        
        c.setDays(days);
        System.out.println("Saving..");
        if (c.save()) System.out.println("done");
        else System.out.println("failed");
        
    }
    
}
