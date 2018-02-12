/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam.all;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel Colville
 */
public class DBMgrTest {
    
    public DBMgrTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    public static Date parseDate(String date) {
     try {
         return new SimpleDateFormat("yyyy-MM-dd").parse(date);
     } catch (ParseException e) {
         return null;
     }
    }
    
    /**
     * Test of getInstance method, of class DBMgr.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        DBMgr expResult = DBMgr.getInstance();
        DBMgr result = DBMgr.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of addExam method, of class DBMgr.
     */
    @Test
    public void testAddExam() throws Exception {
        System.out.println("addExam");
        String examType="Esophagomyotomy";
        String building = "Packard";
        String doctor="Test";
        String patient="John Doe";
        int roomNum = 303;
        Date examPlannedDayAndTime = parseDate("2016-12-29");
        Date plannedEndTime = parseDate("2016-12-30");
        DBMgr instance = DBMgr.getInstance();
        //Boolean expResult = null;
        Boolean result = instance.addExam(examType,building,doctor,patient, roomNum, examPlannedDayAndTime, plannedEndTime);
        Connection con = DriverManager.getConnection("jdbc:mysql://cr137.cse.lehigh.edu:3306/db09?user=user09&password=rite-show");
        //all of these prepared statements are super tentative since the table(s) isn't even created yet
        PreparedStatement find = con.prepareStatement("SELECT * FROM exam "+
                "WHERE building = ? AND room = ? "+
                "AND ((start_time BETWEEN ? AND ?) OR (end_time BETWEEN ? AND ?))");
        Timestamp start = new Timestamp(examPlannedDayAndTime.getTime());
        Timestamp end = new Timestamp(plannedEndTime.getTime());
        find.setString(1, building);
        find.setString(2, String.valueOf(roomNum));
        find.setTimestamp(3, start);
        find.setTimestamp(4, end);
        find.setTimestamp(5, start);
        find.setTimestamp(6, end);
        ResultSet results = find.executeQuery();
        if(results.next()) {
            if(!building.equals(results.getString("building"))||!String.valueOf(roomNum).equals(results.getString("room"))
                    ||!start.toString().equals(results.getString("start_time"))||!end.toString().equals(results.getString("end_time"))) {
            fail("Couldn't find added exam in database.");
            }
        }
        instance.deleteExam(building, roomNum, examPlannedDayAndTime, plannedEndTime);
    }

    /**
     * Test of checkAvailability method, of class DBMgr.
     */
    @Test
    public void testCheckAvailability() throws Exception {
        System.out.println("checkAvailability");
        String building = "Packard";
        int roomNum = 303;
        Date examPlannedDayAndTime = parseDate("2016-12-29");
        Date plannedEndTime = parseDate("2016-12-30");
        DBMgr instance = DBMgr.getInstance();
        Boolean expResult = true;
        Boolean result = instance.checkAvailability(building, roomNum, examPlannedDayAndTime, plannedEndTime);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if(!Objects.equals(expResult, result)) {
            fail("Check Availability gave wrong result.");
        }
    }
    @Test
    public void testViewExam() throws SQLException {
        
        System.out.println("viewExam");
        int exam_num=92;
        DBMgr instance = DBMgr.getInstance();
        Exam result=instance.viewExam(exam_num);
        boolean expResult=true;//should be true if that exam number actually is in database
        try {
            String test=result.doctor;
            if(!expResult) {
                fail("Unexpected result from viewExam");
            }
        }
        catch(NullPointerException ex) {
            if(expResult) {
                fail("Unexpected result from viewExam");
            }
        }
        
    }
     @Test 
    public void testEndExam() throws SQLException {
        System.out.println("endExam");
        int exam_num=102;
        DBMgr instance = DBMgr.getInstance();
        Boolean result=instance.endExam(exam_num);
        String curr= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+".0";
        Connection con = DriverManager.getConnection("jdbc:mysql://cr137.cse.lehigh.edu:3306/db09?user=user09&password=rite-show");
        PreparedStatement find = con.prepareStatement("select * from db09.exam where exam_num = ?");
        find.setString(1,String.valueOf(exam_num));
        ResultSet stored=find.executeQuery();
        if(stored.next()) {
            if(!curr.equals(stored.getString("actual_end"))&&result) {
                fail("Exam not ended");
            }
        }
    }
    @Test 
    public void testStartExam() throws SQLException {
        System.out.println("startExam");
        int exam_num=102;
        DBMgr instance = DBMgr.getInstance();
        Boolean result=instance.startExam(exam_num);
        String curr= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+".0";
        Connection con = DriverManager.getConnection("jdbc:mysql://cr137.cse.lehigh.edu:3306/db09?user=user09&password=rite-show");
        PreparedStatement find = con.prepareStatement("select * from db09.exam where exam_num = ?");
        find.setString(1,String.valueOf(exam_num));
        ResultSet stored=find.executeQuery();
        if(stored.next()) {
            if(!curr.equals(stored.getString("actual_start"))&&result) {
                fail("Exam not started");
            }
        }
    }
    @Test 
    public void testDeleteExam() throws SQLException {
        String examType="Esophagomyotomy";
        String building = "Packard";
        String doctor="Test";
        String patient="John Doe";
        int roomNum = 303;
        Date examPlannedDayAndTime = parseDate("2016-12-29");
        Date plannedEndTime = parseDate("2016-12-30");
        DBMgr instance = DBMgr.getInstance();
        instance.addExam(examType,building,doctor,patient,roomNum,examPlannedDayAndTime,plannedEndTime);
        instance.deleteExam(building,roomNum,examPlannedDayAndTime,plannedEndTime);
        if(!instance.checkAvailability(building,roomNum,examPlannedDayAndTime,plannedEndTime)) {
           fail("Couldn't delete exam"); 
        }
        instance.addExam(examType,building,doctor,patient,roomNum,examPlannedDayAndTime,plannedEndTime);
        instance.deleteExam(new Exam(examType,building,doctor,patient,roomNum,examPlannedDayAndTime,plannedEndTime));
        if(!instance.checkAvailability(building,roomNum,examPlannedDayAndTime,plannedEndTime)) {
           fail("Couldn't delete exam"); 
        }
        instance.addExam(examType,building,doctor,patient,roomNum,examPlannedDayAndTime,plannedEndTime);
        instance.deleteExam(DBMgr.lastInserted());
       if(!instance.checkAvailability(building,roomNum,examPlannedDayAndTime,plannedEndTime)) {
           fail("Couldn't delete exam"); 
        }
        
    }
    
    
}
