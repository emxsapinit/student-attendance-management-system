/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class InsertData {
    private LoadConfig configLoader;
    private Connection conn;
    private String dbName, tblAttendanceName, tblTimeInName,tblTimeOutName, address, url, username, pass, query;
    private String insertName, insertCourse, insertYrSec, insertAddress, filePath;
    private File imageFile;
    private FileInputStream fileRead;
 
    public InsertData() {
        configLoader = new LoadConfig();
        dbName = configLoader.getDbName();
        tblAttendanceName = configLoader.getAttendanceTableName();
        tblTimeInName = configLoader.getDbTable_timeIn();
        tblTimeOutName = configLoader.getDbTable_timeOut();
        address = configLoader.getAddress();
        username = configLoader.getUsername();
        pass = configLoader.getPassword();
        url = "jdbc:mysql://" + address +"/" + dbName;
    }
      PreparedStatement preparedStmnt;
        ResultSet rs;
     private void UpdataTable(){
         try {
             String sql="Select * from"+ tblAttendanceName;
              preparedStmnt = conn.prepareStatement(sql);
              rs=preparedStmnt.executeQuery();
              DefaultTableModel tblModel = new DefaultTableModel();
          //   tblModel.setModel(Dbutils.resultSetToTableModel(rs));
         }
         catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
         finally{
             try{
                 rs.close();
                 preparedStmnt.close();
             } catch (SQLException ex) {
                 Logger.getLogger(InsertData.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
     }
    public boolean insertData(){
        try {
            query = "Insert into "+ tblAttendanceName + " (attendance_name, attendance_course, attendance_sec, attendance_address, attendance_image) "
                + "values(?,?,?,?,?)";
            conn = DriverManager.getConnection(url, username, pass);
            PreparedStatement preparedStmnt = conn.prepareStatement(query);
            preparedStmnt.setString(1, insertName);
            preparedStmnt.setString(2, insertCourse);
            preparedStmnt.setString(3, insertYrSec);
            preparedStmnt.setString(4, insertAddress);
            imageFile = new File(filePath);
            fileRead = new FileInputStream(imageFile);
            preparedStmnt.setBlob(5, fileRead);
            preparedStmnt.execute();
            
            int index = new SearchID(insertName, insertCourse, insertYrSec, insertAddress).getSearchedID();
            query = "Insert into "+tblTimeInName+" (timeIn_id, timeIn_name) values(?,?)";
            preparedStmnt = conn.prepareStatement(query);
            preparedStmnt.setInt(1, index);
            preparedStmnt.setString(2, insertName);
            preparedStmnt.execute();
            
            query = "Insert into "+tblTimeOutName+" (timeOut_id, timeOut_name) values(?,?)";
            preparedStmnt = conn.prepareStatement(query);
            preparedStmnt.setInt(1, index);
            preparedStmnt.setString(2, insertName);
            preparedStmnt.execute();
            
            conn.close();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setInsertAddress(String insertAddress) {
        this.insertAddress = insertAddress;
    }

    public void setInsertCourse(String insertCourse) {
        this.insertCourse = insertCourse;
    }

    public void setInsertName(String insertName) {
        this.insertName = insertName;
    }

    public void setInsertYrSec(String insertYrSec) {
        this.insertYrSec = insertYrSec;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    
}
