/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam.all;

import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static junit.framework.Assert.fail;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel
 */
public class ExamTest {
    //Exam testExam = new Exam("Packard",303,parseDate("2016-12-16"),parseDate("2016-12-17"));
    //DBMgr dataBaseInstance = DBMgr.getInstance();
    public ExamTest() {
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
    public void tearDown() throws Exception {
    }
    public static Date parseDate(String date) {
     try {
         return new SimpleDateFormat("yyyy-MM-dd").parse(date);
     } catch (ParseException e) {
         return null;
     }
  }

    /**
     * Test of updateDB method, of class Exam.
     */
    @Test
    public void testUpdateDB() throws Exception {
        System.out.println("updateDB");
        DBMgr sample = DBMgr.getInstance();
        Exam instance = new Exam("Esophagomyotomy","Packard","Test","John Doe",303,parseDate("2017-12-16"),parseDate("2017-12-17"));
        Boolean expResult = true;
        Boolean result = false;
        try {
            result = instance.updateDB();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(e.getMessage());
            expResult = false; // set expected result to be false if we received a duplicate entry
        }
        assertEquals(expResult, result);
        sample.deleteExam(instance);
        // TODO review the generated test code and remove the default call to fail.
        if(result!=expResult) {
            fail("Updating the database failed.");
        }
    }

    /**
     * Test of setActualTime method, of class Exam.
     */
    @Test
    public void testSetActualTime() {
        System.out.println("setActualTime");
        Date actualTime = parseDate("2014-12-17");
        Exam instance = new Exam("Esophagomyotomy","Packard","Test","John Doe",303,parseDate("2017-12-16"),parseDate("2017-12-17"));
        instance.setActualStartTime();
        boolean expResult = true;
        boolean result = false;
        if (actualTime.before(instance.getActualStartTime())) {
            result = true;
        }
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(expResult, result);
        if(expResult != result) {
            fail("Setting the actual time of an exam failed.");
        }
        
    }

    /**
     * Test of setPlannedTime method, of class Exam.
     */
    @Test
    public void testSetPlannedTime() {
        System.out.println("setPlannedTime");
        Date plannedTime = parseDate("2016-12-17");
        Exam instance = new Exam("Esophagomyotomy","Packard","Test","John Doe",303,parseDate("2017-12-16"),parseDate("2017-12-17"));
        instance.setPlannedTime(plannedTime);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(plannedTime, instance.getPlannedTime());
        if(plannedTime!=instance.getPlannedTime()) {
            fail("Setting the planned time of an exam failed.");
        }
    }
    
}
