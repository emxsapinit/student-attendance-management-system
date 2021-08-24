
package studentattendance;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.Date;

public class UpdateTimeOut {
    private LoadConfig configLoader;
    private Connection conn;
    private String dbName, tblTimeOutName, address, url, username, pass, query;
    private int selectedId;
    private String currentDate;
    private Time currentTime;

    public UpdateTimeOut() {
        configLoader = new LoadConfig();
        dbName = configLoader.getDbName();
        tblTimeOutName = configLoader.getDbTable_timeOut();
        address = configLoader.getAddress();
        username = configLoader.getUsername();
        pass = configLoader.getPassword();
        url = "jdbc:mysql://" + address +"/" + dbName;
        currentTime = new Time(new Date().getHours(), new Date().getMinutes(), new Date().getSeconds());
    }
    public boolean timeOut(){
        try{
            conn = DriverManager.getConnection(url, username, pass);
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet result = metaData.getColumns(null, null, tblTimeOutName, currentDate);
            if(result.next()){
                query = "UPDATE "+dbName+"."+tblTimeOutName+" SET "+currentDate+" = ? WHERE timeOut_id = ?";
                PreparedStatement st = conn.prepareStatement(query);
                st.setTime(1, currentTime);
                st.setInt(2, selectedId);
                st.execute();
                conn.close();
            }else{
                query = "ALTER TABLE "+tblTimeOutName+" ADD "+currentDate+" TIME";
                Statement st = conn.createStatement();
                st.execute(query);
                st.close();
                query = "UPDATE "+dbName+"."+tblTimeOutName+" SET "+currentDate+" = ? WHERE timeOut_id = ?";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setTime(1, currentTime);
                pst.setInt(2, selectedId);
                pst.execute();
                conn.close();
            }
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }
    public boolean timedOut(){
        try{
            conn = DriverManager.getConnection(url, username, pass);
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet result = metaData.getColumns(null, null, tblTimeOutName, currentDate);
            if(result.next()){
                query = "Select "+currentDate+" FROM "+tblTimeOutName+ " WHERE timeOut_id = "+selectedId;
                Statement st = conn.createStatement();
                result = st.executeQuery(query);
                if(result.next() && result.getTime(currentDate).toString().length() >0){
                    if(result.getTime(currentDate).toString().equals(null)){
                        return true;
                    }else{
                    return false;
                    }
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }catch(Exception ex){
            
            return false;
        }
    }
}
