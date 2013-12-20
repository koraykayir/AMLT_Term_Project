/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelling.dao;

import com.travelling.entity.CbrCategory;

/**
 *
 * @author Stefan
 */
public class CategoryDAO extends AbstractDAOImpl<CbrCategory>{
    public static final CategoryDAO instance = new CategoryDAO();
    
    private CategoryDAO() {
        
    }
    
}
