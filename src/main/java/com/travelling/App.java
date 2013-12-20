package com.travelling;

import com.travelling.dao.CaseDAO;
import com.travelling.entity.CbrCase;

/**
 * Hello world!
 *
 */
public class App 
{
    
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        CbrCase c = CaseDAO.instance.find(1);
        System.out.println(c);
    }
}
