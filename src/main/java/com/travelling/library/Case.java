/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelling.library;

/**
 *
 * @author Stefan
 */
public interface Case {
    
    public Comparable<?> getAttributeValue(String name);
    public void setAttributeValue(String name, Comparable<?> value) throws ClassCastException, IllegalArgumentException;
    public boolean save();
    public double getDistanceForAttribute(Attribute<?> attribute, Case other);
    public double getSimilarityForAttribute(Attribute<?> attribute, Case other);
    public Integer getId();        
    
}
