/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelling.library;

import com.travelling.dao.CaseDAO;
import com.travelling.dao.CategoryDAO;
import com.travelling.entity.CbrCase;
import com.travelling.entity.CbrCategory;
import com.travelling.pojo.TravellingCase;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
        for (CbrCase c : CaseDAO.instance.findAll())
            caseMap.put(c.getId(), new TravellingCase(c));
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
    
}
