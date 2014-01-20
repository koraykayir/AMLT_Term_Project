/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelling;

import com.travelling.UI.InputUI;
import com.travelling.library.Library;
import java.io.FileInputStream;
import java.util.Properties;

/**
 *
 * @author Stefan
 */
public class Main {
    
    public static void main(String[] args) {
        Library library = Library.load();
        InputUI inputUI = new InputUI(library);
        inputUI.setVisible(true);
    }
    
}
