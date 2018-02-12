/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam.all;

import exam.gui.ScheduleExamGUI;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author jordancutler, paulgrocholske, danielcolville
 */

public class Exam {

    // Fields for an Exam
    protected String building;
    protected String doctor;
    protected String patient;
    protected int roomNum;
    protected Date plannedExamDayAndTime;
    protected Date plannedEndTime;
    protected Date actualStartTime = null;
    protected Date actualEndTime = null;
    protected String status;
    protected final int exam_num;
    protected String examType;

    /**
     * Controller will create Exam objects. 
     *
     * @param building
     * @param roomNum
     * @param examDay
     * @param plannedTime
     * @param status
     */
    
    public Exam() {
        this.exam_num = -1;
    }
    
    public Exam(String examType, String building, String doctor, String patient, 
            int roomNum, Date examTime, Date plannedEndTime) {
        this.examType = examType;
        this.building = building;
        this.doctor = doctor;
        this.patient = patient;
        this.roomNum = roomNum;
        this.plannedExamDayAndTime = examTime;
        this.plannedEndTime = plannedEndTime;
        this.status = "Scheduled";
        this.exam_num = -1;
    }
    
   public Exam(String examType, String building, String doctor, String patient, 
           int roomNum, Date examTime, Date plannedEndTime,
           Date actualTime, Date actualEnd, String status,int exam_num) {
       this.examType = examType;
       this.building = building;
       this.doctor = doctor;
       this.patient = patient;
       this.roomNum = roomNum;
       this.plannedExamDayAndTime = examTime;
       this.plannedEndTime = plannedEndTime;
       this.status = status;
       this.actualStartTime = actualTime;
       this.actualEndTime = actualEnd;
       this.exam_num = exam_num;
   }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    /**
     * For changing the status of an exam to either "Scheduled" (0), "In
     * process" (1), "Completed" (2)
     *
     * @param status
     */

    /** Updates the database with the exam */
    public Boolean updateDB() throws Exception {
        DBMgr dbInstance = DBMgr.getInstance();
        System.out.println("Exam.java updatedb() examType: " + examType);
        return dbInstance.addExam(examType, building, doctor, patient, 
                roomNum, plannedExamDayAndTime, plannedEndTime);
    }

    /**
     * For setting the actual start time once an exam begins.
     */
    public void setActualStartTime() {
        this.actualStartTime = new Date();
        this.status = "In process";
    }
    
    public void setActualEndTime() {
        this.actualEndTime = new Date();
        this.status = "Completed";
    }

    /**
     * For changing the planned time if rescheduling occurs
     */
    protected void setPlannedTime(Date examTime) {
        this.plannedExamDayAndTime = examTime;
    }
    /* Get method for getting actual time */
    public Date getActualStartTime() {
        return this.actualStartTime;
    }
    
    public Date getActualEndTime() {
        return this.actualEndTime;
    }

    /**
     * Get method for planned time
     */
    public Date getPlannedTime() {
        return this.plannedExamDayAndTime;
    }

    public int getExamNumber() {
        return this.exam_num;
    }
    /**
     * Get method for actual end time
     */
    public Date getEndTime() {
        return this.plannedEndTime;
    }

    /**
     * Get method for status of the exam
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Get method for building field
     */
    public String getBuilding() {
        return this.building;
    }

    /**
     * Get method for room number field
     */
    public int getRoomNumber() {
        return this.roomNum;
    }
    
    public String getExamType() {
        return this.examType;
    }
    
    public String getDoctor() {
        return this.doctor;
    }
    
    public String getPatient() {
        return this.patient;
    }
    
}

