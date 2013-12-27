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
    
    public Object getAttribute(String name);
    public void setAttribute(String name, Object value) throws ClassCastException, IllegalArgumentException;
    public boolean save();
}
