
package studentattendance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class LoadTable {
    private LoadConfig configLoader;
    private Connection conn;
    private String dbName, tblAttendanceName, address, url, username, pass, searchword;
    public LoadTable() {
        configLoader = new LoadConfig();
        dbName = configLoader.getDbName();
        tblAttendanceName = configLoader.getAttendanceTableName();
        address = configLoader.getAddress();
        username = configLoader.getUsername();
        pass = configLoader.getPassword();
        searchword = "";
        url = "jdbc:mysql://" + address +"/" + dbName;
    }
    public DefaultTableModel loadTable(){
        ArrayList<Student> studentList= new ArrayList<>();
        try {
            conn = DriverManager.getConnection(url, username, pass);
            String query = "SELECT * FROM "+ tblAttendanceName + " WHERE attendance_name LIKE '%"+ searchword + "%'";
            ResultSet resultSet;
            Statement statement;
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            Student student;
            while (resultSet.next()) {               
                
                student = new Student(resultSet.getString("attendance_name"),
                        resultSet.getString("attendance_course"),
                        resultSet.getString("attendance_sec"),
                        resultSet.getString("attendance_address"));
                
                studentList.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new Object[]{"Name", "Course", "Yr.Sec","Address"});
  
            Object[] tableRow = new  Object[4];
        for(int i = 0; i<studentList.size();i++){
            tableRow[0] = studentList.get(i).getName();
            tableRow[1]= studentList.get(i).getCourse();
            tableRow[2] = studentList.get(i).getYear();
            tableRow[3] = studentList.get(i).getAddress();
            model.addRow(tableRow);
        }
            return model;
    }
    public void setSearchWord(String userInput){
        searchword = userInput;
    }

    
}
