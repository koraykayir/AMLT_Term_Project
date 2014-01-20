/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelling.reuse;

import java.util.ArrayList;

/**
 *
 * @author Filip
 */
public class ReuseDay {
    
    ArrayList<Integer> attractionIDS;

    public ReuseDay() {
        attractionIDS = new ArrayList<>();
    }
    
    public void addAttraction(int id) {
        attractionIDS.add(id);
    }
}
