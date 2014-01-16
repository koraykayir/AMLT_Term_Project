/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.travelling.dao;

import com.travelling.entity.CbrAttraction;
import com.travelling.entity.CbrAttractionXAttraction;

/**
 *
 * @author Koray
 */
public class AttractionXAttractionDAO  extends AbstractDAOImpl<CbrAttractionXAttraction>{
    
    public static final AttractionXAttractionDAO instance = new AttractionXAttractionDAO();
    
    private AttractionXAttractionDAO() {
        
    }
}
    
