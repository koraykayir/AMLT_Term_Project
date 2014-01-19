/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.travelling.retain;

import com.travelling.dao.CaseDAO;
import com.travelling.entity.CbrCase;
import com.travelling.entity.CbrDay;
import com.travelling.library.Case;
import com.travelling.library.TreeNode;
import com.travelling.pojo.TravellingCase;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Koray
 */
public class retain {

    public retain(int value, TravellingCase tc, Map<Case, Double> similarities, TreeNode node) {
        double poss = (Math.abs(value - 50))/62.5;
        
        Random rand = new Random();
        
        double n = rand.nextDouble();
        TravellingCase tc1 = tc;
        
        
       
        List<CbrCase> caseList = CaseDAO.instance.findAll();
        Collection<Double> u = similarities.values();
        boolean check =true;
        for(Double a : u){
             if(a>15)
                 check=false;
        }
        
        tc1.setId(null);
        if(check){
            tc1.setSuccessRatio((double)value);
            if(poss>=n){
                tc1.save();
                node.add(tc1);
            }
        }
    }

    public retain(TravellingCase tc, Map<Case, Double> similarities,TreeNode node) {
        TravellingCase tc1 = tc;
        Collection<Double> u = similarities.values();
        boolean check =true;
        for(Double a : u){
             if(a>15)
                 check=false;
        }
        
        tc1.setId(null);
   //     if(check){
            tc1.setSuccessRatio(null);
            
            tc1.save();
            node.add(tc1);
     //   }
    }
   
}
