/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.travelling.UI;

import com.travelling.dao.CategoryDAO;
import com.travelling.entity.CbrCategory;
import com.travelling.library.Case;
import com.travelling.library.Library;
import com.travelling.pojo.TravellingCase;
import com.travelling.retrieval.RetrievalIndexing;
import com.travelling.retrieval.RetrievalSimilarityAssessment;
import com.travelling.reuse.Reuse;
import java.awt.GridLayout;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Koray
 */
public class InputUI extends javax.swing.JFrame {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("H:m");
    
    private List<PreferenceBox> preferenceBoxes = new LinkedList<>();
    private Library library;
    /**
     * Creates new form InputUI
     */
    public InputUI(Library library) {
        
        initComponents();
        this.library = library;
        List<CbrCategory> categories = CategoryDAO.instance.findLeaves();
        JPanel preferences = new JPanel(new GridLayout(0, 3));
        scrollPanel.setViewportView(preferences);
        int i = 0;
        for (CbrCategory cat : categories) {
            PreferenceBox pf = new PreferenceBox(cat);
            preferences.add(pf);
            preferenceBoxes.add(pf);
        }
        startTime.setText("00:00");
        endTime.setText("23:59");
        repaint();
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        money = new javax.swing.JSpinner();
        days = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        startTime = new javax.swing.JFormattedTextField();
        endTime = new javax.swing.JFormattedTextField();
        scrollPanel = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Please specify how much money do you have in order to spend for this holiday?");

        jLabel2.setText("And how many days do you have?");

        money.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(10)));

        days.setModel(new javax.swing.SpinnerNumberModel(1, 1, 5, 1));

        jLabel3.setText("Finally, give points between 0 and 100 to following attractions according to your desires and I am going to create a travel plan for you.");

        jButton1.setText("I am ready!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel44.setText("Starting time is");

        jLabel45.setText("Ending time is");

        startTime.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("h:mm"))));
        startTime.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        startTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startTimeActionPerformed(evt);
            }
        });

        endTime.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("h:mm"))));
        endTime.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        endTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endTimeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(money, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(days, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(endTime, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(startTime, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(scrollPanel)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(money, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(days, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(endTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
        TravellingCase inputCase = new TravellingCase();
        inputCase.setMoney((Integer)money.getValue());
        inputCase.setNumberOfDays((Integer)days.getValue());
        try {
            inputCase.setStartTime(SDF.parse(startTime.getText()));
            inputCase.setEndTime(SDF.parse(endTime.getText()));
        } catch (ParseException ex) {
            Logger.getLogger(InputUI.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        Map<CbrCategory, Double> preferences = inputCase.getPreferences();
        for (PreferenceBox pf : preferenceBoxes) {
            preferences.put(pf.getCategory(), pf.getValue());
        }
        
        //Retrieve
        RetrievalIndexing ri = new RetrievalIndexing(library, inputCase);
        List<Case> promising = ri.getPromisingCases();
        
        RetrievalSimilarityAssessment rsa = new RetrievalSimilarityAssessment(library, promising, inputCase);
        List<Case> similar = rsa.getSimilarCases();
        
        //Reuse
        Reuse reuse = new Reuse(inputCase, similar);
        Case solution = reuse.getSolution();
        
        this.dispose();
        
        try {
            RetainUI retainUI = new RetainUI(library, (TravellingCase)solution);
            retainUI.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(InputUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void startTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startTimeActionPerformed

    private void endTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_endTimeActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        library.save();
    }//GEN-LAST:event_formWindowClosing
  
                           
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner days;
    private javax.swing.JFormattedTextField endTime;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner money;
    private javax.swing.JScrollPane scrollPanel;
    private javax.swing.JFormattedTextField startTime;
    // End of variables declaration//GEN-END:variables
}
