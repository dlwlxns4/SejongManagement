import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.*;




public class Administrator extends JFrame implements ActionListener{

	   JPanel pn; 
	   
	   // ��ư 8�� - �ʱ�ȭ, ķ��ī �뿩ȸ��, ķ��ī, ��, ķ��ī �����, ķ��ī��ȯ, ����, �˻�
	   JButton btn_init, btn_student, btn_car, btn_cust, btn_shop, btn_return, btn_repair, btn_search;
	   
	   
	   Statement stmt;
	   
	   
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
	      btn_car = new JButton("ķ��ī ����");
	      btn_cust = new JButton("�� ����");
	      btn_shop = new JButton("�����");
	      btn_return = new JButton("ķ��ī ��ȯ");
	      btn_repair = new JButton("ķ��ī ����");
	      btn_search = new JButton("�˻�");
	      
	      
	      pn.setLayout(new GridLayout(2,4));
	      // �гο� ��ư 8�� �߰�
	      pn.add(btn_init);
	      pn.add(btn_student);
	      pn.add(btn_car);
	      pn.add(btn_cust);
	      pn.add(btn_shop);
	      pn.add(btn_return);
	      pn.add(btn_repair);
	      pn.add(btn_search);
	      
	      add(pn);
	      
	      pn.setVisible(true);
	      
	      // Add ActionListner
	      btn_student.addActionListener(this);

	   }
	   
	   public void actionPerformed(ActionEvent e) {    
		      try {
		         if (e.getSource() == btn_student){
		        	 Manage_Student manage_Student = new Manage_Student();
		        	 manage_Student.setVisible(true);
		         }
		         
		      } catch (Exception e2) {
		         System.out.println("Main ���� �б� ���� :" + e2);
		         System.out.println("���� �߻�!"); 
		      }
	   }
}
