/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelling.library;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.travelling.dao.CaseDAO;
import com.travelling.dao.CategoryDAO;
import com.travelling.entity.CbrCase;
import com.travelling.entity.CbrCategory;
import com.travelling.pojo.TravellingCase;
import com.travelling.retrieval.CaseAttributeWeightAssessment;
import com.travelling.retrieval.RetrievalIndexing;
import com.travelling.retrieval.RetrievalSimilarityAssessment;

/**
 *
 * @author Stefan
 */
public class Library {

    public static final int NEIGHBOURHOOD_SIZE = 3;
    private TreeNode tree;
    private Map<Integer, Case> caseMap = new HashMap<>();
    private Map<String, Attribute<?>> attributes = new HashMap<>();

    public Library() {
        for (CbrCase c : CaseDAO.instance.findAll()) {
            caseMap.put(c.getId(), new TravellingCase(c));
        }
    }

    public void constructAttributes() {
        attributes.put("money", new Attribute<Integer>("money"));
        attributes.put("startTime", new Attribute<Date>("startTime"));
        attributes.put("endTime", new Attribute<Date>("endTime"));
        attributes.put("interval", new Attribute<Integer>("interval"));
        attributes.put("days", new Attribute<Integer>("days"));

        for (CbrCategory cat : CategoryDAO.instance.findLeaves()) {
            attributes.put(cat.getId().toString(), new Attribute<Double>(cat.getId().toString()));
        }
        
        // Iterate through all attributes to compute the minimum and the maximum values.
		for (Attribute<?> attribute : attributes.values()) {
			attribute.computeMinAndMaxValues(caseMap.values());
		}
		
		// Compute weights for attributes to be used in retrieval.
		CaseAttributeWeightAssessment weightAssessment = new CaseAttributeWeightAssessment(
				caseMap.values(), attributes.values());
		weightAssessment.computeWeights();
    }

    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void constructTree() {
        for (Attribute a : attributes.values()) {
            a.discretize(a.casesToValues(caseMap.values()));
        }
        tree = new TreeNode(
                this,
                new HashSet<>(attributes.values()),
                new LinkedList<>(caseMap.keySet()));
    }

    public Case getCase(int caseId) {
        return caseMap.get(caseId);
    }

    public List<Case> getCases(List<Integer> caseIds) {
        List<Case> result = new LinkedList<>();
        for (Integer caseId : caseIds) {
            result.add(getCase(caseId));
        }
        return result;

    }

    @SuppressWarnings("unchecked")
	public static Library load() {
        Library library = new Library();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("attributes.ser"));
            List<Attribute<?>> attributes = (LinkedList<Attribute<?>>) in.readObject();
            for (Attribute<?> a : attributes) {
                library.attributes.put(a.getName(), a);
            }
        } catch (IOException | ClassNotFoundException ex) {
            library.constructAttributes();
            library.constructTree();
            return library;
            //ex.printStackTrace();
        }
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("tree.ser"));
            library.tree = (TreeNode) in.readObject();
            library.tree.setLibrary(library);
        } catch (IOException | ClassNotFoundException ex) {
            library.constructTree();
            //ex.printStackTrace();
        }
        return library;
    }

    public void save() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("attributes.ser"));
            out.writeObject(new LinkedList<>(attributes.values()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("tree.ser"));
            out.writeObject(tree);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public TreeNode getTree() {
        return tree;
    }
    
    public Map<String, Attribute<?>> getAttributes() {
    	return attributes;
    }
    
    private static final SimpleDateFormat sdf = new SimpleDateFormat("H:m");
    
    public static Case getTestCase() throws ParseException {
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
        
        return c;
    }
    
    public static void main(String[] args) throws ParseException {
//        Library library = new Library();
//        library.constructAttributes();
//        library.constructTree();
//        library.save();
        Library library = Library.load();
        TreeNode t = library.getTree();
        System.out.println(t);
        
        Case target = getTestCase();
        RetrievalIndexing ri = new RetrievalIndexing(library, target);
        List<Case> promising = ri.getPromisingCases();
        System.out.println(promising.size());
        System.out.println(promising);
        
        RetrievalSimilarityAssessment rsa = new RetrievalSimilarityAssessment(library, promising, target);
        List<Case> similar = rsa.getSimilarCases();
        System.out.println(similar);
    }
}
