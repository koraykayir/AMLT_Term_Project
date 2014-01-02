/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelling;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.travelling.dao.AttractionDAO;
import com.travelling.dao.CategoryDAO;
import com.travelling.entity.CbrAttraction;
import com.travelling.entity.CbrCategory;
import com.travelling.pojo.Day;
import com.travelling.pojo.TravellingCase;

/**
 *
 * @author Stefan
 */
public class AddCases {
    
    private static final SimpleDateFormat sdf = new SimpleDateFormat("H:m");
    
    public static void addGeneralExampleOneDay() throws ParseException {
        TravellingCase c = new TravellingCase();
        c.setStartTime(sdf.parse("9:00"));
        c.setEndTime(sdf.parse("21:00"));
        c.setMoney(50);
        c.setNumberOfDays(1);
        //System.out.println(c.getId());
        Map<CbrCategory, Double> preferences = new HashMap<CbrCategory, Double>();
        preferences.put(CategoryDAO.instance.find(8), 0.); //Art
        preferences.put(CategoryDAO.instance.find(9), 0.); //History
        preferences.put(CategoryDAO.instance.find(10), 0.5); //Science
        preferences.put(CategoryDAO.instance.find(11), 0.5); //Special
        preferences.put(CategoryDAO.instance.find(12), 0.5); //Zoo
        preferences.put(CategoryDAO.instance.find(13), 0.5); //Aquarium
        preferences.put(CategoryDAO.instance.find(19), 0.5); //Amusement park
        preferences.put(CategoryDAO.instance.find(5), 0.); //Sports
        preferences.put(CategoryDAO.instance.find(14), 0.6); //Monuments
        preferences.put(CategoryDAO.instance.find(15), 1.); //Squares
        preferences.put(CategoryDAO.instance.find(16), 0.5); //Markets
        preferences.put(CategoryDAO.instance.find(17), 0.5); //Beach
        preferences.put(CategoryDAO.instance.find(18), 0.8); //Neighbourhood
        preferences.put(CategoryDAO.instance.find(23), 0.5); //Fountains
        preferences.put(CategoryDAO.instance.find(6), 0.5); //Parks
        preferences.put(CategoryDAO.instance.find(20), 0.); //Religious
        preferences.put(CategoryDAO.instance.find(21), 0.5); //Historical
        preferences.put(CategoryDAO.instance.find(22), 0.6); //Architectural
        
        c.setPreferences(preferences);
        
        List<Day> days = new LinkedList<Day>();
        List<CbrAttraction> attractions;
        Day day;
        
        day = new Day();
        day.setStartTime(sdf.parse("9:00"));
        attractions = new LinkedList<CbrAttraction>();
        attractions.add(AttractionDAO.instance.find(5)); // Sagrada
        attractions.add(AttractionDAO.instance.find(17)); // Passeig de Gracia
        attractions.add(AttractionDAO.instance.find(35)); // Arc de Trioumf
        attractions.add(AttractionDAO.instance.find(20)); // Parc de la Ciutadella
        attractions.add(AttractionDAO.instance.find(31)); // Barceloneta
        attractions.add(AttractionDAO.instance.find(37)); // Columbus
        attractions.add(AttractionDAO.instance.find(32)); // Las Ramblas
        day.setAttractions(attractions);
        days.add(day);
        
        c.setDays(days);
        System.out.println("Saving..");
        if (c.save()) System.out.println("done");
        else System.out.println("failed");
    }
    
    public static void addGeneralExampleHalfDay() throws ParseException {
    	TravellingCase c = new TravellingCase();
        c.setStartTime(sdf.parse("11:00"));
        c.setEndTime(sdf.parse("17:00"));
        c.setMoney(0);
        c.setNumberOfDays(1);
        //System.out.println(c.getId());
        Map<CbrCategory, Double> preferences = new HashMap<CbrCategory, Double>();
        preferences.put(CategoryDAO.instance.find(8), 0.); //Art
        preferences.put(CategoryDAO.instance.find(9), 0.); //History
        preferences.put(CategoryDAO.instance.find(10), 0.5); //Science
        preferences.put(CategoryDAO.instance.find(11), 0.5); //Special
        preferences.put(CategoryDAO.instance.find(12), 0.5); //Zoo
        preferences.put(CategoryDAO.instance.find(13), 0.5); //Aquarium
        preferences.put(CategoryDAO.instance.find(19), 0.5); //Amusement park
        preferences.put(CategoryDAO.instance.find(5), 0.); //Sports
        preferences.put(CategoryDAO.instance.find(14), 0.6); //Monuments
        preferences.put(CategoryDAO.instance.find(15), 1.); //Squares
        preferences.put(CategoryDAO.instance.find(16), 0.5); //Markets
        preferences.put(CategoryDAO.instance.find(17), 0.5); //Beach
        preferences.put(CategoryDAO.instance.find(18), 0.8); //Neighbourhood
        preferences.put(CategoryDAO.instance.find(23), 0.5); //Fountains
        preferences.put(CategoryDAO.instance.find(6), 0.5); //Parks
        preferences.put(CategoryDAO.instance.find(20), 0.); //Religious
        preferences.put(CategoryDAO.instance.find(21), 0.5); //Historical
        preferences.put(CategoryDAO.instance.find(22), 0.6); //Architectural
        
        c.setPreferences(preferences);
        
        List<Day> days = new LinkedList<Day>();
        List<CbrAttraction> attractions;
        Day day;
        
        day = new Day();
        day.setStartTime(sdf.parse("11:00"));
        attractions = new LinkedList<CbrAttraction>();
        attractions.add(AttractionDAO.instance.find(26)); // Espanya
        attractions.add(AttractionDAO.instance.find(17)); // Passeig de Gracia
        attractions.add(AttractionDAO.instance.find(33)); // Catalunya
        attractions.add(AttractionDAO.instance.find(32)); // Las Ramblas
        attractions.add(AttractionDAO.instance.find(37)); // Columb
        
        day.setAttractions(attractions);
        days.add(day);
        
        c.setDays(days);
        System.out.println("Saving..");
        if (c.save()) System.out.println("done");
        else System.out.println("failed");
    }
    
