/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam.gui;

import java.awt.Color;

/**
 *
 * @author jordancutler, Lizzie Litt
 */
public class MainGUI extends javax.swing.JFrame {
    // Implement Singleton pattern for MainGUI
    private static MainGUI mainGUI = MainGUI.getInstance();
    
    /**
     * Creates new form MainGUI
     */
    private MainGUI() {
        initComponents();
        this.getContentPane().setBackground(Color.pink);
    }

    public static MainGUI getInstance() {
        if (mainGUI == null) {
            mainGUI = new MainGUI();
        }
        return mainGUI;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        enterExamButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        updateExamButton = new javax.swing.JButton();
        startExamButton = new javax.swing.JButton();
        endExamButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 400));

        enterExamButton.setText("Create New Exam");
        enterExamButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterExamButtonActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/exam/gui/logo small.png"))); // NOI18N

        updateExamButton.setText("View Exam");
        updateExamButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateExamButtonActionPerformed(evt);
            }
        });

        startExamButton.setText("Start Exam");
        startExamButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startExamButtonActionPerformed(evt);
            }
        });

        endExamButton.setText("End Exam");
        endExamButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endExamButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(enterExamButton)
                            .addGap(100, 100, 100)
                            .addComponent(endExamButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(updateExamButton, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(100, 100, 100)
                            .addComponent(startExamButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateExamButton)
                    .addComponent(startExamButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enterExamButton)
                    .addComponent(endExamButton))
                .addGap(59, 59, 59))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enterExamButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterExamButtonActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new ScheduleExamGUI().setVisible(true);
    }//GEN-LAST:event_enterExamButtonActionPerformed

    private void updateExamButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateExamButtonActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new IntermediateViewExamGUI().setVisible(true);
    }//GEN-LAST:event_updateExamButtonActionPerformed

    private void startExamButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startExamButtonActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new StartOrEndExamGUI(1).setVisible(true);
    }//GEN-LAST:event_startExamButtonActionPerformed

    private void endExamButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endExamButtonActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new StartOrEndExamGUI(0).setVisible(true);
    }//GEN-LAST:event_endExamButtonActionPerformed

    static void makeGUIVisible() {
        mainGUI.setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mainGUI.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton endExamButton;
    private javax.swing.JButton enterExamButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton startExamButton;
    private javax.swing.JButton updateExamButton;
    // End of variables declaration//GEN-END:variables
}