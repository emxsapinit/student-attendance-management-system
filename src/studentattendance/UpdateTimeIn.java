package studentattendance;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.Date;

public class UpdateTimeIn {
    private LoadConfig configLoader;
    private Connection conn;
    private String dbName, tblTimeInName, address, url, username, pass, query;
    private int selectedId;
    private String currentDate;
    private Time currentTime;
    public UpdateTimeIn() {
        configLoader = new LoadConfig();
        dbName = configLoader.getDbName();
        tblTimeInName = configLoader.getDbTable_timeIn();
        address = configLoader.getAddress();
        username = configLoader.getUsername();
        pass = configLoader.getPassword();
        url = "jdbc:mysql://" + address +"/" + dbName;
        currentTime = new Time(new Date().getHours(), new Date().getMinutes(), new Date().getSeconds());
   }
    public boolean timeIn(){
        try{
            conn = DriverManager.getConnection(url, username, pass);
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet result = metaData.getColumns(null, null, tblTimeInName, currentDate);
            if(result.next()){
                query = "UPDATE "+dbName+"."+tblTimeInName+" SET "+currentDate+" = ? WHERE timeIn_id = ?";
                PreparedStatement st = conn.prepareStatement(query);
                st.setTime(1, currentTime);
                st.setInt(2, selectedId);
                st.execute();
                conn.close();
            }else{
                query = "ALTER TABLE "+tblTimeInName+" ADD "+currentDate+" TIME";
                Statement st = conn.createStatement();
                st.execute(query);
                st.close();
                query = "UPDATE "+dbName+"."+tblTimeInName+" SET "+currentDate+" = ? WHERE timeIn_id = ?";
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
    public boolean timedIn(){
        try{
            conn = DriverManager.getConnection(url, username, pass);
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet result = metaData.getColumns(null, null, tblTimeInName, currentDate);
            if(result.next()){
                query = "Select "+currentDate+" FROM "+tblTimeInName+ " WHERE timeIn_id = "+selectedId;
                Statement st = conn.createStatement();
                result = st.executeQuery(query);
                if(result.next()){
                    if(!result.getTime(currentDate).toString().equals(null)){
                    return true;}
                    else{
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
