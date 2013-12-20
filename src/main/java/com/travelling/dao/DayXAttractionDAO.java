/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelling.dao;

import com.travelling.entity.CbrDay;
import com.travelling.entity.CbrDayXAttraction;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Stefan
 */
public class DayXAttractionDAO extends AbstractDAOImpl<CbrDayXAttraction> {
    public static final DayXAttractionDAO instance = new DayXAttractionDAO();
    
    private DayXAttractionDAO() {
    }
    
    public List<CbrDayXAttraction> findByDay(CbrDay day) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("day", day);
        return instance.findByNamedQuery("CbrDayXAttraction.findByDay", params);
    }
}
