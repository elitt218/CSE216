/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam.all;

import static exam.all.ExamTest.parseDate;
import java.util.Calendar;
import java.util.Date;
import junit.framework.TestCase;

/**
 *
 * @author jordancutler
 */
public class SchedulingControllerTest extends TestCase {
    
    protected static SchedulingController instance;
    protected static DBMgr dbMgrInstance;
    public SchedulingControllerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        instance = SchedulingController.getInstance();
        dbMgrInstance = DBMgr.getInstance();
        Date randomDate = new Date(118, 11, 20, 4, 30);
        Date endTime = new Date(118, 11, 20, 5, 0);
        instance.setFields("Esophagomyotomy","Packard","Test","John Doe", 203, randomDate, endTime);
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        // This is done for the handleDate() method test. Need to delete
        // the exam we created so it wont give an error
        Calendar cal = Calendar.getInstance();
        cal.setTime(instance.getPlannedStartTime());
        cal.set(Calendar.HOUR, 7);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.AM_PM, Calendar.AM);
        Date examDate = cal.getTime();
        cal.add(Calendar.HOUR, 1);
        Date endTime = cal.getTime();
        try {
            dbMgrInstance.deleteExam("Linderman", 303, examDate, endTime);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        super.tearDown();
    }

    /**
     * Test of getInstance method, of class SchedulingController.
     */
    public void testGetInstance() {
        System.out.println("getInstance");
        SchedulingController expResult = instance;
        SchedulingController result = SchedulingController.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if (expResult!=result) {
            fail("Instance did not equal what was expected.");
        }
    }

    public void testHandleData() {
        System.out.println("handleData");
        boolean result = instance.handleData(instance.getPlannedStartTime(),"Esophagomyotomy","Test","John Doe", 1, 0, 303, 7, 0, 0, "Linderman");
        boolean expResult = true;
        assertEquals(expResult, result);
        if (expResult != result) {
            fail("handleData() did not yeild the expected result.");
        }
    }
    /**
     * Test of validateData method, of class SchedulingController.
     */
    public void testValidateData() {
        System.out.println("validateData");
        boolean expResult = true;
        boolean result = instance.validateData(parseDate("2016-12-16"), 1, 5);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if (expResult!=result) {
            fail("Validate data did not give expected result.");
        }
    }

    /**
     * Test of isAvailable method, of class SchedulingController.
     */
    public void testIsAvailable() {
        System.out.println("isAvailable");
        boolean expResult = true;
        boolean result = instance.isAvailable();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setFields method, of class SchedulingController.
     */
    public void testSetFields() {
        System.out.println("setFields");
        String building = "Linderman";
        String examType="Esophagomyotomy";
        String doctor="Test";
        String patient="John Doe";
        int roomNum = 102;
        Date examDay = new Date(119, 11, 20, 4, 30);
        Date plannedEndTime = new Date(119, 11, 20, 5, 0);
        instance.setFields(examType,building,doctor,patient, roomNum, examDay, plannedEndTime);
        boolean expResult1 = (building == instance.getBuilding());
        boolean expResult2 = (roomNum == instance.getRoomNumber());
        boolean expResult3 = (examDay == instance.getPlannedStartTime());
        boolean expResult4 = (plannedEndTime == instance.getPlannedEndTime());
        assertEquals(building, instance.getBuilding());
        assertEquals(roomNum, instance.getRoomNumber());
        assertEquals(examDay, instance.getPlannedStartTime());
        assertEquals(plannedEndTime, instance.getPlannedEndTime());
        // TODO review the generated test code and remove the default call to fail.
        if (!(expResult1 && expResult2 && expResult3 && expResult4)) {
            fail("The fields were not set properly.");
        }
    }

    /**
     * Test of createExam method, of class SchedulingController.
     */
    public void testCreateExam() {
        System.out.println("createExam");
        Exam expResult = new Exam(instance.getExamType(),instance.getBuilding(),instance.getDoctor(),instance.getPatient(), instance.getRoomNumber(),
        instance.getPlannedStartTime(), instance.getPlannedEndTime());
        Exam result = instance.createExam();
        boolean expResult1 = (expResult.building == result.building);
        boolean expResult2 = (expResult.roomNum == result.roomNum);
        boolean expResult3 = (expResult.plannedExamDayAndTime == result.plannedExamDayAndTime);
        boolean expResult4 = (expResult.plannedEndTime == result.plannedEndTime);
        assertEquals(expResult.building, result.building);
        assertEquals(expResult.roomNum, result.roomNum);
        assertEquals(expResult.plannedExamDayAndTime, result.plannedExamDayAndTime);
        assertEquals(expResult.plannedEndTime, result.plannedEndTime);
        // TODO review the generated test code and remove the default call to fail.
        if (!(expResult1 && expResult2 && expResult3 && expResult4)) {
            fail("The exams did not populate with the same fields");
        }
    }
    
    public void main() {
        testGetInstance();
        testValidateData();
        testSetFields();
        testCreateExam();
    }
    
}
