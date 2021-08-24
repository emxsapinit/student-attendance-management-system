
package studentattendance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SearchID {
    private LoadConfig configLoader;
    private Connection conn;
    private String dbName, tblAttendanceName, sqladdress, url, username, pass, query;
    private boolean isExisting;
    private int searchedID;
    public SearchID(String name, String course, String yrSec, String address) {
        configLoader = new LoadConfig();
        dbName = configLoader.getDbName();
        tblAttendanceName = configLoader.getAttendanceTableName();
        sqladdress = configLoader.getAddress();
        username = configLoader.getUsername();
        pass = configLoader.getPassword();
        isExisting = false;
        url = "jdbc:mysql://" + sqladdress +"/" + dbName;
        try{
            query = "SELECT * FROM "+tblAttendanceName+" WHERE attendance_name = '"+name+"' AND attendance_course = '"
                    + course +"' AND attendance_sec = '"+ yrSec +"' AND attendance_address = '"+address+"'";
            conn = DriverManager.getConnection(url, username, pass);
            ResultSet resultSet;
            Statement stmnt = conn.createStatement();
            resultSet = stmnt.executeQuery(query);
            while (resultSet.next()) {                
                searchedID = resultSet.getInt("attendance_id");
                isExisting = true;
            }
            conn.close();
            
            }catch(Exception ex){}
    }

    public int getSearchedID() {
        return searchedID;
    }
    public boolean idExist(){
        return isExisting;
    }
    
}
