/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelling.reuse;

import com.travelling.dao.AttractionDAO;
import com.travelling.dao.AttractionXAttractionDAO;
import com.travelling.dao.CategoryDAO;
import com.travelling.entity.CbrAttraction;
import com.travelling.entity.CbrCategory;
import com.travelling.library.Case;
import com.travelling.library.Library;
import com.travelling.library.TreeNode;
import com.travelling.pojo.Day;
import com.travelling.pojo.TravellingCase;
import com.travelling.retrieval.RetrievalIndexing;
import com.travelling.retrieval.RetrievalSimilarityAssessment;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Filip
 */
public class Reuse implements Constants {
    
    TravellingCase targetCase;
    List<TravellingCase> retrievedCases, positiveCases, negativeCases;    
    List<ScoredCase> population;
    Random r;
    List<CbrAttraction> availableAttractions;
    Map<CbrCategory, Double> targetCategoryScores;
    LinkedList<CbrCategory> categories;
    Map<CbrAttraction, List<CbrCategory>> attractionsCategories;
    Map<CbrAttraction, Map<CbrAttraction, Integer>> distanceMap;
    
    /**
     * The constructor takes the retrieved cases from the Retrieval and the
     * taget case as parameters.
     * @param targetCase the target case
     * @param similar the retrieved cases
     */
    public Reuse(Case targetCase, List<Case> cases) {
        retrievedCases = new LinkedList<>();
        int i;
        
        for (i = 0; i < cases.size(); i++) {
            retrievedCases.add((TravellingCase) cases.get(i));
        }
        
        positiveCases = new LinkedList<>();
        negativeCases = new LinkedList<>();
        population = new LinkedList<>();
        
        this.targetCase = (TravellingCase)targetCase;
        initializeCategories();
        initializeAttractions();
        
        r = new Random();
    }    
    
    
    public void initializeCategories() {
        targetCategoryScores = targetCase.getPreferences();
        categories = new LinkedList<>();
        
        Set<Entry<CbrCategory, Double>> set = targetCategoryScores.entrySet();
        Iterator<Entry<CbrCategory, Double>> it = set.iterator();
        
        while ( it.hasNext() ) {
            categories.add(it.next().getKey());
        }
        
    }
    
    
    public void initializeAttractions() {        
        int dist;
        attractionsCategories = new HashMap<>();
        distanceMap = new HashMap<>();
        Map<CbrAttraction, Integer> destinations;
        
        // Get the list of all attractions
        availableAttractions = AttractionDAO.instance.findAll();
        for (CbrAttraction attraction:availableAttractions) {
            attractionsCategories.put(attraction, CategoryDAO.instance.findByAttraction(attraction));
            
            destinations = new HashMap<>();
            for (CbrAttraction attraction2:availableAttractions) {
                dist = AttractionXAttractionDAO.instance.findDist(attraction, attraction2).get(0).getBusTime();
                destinations.put(attraction2, dist);
            }
            
            distanceMap.put(attraction, destinations);
        }
    }
    
    
    public void separateCases() {
        TravellingCase tCase;
        int i;
        
        for (i = 0; i < retrievedCases.size(); i++) {
            tCase = retrievedCases.get(i);
            if ( tCase.getSuccessRatio() > POSITIVE_THRESHOLD ) {
                positiveCases.add(tCase);
            }
            else if ( tCase.getSuccessRatio() < NEGATIVE_THRESHOLD ) {
                negativeCases.add(tCase);
            }
        }
    }
    
    
    public int getMinutes(Calendar c) {
        int minutes = c.get(Calendar.HOUR_OF_DAY) * 60 + c.get(Calendar.MINUTE);
        return minutes;
    }
    
    
    public boolean solutionValid(ScoredCase sCase) {
        List<Day> days = sCase.getDays();
        List<CbrAttraction> attractions;
        int i, j, time, startTime, stopTime, lastStopTime, attrStartTime, attrStopTime;
        GregorianCalendar caseStart, dayStart, caseStop, aux;
        CbrAttraction attraction;
        
        caseStart = new GregorianCalendar();
        caseStart.setTime(targetCase.getStartTime());
        caseStop = new GregorianCalendar();
        caseStop.setTime(targetCase.getEndTime());
        
        if ( days.size() > 1 ) {
            startTime = getMinutes(caseStart);
            lastStopTime = getMinutes(caseStop);
            stopTime = 23 * 60;
        }
        else {
            startTime = getMinutes(caseStart);
            lastStopTime = getMinutes(caseStop);
            stopTime = lastStopTime;
        }
        
        aux = new GregorianCalendar();                
        
        // All days except last
        for (i = 0; i < days.size() -1 ; i++) {
            time = startTime;
            attractions = days.get(i).getAttractions();
            
            for (j = 0; j < attractions.size(); j++) {
                        
                attraction = attractions.get(j);
                aux.setTime(attraction.getOpeningTime());
                attrStartTime = getMinutes(aux);
                aux.setTime(attraction.getClosingTime());
                attrStopTime = getMinutes(aux);
               
                // Add bus time
                if ( j > 0 ) {
                    time += distanceMap.get(attractions.get(j - 1)).get(attraction);
                }
                
                
                if ( time < attrStartTime || time + attraction.getVisitDuration() > attrStopTime ) {
                    return false;
                }
                
                // Add visit time
                time += attraction.getVisitDuration();
            }
        
            if ( time > stopTime ) {
                return false;
            }
            
        }
        
        // Last day
        time = startTime;
        attractions = days.get(days.size() - 1).getAttractions();
            
        for (j = 0; j < attractions.size(); j++) {
                       
            attraction = attractions.get(j);
            aux.setTime(attraction.getOpeningTime());
            attrStartTime = getMinutes(aux);
            aux.setTime(attraction.getClosingTime());
            attrStopTime = getMinutes(aux);
               
            // Add bus time
            if ( j > 0 ) {
                time += distanceMap.get(attractions.get(j - 1)).get(attraction);
            }
                
            if ( time < attrStartTime || time + attraction.getVisitDuration() > attrStopTime ) {
                return false;
            }
                
            // Add visit time
            time += attraction.getVisitDuration();
        }
        
        if ( time > lastStopTime ) {
            return false;
        }
        
        return true;
    }
    
    
    public double solutionSimilarity(TravellingCase tc1, TravellingCase tc2) {
        List<Day> days1 = tc1.getDays(), days2 = tc2.getDays();
        List<CbrAttraction> attr;
        int[] attractionPresence1, attractionPresence2;
        int i, j;
        double score;
        
        attractionPresence1 = new int[availableAttractions.size()];
        attractionPresence2 = new int[availableAttractions.size()];
        
        for (i = 0; i < availableAttractions.size(); i++) {
            attractionPresence1[i] = 0;
            attractionPresence2[i] = 0;
        }            
        
        // First solution
        for (i = 0; i < days1.size(); i++) {
            attr = days1.get(i).getAttractions(); 
            for (j = 0; j < attr.size(); j++) {
                attractionPresence1[availableAttractions.indexOf(attr.get(j))]++;
            }
        }
        
        // Second solution
        for (i = 0; i < days2.size(); i++) {
            attr = days2.get(i).getAttractions(); 
            for (j = 0; j < attr.size(); j++) {
                attractionPresence2[availableAttractions.indexOf(attr.get(j))]++;
            }
        }
        
        score = 0;
        for (i = 0; i < attractionPresence1.length; i++) {
            if ( attractionPresence1[i] == 1 && attractionPresence2[i] == 1) {
                score++;
            }
        }
        
        return score;
    }
    
    
    public void setFitness(ScoredCase sCase) {
        Map<CbrCategory, Double> solutionCategories = new HashMap<>();
        TravellingCase tCase = sCase.gettCase();
        List<Day> days = tCase.getDays();
        List<CbrAttraction> attractions;
        List<CbrCategory> attractionCategories;
        CbrAttraction attraction;
        Day day;
        int i, j, money, duration, n = 0, currentTime;
        double fitness = 0, count, percentage, categoryScore, maxCount, sim, maxSim;
        
        // Check if solution is valid
        if ( !solutionValid(sCase) ) {
            fitness += INVALID_PENALTY;
        }            
        
        money = 0;
        duration = 0;
        maxCount = 1;
        for (i = 0; i < days.size(); i++) {
            day = days.get(i);
            attractions = day.getAttractions();
            
            for (j = 0; j < attractions.size(); j++) {
                attraction = attractions.get(j);
                money += attraction.getVisitCost();
                duration += attraction.getVisitDuration();
                
                // Get the time between attractions
                if ( j < attractions.size() - 1 ) {
                    duration += distanceMap.get(attraction).get(attractions.get(j + 1));
                }
                
                // Add all the attraction's categories to the count
                attractionCategories = attractionsCategories.get(attraction);
                for (CbrCategory category:attractionCategories) {
                    if ( solutionCategories.containsKey(category) ) { 
                        count = solutionCategories.get(category);
                        solutionCategories.put(category, count + 1);
                        if ( count > maxCount ) {
                            maxCount = count;
                        }
                    }
                    else {
                        solutionCategories.put(category, 1.0);
                    }    
                }
                
                // Count the total number of attractions
                n++;
            } 
        }    
        
        categoryScore = 0;
        for (CbrCategory category:categories) {
            if ( !solutionCategories.containsKey(category) ) {
                solutionCategories.put(category, 0.0);
            }
            
            
            //categoryScore += Math.pow(10 * Math.abs(targetCategoryScores.get(category) - (solutionCategories.get(category) / maxCount)), 3);
            //System.out.print(targetCategoryScores.get(category) + "/" + solutionCategories.get(category) + "; ");
            
            //categoryScore += Math.abs(targetCategoryScores.get(category) - (solutionCategories.get(category) / maxCount));
            categoryScore += Math.pow((targetCategoryScores.get(category) - 0.5) * solutionCategories.get(category) * 10, 1);                    
            //System.out.print(targetCategoryScores.get(category) + "/" + solutionCategories.get(category) + "=" + Math.pow((targetCategoryScores.get(category) - 0.5) * solutionCategories.get(category) * 10, 3) +"; ");
        }
        //System.out.println(categoryScore + " " + money + " " + duration + " " + days.size());
        
        // Calculate maximum similarity with a negative solution
        maxSim = 0;
        for (TravellingCase negCase:negativeCases) {
            sim = solutionSimilarity(sCase.gettCase(), negCase);
            if ( sim > maxSim ) {
                maxSim = sim;
            }
        }
        
        fitness += Math.abs(targetCase.getMoney() - money)
                + Math.abs((Integer)targetCase.getAttributeValue("interval") - duration)
                + Math.abs(targetCase.getNumberOfDays() - days.size())
                - WEIGHT_CATEGORY * categoryScore
                + WEIGHT_NEGATIVE * maxSim;
        
        sCase.setFitness(fitness);
    }
    
    
    public void initializePopulation() {
        ScoredCase sCase;
        
        for (TravellingCase tCase:positiveCases) {
            sCase = new ScoredCase(tCase, 0);
            setFitness(sCase);
            population.add(sCase);
        }
        
    }
    
    
    public List<ScoredCase> rouletteWheelSelection(List<ScoredCase> set, int n) {
        List<ScoredCase> selectedSet = new LinkedList<>();
        List<Integer> accumulatedRank = new LinkedList<>();
        int totalRank, i, j, num;
        
        totalRank = 0;
	for (i = 0; i < set.size(); i++) {
            totalRank += set.size() - i + 1;
            accumulatedRank.add(totalRank);
	}
	
        
	for (i = 0; i < n; i++) {
            num = r.nextInt(totalRank);
            for (j = 0; j < accumulatedRank.size(); j++) {
                if ( accumulatedRank.get(j) > num ) {
                    selectedSet.add(set.get(j));
                    break;
                }
            }
	}
	
	return selectedSet;	
    }
    
    
    public void crossover() {
        ScoredCase case1, case2, child1, child2;
        int type, pivot1, pivot2, day1, day2, i, n;
        
        n = population.size();
        if ( n > MAX_POPULATION ) {
            n = MAX_POPULATION;
        }
        
        for (i = 0; i < (PERCENTAGE_CROSSOVER * n) / 100; i++) {

            case1 = rouletteWheelSelection(population, 1).get(0);
            //case2 = rouletteWheelSelection(population, 1).get(0);
            //case1 = population.get(r.nextInt(population.size()));
            case2 = population.get(r.nextInt(population.size()));

            type = r.nextInt(100);

            child1 = new ScoredCase();
            child2 = new ScoredCase();

            // Crossover of entire days
            if ( type < PERCENTAGE_CROSS_DAYS ) {
                // same pivot
                if ( case1.getDays().size() < case2.getDays().size() ) {
                    pivot1 = r.nextInt(case1.getDays().size());
                }
                else {
                    pivot1 = r.nextInt(case2.getDays().size());
                }

                child1.crossoverDays(case1, case2, pivot1, pivot1);
                child2.crossoverDays(case2, case1, pivot1, pivot1);
            }
            // Crossover of attractions from a certain day
            else {            
                day1 = r.nextInt(case1.getDays().size());
                day2 = r.nextInt(case2.getDays().size());

                // same pivot
                List<CbrAttraction> a1, a2;
                a1 = case1.getDays().get(day1).getAttractions();
                a2 = case2.getDays().get(day2).getAttractions();

                if ( a1.size() < a2.size() ) {
                    pivot1 = r.nextInt(a1.size());
                }
                else {
                    pivot1 = r.nextInt(a2.size());
                }

                child1.crossoverDay(case1, case2, day1, day2, pivot1, pivot1);
                child2.crossoverDay(case2, case1, day2, day1, pivot1, pivot1);
            }

            setFitness(child1);
            setFitness(child2);
            population.add(child1);
            population.add(child2);
        }
    }
    
    
    public CbrAttraction selectNewAttraction() {
        int index = r.nextInt(availableAttractions.size());
        return availableAttractions.get(index);
    }
    
    
    public void mutation() {
        int i, day, attraction, n;
        int perc;
        CbrAttraction newAttraction;
        ScoredCase child, parent;
        
        n = population.size();
        for (i = 0; i < n; i++) {
            perc = r.nextInt(100);
            
            if ( perc < PERCENTAGE_MUTATION ) {
                parent = population.get(i);
                child = new ScoredCase();
                
                day = r.nextInt(parent.getDays().size());
                attraction = r.nextInt(parent.getDays().get(day).getAttractions().size());
                newAttraction = selectNewAttraction();
                
                child.mutate(parent, newAttraction, day, attraction);
                
                setFitness(child);
                population.add(child);
            }
        }
    }
    
    
    public void sort() {
        // Sort population according to 
        Collections.sort(population);
    }
    
    
    public void selection() {
        List<ScoredCase> selectedCases;
        int i;
                
        if ( population.size() > MAX_POPULATION ) {

            selectedCases = new LinkedList<>();
            
            // Elitism
            for (i = 0; i < PERCENTAGE_ELITISM * population.size(); i++) {
                selectedCases.add(population.get(0));
                population.remove(0);
            }
            
            selectedCases.addAll(rouletteWheelSelection(population, MAX_POPULATION - selectedCases.size()));
            population = new LinkedList<>();
            population.addAll(selectedCases);
        }
    }
    
    
    public void printSolution() {
        Map<CbrCategory, Double> solutionCategories = new HashMap<>();
        TravellingCase tCase = population.get(0).gettCase();
        List<Day> days = tCase.getDays();
        List<CbrAttraction> attractions;
        List<CbrCategory> attractionCategories;
        CbrAttraction attraction;
        Day day;
        int i, j, money, duration, n = 0;
        double count, percentage, categoryScore, maxCount;
        
        money = 0;
        duration = 0;
        maxCount = 1;
        for (i = 0; i < days.size(); i++) {
            day = days.get(i);
            attractions = day.getAttractions();
            
            for (j = 0; j < attractions.size(); j++) {
                attraction = attractions.get(j);
                money += attraction.getVisitCost();
                duration += attraction.getVisitDuration();
                
                // Get the time between attractions
                if ( j < attractions.size() - 1 ) {
                    duration += distanceMap.get(attraction).get(attractions.get(j + 1));
                }
                
                
                attractionCategories = attractionsCategories.get(attraction);
                for (CbrCategory category:attractionCategories) {
                    if ( solutionCategories.containsKey(category) ) { 
                        count = solutionCategories.get(category);
                        solutionCategories.put(category, count + 1);
                        if ( count + 1 > maxCount ) {
                            maxCount = count + 1;
                        }
                    }
                    else {
                        solutionCategories.put(category, 1.0);
                    }    
                }
                
                n++;
            } 
        }    
        
        categoryScore = 0;
        for (CbrCategory category:categories) {
            if ( !solutionCategories.containsKey(category) ) {
                solutionCategories.put(category, 0.0);
            }
            
            /*categoryScore += Math.pow(10 * Math.abs(targetCategoryScores.get(category) - (solutionCategories.get(category) / maxCount)), 3);
            System.out.print(targetCategoryScores.get(category) + "/" + solutionCategories.get(category) + "; ");*/
            
            categoryScore += Math.pow((targetCategoryScores.get(category) - 0.5) * solutionCategories.get(category) * 10, 3);                    
            //System.out.print(targetCategoryScores.get(category) + "/" + solutionCategories.get(category) + "=" + Math.pow((targetCategoryScores.get(category) - 0.5) * solutionCategories.get(category) * 10, 3) +"; ");
        }
        //System.out.println("");
        //System.out.println(categoryScore + " " + money + " " + duration + " " + days.size());
        
        double fitness = Math.abs(targetCase.getMoney() - money) 
                + Math.abs((Integer)targetCase.getAttributeValue("interval") - duration)
                + Math.abs(targetCase.getNumberOfDays() - days.size())
                - WEIGHT_CATEGORY * categoryScore;
    }
       
    
    public void evolve() {
        int generation = 0;
        
        initializePopulation();
        
        while ( generation < MAX_GENERATION ) {
            sort();
            
            mutation();
            if ( population.size() > 1 ) {
                crossover();
            }
            
            sort();            
            selection();
            //System.out.print(generation + ": " + population.size() + " " + population.get(0).getFitness() + " ");
            
            int f = 0;
            for (int i = 0; i < population.size(); i++) {
                f+= population.get(i).getFitness();
            }
            //System.out.println((double)f/population.size());
            
            generation++;
        }
        
        //printSolution();
    }
    
    
    public Case getSolution() {
        separateCases();
        
        evolve();
                
        TravellingCase solution = population.get(0).gettCase();                
        targetCase.setDays(solution.getDays());
        
        return targetCase;
    }
    
    
    public static void main(String[] args) throws ParseException {
//        Library library = new Library();
//        library.constructAttributes();
//        library.constructTree();
//        library.save();
        Library library = Library.load();
        TreeNode t = library.getTree();
        TravellingCase tCase;
        System.out.println(t);
        int i, j;
        
        Case target = Library.getTestCase();
        RetrievalIndexing ri = new RetrievalIndexing(library, target);
        List<Case> promising = ri.getPromisingCases();
        /*System.out.println("Size: "+ promising.size());
        for (i = 0; i < promising.size(); i++) {
            tCase = (TravellingCase) promising.get(i);
            System.out.println(tCase.getAttributeValue("starttime") + "; " 
                    + tCase.getAttributeValue("endtime") + "; " 
                    + tCase.getAttributeValue("days") + "; "
                    + tCase.getAttributeValue("money") + "; "
                    + tCase.getAttributeValue("interval") + " "
                    + tCase.getId());
            
            List<Day> days = tCase.getDays();
            for (j = 0; j < days.size(); j++) {
                System.out.print("\t" + days.get(j).getStartTime() + ": ");
                for (CbrAttraction attraction:days.get(j).getAttractions()) {
                    System.out.print(attraction.getName() + " " + attraction.getVisitCost() + " " + attraction.getVisitDuration() + "; ");
                }
                System.out.println();
            }
        }*/
        
        RetrievalSimilarityAssessment rsa = new RetrievalSimilarityAssessment(library, promising, target);
        List<Case> similar = rsa.getSimilarCases();
        
        Reuse reuse = new Reuse(target, similar);
        reuse.getSolution();
        //reuse.printSolution();        
    }
}
