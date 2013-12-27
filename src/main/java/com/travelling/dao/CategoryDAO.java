/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelling.dao;

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
    
}