    public static void addGaudi() throws ParseException {
    	TravellingCase c = new TravellingCase();
        c.setStartTime(sdf.parse("9:00"));
        c.setEndTime(sdf.parse("21:00"));
        c.setMoney(100);
        c.setNumberOfDays(1);
        //System.out.println(c.getId());
        Map<CbrCategory, Double> preferences = new HashMap<CbrCategory, Double>();
        preferences.put(CategoryDAO.instance.find(8), 0.); //Art
        preferences.put(CategoryDAO.instance.find(9), 0.); //History
        preferences.put(CategoryDAO.instance.find(10), 0.); //Science
        preferences.put(CategoryDAO.instance.find(11), 0.); //Special
        preferences.put(CategoryDAO.instance.find(12), 0.); //Zoo
        preferences.put(CategoryDAO.instance.find(13), 0.); //Aquarium
        preferences.put(CategoryDAO.instance.find(19), 0.); //Amusement park
        preferences.put(CategoryDAO.instance.find(5), 0.); //Sports
        preferences.put(CategoryDAO.instance.find(14), 0.); //Monuments
        preferences.put(CategoryDAO.instance.find(15), 0.); //Squares
        preferences.put(CategoryDAO.instance.find(16), 0.); //Markets
        preferences.put(CategoryDAO.instance.find(17), 0.); //Beach
        preferences.put(CategoryDAO.instance.find(18), 0.); //Neighbourhood
        preferences.put(CategoryDAO.instance.find(23), 0.); //Fountains
        preferences.put(CategoryDAO.instance.find(6), 0.); //Parks
        preferences.put(CategoryDAO.instance.find(20), 0.); //Religious
        preferences.put(CategoryDAO.instance.find(21), 0.); //Historical
        preferences.put(CategoryDAO.instance.find(22), 1.); //Architectural
        
        c.setPreferences(preferences);
        
        List<Day> days = new LinkedList<Day>();
        List<CbrAttraction> attractions;
        Day day;
        
        day = new Day();
        day.setStartTime(sdf.parse("9:00"));
        attractions = new LinkedList<CbrAttraction>();
        attractions.add(AttractionDAO.instance.find(5)); // Sagrada
        attractions.add(AttractionDAO.instance.find(12)); // Guell Park
        attractions.add(AttractionDAO.instance.find(9)); // Guell Palace
        attractions.add(AttractionDAO.instance.find(16)); // Casa Mila
        attractions.add(AttractionDAO.instance.find(7)); // Casa Batlo
        
        day.setAttractions(attractions);
        days.add(day);
        
        c.setDays(days);
        System.out.println("Saving..");
        if (c.save()) System.out.println("done");
        else System.out.println("failed");
    }
    
