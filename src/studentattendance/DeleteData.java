package studentattendance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;


public class DeleteData {
    private LoadConfig configLoader;
    private Connection conn;
    private String dbName, tblAttendanceName, address, url, username, pass, query,query1, tblTimeIn, tblTimeOut, query2;
    private int selectedId;
    public DeleteData(int inputId) {
        configLoader = new LoadConfig();
        dbName = configLoader.getDbName();
        tblAttendanceName = configLoader.getAttendanceTableName();
        tblTimeIn=configLoader.getDbTable_timeIn();
        tblTimeOut=configLoader.getDbTable_timeOut();
        address = configLoader.getAddress();
        username = configLoader.getUsername();
        pass = configLoader.getPassword();
        url = "jdbc:mysql://" + address +"/" + dbName;
        query = "";
        query1="";
         query2="";
        conn = null;
        selectedId = inputId;
    }
    public boolean deleteData(){
        try {
            query = "DELETE FROM "+dbName+"."+tblAttendanceName+" WHERE attendance_id = ?";
            conn = DriverManager.getConnection(url, username, pass);
            PreparedStatement stmnt = conn.prepareStatement(query);
            stmnt.setInt(1, selectedId);
            stmnt.executeUpdate();
            stmnt.close();
            
            
            query = "DELETE FROM "+dbName+"."+tblTimeIn+" WHERE timeIn_id = ?";
            stmnt = conn.prepareStatement(query);
            stmnt.setInt(1, selectedId);
            stmnt.executeUpdate();
            stmnt.close();
            
             query = "DELETE FROM "+dbName+"."+tblTimeOut+" WHERE timeOut_id = ?";
            stmnt = conn.prepareStatement(query);
            stmnt.setInt(1, selectedId);
            stmnt.executeUpdate();
            stmnt.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
