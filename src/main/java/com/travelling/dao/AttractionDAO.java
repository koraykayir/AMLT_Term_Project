/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelling.dao;

import com.travelling.entity.CbrAttraction;

/**
 *
 * @author Stefan
 */
public class AttractionDAO extends AbstractDAOImpl<CbrAttraction>{
    
    public static final AttractionDAO instance = new AttractionDAO();
    
    private AttractionDAO() {
        
    }
    
}
