/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelling.dao;

import com.travelling.entity.CbrCase;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Stefan
 */

public class CaseDAO extends AbstractDAOImpl<CbrCase>{
    public static final CaseDAO instance = new CaseDAO();
    
    private CaseDAO() {
        
    }    
    
    
}
