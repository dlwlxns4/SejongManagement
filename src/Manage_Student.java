

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.*;


public class Manage_Student extends JFrame implements ActionListener{
	JLabel num,name,addr,phone,email,main_major,sub_major,professor,account,circles, information_del, information_update; 
    JTextField tf_num,tf_name,tf_addr,tf_phone,tf_email,tf_main_major, tf_sub_major, tf_professor, tf_account, tf_circles;
    JButton btn_input,btn_del,btn_update;
    
    JTextArea ta_state;
    
    JPanel pn, pn_btn, pn_inf, pn_state;
    
    Statement stmt;
    PreparedStatement pstmt;
    
	String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
	String userid = "madang";
	String pwd = "madang";
	
	String sql= "";
	
    static Connection con;
    
	public Manage_Student() {	
		
		  super("캠핑카 대여회사");

		  
		  pn=new JPanel();
		  pn_btn = new JPanel();
		  pn_inf = new JPanel();
		  pn_inf.setPreferredSize(new Dimension(500,55));
		  
		  
	      num = new JLabel("학생 번호");
	      name=new JLabel("학생 이름");
	      addr=new JLabel("학생 주소");
	      phone=new JLabel("학생 전화번호");
	      email=new JLabel("학생 이메일");
	      main_major=new JLabel("학생 전공");
	      sub_major=new JLabel("학생 부전공");
	      professor = new JLabel("지도 교수");
	      account = new JLabel("등록금 납부계좌");
	      circles = new JLabel("동아리");
	      information_del = new JLabel("삭제하는 경우 삭제할 회사 번호만 입력하면 삭제완료");
	      information_update = new JLabel("수정하는 경우 학생 번호 입력후 원하는 정보 입력하면 수정완료");
	      
	      pn_inf.add(information_del);
	      pn_inf.add(information_update);
	      
	      tf_num=new JTextField();
	      tf_name=new JTextField();
	      tf_addr=new JTextField();
	      tf_phone=new JTextField();
	      tf_email=new JTextField();
	      tf_main_major=new JTextField();
	      tf_sub_major=new JTextField();
	      tf_professor = new JTextField();
	      tf_account = new JTextField();
	      tf_circles = new JTextField();
	      btn_input=new JButton("입력");
	      btn_del=new JButton("삭제");
	      btn_update=new JButton("수정");
	      
	      pn.setLayout(new GridLayout(11,2));
		  pn_btn.setLayout(new GridLayout(1,3));
		   
		  pn.add(num);	   
		  pn.add(tf_num);
		   
		  pn.add(name);	
		  pn.add(tf_name);
		   
		  pn.add(addr);
		  pn.add(tf_addr);	
		   
		  pn.add(phone);	   
		  pn.add(tf_phone);
		   
		  pn.add(email);	
		  pn.add(tf_email);
		   
		  pn.add(main_major);
		  pn.add(tf_main_major);
	      
		  pn.add(sub_major);
		  pn.add(tf_sub_major);
		  pn.add(professor);
		  pn.add(tf_professor);
		  pn.add(account);
		  pn.add(tf_account);
		  pn.add(circles);
		  pn.add(tf_circles);
		  
	      pn_btn.add(btn_input);
	      pn_btn.add(btn_del);
	      pn_btn.add(btn_update);
	      
	      ta_state = new JTextArea();
	      ta_state.setEditable(false);
	      pn.add(ta_state);
	      
	      
	      add(pn);
		  add("South",pn_btn);
		  add("North",pn_inf);
		  
		  btn_input.addActionListener(this);
	     	      
	      
	      setBounds(200, 200, 500, 600);
	}
	
	   public void conDB() {
		      try {
		         Class.forName("com.mysql.cj.jdbc.Driver");
		         System.out.println("드라이버 로드 성공");
		      } catch (ClassNotFoundException e) {
		         e.printStackTrace();
		      }
		      
		      try { /* 데이터베이스를 연결하는 과정 */
		          System.out.println("데이터베이스 연결 준비...");
		          con = DriverManager.getConnection(url, userid, pwd);
		          System.out.println("데이터베이스 연결 성공");
		       } catch (SQLException e1) {
		          e1.printStackTrace();
		       }
	   }

	public void actionPerformed(ActionEvent e) {    
	   
	    if (e.getSource() == btn_input){
	    	sql = "insert into Student(number, name, address, phone_number, email, main_major, sub_major, professor, account, circles) value(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    	
	    	conDB();
	    	try {  
	    		pstmt = con.prepareStatement(sql);
	            pstmt.setInt(1, Integer.parseInt(tf_num.getText()));
	            pstmt.setString(2, tf_name.getText());
	            pstmt.setString(3, tf_addr.getText());
	            pstmt.setString(4, tf_phone.getText());
	            pstmt.setString(5, tf_email.getText());
	            pstmt.setInt(6, Integer.parseInt(tf_main_major.getText()));
	            pstmt.setString(7, tf_sub_major.getText());
	            pstmt.setInt(8, Integer.parseInt( tf_professor.getText()));
	            pstmt.setString(9, tf_account.getText());
	            pstmt.setString(10, tf_circles.getText());
	            pstmt.executeUpdate();
	            
	            tf_num.setText("");
	            tf_name.setText("");
	            tf_addr.setText("");
	            tf_phone.setText("");
	            tf_email.setText("");
	            tf_main_major.setText("");
	            tf_sub_major.setText("");
	            tf_professor.setText("");
	            tf_account.setText("");
	            tf_circles.setText("");
	            
	            ta_state.setText("학생이 입력되었습니다.");
	            
	         }catch (Exception e1) {
	               System.out.println("쿼리 읽기 실패 :" + e1);
	               System.out.println("오류 발생!"); 
	         }
	    }
	         
	      
	}
}
