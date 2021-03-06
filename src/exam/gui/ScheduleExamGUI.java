/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam.gui;

import exam.all.Exam;
import exam.all.SchedulingController;
import exam.all.DBMgr;
import static exam.all.DBMgr.getBuildings;
import static exam.all.DBMgr.getExamTypes;
import static exam.all.DBMgr.getDoctors;
import exam.all.DataValidation;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static java.lang.Math.toIntExact;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Calendar;
import javax.swing.JDialog;

/**
 *
 * @author jordancutler, danielcolville, andrewdalzon, danielbeadle, Lizzie Litt, paulgrocholske
 */
public class ScheduleExamGUI extends javax.swing.JFrame {

    SchedulingController singletonExamController = SchedulingController.getInstance();
    public static final long HOUR = 3600 * 1000;
    public static final long MINUTE = 60000;

    /**
     * Creates new form ScheduleExamGUI
     */
    public ScheduleExamGUI() {
        initComponents();
        
        
        
        
        this.getContentPane().setBackground(Color.pink);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        submitExamButton = new javax.swing.JButton();
        buildingLabel = new javax.swing.JLabel();
        examDayLabel = new javax.swing.JLabel();
        buildingDropdown = new javax.swing.JComboBox<>();
        roomNumberLabel = new javax.swing.JLabel();
        plannedStartTimeLabel = new javax.swing.JLabel();
        durationLabel = new javax.swing.JLabel();
        doubleTelephonesLabel = new javax.swing.JLabel();
        durationHoursLabel = new javax.swing.JLabel();
        durationMinutesLabel = new javax.swing.JLabel();
        scheduledTimeDropdownHours = new javax.swing.JComboBox<>();
        scheduledTimeDropdownMinutes = new javax.swing.JComboBox<>();
        durationDropdownHours = new javax.swing.JComboBox<>();
        durationDropdownMinutes = new javax.swing.JComboBox<>();
        roomNumberDropdown = new javax.swing.JComboBox<>();
        scheduledTimeDropdownMeridian = new javax.swing.JComboBox<>();
        examDayDatePicker = new org.jdesktop.swingx.JXDatePicker();
        doctorDropDown = new javax.swing.JComboBox<>();
        examTypeDropDown = new javax.swing.JComboBox<>();
        doctorLabel = new javax.swing.JLabel();
        examTypeLabel = new javax.swing.JLabel();
        patientLabel = new javax.swing.JLabel();
        patientFirstNameTextField = new javax.swing.JTextField();
        patientLastNameTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setForeground(new java.awt.Color(0, 0, 255));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                ScheduleExamGUI.this.windowClosing(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        submitExamButton.setText("Save Exam");
        submitExamButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitExamButtonActionPerformed(evt);
            }
        });

        buildingLabel.setText("Building");

        examDayLabel.setBackground(new java.awt.Color(0, 255, 255));
        examDayLabel.setText("Exam Day");

        buildingDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(getBuildings()));
        buildingDropdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buildingDropdownActionPerformed(evt);
            }
        });

        roomNumberLabel.setText("Room #");

        plannedStartTimeLabel.setText("Scheduled Time");

        durationLabel.setText("Duration");

        doubleTelephonesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        doubleTelephonesLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/exam/gui/logo small.png"))); // NOI18N
        doubleTelephonesLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        doubleTelephonesLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        durationHoursLabel.setText("Hrs");

        durationMinutesLabel.setText("Min");

        scheduledTimeDropdownHours.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        scheduledTimeDropdownMinutes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));

        durationDropdownHours.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        durationDropdownMinutes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));

        roomNumberDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "100", "101", "102", "103", "200", "201", "202", "203", "300", "301", "302", "303" }));

        scheduledTimeDropdownMeridian.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AM", "PM" }));

        doctorDropDown.setModel(new javax.swing.DefaultComboBoxModel<>(getDoctors()));

        examTypeDropDown.setModel(new javax.swing.DefaultComboBoxModel<>(getExamTypes()));
        examTypeDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                examTypeDropDownActionPerformed(evt);
            }
        });

        doctorLabel.setText("Doctor");

        examTypeLabel.setText("Exam Type");

        patientLabel.setText("Patient First Name");

        jLabel1.setText("Patient Last Name");

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(8, 11, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(examTypeLabel)
                            .addComponent(doctorLabel))
                        .addGap(66, 66, 66))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(patientLabel))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(doubleTelephonesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(patientFirstNameTextField)
                            .addComponent(patientLastNameTextField)
                            .addComponent(doctorDropDown, 0, 164, Short.MAX_VALUE)
                            .addComponent(examTypeDropDown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(plannedStartTimeLabel)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(examDayLabel)
                                        .addComponent(durationLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(buildingLabel)
                                    .addComponent(roomNumberLabel))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(roomNumberDropdown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buildingDropdown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(examDayDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(durationHoursLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(durationDropdownHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(durationMinutesLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(durationDropdownMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(scheduledTimeDropdownHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(scheduledTimeDropdownMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(scheduledTimeDropdownMeridian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(65, 65, 65))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(submitExamButton)
                                .addGap(403, 403, 403))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(doubleTelephonesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(backButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doctorLabel)
                    .addComponent(doctorDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(examDayLabel)
                    .addComponent(examDayDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(scheduledTimeDropdownHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scheduledTimeDropdownMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scheduledTimeDropdownMeridian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(durationLabel)
                            .addComponent(durationHoursLabel)
                            .addComponent(durationMinutesLabel)
                            .addComponent(durationDropdownHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(durationDropdownMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buildingLabel)
                            .addComponent(buildingDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roomNumberLabel)
                            .addComponent(roomNumberDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(submitExamButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(examTypeLabel)
                            .addComponent(examTypeDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plannedStartTimeLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(patientFirstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(patientLabel))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(patientLastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitExamButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitExamButtonActionPerformed
        // We will put the values from each textfield into these variables
        Date examDate = examDayDatePicker.getDate();
        int durationHours = Integer.parseInt((String) durationDropdownHours.getSelectedItem());
        int durationMinutes = Integer.parseInt((String) durationDropdownMinutes.getSelectedItem());
        int roomNumber = Integer.parseInt((String) (roomNumberDropdown.getSelectedItem()));
        int plannedHour = Integer.parseInt((String) (scheduledTimeDropdownHours.getSelectedItem()));
        int plannedMinute = Integer.parseInt((String) (scheduledTimeDropdownMinutes.getSelectedItem()));
        int amPM = scheduledTimeDropdownMeridian.getSelectedIndex();
        String building = (String) buildingDropdown.getSelectedItem();
        String examType = (String) examTypeDropDown.getSelectedItem();
        String patientFirstName = patientFirstNameTextField.getText();
        String patientLastName = patientLastNameTextField.getText();
        String doctorName = (String) doctorDropDown.getSelectedItem();
        if (!(DataValidation.isAlpha(patientFirstName) && DataValidation.isAlpha(patientLastName))) {
            return;
        }
        if (patientFirstNameTextField.getText().equals("")|| patientLastNameTextField.getText().equals("")) {
            errorBox("Please enter a valid patient name.");
            return;
        }
        String patientName = patientFirstName + " " + patientLastName;
        if (!(singletonExamController.handleData(examDate, examType, doctorName, patientName, durationHours, durationMinutes, 
                roomNumber, plannedHour, plannedMinute, amPM, building))) {
            return;
        }
      
        this.dispose();
        MainGUI.makeGUIVisible();
    }//GEN-LAST:event_submitExamButtonActionPerformed

    private void buildingDropdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buildingDropdownActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buildingDropdownActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void windowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_windowClosing
        // TODO add your handling code here:
        JDialog.setDefaultLookAndFeelDecorated(true);
        if (JOptionPane.showConfirmDialog(null, 
            "Are you sure you want to close this window and lose all the information you entered?", "Discard Exam", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
        {
            this.dispose();
            MainGUI.makeGUIVisible();
            
        }
    }//GEN-LAST:event_windowClosing

    private void examTypeDropDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_examTypeDropDownActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_examTypeDropDownActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
        MainGUI.makeGUIVisible();
    }//GEN-LAST:event_backButtonActionPerformed

    public static void infoBox(String infoMessage) {
        JOptionPane.showMessageDialog(null, infoMessage, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void errorBox(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
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
            java.util.logging.Logger.getLogger(ScheduleExamGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ScheduleExamGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ScheduleExamGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScheduleExamGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new MainGUI().setVisible(true);
                new ScheduleExamGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox<String> buildingDropdown;
    private javax.swing.JLabel buildingLabel;
    private javax.swing.JComboBox<String> doctorDropDown;
    private javax.swing.JLabel doctorLabel;
    private javax.swing.JLabel doubleTelephonesLabel;
    private javax.swing.JComboBox<String> durationDropdownHours;
    private javax.swing.JComboBox<String> durationDropdownMinutes;
    private javax.swing.JLabel durationHoursLabel;
    private javax.swing.JLabel durationLabel;
    private javax.swing.JLabel durationMinutesLabel;
    private org.jdesktop.swingx.JXDatePicker examDayDatePicker;
    private javax.swing.JLabel examDayLabel;
    private javax.swing.JComboBox<String> examTypeDropDown;
    private javax.swing.JLabel examTypeLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField patientFirstNameTextField;
    private javax.swing.JLabel patientLabel;
    private javax.swing.JTextField patientLastNameTextField;
    private javax.swing.JLabel plannedStartTimeLabel;
    private javax.swing.JComboBox<String> roomNumberDropdown;
    private javax.swing.JLabel roomNumberLabel;
    private javax.swing.JComboBox<String> scheduledTimeDropdownHours;
    private javax.swing.JComboBox<String> scheduledTimeDropdownMeridian;
    private javax.swing.JComboBox<String> scheduledTimeDropdownMinutes;
    private javax.swing.JButton submitExamButton;
    // End of variables declaration//GEN-END:variables
}
