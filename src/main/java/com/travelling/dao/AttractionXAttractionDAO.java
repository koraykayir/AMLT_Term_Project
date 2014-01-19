/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.travelling.dao;

import static com.ibm.icu.lang.UCharacter.GraphemeClusterBreak.T;
import com.travelling.entity.CbrAttraction;
import com.travelling.entity.CbrAttractionXAttraction;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Koray
 */
public class AttractionXAttractionDAO  extends AbstractDAOImpl<CbrAttractionXAttraction>{
    
    public static final AttractionXAttractionDAO instance = new AttractionXAttractionDAO();
    
    private AttractionXAttractionDAO() {
        
    }
    
    public List<CbrAttractionXAttraction> findDist(CbrAttraction id1, CbrAttraction id2) {
        String queryString = "SELECT a FROM CbrAttractionXAttraction a " +
                         "WHERE a.fkAttraction1 = :fkAttraction1 and a.fkAttraction2 = :fkAttraction2";
        EntityManager manager = entityManagerFactory.createEntityManager();
        Query q=manager.createQuery(queryString);
        
        try {
            q.setParameter("fkAttraction1", id1 );
            q.setParameter("fkAttraction2", id2 );
                      
            return q.getResultList();
        } catch (RuntimeException e) {
            return new LinkedList<CbrAttractionXAttraction>();
        } finally {
            manager.close();
        }
        
    }
}