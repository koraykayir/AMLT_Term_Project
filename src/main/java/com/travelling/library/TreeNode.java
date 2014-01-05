/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelling.library;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Stefan
 */
public class TreeNode implements Serializable{
    private Attribute<?> attribute;
    private Map<Attribute.Interval, TreeNode> children = new HashMap<>();
    private int numberOfCases;
    private List<Integer> cases = null;
    private transient Library library;
    
    public TreeNode(Library library, Set<Attribute<?>> attributes, List<Integer> cases) {
        this.library = library;
        numberOfCases = cases.size();
        if (cases.size() <= Library.NEIGHBOURHOOD_SIZE) {
            this.cases = cases;
            return;
        }
        double max = -1.;
        Attribute<?> attr = null;
        for (Attribute<?> a : attributes) {
            double gain = a.getInformationGain(library.getCases(cases));
            if (gain > max) {
                max = gain;
                attr = a;
            }
        }
        attribute = attr;
        Map<Attribute.Interval, List<Integer>> casesMap = new HashMap<>();
        for (Attribute.Interval bin : attribute.getBins()) {
            casesMap.put(bin, new LinkedList<Integer>());
        }
        for (Integer caseId : cases) {
            Attribute.Interval bin = attribute.getBin(library.getCase(caseId));
            if (bin != null) {
                casesMap.get(bin).add(caseId);
            }
        }
        Set<Attribute<?>> newSet = new HashSet<>(attributes);
        newSet.remove(attribute);
        for (Attribute.Interval bin : attribute.getBins()) {
            children.put(bin, new TreeNode(library, newSet, casesMap.get(bin)));
        }
    }
    
    public List<Case> getCases() {
        if (cases != null) return library.getCases(cases);
        List<Case> result = new LinkedList<>();
        for (TreeNode node : children.values()) {
            result.addAll(node.getCases());
        }
        return result;
    }
    
    public int getNumberOfCases() {
        return numberOfCases;
    }
    
    public boolean hasNext() {
        return (children != null);
    }
    
    public TreeNode getNext(Case c) {
        if (children == null) return null;
        Attribute.Interval bin = attribute.getBin(c);
        return children.get(bin);
    }
    
    public void setLibrary(Library library) {
        this.library = library;
        if (children != null) {
            for (TreeNode child : children.values())
                child.setLibrary(library);
        }
    }
    
    @Override
    public String toString() {
        return print(0);
    }
    
    public String print(int depth) {
        String s = "";
        for (int i = 0; i < depth; i++) s += "\t";
        if (attribute == null) {
            s += "leaf\n";
            return s;
        }
        else s += attribute + "\n";
        if (children == null) return s;
        for (Attribute.Interval bin : attribute.getBins()) {
            s += children.get(bin).print(depth + 1);
        }
        return s + "\n";
    }
}