    public static void addReligious() throws ParseException {
    	TravellingCase c = new TravellingCase();
        c.setStartTime(sdf.parse("9:00"));
        c.setEndTime(sdf.parse("22:00"));
        c.setMoney(50);
        c.setNumberOfDays(1);
        //System.out.println(c.getId());
        Map<CbrCategory, Double> preferences = new HashMap<CbrCategory, Double>();
        preferences.put(CategoryDAO.instance.find(8), 0.); //Art
        preferences.put(CategoryDAO.instance.find(9), 0.); //History
        preferences.put(CategoryDAO.instance.find(10), 0.); //Science
        preferences.put(CategoryDAO.instance.find(11), 0.); //Special
        preferences.put(CategoryDAO.instance.find(12), 0.); //Zoo
        preferences.put(CategoryDAO.instance.find(13), 0.); //Aquarium
        preferences.put(CategoryDAO.instance.find(19), 0.); //Amusement park
        preferences.put(CategoryDAO.instance.find(5), 0.); //Sports
        preferences.put(CategoryDAO.instance.find(14), 0.); //Monuments
        preferences.put(CategoryDAO.instance.find(15), 0.); //Squares
        preferences.put(CategoryDAO.instance.find(16), 0.); //Markets
        preferences.put(CategoryDAO.instance.find(17), 0.); //Beach
        preferences.put(CategoryDAO.instance.find(18), 0.7); //Neighbourhood
        preferences.put(CategoryDAO.instance.find(23), 0.); //Fountains
        preferences.put(CategoryDAO.instance.find(6), 0.); //Parks
        preferences.put(CategoryDAO.instance.find(20), 1.); //Religious
        preferences.put(CategoryDAO.instance.find(21), 0.); //Historical
        preferences.put(CategoryDAO.instance.find(22), 0.); //Architectural
        
        c.setPreferences(preferences);
        
        List<Day> days = new LinkedList<Day>();
        List<CbrAttraction> attractions;
        Day day;
        
        day = new Day();
        day.setStartTime(sdf.parse("9:00"));
        attractions = new LinkedList<CbrAttraction>();
        attractions.add(AttractionDAO.instance.find(28)); // Templi Tibidabo
        attractions.add(AttractionDAO.instance.find(5)); // Sagrada
        attractions.add(AttractionDAO.instance.find(18)); // Cathedral Eulalia
        attractions.add(AttractionDAO.instance.find(8)); // Barri Gothic
        attractions.add(AttractionDAO.instance.find(11)); // St Mary Cathedral
        attractions.add(AttractionDAO.instance.find(34)); // Rambla del Mar
        attractions.add(AttractionDAO.instance.find(25)); // Port Vell
        
        day.setAttractions(attractions);
        days.add(day);
        
        c.setDays(days);
        System.out.println("Saving..");
        if (c.save()) System.out.println("done");
        else System.out.println("failed");
    }
    
    public static void addKids() throws ParseException {
    	TravellingCase c = new TravellingCase();
        c.setStartTime(sdf.parse("9:00"));
        c.setEndTime(sdf.parse("21:00"));
        c.setMoney(100);
        c.setNumberOfDays(2);
        //System.out.println(c.getId());
        Map<CbrCategory, Double> preferences = new HashMap<CbrCategory, Double>();
        preferences.put(CategoryDAO.instance.find(8), 0.); //Art
        preferences.put(CategoryDAO.instance.find(9), 0.); //History
        preferences.put(CategoryDAO.instance.find(10), 0.7); //Science
        preferences.put(CategoryDAO.instance.find(11), 0.); //Special
        preferences.put(CategoryDAO.instance.find(12), 1.); //Zoo
        preferences.put(CategoryDAO.instance.find(13), 1.); //Aquarium
        preferences.put(CategoryDAO.instance.find(19), 1.); //Amusement park
        preferences.put(CategoryDAO.instance.find(5), 0.); //Sports
        preferences.put(CategoryDAO.instance.find(14), 0.); //Monuments
        preferences.put(CategoryDAO.instance.find(15), 0.); //Squares
        preferences.put(CategoryDAO.instance.find(16), 0.); //Markets
        preferences.put(CategoryDAO.instance.find(17), 0.7); //Beach
        preferences.put(CategoryDAO.instance.find(18), 0.); //Neighbourhood
        preferences.put(CategoryDAO.instance.find(23), 0.); //Fountains
        preferences.put(CategoryDAO.instance.find(6), 0.); //Parks
        preferences.put(CategoryDAO.instance.find(20), 0.); //Religious
        preferences.put(CategoryDAO.instance.find(21), 0.); //Historical
        preferences.put(CategoryDAO.instance.find(22), 0.); //Architectural
        
        c.setPreferences(preferences);
        
        List<Day> days = new LinkedList<Day>();
        List<CbrAttraction> attractions;
        Day day;
        
        day = new Day();
        day.setStartTime(sdf.parse("10:00"));
        attractions = new LinkedList<CbrAttraction>();
        attractions.add(AttractionDAO.instance.find(30)); // Barcelona Zoo
        attractions.add(AttractionDAO.instance.find(10)); // CosmoCaixa
        day.setAttractions(attractions);
        
        Day day2 = new Day();
        day2.setStartTime(sdf.parse("12:00"));
        List<CbrAttraction> attractions2 = new LinkedList<CbrAttraction>();
        attractions2.add(AttractionDAO.instance.find(38)); // Tibidabo Amusement Park
        attractions2.add(AttractionDAO.instance.find(31)); // Barceloneta Beach
        attractions2.add(AttractionDAO.instance.find(41)); // Barcelona Aquarium
        day2.setAttractions(attractions2);
        
        days.add(day);
        days.add(day2);
        
        c.setDays(days);
        System.out.println("Saving..");
        if (c.save()) System.out.println("done");
        else System.out.println("failed");
    }
    
