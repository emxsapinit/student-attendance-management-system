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

/**
 *
 * @author LENOVO
 */
public class UpdateData {
    private LoadConfig configLoader;
    private Connection conn;
    private String dbName, tblAttendanceName, address, url, username, pass, query;
    private String updateName, updateCourse, updateYrSec, updateAddress, updateFilePath;
    private int selectedId;
    private File imageFile;
    private FileInputStream fileRead;

    public UpdateData() {
        configLoader = new LoadConfig();
        dbName = configLoader.getDbName();
        tblAttendanceName = configLoader.getAttendanceTableName();
        address = configLoader.getAddress();
        username = configLoader.getUsername();
        pass = configLoader.getPassword();
        url = "jdbc:mysql://" + address +"/" + dbName;
        updateFilePath = "";
        selectedId = 0;
    }
    public boolean updateData(){
        try{
            conn = DriverManager.getConnection(url, username, pass);
            if(updateFilePath.equals("")){
                query = "UPDATE "+dbName+"."+tblAttendanceName+" SET attendance_name = ?, attendance_course = ?, attendance_sec = ?"
                        + ", attendance_address = ? WHERE attendance_id = ?";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, updateName);
                pst.setString(2, updateCourse);
                pst.setString(3, updateYrSec);
                pst.setString(4, updateAddress);
                pst.setInt(5, selectedId);
                pst.executeUpdate();
                conn.close();
                return true;
            }else{
                query = "UPDATE "+dbName+"."+tblAttendanceName+" SET attendance_name = ?, attendance_course = ?, attendance_sec = ?"
                        + ", attendance_address = ?, attendance_image = ? WHERE attendance_id = ?";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, updateName);
                pst.setString(2, updateCourse);
                pst.setString(3, updateYrSec);
                pst.setString(4, updateAddress);
                imageFile = new File(updateFilePath);
                fileRead = new FileInputStream(imageFile);
                pst.setBlob(5, fileRead);
                pst.setInt(6, selectedId);
                pst.executeUpdate();
                conn.close();
                return true;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public void setUpdateAddress(String updateAddress) {
        this.updateAddress = updateAddress;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public void setUpdateYrSec(String updateYrSec) {
        this.updateYrSec = updateYrSec;
    }

    public void setUpdateCourse(String updateCourse) {
        this.updateCourse = updateCourse;
    }

    public void setUpdateFilePath(String updateFilePath) {
        this.updateFilePath = updateFilePath;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }
    
    
}
