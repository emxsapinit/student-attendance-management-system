/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author LENOVO
 */
public class LoadConfig {
     private String sqlAddress,sqlUsername,sqlPassword;
    private String dbName, dbTable_attendance, dbTable_timeIn, dbTable_timeOut;
    private File configFile;
    private FileReader configFileRead;
    private Properties configProperty;
    public LoadConfig(){
        try{
            
            configFile = new File("config.cfg");
            configFileRead = new FileReader(configFile);
            configProperty = new Properties();
            configProperty.load(configFileRead);
            sqlAddress = configProperty.getProperty("Address");
            sqlUsername = configProperty.getProperty("Username");
            sqlPassword = configProperty.getProperty("Password");
            dbName = configProperty.getProperty("DbName");
            dbTable_timeOut = configProperty.getProperty("TblTimeOutName");
            dbTable_attendance = configProperty.getProperty("TblAttendanceName");
            dbTable_timeIn = configProperty.getProperty("TblTimeInName");
            configFileRead.close();
            
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    public String getAddress(){
        return sqlAddress;
    }
    public String getPassword(){
        return sqlPassword;
    }
    public String getUsername(){
        return sqlUsername;
    }
    public String getDbName(){
        return dbName;
    }
    public String getAttendanceTableName(){
        return dbTable_attendance;
    }

    public String getDbTable_timeIn() {
        return dbTable_timeIn;
    }

    public String getDbTable_timeOut() {
        return dbTable_timeOut;
    }
    
}
