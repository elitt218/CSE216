/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam.all;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
/**
 * 
 * @author paulgrocholske, danielbeadle, danielcolville
 */
public class DBMgr {
    private Connection con;
    
    //hell yeah we using a singleton boys
    public static DBMgr instance;
    PreparedStatement insert;
    PreparedStatement find;
    PreparedStatement delete;
    PreparedStatement last_inserted_id;
    PreparedStatement exam_types;
    PreparedStatement doc_available;
    PreparedStatement buildings;
    PreparedStatement doctors;
    
    
    private DBMgr(String server, int port, String db_name, String uname, String password) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        
        // con = DriverManager.getConnection("jdbc:mysql://cr137.cse.lehigh.edu:3306/db09?user=user09&password=rite-show");
        System.out.println("jdbc:mysql://" + server + ":" + port + "/" + db_name + "?user=" + uname + "&password=" + password);
        con = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + db_name + "?user=" + uname + "&password=" + password);
        insert = con.prepareStatement("INSERT INTO exam "+
                                        "VALUES (NULL, ?, ?, NULL, NULL, ?, ?, NULL, " +
                                        "?, ?, 'Scheduled', NULL, NULL, ?)", Statement.RETURN_GENERATED_KEYS);
        
        delete = con.prepareStatement("DELETE FROM db09.exam"
                + " WHERE start_time = ?"
                + " AND end_time = ?"
                + " AND building = ?"
                + " AND room = ?");
        
        last_inserted_id = con.prepareStatement("SELECT LAST_INSERT_ID()");
        
        doctors = con.prepareStatement("SELECT * FROM db00.Clinical_Staff where Category = 'Physician'");
        
        buildings = con.prepareStatement("SELECT * FROM db16.facilities");
        
        // Same logic as find, check out the monster comment before that prepared statement if you're curious. 
        doc_available = con.prepareStatement("SELECT * FROM db06.exam_type WHERE physician_name = ? AND"
                + "(start_time < \"2016-11-29 21:00:00\" AND \"2016-11-29 21:00:00\" < end_time)"
                + "OR (start_time < \"2016-11-29 22:00:00\" AND \"2016-11-29 22:00:00\" < end_time)"
                + "OR (start_time = \"2016-11-29 21:00:00\")"
                + "OR (end_time = \"2016-11-29 22:00:00\")");                

        exam_types = con.prepareStatement("SELECT * FROM db06.exam_type WHERE status0 = 'ACTIVE'");
        
        // wow this is ugly
        /* find = con.prepareStatement("SELECT * FROM exam "+
                                    "WHERE building = ? AND room = ? "+
                                    "AND ((start_time < ?) AND (? < end_time)" +
                                    "OR ((start_time < ?) AND (? < end_time)"); */
                                    //"AND ((start_time BETWEEN ? AND ?) OR (end_time BETWEEN ? AND ?))");
                                    // "AND ((? BETWEEN start_time AND end_time) OR (? BETWEEN start_time AND end_time))");
        
        /* This was actually a little difficult to figure out- in case anyone is
            interested, here's the full query. 
        SELECT * FROM db09.exam WHERE building = "Packard"
            AND room = "101"
            AND (
		-- Check if start time falls between range of any other exams
		(start_time < "2016-11-29 21:00:00" AND "2016-11-29 21:00:00" < end_time)
                
                -- Check if end time falls between range of any other exams
                OR (start_time < "2016-11-29 22:00:00" AND "2016-11-29 22:00:00" < end_time)
                
                -- The start_time of our event CAN equal the end_time of another event (So we don't check for that)
                -- the start_time of our event CAN *NOT* equal the start_time of another event (So we DO check for that)
                -- Same logic applies for end_times, except in reverse.
                OR (start_time = "2016-11-29 21:00:00")
                OR (end_time = "2016-11-29 22:00:00")
	); */
        find = con.prepareStatement(""
                + "SELECT * FROM db09.exam WHERE building = ? "
                + "AND room = ? "
                + "AND ("
                    + "(start_time < ? AND ? < end_time) "
                    + "OR (start_time < ? AND ? < end_time) "
                    + "OR (start_time = ?) "
                    + "OR (end_time = ? ))");
                                    
    }
    
    public static int lastInserted(){
        try{
            ResultSet rs = getInstance().last_inserted_id.executeQuery();
            rs.next();
            return rs.getInt("LAST_INSERT_ID()");
        } catch (SQLException e) {
            System.out.println("Something really weird happened and there ISN'T"
                    + "a 'last inserted id'.");
            System.out.println(e.getMessage());
            return -1;
        }
    }
    
    public static String[] getExamTypes(){
        ArrayList<String> array = new ArrayList(20);
        
        try{
            ResultSet rs = getInstance().exam_types.executeQuery();
            
            // if there is nothing in the table:
            if(rs.isBeforeFirst() == false){
                String error[] = {"No entries in ExamType. Call support."};
                System.out.println("Error in DBMgr.java function: getExamTypes()\nThere are no entries in Group 6's Exam Type table!\n");
                return error;
            }
            
            while(rs.next()){   
                array.add(rs.getString("exam_type_name"));
            }
        } catch(Exception e){
            System.out.println("Something went wrong while loading exam types.");
            System.out.println(e.getMessage());
            return null; 
        }
        
        return array.toArray(new String[0]);
    }
    
    public static String[] getDoctors(){
        ArrayList<String> array = new ArrayList(20);
        
        try{
             ResultSet rs = getInstance().doctors.executeQuery();
            
            // if there is nothing in the table:
            if(rs.isBeforeFirst() == false){
                String error[] = {"No entries in doctors. Call support."};
                System.out.println("Error in DBMgr.java function: getExamTypes()\nThere are no entries in Group 0's Clinical Staff table!\n");
                return error;
            }
            
            while(rs.next()){   
                array.add(rs.getString("First_Name") + " " + rs.getString("Last_Name"));
            }
        } catch(Exception e){
            System.out.println("Something went wrong while loading doctors.");
            System.out.println(e.getMessage());
            return null; 
        }
        Collections.sort(array);
        return array.toArray(new String[0]);
    }
    
    
    public static String[] getBuildings(){
        ArrayList<String> buildings = new ArrayList(30);
        
        try{
            ResultSet rs = getInstance().buildings.executeQuery();
            if(rs.isBeforeFirst() == false){
                System.out.println("Apparently there are no buildings in this company. Call support!");
                return null;
            }
            
            while(rs.next()){
                buildings.add(rs.getString("name"));
            }
            
        } catch (Exception e){
            System.out.println("Something went wrong while loading buildings");
            System.out.println(e.getMessage());
            return null;
        }
        
        for(int i = 0; i< buildings.size(); i++){
            System.out.println("Buidings: " + buildings.get(i));
        }
        
        Collections.sort(buildings);
        return buildings.toArray(new String[0]);
    }
    
    //creates the DBMgr singleton, yells at you if connection can't be made
    public static DBMgr getInstance() {
        if (instance == null) {
            try {
                String server = "cr137.cse.lehigh.edu";
                int port = 3306;
                String db_name = "db09";
                String uname ="user09";
                String password = "rite-show";
                
                System.out.println("******************************");
                System.out.println("* Connecting to the Database");
                System.out.println("* Server: " + server);
                System.out.println("* Port: " + port);
                System.out.println("* Database Name: " + db_name);
                System.out.println("* Username: " + uname);
                System.out.println("* Password: " + password);
                
                instance = new DBMgr(server, port, db_name, uname, password);
                
                System.out.println("");
                System.out.println("...Connection Succeeded!");
            } catch(Exception e) {
                System.out.println("");
                System.out.println("**ERROR CONNECTION FAILED: " + e.getMessage());
                return null;
            }
        }
        return instance;
    }
  /*   public Exam viewExam(String building,int roomNum,java.util.Date examPlannedDayAndTime, java.util.Date plannedEndTime) throws SQLException {
         Timestamp start = new Timestamp(examPlannedDayAndTime.getTime());
        Timestamp end = new Timestamp(plannedEndTime.getTime());
        find.setString(1, building);
        find.setString(2, String.valueOf(roomNum));
        find.setTimestamp(3, start);
        find.setTimestamp(4, end);
        ResultSet results=find.executeQuery();
        if(results.next()) {
            Exam retVal=new Exam(results.getString("exam_type"), results.getString("building"),Integer.parseInt(results.getString("room")),results.getTimestamp("start_time")
                ,results.getTimestamp("end_time"),results.getTimestamp("actual_start"), results.getTimestamp("actual_end"), results.getString("status"),results.getInt("exam_num"));
//ResultSet results
            return retVal;
        }
        return null;
    }*/
     public Exam viewExam(int exam_num) throws SQLException {
        PreparedStatement find2=con.prepareStatement("SELECT * FROM db09.exam WHERE exam_num = ?");
        find2.setString(1,String.valueOf(exam_num));
        ResultSet results=find2.executeQuery();
        if(results.next()) {
        Exam retVal=new Exam(results.getString("exam_type"), results.getString("building"), 
                results.getString("physician_name"), results.getString("patient_name"),
                Integer.parseInt(results.getString("room")),results.getTimestamp("start_time"), 
                results.getTimestamp("end_time"),results.getTimestamp("actual_start"),
                results.getTimestamp("actual_end"), results.getString("exam_status"),results.getInt("exam_num"));
                return retVal;
        }
        return null;
    }
     
     
    public Boolean addExam(String examType, String building, String doctor, String patient,
            int roomNum, java.util.Date examPlannedDayAndTime, java.util.Date plannedEndTime) throws SQLException {
        Timestamp start = new Timestamp(examPlannedDayAndTime.getTime());
        Timestamp end = new Timestamp(plannedEndTime.getTime());
        insert.setTimestamp(1, start);
        insert.setTimestamp(2, end);
        insert.setString(3, building);
        insert.setString(4, String.valueOf(roomNum));
        insert.setString(5, patient);
        insert.setString(6, doctor);
        insert.setString(7, examType);
        insert.executeUpdate();
        return true;
    }
    
    public Boolean startExam(int exam_num) throws SQLException {
        PreparedStatement find2 = con.prepareStatement("SELECT * FROM db09.exam WHERE exam_num = ? AND actual_start IS NULL");
        find2.setString(1,String.valueOf(exam_num));
        ResultSet rs = find2.executeQuery();
        if(rs.next()) {
            PreparedStatement update = con.prepareStatement("UPDATE db09.exam "
                    + "SET actual_start = ?, exam_status = \"In Progress\" "
                    + "WHERE exam_num = ?");
            update.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            update.setString(2, String.valueOf(exam_num));
            update.executeUpdate();
            return true;
        }
        return false;
    }
    
    public Boolean endExam(int exam_num) throws SQLException {
        PreparedStatement findStarted = con.prepareStatement("SELECT * FROM db09.exam WHERE exam_num = ? AND actual_start IS NOT NULL AND actual_end IS NULL");
        findStarted.setString(1,String.valueOf(exam_num));
        ResultSet rs = findStarted.executeQuery();
        if(rs.next()) {
            PreparedStatement update = con.prepareStatement("UPDATE db09.exam "
                    + "SET actual_end = ?, exam_status = \"Completed\" "
                    + "WHERE exam_num = ?");
            
            update.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            update.setString(2, String.valueOf(exam_num));
            System.out.println("SQL Statment looks like this:\n " + update);
            
            update.executeUpdate();
            return true;  
        }
        return false;
    }
    
    public Boolean deleteExam(String building, int roomNum, java.util.Date examPlannedDayAndTime, java.util.Date plannedEndTime) throws SQLException {
        Timestamp start = new Timestamp(examPlannedDayAndTime.getTime());
        Timestamp end = new Timestamp(plannedEndTime.getTime());
        delete.setTimestamp(1, start);
        delete.setTimestamp(2, end);
        delete.setString(3, building);
        delete.setString(4, String.valueOf(roomNum));
        delete.executeUpdate();
        return true;
    }
        
    public Boolean deleteExam(Exam exam) throws SQLException {
        Timestamp start = new Timestamp(exam.getPlannedTime().getTime());
        Timestamp end = new Timestamp(exam.getEndTime().getTime());
        delete.setTimestamp(1, start);
        delete.setTimestamp(2, end);
        delete.setString(3, exam.getBuilding());
        delete.setString(4, String.valueOf(exam.getRoomNumber()));
        delete.executeUpdate();
        
        return true;
    }
    public Boolean deleteExam(int exam_num) throws SQLException {
         PreparedStatement delete2 = con.prepareStatement("delete from db09.exam where exam_num = ?");
         delete2.setString(1,String.valueOf(exam_num));
         delete2.executeUpdate();
         return true;
    }
    
    public Boolean checkAvailability(String building, int roomNum, java.util.Date examPlannedDayAndTime, java.util.Date plannedEndTime) throws SQLException{
        Timestamp start = new Timestamp(examPlannedDayAndTime.getTime());
        Timestamp end = new Timestamp(plannedEndTime.getTime());
        find.setString(1, building);
        find.setString(2, String.valueOf(roomNum));
        find.setTimestamp(3, start);
        find.setTimestamp(4, start);
        find.setTimestamp(5, end);
        find.setTimestamp(6, end);
        find.setTimestamp(7, start);
        find.setTimestamp(8, end);
        ResultSet results = find.executeQuery();
        if (!results.next())
            return true;
        return false;
    }
    
    public Boolean checkDocAvailability(String physician_name) throws SQLException {
        doc_available.setString(1, physician_name);
        
        ResultSet results = doc_available.executeQuery();
        return !results.next();
    }
}