    public static void addLandmarks() throws ParseException {
    	TravellingCase c = new TravellingCase();
        c.setStartTime(sdf.parse("9:00"));
        c.setEndTime(sdf.parse("20:00"));
        c.setMoney(20);
        c.setNumberOfDays(3);
        //System.out.println(c.getId());
        Map<CbrCategory, Double> preferences = new HashMap<CbrCategory, Double>();
        preferences.put(CategoryDAO.instance.find(8), 0.); //Art
        preferences.put(CategoryDAO.instance.find(9), 0.); //History
        preferences.put(CategoryDAO.instance.find(10), 0.); //Science
        preferences.put(CategoryDAO.instance.find(11), 0.); //Special
        preferences.put(CategoryDAO.instance.find(12), 0.); //Zoo
        preferences.put(CategoryDAO.instance.find(13), 0.); //Aquarium
        preferences.put(CategoryDAO.instance.find(19), 0.); //Amusement park
        preferences.put(CategoryDAO.instance.find(5), 0.); //Sports
        preferences.put(CategoryDAO.instance.find(14), 1.); //Monuments
        preferences.put(CategoryDAO.instance.find(15), 1.); //Squares
        preferences.put(CategoryDAO.instance.find(16), 1.); //Markets
        preferences.put(CategoryDAO.instance.find(17), 1.); //Beach
        preferences.put(CategoryDAO.instance.find(18), 1.); //Neighbourhood
        preferences.put(CategoryDAO.instance.find(23), 1.); //Fountains
        preferences.put(CategoryDAO.instance.find(6), 1.); //Parks
        preferences.put(CategoryDAO.instance.find(20), 0.); //Religious
        preferences.put(CategoryDAO.instance.find(21), 0.7); //Historical
        preferences.put(CategoryDAO.instance.find(22), 0.); //Architectural
        
        c.setPreferences(preferences);
        
        List<Day> days = new LinkedList<Day>();
        List<CbrAttraction> attractions;
        Day day;
        
        day = new Day();
        day.setStartTime(sdf.parse("9:00"));
        attractions = new LinkedList<CbrAttraction>();
        attractions.add(AttractionDAO.instance.find(17)); // Passeig de Gracia
        attractions.add(AttractionDAO.instance.find(33)); // Catalunya
        attractions.add(AttractionDAO.instance.find(32)); // Las Ramblas
        attractions.add(AttractionDAO.instance.find(42)); // Boqueria Market
        attractions.add(AttractionDAO.instance.find(29)); // Placa Reial
        attractions.add(AttractionDAO.instance.find(37)); // Columbus Monument
        attractions.add(AttractionDAO.instance.find(34)); // Rambla del Mar
        attractions.add(AttractionDAO.instance.find(25)); // Port Vell
        attractions.add(AttractionDAO.instance.find(31)); // Barceloneta Beach
        day.setAttractions(attractions);
        
        Day day2 = new Day();
        day2.setStartTime(sdf.parse("9:00"));
        List<CbrAttraction> attractions2 = new LinkedList<CbrAttraction>();
        attractions2.add(AttractionDAO.instance.find(8)); // Barri Gotic
        attractions2.add(AttractionDAO.instance.find(48)); // Placa Sant Jaume
        attractions2.add(AttractionDAO.instance.find(43)); // El Born
        attractions2.add(AttractionDAO.instance.find(20)); // Parc de la Ciutadella
        attractions2.add(AttractionDAO.instance.find(35)); // Arc de Triomf
        day2.setAttractions(attractions2);
        
        Day day3 = new Day();
        day3.setStartTime(sdf.parse("11:30"));
        List<CbrAttraction> attractions3 = new LinkedList<CbrAttraction>();
        attractions3.add(AttractionDAO.instance.find(21)); // Tibidabo Mountain
        attractions3.add(AttractionDAO.instance.find(26)); // Placa Espanya
        attractions3.add(AttractionDAO.instance.find(46)); // Montjuic Castle
        attractions3.add(AttractionDAO.instance.find(14)); // Magic Fountain
        day3.setAttractions(attractions3);
        
        days.add(day);
        days.add(day2);
        days.add(day3);
        
        c.setDays(days);
        System.out.println("Saving..");
        if (c.save()) System.out.println("done");
        else System.out.println("failed");
    }
    
    public static void main(String[] args) throws ParseException {
//        addGeneralExampleOneDay();
//        addGeneralExampleHalfDay();
//        addGaudi();
//        addReligious();
//        addKids();
//        addLandmarks();
    }
}
