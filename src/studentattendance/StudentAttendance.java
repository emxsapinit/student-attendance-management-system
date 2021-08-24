
package studentattendance;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class StudentAttendance {
    
    public static void main(String[] args) throws IOException {
        JFrame mainWindow = new JFrame();
        BufferedImage imgs = null;
    try {
    imgs = ImageIO.read(new File("back.jpg"));
    } catch (IOException e) {
    e.printStackTrace();
    }
        Image dimgs = imgs.getScaledInstance(800, 508, Image.SCALE_SMOOTH);
        ImageIcon imageIcons = new ImageIcon(dimgs);
        mainWindow.setContentPane(new JLabel(imageIcons));

        mainWindow.setSize(550, 450);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setResizable(false);
        mainWindow.setTitle("Student Attendance");
        mainWindow.setLayout(null);
       //mainWindow.getContentPane().setBackground(Color.lightGray);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        BufferedImage img;
        img = ImageIO.read(new File("ad.png"));
        Image dimg = img.getScaledInstance(45, 45,Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        
        BufferedImage img1;
        img1 = ImageIO.read(new File("edit.png"));
        Image dimg1 = img1.getScaledInstance(45, 45,Image.SCALE_SMOOTH);
        ImageIcon imageIcon1 = new ImageIcon(dimg1);
        
        BufferedImage img2;
        img2 = ImageIO.read(new File("delt.png"));
        Image dimg2 = img2.getScaledInstance(35, 35,Image.SCALE_SMOOTH);
        ImageIcon imageIcon2 = new ImageIcon(dimg2);
    
        BufferedImage img3;
        img3 = ImageIO.read(new File("ref.png"));
        Image dimg3 = img3.getScaledInstance(30, 30,Image.SCALE_SMOOTH);
        ImageIcon imageIcon3 = new ImageIcon(dimg3);
        
        JButton insertButton = new JButton(imageIcon);       
        JButton editButton = new JButton(imageIcon1);
        JButton deleteButton = new JButton(imageIcon2);       
        JButton refreshButton= new JButton(imageIcon3);
        JButton viewButton = new RoundButton(10);  
        JTable dataTable = new JTable();
        JTextField searchField = new RoundJTextField(10);
        JLabel attLbl= new JLabel();
        JLabel searchLbl= new JLabel();
        
        attLbl.setText("Attendance System");
        attLbl.setBounds(175, 10, 300, 40);
        attLbl.setVisible(true);
        attLbl.setFont(new Font("Tahoma", Font.BOLD, 22));
                
        searchLbl.setText("Search:");
        searchLbl.setBounds(80, 60, 100, 40);
        searchLbl.setVisible(true);
        searchLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        searchField.setBounds(150, 65, 300, 30);
        searchField.setVisible(true);
                
        refreshButton.setBounds(430, 315, 30, 30);
       // refreshButton.setText("R");
        refreshButton.setBorderPainted(false);
      refreshButton.setContentAreaFilled(false);
      //  refreshButton.setFocusPainted(false);
        //refreshButton.setFocusTraversalKeysEnabled(true);
        refreshButton.setMargin(new Insets(0, 0, 0, 0));
        refreshButton.setBorder(null);
        refreshButton.setVisible(true);
        refreshButton.setEnabled(true);
        
        insertButton.setBounds(170, 350, 35, 35);
        insertButton.setEnabled(true);   
        insertButton.setBorderPainted(false);
        insertButton.setContentAreaFilled(false);
        insertButton.setFocusPainted(false);
        insertButton.setFocusTraversalKeysEnabled(true);
        insertButton.setMargin(new Insets(0, 0, 0, 0));
        insertButton.setBorder(null);
        insertButton.setVisible(true);       
              
        editButton.setBounds(250, 350, 35, 35);
        editButton.setEnabled(true); 
        editButton.setVisible(true);
        editButton.setBorderPainted(false);
        editButton.setContentAreaFilled(false);
        editButton.setFocusPainted(false);
        editButton.setFocusTraversalKeysEnabled(true);
        editButton.setMargin(new Insets(0, 0, 0, 0));
        editButton.setBorder(null);
        
        deleteButton.setBounds(330, 350, 35, 35);
        deleteButton.setEnabled(true);
        deleteButton.setVisible(true);
        deleteButton.setBorderPainted(false);
        deleteButton.setContentAreaFilled(false);
        deleteButton.setFocusPainted(false);
        deleteButton.setFocusTraversalKeysEnabled(true);
        deleteButton.setMargin(new Insets(0, 0, 0, 0));
        deleteButton.setBorder(null);
        
        viewButton.setBounds(355, 317, 66, 25);
        viewButton.setVisible(true);
        viewButton.setFont(new Font("Dialog", Font.BOLD, 12));
        viewButton.setForeground(Color.lightGray);
        viewButton.setBackground(Color.black);
        viewButton.setText("View");
        viewButton.setEnabled(true);
        
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpae(DocumentEvent e) {
                LoadTable load = new LoadTable();
                load.setSearchWord(searchField.getText());
                dataTable.setModel(load.loadTable());
            }

            public void removeUpdate(DocumentEvent e) {
                LoadTable load = new LoadTable();
                load.setSearchWord(searchField.getText());
                dataTable.setModel(load.loadTable());
            }

            public void changedUpdate(DocumentEvent e) {
                LoadTable load = new LoadTable();
                load.setSearchWord(searchField.getText());
                dataTable.setModel(load.loadTable());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });  
        
        dataTable.setBounds(70,110, 400, 200);
        dataTable.setModel(new LoadTable().loadTable());
        dataTable.setVisible(true);
        dataTable.setBackground(Color.white);
      
          dataTable.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer() {});
        
   
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new InsertDataGUI();
                
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!dataTable.getSelectionModel().isSelectionEmpty()){
                    int yesOrNo = new JOptionPane().showConfirmDialog(null, "Do You Want to Delete Data?", "Delete Alarm", JOptionPane.YES_NO_OPTION);
                    if(yesOrNo == JOptionPane.YES_OPTION){
                        try {
                            
                            int selectedRow = dataTable.getSelectedRow();
                            String name, course, yrsec, address;
                            name = dataTable.getValueAt(selectedRow, 0).toString();
                            course = dataTable.getValueAt(selectedRow, 1).toString();
                            yrsec = dataTable.getValueAt(selectedRow, 2).toString();
                            address = dataTable.getValueAt(selectedRow, 3).toString();
                            SearchID search = new SearchID(name, course, yrsec, address);
                            System.out.println(search.getSearchedID());
                            DeleteData deleteData = new DeleteData(search.getSearchedID());
                            if(deleteData.deleteData()){
                                JOptionPane.showMessageDialog(null, "Success", "DELETE", JOptionPane.CLOSED_OPTION);
                                dataTable.setModel(new LoadTable().loadTable());
                            }
                            
                        } catch (Exception a) {
                        }
                    }
                }
            }
        });
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!dataTable.getSelectionModel().isSelectionEmpty()){
                    int selectedRow = dataTable.getSelectedRow();
                    String name, course ,yrsec ,address;
                    name = dataTable.getValueAt(selectedRow, 0).toString();
                    course = dataTable.getValueAt(selectedRow, 1).toString();
                    yrsec = dataTable.getValueAt(selectedRow, 2).toString();
                    address = dataTable.getValueAt(selectedRow, 3).toString();
                    SearchID searchId = new SearchID(name, course, yrsec, address);
                    SearchImage searchImage = new SearchImage(searchId.getSearchedID());
                    ImageIcon imageIcon = new ImageIcon();
                    try{
                    byte[] blobAsByte = searchImage.getImageBlob();
                    BufferedImage readBlob = ImageIO.read(new ByteArrayInputStream(blobAsByte));
                    imageIcon.setImage(readBlob);
                    }catch(Exception ex){}
                    new ViewStudent(name, course, yrsec, address,imageIcon);
                }
            }
        });
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!dataTable.getSelectionModel().isSelectionEmpty()){
                    int yesOrNo = new JOptionPane().showConfirmDialog(null, "Do You Want to Edit Data?", "Delete Alarm", JOptionPane.YES_NO_OPTION);
                    if(yesOrNo == JOptionPane.YES_OPTION){
                        String name, course, yrsec, address;
                        int selectedRow = dataTable.getSelectedRow();
                        name = dataTable.getValueAt(selectedRow, 0).toString();
                        course = dataTable.getValueAt(selectedRow, 1).toString();
                        yrsec = dataTable.getValueAt(selectedRow, 2).toString();
                        address = dataTable.getValueAt(selectedRow, 3).toString();
                        SearchID searchId = new SearchID(name, course, yrsec, address);
                        SearchImage searchImage = new SearchImage(searchId.getSearchedID());
                        Image image;
                        ImageIcon imageIcon = new ImageIcon();
                        try{
                            image = ImageIO.read(new ByteArrayInputStream(searchImage.getImageBlob()));
                            imageIcon.setImage(image);
                        }catch(Exception ex){}
                        new UpdateDataGui(name, course, yrsec, address, imageIcon);
                    }
                }
            }
        });
        
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                   
                      dataTable.setModel(new LoadTable().loadTable());
        
             
            }
        });
    
        JScrollPane pane = new JScrollPane(dataTable);
        pane.setBounds(70, 110, 400, 200);
        pane.setEnabled(false);
        pane.setVisible(true);
        
        mainWindow.add(pane);
        mainWindow.add(searchField);
        mainWindow.add(refreshButton);
        mainWindow.add(editButton);
        mainWindow.add(insertButton);
        mainWindow.add(deleteButton);
        mainWindow.add(viewButton);
        mainWindow.add(searchLbl);
        mainWindow.add(attLbl);
        mainWindow.setVisible(true);
        
    }
}
