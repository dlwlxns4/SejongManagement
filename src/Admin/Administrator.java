package Admin;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.*;



public class Administrator extends JFrame implements ActionListener{

	   JPanel pn; 
	   
	 
	   JButton btn_table_Lookup,btn_student_professor, btn_student, btn_professor, btn_Department, btn_lecture, btn_lecture_history, btn_circle, btn_tuition_payment, btn_department_lecture, btn_student_circle;
	   
	   
	 
	   public Administrator(){
	      super("������");
	      layInit();

	      setBounds(200, 200, 700, 300);       
	   }

	   public void layInit() {
	                  
		  
		  //shop = new RepairShop(con_adm);
		   
	      //������ ȭ��
	      pn=new JPanel();
	      
	      // ��ư 8�� �ʱ�ȭ
	      btn_student_professor = new JButton("�л���������");
	      btn_student = new JButton("�л�����");
	      btn_professor = new JButton("��������");
	      btn_Department = new JButton("�а�����");
	      btn_lecture = new JButton("���°���");
	      btn_lecture_history = new JButton("������������");
	      btn_circle = new JButton("���Ƹ�����");
	      btn_tuition_payment = new JButton("���γ�������");
	      btn_table_Lookup = new JButton("���̺���ȸ");
	      btn_department_lecture = new JButton("�а����ǰ���");
	      btn_student_circle = new JButton("���Ƹ��л�����");
	      
	      
	      pn.setLayout(new GridLayout(2,6));
	      // �гο� ��ư 8�� �߰�
	      pn.add(btn_student_professor);
	      pn.add(btn_student);
	      pn.add(btn_professor);
	      pn.add(btn_Department);
	      pn.add(btn_lecture);
	      pn.add(btn_lecture_history);
	      pn.add(btn_circle);
	      pn.add(btn_tuition_payment);
	      pn.add(btn_department_lecture);
	      pn.add(btn_student_circle);
	      pn.add(btn_table_Lookup);
	      
	      add(pn);
	      
	      pn.setVisible(true);
	      
	      // Add ActionListner
	      btn_student.addActionListener(this);
	      btn_professor.addActionListener(this);
	      btn_Department.addActionListener(this);
	      btn_lecture.addActionListener(this);
	      btn_lecture_history.addActionListener(this);
	      btn_circle.addActionListener(this);
	      btn_tuition_payment.addActionListener(this);
	      btn_table_Lookup.addActionListener(this);
	      btn_student_professor.addActionListener(this);
	      btn_department_lecture.addActionListener(this);
	      btn_student_circle.addActionListener(this);

	   }
	   
	   public void actionPerformed(ActionEvent e) {    
		    
		         if (e.getSource() == btn_student){
		        	 Manage_Student manage_Student = new Manage_Student();
		        	 manage_Student.setVisible(true);
		         }else if(e.getSource() == btn_professor) {
		        	 Manage_Professor manage_professor = new Manage_Professor();
		        	 manage_professor.setVisible(true);
		         }else if(e.getSource() == btn_Department) {
		        	 Manage_Department manage_Department = new Manage_Department();
		        	 manage_Department.setVisible(true);
		         }else if(e.getSource() == btn_lecture) {
		        	 Manage_Lecture manage_lecture = new Manage_Lecture();
		        	 manage_lecture.setVisible(true);
		         }else if(e.getSource() == btn_lecture_history) {
		        	 Manage_Lecture_History manage_lecture_history = new Manage_Lecture_History();
		        	 manage_lecture_history.setVisible(true);
		         }else if(e.getSource() == btn_circle) {
		        	 Manage_Circles manage_circles = new Manage_Circles();
		        	 manage_circles.setVisible(true);
		         }else if(e.getSource() == btn_tuition_payment) {
		        	 Manage_tuition_payment manage_tuition_payment = new Manage_tuition_payment();
		        	 manage_tuition_payment.setVisible(true);
		         }else if(e.getSource() == btn_table_Lookup) {
		        	 Admin_Table_LookUp admin_Table_LookUp = new Admin_Table_LookUp();
		        	 admin_Table_LookUp.setVisible(true);
		         }else if(e.getSource() == btn_student_professor) {
		        	 Manage_student_professor student_professor = new Manage_student_professor();
		        	 student_professor.setVisible(true);
		         }else if(e.getSource() == btn_department_lecture) {
		        	 Manage_department_lecture manage_department_lecture = new Manage_department_lecture();
		        	 manage_department_lecture.setVisible(true);
		         }else if(e.getSource() == btn_student_circle) {
		        	 Manage_student_circle student_circle = new Manage_student_circle();
		        	 student_circle.setVisible(true);
		         }
		         
		         
		      
	   }
}
