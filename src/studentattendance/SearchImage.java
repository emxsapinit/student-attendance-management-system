
package studentattendance;

import com.sun.javafx.tk.Toolkit;
import java.awt.Image;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class SearchImage {
    private LoadConfig configLoader;
    private Connection conn;
    private String dbName, tblAttendanceName, sqladdress, url, username, pass, query;
    private boolean isExisting;
    private byte[] imageBlob;
    public SearchImage(int selectedId) {
        configLoader = new LoadConfig();
        dbName = configLoader.getDbName();
        tblAttendanceName = configLoader.getAttendanceTableName();
        sqladdress = configLoader.getAddress();
        username = configLoader.getUsername();
        pass = configLoader.getPassword();
        isExisting = false;
        url = "jdbc:mysql://" + sqladdress +"/" + dbName;
        try {
            query = "SELECT * FROM "+tblAttendanceName+" WHERE attendance_id = '"+selectedId+"'";
            conn = DriverManager.getConnection(url, username, pass);
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(query);
            while (result.next()) {                
                imageBlob = result.getBytes("attendance_image");
                isExisting = true;
            }
            
        } catch (Exception e) {
        }
    
    }

    public byte[] getImageBlob() {
        return imageBlob;
    }
    public boolean imageExist(){
        return isExisting;
    }
    
}
