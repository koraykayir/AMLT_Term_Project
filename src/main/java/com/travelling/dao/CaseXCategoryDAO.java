/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelling.dao;

import com.travelling.entity.CbrCase;
import com.travelling.entity.CbrCaseXCategory;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Stefan
 */
public class CaseXCategoryDAO extends AbstractDAOImpl<CbrCaseXCategory>{
    
    public static final CaseXCategoryDAO instance = new CaseXCategoryDAO();
    
    private CaseXCategoryDAO() {
        
    }
    
    public List<CbrCaseXCategory> findByCase(CbrCase c) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("case", c);
        return instance.findByNamedQuery("CbrCaseXCategory.findByCase", params);
    }
    
}
