/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelling.UI;

import com.travelling.retain.retain;
import java.util.HashMap;
import com.travelling.dao.DayDAO;
import com.travelling.dao.DayXAttractionDAO;
import java.io.IOException;
import com.travelling.dao.AttractionDAO;
import com.travelling.dao.AttractionXAttractionDAO;
import com.travelling.entity.CbrAttraction;
import com.travelling.entity.CbrAttractionXAttraction;
import com.travelling.entity.CbrDay;
import com.travelling.entity.CbrDayXAttraction;
import java.util.LinkedList;
import java.util.List;
import com.travelling.dao.CaseDAO;
import com.travelling.entity.CbrCase;
import com.travelling.library.Case;
import com.travelling.library.Library;
import com.travelling.library.TreeNode;
import com.travelling.pojo.Day;
import com.travelling.pojo.TravellingCase;
import com.travelling.retrieval.RetrievalSimilarityAssessment;
import java.awt.Image;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.util.Map;

/**
 *
 * @author Koray
 */
public class RetainUI extends javax.swing.JFrame {

    private List<CbrAttraction> attList;
    private TravellingCase target;
    private List<Case> clist;
    private Library library;
    TreeNode a;
    Map<Case, Double> similarities = new HashMap<Case, Double>();

    public RetainUI(Library library, TravellingCase tc) throws IOException {
        initComponents();

        this.library = library;
        this.setResizable(false);
        this.setLocation(200, 200);
        this.jLabel3.setText("");
        this.jLabel4.setText((Integer.toString(jSlider1.getValue())) + "%");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setVisible(true);
        this.jSlider1.setVisible(false);
        this.jLabel1.setVisible(false);
        this.jLabel4.setVisible(false);
        attList = new LinkedList<CbrAttraction>();
        this.target = tc;
        setText(tc);
        getGoogleAPI();
        getDistances();
    }

    private void getGoogleAPI() throws IOException {
        String googleQuery = "http://maps.googleapis.com/maps/api/staticmap?size=500x300&sensor=false&path=color:0xff0000ff|weight:5|";
        for (int i = 0; i < attList.size(); i++) {
            if (i == attList.size() - 1) {
                googleQuery += Double.toString(attList.get(i).getLongitude()) + "," + Double.toString(attList.get(i).getLatitude());
            } else {
                googleQuery += Double.toString(attList.get(i).getLongitude()) + "," + Double.toString(attList.get(i).getLatitude()) + "|";
            }

        }
        for (int i = 0; i < attList.size(); i++) {
            if (i == attList.size() - 1) {
                googleQuery += "&markers=color:blue%7Clabel:" + Integer.toString(i + 1) + "%7C" + Double.toString(attList.get(i).getLongitude()) + "," + Double.toString(attList.get(i).getLatitude());
            } else {
                googleQuery += "&markers=color:blue%7Clabel:" + Integer.toString(i + 1) + "%7C" + Double.toString(attList.get(i).getLongitude()) + "," + Double.toString(attList.get(i).getLatitude());
            }

        }

        Image map = null;
        URL mapURL = new URL(googleQuery);
        map = ImageIO.read(mapURL);

        setVisible(true);
        ImagePanel u;
        u = new ImagePanel(map);
        jPanel2.add(u);
        jPanel2.repaint();



    }

    private void setText(TravellingCase target) {

        String txt = "<html>";



        List<Day> days = target.getDays();

        CbrAttraction prevAttraction = null;
        
        List<CbrAttractionXAttraction> t = new LinkedList<CbrAttractionXAttraction>();
        for (int k = 0; k < days.size(); k++) {
            Day day = days.get(k);
            txt = txt + "<br><font color=\"red\">Day " + (k + 1) + "</font>";
            boolean check = false;
            for (CbrAttraction attraction : day.getAttractions()) {
                txt = txt + "<br>" + attraction.getName();
                if (check) {
                    attList.add(attraction);
                    t = AttractionXAttractionDAO.instance.findDist(attraction, prevAttraction);

                    txt = txt + "&nbsp;&nbsp;<font color=\"green\"> Travelling Time to Here = " + (t.get(0).getBusTime()) + " mins </font> ";
                }
                txt = txt + "&nbsp;<font color=\"blue\"> Visit Duration = " + attraction.getVisitDuration() + " mins </font> ";
                check = true;
                prevAttraction = attraction;
            }
        }

        txt += "</html>";
        this.jLabel3.setText(txt);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jSlider1 = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        retryButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        exitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jCheckBox1.setText("I want to evaluate myself. (Suggested for professional users)");
        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseClicked(evt);
            }
        });
        jCheckBox1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox1StateChanged(evt);
            }
        });
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jCheckBox1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jCheckBox1PropertyChange(evt);
            }
        });

        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });
        jSlider1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jSlider1PropertyChange(evt);
            }
        });

        jLabel1.setText("How good was my result?");

        retryButton.setText("Try another one");
        retryButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retryButtonMouseClicked(evt);
            }
        });
        retryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retryButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("jLabel4");

        jLabel3.setText("jLabel3");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(retryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jCheckBox1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 48, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 204, Short.MAX_VALUE)
                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4))
                .addGap(3, 3, 3)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(retryButton, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void retryButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retryButtonMouseClicked
        this.dispose();
    }//GEN-LAST:event_retryButtonMouseClicked

    private void jCheckBox1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox1StateChanged
        if (this.jCheckBox1.isSelected()) {
            this.jSlider1.setVisible(true);
            this.jLabel1.setVisible(true);
            this.jLabel4.setVisible(true);
        } else {
            this.jSlider1.setVisible(false);
            this.jLabel1.setVisible(false);
            this.jLabel4.setVisible(false);
        }
    }//GEN-LAST:event_jCheckBox1StateChanged

    private void jCheckBox1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jCheckBox1PropertyChange
    }//GEN-LAST:event_jCheckBox1PropertyChange

    private void jCheckBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseClicked
    }//GEN-LAST:event_jCheckBox1MouseClicked

    private void retryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retryButtonActionPerformed
        if (jCheckBox1.isSelected()) {
            retain rtn = new retain(jSlider1.getValue(), this.target, similarities, a);
        } else {
            retain rtn = new retain(this.target, similarities, a);
        }
        this.dispose();
        InputUI inputUI = new InputUI(library);
        inputUI.setVisible(true);
    }//GEN-LAST:event_retryButtonActionPerformed

    private void jSlider1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jSlider1PropertyChange
    }//GEN-LAST:event_jSlider1PropertyChange

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        this.jLabel4.setText((Integer.toString(jSlider1.getValue()) + "%"));
    }//GEN-LAST:event_jSlider1StateChanged

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        if (jCheckBox1.isSelected()) {
            retain rtn = new retain(jSlider1.getValue(), this.target, similarities, a);
        } else {
            retain rtn = new retain(this.target, similarities, a);
        }
        library.save();
        dispose();
    }//GEN-LAST:event_exitButtonActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitButton;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JButton retryButton;
    // End of variables declaration//GEN-END:variables

    private void getDistances() {
        a = library.getTree();
        while (a.hasNext()) {
            a = a.getNext(target);
        }
        clist = library.getTree().getCases();
        clist = a.getCases();
        RetrievalSimilarityAssessment rsa = new RetrievalSimilarityAssessment(library, clist, target);

        similarities = rsa.computeSimilarity();
    }
}
