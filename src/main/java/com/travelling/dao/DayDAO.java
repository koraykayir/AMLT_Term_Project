/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelling.dao;

import com.travelling.entity.CbrCase;
import com.travelling.entity.CbrDay;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Stefan
 */
public class DayDAO extends AbstractDAOImpl<CbrDay> {
    
    public static final DayDAO instance = new DayDAO();
    
    private DayDAO() {
        
    }
    
    public List<CbrDay> findByCase(CbrCase c) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("case", c);
        return instance.findByNamedQuery("CbrDay.findByCase", params);
    }
    
}
