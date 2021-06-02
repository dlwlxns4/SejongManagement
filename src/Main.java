import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.*;
import java.awt.event.*;

public class Main extends JFrame  implements ActionListener{

	
	   static Connection con;
	   PreparedStatement pstmt = null; // SQL���� DB�� ������ ���� ��ü
	   Statement stmt;
	   ResultSet rs;

	
	   Administrator administrator;
	   Professor professor;
	   Student student;
	   
	   
	   //main panel
	   JButton btn_adm, btn_student, btn_professor;
	   
	   JPanel pn_main;
	  
	   
	   public Main() {
	      super("���� �л�����ý���");
	      layInit();

	      setVisible(true);
	      setBounds(300, 300, 450, 150); 
	      

	   }

	   public void layInit() {
		   
	      btn_adm = new JButton("������");
	      btn_student = new JButton("�л�");
	      btn_professor = new JButton("����");
		  
	      pn_main = new JPanel();
	      
	      pn_main.setLayout(new GridLayout(1,2));
	      
	      pn_main.add(btn_adm);
	      pn_main.add(btn_student);
	      pn_main.add(btn_professor);
	      add(pn_main);
	      
	      
	      administrator = new Administrator();
	      professor = new Professor();
	      student = new Student();
	      
	      
	      btn_adm.addActionListener(this);
	      btn_student.addActionListener(this); 
	      btn_professor.addActionListener(this);
	      
	   }
	   

	   
	   public void actionPerformed(ActionEvent e) {    
		      try {
		         if (e.getSource() == btn_adm) {
		        	 System.out.println("adm");
		        	 administrator.setVisible(true);
		        	 
		         }else if (e.getSource() == btn_student) {
		        	 System.out.println("user");
		        	 student.setVisible(true);
		        	
		         }
		         else if (e.getSource() == btn_professor) {
		        	 System.out.println("user");
		        	 professor.setVisible(true);
		        	
		         }
		      } catch (Exception e2) {
		         System.out.println("Main ���� �б� ���� :" + e2);
		         System.out.println("���� �߻�!"); 
		      }
		   }
	   
	   
	   public static void main(String[] args) {
		   // TODO Auto-generated method stub
		   
		   Main SejongManagementSystem = new Main();
	
	   }

}
