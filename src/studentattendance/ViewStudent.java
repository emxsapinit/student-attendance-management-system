/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendance;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author LENOVO
 */
public class ViewStudent extends JFrame {
    private String name, course, yrsec, address;
    private int selectedId;
    public ViewStudent(String sname, String scourse, String syrsec, String saddress, ImageIcon imageIcon){
        name = sname;
        course = scourse;
        yrsec = syrsec;
        address = saddress;
        selectedId = new SearchID(name, course, yrsec, address).getSearchedID();
            BufferedImage imgs = null;
    
        try {
            imgs = ImageIO.read(new File("back.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(ViewStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
  
        Image dimgs = imgs.getScaledInstance(800, 508, Image.SCALE_SMOOTH);
        ImageIcon imageIcons = new ImageIcon(dimgs);
     
        setSize(280, 380);
        setTitle("View Student");
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setContentPane(new JLabel(imageIcons));
       // getContentPane().setBackground(Color.lightGray);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JTextField nameField, courseField, yearField, addressField;
        nameField = new RoundJTextField(10);
        courseField = new RoundJTextField(10);
        yearField = new RoundJTextField(10);
        addressField = new RoundJTextField(10);
        
        JLabel nameLabel, courseLabel, yearLabel, addressLabel, imageLabel,imgShow;
        
        nameLabel= new JLabel();
        courseLabel= new JLabel();
        yearLabel= new JLabel();
        addressLabel= new JLabel();
        imageLabel= new JLabel();
        imgShow= new JLabel();
        
        JButton loginBtn= new RoundButton(10);
        JButton logoutBtn= new RoundButton(10);
        
       
        nameLabel.setBounds(30,150,80,30);
        nameLabel.setVisible(true);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        nameLabel.setText("Name: ");
        
        courseLabel.setBounds(30,180,80,30);
        courseLabel.setText("Course: ");
        courseLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        courseLabel.setVisible(true);
        
        yearLabel.setBounds(30,210,80,30);
        yearLabel.setVisible(true);
        yearLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        yearLabel.setText("Yr.& Sec: ");
        
        addressLabel.setBounds(30,240,80,30);
        addressLabel.setVisible(true);
        addressLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        addressLabel.setText("Address: ");
          
        imgShow.setBounds(75, 10, 130, 130);
        imgShow.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Image image = imageIcon.getImage().getScaledInstance(imgShow.getWidth(), imgShow.getHeight(), Image.SCALE_SMOOTH);
        imageIcon.setImage(image);
        imgShow.setIcon(imageIcon);
        imgShow.setVisible(true);
        imgShow.setEnabled(true);
        
        nameField.setBounds(100, 150, 150, 25);
        nameField.setEditable(false);
        nameField.setText(sname);
        nameField.setVisible(true);
        nameField.setEnabled(true);
        
        courseField.setBounds(100, 180, 150, 25);
        courseField.setEditable(false);
        courseField.setText(scourse);
        courseField.setVisible(true);
        courseField.setEnabled(true);
                
        yearField.setBounds(100, 210, 150, 25);
        yearField.setEditable(false);
        yearField.setText(syrsec);
        yearField.setVisible(true);
        yearField.setEnabled(true);
        
        addressField.setBounds(100, 240, 150, 25);
        addressField.setEditable(false);
        addressField.setText(saddress);
        addressField.setVisible(true);
        addressField.setEnabled(true);
        
        loginBtn.setBounds(46, 300, 80,30);
        loginBtn.setVisible(true);     
        loginBtn.setFont(new Font("Dialog", Font.BOLD, 12));
        loginBtn.setBackground(Color.gray);
    //    loginBtn.setEnabled(!timedIn());
        loginBtn.setText("Time in");
        
        logoutBtn.setBounds(150, 300, 80,30);
        logoutBtn.setVisible(true);
        logoutBtn.setFont(new Font("Dialog", Font.BOLD, 12));
        logoutBtn.setBackground(Color.gray);
     //   logoutBtn.setEnabled(timedIn()&&!timedOut());
        logoutBtn.setText("Time out");
        
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              /*  timeIn();
                loginBtn.setEnabled(!timedIn() && !timedOut());
                logoutBtn.setEnabled(timedIn()&&!timedOut());*/
            }
        });
       logoutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              /*  timeOut();
                logoutBtn.setEnabled(false);*/
                
            }
        });
                
         add(nameLabel);
          add(courseLabel);
          add(yearLabel);
          add(addressLabel);
          add(nameField);
          add(courseField);
          add(yearField);
          add(addressField);
          add(loginBtn);
          add(imgShow);
          add(logoutBtn);
          setVisible(true);
    }}
   /*           System.out.println(timedOut());

     private void timeIn(){
  
        Date currentDate = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM_dd_yyyy");
        String currentFormattedDate = dateFormatter.format(currentDate);
        UpdateTimeIn timeIn = new UpdateTimeIn();
        timeIn.setCurrentDate(currentFormattedDate);
        timeIn.setSelectedId(selectedId);
        if(timeIn.timeIn()){
            new JOptionPane().showMessageDialog(null, "Sucess", "Time in", JOptionPane.CLOSED_OPTION);
        }
    }
    
    private boolean timedIn(){
        Date currentDate = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM_dd_yyyy");
        String currentFormattedDate = dateFormatter.format(currentDate);
        UpdateTimeIn timeIn = new UpdateTimeIn();
        timeIn.setCurrentDate(currentFormattedDate);
        timeIn.setSelectedId(selectedId);
        boolean timedIn = timeIn.timedIn();
        return timedIn;
    }
    private void timeOut(){
        Date currentDate = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM_dd_yyyy");
        String currentFormattedDate = dateFormatter.format(currentDate);
        UpdateTimeOut timeOut = new UpdateTimeOut();
        timeOut.setCurrentDate(currentFormattedDate);
        timeOut.setSelectedId(selectedId);
        if(timeOut.timeOut()){
            new JOptionPane().showMessageDialog(null, "Sucess", "Time Out", JOptionPane.CLOSED_OPTION);
        }
    }
    private boolean timedOut(){
        Date currentDate = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM_dd_yyyy");
        String currentFormattedDate = dateFormatter.format(currentDate);
        UpdateTimeOut timeOut = new UpdateTimeOut();
        timeOut.setCurrentDate(currentFormattedDate);
        timeOut.setSelectedId(selectedId);
        boolean timedOut = timeOut.timedOut();
        return timedOut;
    }
    
}
*/