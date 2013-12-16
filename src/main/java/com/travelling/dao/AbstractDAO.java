package com.travelling.dao;

import java.util.HashMap;
import java.util.List;


/**
 *
 * @author Deroude
 */

public interface AbstractDAO<T> {
    
    T create(T t);

    void delete(Object id);

    T find(Object id);

    T update(T t);
    
    List<T> findAll();
    
    List<T> findByNamedQuery(String qry,HashMap<String,Object> params);
    
    T findOneByNamedQuery(String qry,HashMap<String,Object> params);
}
