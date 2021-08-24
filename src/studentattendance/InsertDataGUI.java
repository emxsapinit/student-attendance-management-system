
package studentattendance;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


public class InsertDataGUI extends JDialog{
    private String imagePath;
    public InsertDataGUI() {
         BufferedImage imgs = null;
    try {
    imgs = ImageIO.read(new File("back.jpg"));
    } catch (IOException e) {
    e.printStackTrace();
    }
        Image dimgs = imgs.getScaledInstance(800, 508, Image.SCALE_SMOOTH);
        ImageIcon imageIcons = new ImageIcon(dimgs);
        setContentPane(new JLabel(imageIcons));
        setSize(280, 380);
        setTitle("Add Student");
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
    //    getContentPane().setBackground(Color.lightGray);
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
         
        JButton chooseBtn= new JButton();
        JButton deleteBtn= new RoundButton(10);
        JButton insertButton = new RoundButton(10);
        nameLabel.setBounds(30,30,80,30);
        nameLabel.setVisible(true);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        nameLabel.setText("Name: ");
        
        courseLabel.setBounds(30,60,80,30);
        courseLabel.setText("Course: ");
        courseLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        courseLabel.setVisible(true);
        
        yearLabel.setBounds(30,90,80,30);
        yearLabel.setVisible(true);
        yearLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        yearLabel.setText("Yr.& Sec: ");
        
        addressLabel.setBounds(30,120,80,30);
        addressLabel.setVisible(true);
        addressLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        addressLabel.setText("Address: ");
               
        imageLabel.setBounds(30,150,80,30);
        imageLabel.setVisible(true);
        imageLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        imageLabel.setText("Image: ");
        
        nameField.setBounds(100, 30, 150, 25);
        nameField.setVisible(true);
        nameField.setEnabled(true);
        
        courseField.setBounds(100, 60, 150, 25);
        courseField.setVisible(true);
        courseField.setEnabled(true);
                
        yearField.setBounds(100, 90, 150, 25);
        yearField.setVisible(true);
        yearField.setEnabled(true);
        
        addressField.setBounds(100, 120, 150, 25);
        addressField.setVisible(true);
        addressField.setEnabled(true);
             
        imgShow.setBounds(100, 160, 115, 110);
        imgShow.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        imgShow.setVisible(true);
        imgShow.setEnabled(true);
        
        insertButton.setBounds(40, 300, 90, 30);
        insertButton.setEnabled(true);
        insertButton.setFont(new Font("Dialog", Font.BOLD, 12));
        insertButton.setBackground(Color.gray);
        insertButton.setText("ADD");
        insertButton.setVisible(true);
        
        deleteBtn.setBounds(150, 300, 90, 30);
        deleteBtn.setEnabled(true);
        deleteBtn.setFont(new Font("Dialog", Font.BOLD, 12));
        deleteBtn.setBackground(Color.gray);
        deleteBtn.setText("CLEAR");
        deleteBtn.setVisible(true);
        
        chooseBtn.setBounds(220, 160, 28, 110);
        chooseBtn.setVisible(true);
        chooseBtn.setEnabled(true);
       // chooseBtn.setText("Choose");
        
        chooseBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChoose = new JFileChooser();
                fileChoose.setCurrentDirectory(new File(System.getProperty("user.home")));
                FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("*.images", "jpg","jpeg","png");
                fileChoose.addChoosableFileFilter(fileFilter);
                int result = fileChoose.showSaveDialog(null);
                if(result == JFileChooser.APPROVE_OPTION){
                    File selectedImage = fileChoose.getSelectedFile();
                    String path = selectedImage.getAbsolutePath();
                    
                    ImageIcon imageIcon = new ImageIcon(path);
                    Image image = imageIcon.getImage().getScaledInstance(imgShow.getWidth(), imgShow.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon finalImage = new ImageIcon(image);
                    imgShow.setIcon(finalImage);
                    imagePath = path;
                }
                
            }
        });
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InsertData insertData = new InsertData();
                insertData.setFilePath(imagePath);
                insertData.setInsertAddress(addressField.getText());
                insertData.setInsertCourse(courseField.getText());
                insertData.setInsertName(nameField.getText());
                insertData.setInsertYrSec(yearField.getText());
                
                if(insertData.insertData()){
                   
                    JOptionPane.showMessageDialog(null, "Success", "Add Student", JOptionPane.CLOSED_OPTION);
               dispose();
               }
                
            }
            
        });
        deleteBtn.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 nameField.setText("");
                 courseField.setText("");
                 yearField.setText("");
                 addressField.setText("");
                 
             }
         });
        add(insertButton);
         add(nameLabel);
          add(courseLabel);
          add(yearLabel);
          add(addressLabel);
          add(nameField);
          add(courseField);
          add(yearField);
          add(addressField);
          add(imageLabel);
          add(imgShow);
          add(deleteBtn);
          add(chooseBtn);
          setVisible(true);
              

        
    }
    
}
