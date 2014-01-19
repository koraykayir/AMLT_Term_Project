package com.travelling;

import com.travelling.dao.AttractionDAO;
import com.travelling.dao.AttractionXAttractionDAO;
import com.travelling.dao.CaseDAO;
import com.travelling.entity.CbrAttraction;
import com.travelling.entity.CbrAttractionXAttraction;
import com.travelling.entity.CbrCase;
import com.travelling.mapQuery.mapSearch; //For Testing
import com.travelling.mapQuery.location; //For Testing
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.travelling.security.getSecure;
import java.io.IOException;
import com.travelling.UI.retain_UI;
/**
 * Hello world!
 *
 */
public class App 
{
    
    public static void main( String[] args ) throws IOException, InterruptedException
    {
        System.out.println( "Hello World!" );
        
        //////////////////////////////////////////////////////////////////////////////////////////
        //        getSecure x = new getSecure();
        //        int a;
        //        a=x.secure(1);      // DO NOT RUN THESE LINES UNLESS YOU CHANGE THE ATTRACTIONS
        /////////////////////////////////////////////////////////////////////////////////////////
        

        retain_UI a;
        a = new retain_UI(5);
        CbrCase c = CaseDAO.instance.find(1);
        System.out.println(c);
    }
}
