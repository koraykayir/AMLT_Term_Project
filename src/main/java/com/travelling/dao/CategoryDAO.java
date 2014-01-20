/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelling.dao;

import com.travelling.entity.CbrAttraction;
import com.travelling.entity.CbrCategory;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Stefan
 */
public class CategoryDAO extends AbstractDAOImpl<CbrCategory>{
    public static final CategoryDAO instance = new CategoryDAO();
    
    private CategoryDAO() {
        
    }
    
    public List<CbrCategory> findLeaves() {
        return instance.findByNamedQuery("CbrCategory.findLeaves", new HashMap<String, Object>());
    }
    
    public List<CbrCategory> findByAttraction(CbrAttraction attraction) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("attraction", attraction);
        return instance.findByNamedQuery("CbrCategory.findByAttraction", params);
    }
    
}
