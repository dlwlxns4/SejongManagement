import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.*;




public class Administrator extends JFrame implements ActionListener{

	   JPanel pn; 
	   
	 
	   JButton btn_init, btn_student, btn_professor, btn_Department, btn_lecture, btn_lecture_history, btn_circle, btn_tuition_payment;
	   
	   
	 
	   public Administrator(){
	      super("������");
	      layInit();

	      setBounds(200, 200, 500, 300);       
	   }

	   public void layInit() {
	                  
		  
		  //shop = new RepairShop(con_adm);
		   
	      //������ ȭ��
	      pn=new JPanel();
	      
	      // ��ư 8�� �ʱ�ȭ
	      btn_init = new JButton("DB �ʱ�ȭ");
	      btn_student = new JButton("�л�����");
	      btn_professor = new JButton("��������");
	      btn_Department = new JButton("�а�����");
	      btn_lecture = new JButton("���°���");
	      btn_lecture_history = new JButton("������������");
	      btn_circle = new JButton("���Ƹ�����");
	      btn_tuition_payment = new JButton("���γ�������");
	      
	      
	      pn.setLayout(new GridLayout(2,4));
	      // �гο� ��ư 8�� �߰�
	      pn.add(btn_init);
	      pn.add(btn_student);
	      pn.add(btn_professor);
	      pn.add(btn_Department);
	      pn.add(btn_lecture);
	      pn.add(btn_lecture_history);
	      pn.add(btn_circle);
	      pn.add(btn_tuition_payment);
	      
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
		         }
		         
		      
	   }
}
