import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.*;




public class Administrator extends JFrame implements ActionListener{

	   JPanel pn; 
	   
	   // 버튼 8개 - 초기화, 캠핑카 대여회사, 캠핑카, 고객, 캠핑카 정비소, 캠핑카반환, 수리, 검색
	   JButton btn_init, btn_student, btn_car, btn_cust, btn_shop, btn_return, btn_repair, btn_search;
	   
	   
	   Statement stmt;
	   
	   
	   public Administrator(){
	      super("관리자");
	      layInit();

	      setBounds(200, 200, 500, 300);       
	   }

	   public void layInit() {
	                  
		  
		  //shop = new RepairShop(con_adm);
		   
	      //관리자 화면
	      pn=new JPanel();
	      
	      // 버튼 8개 초기화
	      btn_init = new JButton("DB 초기화");
	      btn_student = new JButton("학생관리");
	      btn_car = new JButton("캠핑카 관리");
	      btn_cust = new JButton("고객 관리");
	      btn_shop = new JButton("정비소");
	      btn_return = new JButton("캠핑카 반환");
	      btn_repair = new JButton("캠핑카 수리");
	      btn_search = new JButton("검색");
	      
	      
	      pn.setLayout(new GridLayout(2,4));
	      // 패널에 버튼 8개 추가
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
		         System.out.println("Main 쿼리 읽기 실패 :" + e2);
		         System.out.println("오류 발생!"); 
		      }
	   }
}
