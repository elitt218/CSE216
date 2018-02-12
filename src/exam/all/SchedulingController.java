package exam.all;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static exam.gui.ScheduleExamGUI.errorBox;
import static exam.gui.ScheduleExamGUI.infoBox;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Time;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author jordancutler, andrewdalzon, paulgrocholske, danielcolville
 */
public class SchedulingController {
    
    // These fields will be used to create an exam object. In the createExam() method.
    private String examType;
    private String building;
    private String doctor;
    private String patient;
    private int roomNumber;
    private Date examPlannedDayAndTime;
    private Date plannedEndTime;
    
    // Applying Singleton pattern
    private static SchedulingController instance;
    
    private SchedulingController() {
        
    }
    
    /** Applying Singleton Pattern to Scheduling Controller */
    public static SchedulingController getInstance() {
        if (instance == null) {
            instance = new SchedulingController();
        }
        return instance;
    }
    
    /** Does all of the necessary work to see if the data the user entered was valid
     * and the exam is actually available and able to be entered into the database.
     * If all is correct, it will create the exam and save it to the database.
     * @param examDate
     * @param durationHours
     * @param durationMinutes
     * @param roomNumber
     * @param plannedHour
     * @param plannedMinute
     * @param amPM
     * @param building
     * @return 
     */
    public boolean handleData(Date examDate, String examType, String doctor, String patient, int durationHours,
            int durationMinutes, int roomNumber, int plannedHour, int plannedMinute, int amPM, String building) {
        /* Check if entered data was valid. This will check if a date and duration
        were entered properly */
        if (!this.validateData(examDate, durationHours, durationMinutes)) {
            return false;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(examDate);
        cal.set(Calendar.HOUR, plannedHour);
        cal.set(Calendar.MINUTE, plannedMinute);
        if (amPM == 0)
            cal.set(Calendar.AM_PM, Calendar.AM);
        else
            cal.set(Calendar.AM_PM, Calendar.PM);
        
        examDate = cal.getTime();
        
        cal.add(Calendar.HOUR, durationHours);
        cal.add(Calendar.MINUTE, durationMinutes);
        
        Date endTime = cal.getTime();
        // Now that we know all fields are valid and set to proper values, we can 
        // check if the exam is available.
        this.setFields(examType, building, doctor, patient, roomNumber, examDate, endTime);
        Exam toCreate;
        if (this.isAvailable()) {
            toCreate = this.createExam();
        }
        else {
            errorBox("This exam is not available. Please try again.");
            return false;
        }
        // After creating the exam, lets attempt to connect to the database and save it
        try {
            toCreate.updateDB();
            infoBox("The exam has been saved to the database as exam number: " + DBMgr.lastInserted());
        } catch (SQLIntegrityConstraintViolationException e) {
            infoBox("Duplicate entry into the database.");
        } catch (Exception e) {
            infoBox("An error occurred with updating the database.");
            System.out.println("An error occurred with updating the database.");
            System.out.println(e.getMessage());
        }
        
        System.out.println("The user entered this data:");
        System.out.println("Exam Type: " + examType);
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Building: " + building);
        System.out.println("Doctor: " + doctor);
        System.out.println("Patient: " + patient);
        System.out.println("Planned Start: " + examDate.toString());
        System.out.println("Planned End: " + endTime.toString());
        System.err.println();
        return true;
    }
    
    /** Checks the building and room number to make sure it is a valid building
     * and room number combination.
     * @return 
     */
    public boolean validateData(Date examDate, int durationHours, int durationMinutes) {
        return DataValidation.validateTime(examDate, durationHours, durationMinutes);
    }
    
    /** Calls on the private isAvailable method to make sure the time slots and room
     is available for usage. */
    public boolean isAvailable() {
        return isAvailable(building, roomNumber, examPlannedDayAndTime, plannedEndTime);
    }
    
    /** Checks database for availablity based on fields given. */
    private boolean isAvailable(String building, int roomNum, Date examPlannedDayAndTime, Date plannedEndTime) {
        DBMgr dbSingleton = DBMgr.getInstance();
        try {
            return dbSingleton.checkAvailability(building, roomNum, examPlannedDayAndTime, plannedEndTime);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    /** Set the fields of the controller to specified values */
    public void setFields(String examType, String building, String doctor, String patient,
            int roomNum, Date examDay, Date plannedEndTime) {
        this.examType = examType;
        this.building = building;
        this.doctor = doctor;
        this.patient = patient;
        this.roomNumber = roomNum;
        this.examPlannedDayAndTime = examDay;
        this.plannedEndTime = plannedEndTime;
    }
    
    /** Creates an exam after all fields have been validated and we have been converted
     * to the proper data type.
     * @return 
     */
    public Exam createExam() {
        return new Exam(examType, building, doctor, patient, roomNumber, examPlannedDayAndTime, plannedEndTime);
    }
    protected String getExamType() {
        return examType;
    }
    /** Get method for building field */
    protected String getBuilding() {
        return building;
    }
    protected String getDoctor() {
        return doctor;
    }
    protected String getPatient() {
        return patient;
    }
    /** Get method for room number field */
    protected int getRoomNumber() {
        return roomNumber;
    }
    
    /** Get method for planned start time */
    protected Date getPlannedStartTime() {
        return examPlannedDayAndTime;
    }
    
    /** Get method for planned end time */
    protected Date getPlannedEndTime() {
        return plannedEndTime;
    }
}
