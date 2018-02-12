/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* HI Danny and Llit! *?

*/
package exam.all;

import static exam.gui.ScheduleExamGUI.errorBox;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author jordancutler, danielcolville, andrewdalzon
 */
public class DataValidation {
    
    // Will need to check the database for all buildings and see if the building
    // and room number entered is a valid building/room

    public static boolean validateTime(Date examTime, int durationHours, int durationMinutes) {
        if (examTime == null) {
            errorBox("Please make sure to enter an exam date before attempting to submit.");
            return false;
        }
        else if (new Date().after(examTime)) {
            errorBox("You cannot schedule an exam in the past. Please try again.");
            return false;
        }
        else if (durationHours == 0 && durationMinutes == 0) {
            errorBox("No duration for the exam was set.");
            return false;
        }
        return true;
    }
    
    public static boolean isAlpha(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if (!Character.isLetter(c)) {
                errorBox("Please enter a valid patient name.");
                return false;
            }
        }
        return true;
    }
}
