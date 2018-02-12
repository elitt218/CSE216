/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam.all;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static junit.framework.Assert.assertEquals;
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
public class DataValidationTest {
    
    public DataValidationTest() {
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
     * Test of validateTime method, of class DataValidation.
     */
    @Test
    public void testValidateTime() {
      System.out.println("validateTime");
        Date examTime = parseDate("2017-11-29");//any date today or earlier should result in false
        boolean expResult = true;
        boolean result = DataValidation.validateTime(examTime, 1, 10);
        assertEquals(expResult, result);      
        if(expResult!=result) {
            fail("Failure validating time.");
        }
    }
}