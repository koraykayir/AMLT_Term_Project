/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.travelling.retain;

import com.travelling.dao.CaseDAO;
import com.travelling.entity.CbrCase;
import com.travelling.entity.CbrDay;
import com.travelling.pojo.TravellingCase;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Koray
 */
public class retain {

    public retain(int value, TravellingCase tc) {
        
        double poss = (Math.abs(value - 50))/62.5;
        
        Random rand = new Random();
        
        double n = rand.nextDouble();
        TravellingCase tc1 = tc;
       
        List<CbrCase> caseList = CaseDAO.instance.findAll();
        
        tc1.setId(null);
        
        tc1.setSuccessRatio((double)value);
        if(poss>=n){
            tc1.save();
        }
        
    }

    public retain(TravellingCase tc) {
        TravellingCase tc1 = tc;
        tc1.setSuccessRatio(null);
        tc1.setId(null);
        tc1.save();
    }
    
}
