package Admin;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Manage_Professor extends JFrame implements ActionListener{
	JLabel num,name,addr,phone,email,main_major,sub_major,student, information_del, information_update, lecture; 
    JTextField tf_num,tf_name,tf_addr,tf_phone,tf_email,tf_main_major, tf_sub_major, tf_student, tf_account, tf_lecture;
    JButton btn_input,btn_del,btn_update;
    
    JTextArea ta_state;
    
    JPanel pn, pn_btn, pn_inf, pn_state;
    
    Statement stmt;
    PreparedStatement pstmt;
    
	String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
	String userid = "madang";
	String pwd = "madang";
	
	String sql= "";
	ResultSet rs;
    static Connection con;
    
	public Manage_Professor() {	
		
		  super("교수관리");

		  
		  pn=new JPanel();
		  pn_btn = new JPanel();
		  pn_inf = new JPanel();
		  pn_inf.setPreferredSize(new Dimension(500,55));
		  
		  
	      num = new JLabel("교수 번호(필수)");
	      name=new JLabel("교수 이름(필수)");
	      addr=new JLabel("교수 주소(필수)");
	      phone=new JLabel("교수 전화번호(필수)");
	      email=new JLabel("교수 이메일");
	      main_major=new JLabel("소속 학과번호(필수)");
	      sub_major=new JLabel("복수학과");
	      student = new JLabel("담당 학생(필수)");
	      lecture = new JLabel("담당 강의(필수)");
	      information_del = new JLabel("삭제하는 경우 삭제할 회사 번호만 입력하면 삭제완료");
	      information_update = new JLabel("수정하는 경우 학생 번호 입력후 모든 필수 정보 입력하면 수정완료");
	      
	      pn_inf.add(information_del);
	      pn_inf.add(information_update);
	      
	      tf_num=new JTextField();
	      tf_name=new JTextField();
	      tf_addr=new JTextField();
	      tf_phone=new JTextField();
	      tf_email=new JTextField();
	      tf_main_major=new JTextField();
	      tf_sub_major=new JTextField();
	      tf_student = new JTextField();
	      tf_lecture = new JTextField();
	      btn_input=new JButton("입력");
	      btn_del=new JButton("삭제");
	      btn_update=new JButton("수정");
	      
	      pn.setLayout(new GridLayout(10,2));
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
		  pn.add(student);
		  pn.add(tf_student);
		  
		  pn.add(lecture);
		  pn.add(tf_lecture);
		  
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
		  btn_del.addActionListener(this);
	      btn_update.addActionListener(this);
	      
	      setBounds(200, 200, 500, 600);
	}
	public void closeDB() { // 데이터 베이스 접속 종료
        try {
            pstmt.close();
            if(rs !=null) rs.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
	   ta_state.setText("");
	    if (e.getSource() == btn_input){
	    	sql = "insert into professor(number, name, address, phone_number, email, dep_num, ddp, res_student, lecture) value(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    	
	    	conDB();
	    	try {  
	    		pstmt = con.prepareStatement(sql);
	    		
	    		if(tf_num.getText().length()!=0)
	    			pstmt.setInt(1, Integer.parseInt(tf_num.getText()));
	    		if(tf_name.getText().length()!=0)
	    			pstmt.setString(2, tf_name.getText());
	    		if(tf_addr.getText().length()!=0)
	    			pstmt.setString(3, tf_addr.getText());
	    		if(tf_phone.getText().length()!=0)
	    			pstmt.setString(4, tf_phone.getText());
	            pstmt.setString(5, tf_email.getText());
	    		if(tf_main_major.getText().length()!=0)
	    			pstmt.setInt(6, Integer.parseInt(tf_main_major.getText()));
	            pstmt.setString(7, tf_sub_major.getText());
	    		if(tf_student.getText().length()!=0)
	    			pstmt.setInt(8, Integer.parseInt( tf_student.getText()));
	    		if(tf_lecture.getText().length()!=0)
	    			pstmt.setString(9, tf_lecture.getText());
	    		
	            pstmt.executeUpdate();
	            

	            ta_state.setText("교수가 입력되었습니다.");
	           	            
	            tf_num.setText("");
	            tf_name.setText("");
	            tf_addr.setText("");
	            tf_phone.setText("");
	            tf_email.setText("");
	            tf_main_major.setText("");
	            tf_sub_major.setText("");
	            tf_student.setText("");
	            tf_lecture.setText("");
	            
	         }catch (SQLException e1) {
	        	 if(e1.getErrorCode() == 1062)
	        		 ta_state.setText("중복된 교수번호입니다.");
	        	 
	              
	             else {
	            	   ta_state.setText("필수 정보를 모두 입력해주세요.");
	               }
	         }catch(Exception e1) {
	        	 System.out.println(e1.toString());
	         }
	    	
	    }else if(e.getSource() == btn_del) {
	    	conDB();
	        sql = "delete from professor where number = ?";

	        try {
	        	
	            pstmt = con.prepareStatement(sql);
	            pstmt.setInt(1, Integer.parseInt(tf_num.getText()));
	            int check = pstmt.executeUpdate();
	            
	            if(check == 1)
	            	ta_state.setText(Integer.parseInt(tf_num.getText()) + "의 교수정보가 삭제되었습니다.");
	            else
	            	ta_state.setText("정보가 없습니다.");
	        } catch (SQLException e1) {
	        	if(e1.getErrorCode()==1451) {
	        		ta_state.setText("다른 테이블에서 정보를 삭제후 삭제해주세요.");
	        	}System.out.println(e1.getErrorCode());
	            System.out.println(e1);
	        } catch (Exception e1) {
	        	ta_state.setText("옳지않은 정보입니다.");
	        }
	        

	        
	        tf_num.setText("");
            tf_name.setText("");
            tf_addr.setText("");
            tf_phone.setText("");
            tf_email.setText("");
            tf_main_major.setText("");
            tf_sub_major.setText("");
            tf_student.setText("");
            tf_lecture.setText("");


	    }else if(e.getSource() == btn_update) {
	    	conDB();
	        sql = "update professor set name = ?, address = ?, phone_number = ?, email = ?, dep_num = ?, ddp = ?, res_student = ?, lecture = ? where number = ?";
	        
	        
	        try {
	        	
	        	
	            pstmt = con.prepareStatement(sql);

	    		if(tf_num.getText().length()!=0)
	    			pstmt.setInt(9, Integer.parseInt(tf_num.getText()));
	    		if(tf_name.getText().length()!=0)
	    			pstmt.setString(1, tf_name.getText());
	    		if(tf_addr.getText().length()!=0)
	    			pstmt.setString(2, tf_addr.getText());
	    		if(tf_phone.getText().length()!=0)
	    			pstmt.setString(3, tf_phone.getText());
	            pstmt.setString(4, tf_email.getText());
	    		if(tf_main_major.getText().length()!=0)
	    			pstmt.setInt(5, Integer.parseInt(tf_main_major.getText()));
	            pstmt.setString(6, tf_sub_major.getText());
	    		if(tf_student.getText().length()!=0)
	    			pstmt.setInt(7, Integer.parseInt( tf_student.getText()));
	    		if(tf_lecture.getText().length()!=0)
	    			pstmt.setString(8, tf_lecture.getText());
	            
	            int check = pstmt.executeUpdate();
	            System.out.println(check);
	            if(check == 0)
	            	ta_state.setText("번호를 올바르게 입력해주세요.");
	            else
	            	ta_state.setText(Integer.parseInt(tf_num.getText()) + "의 교수정보가 수정되었습니다.");
	            
	            
	            tf_num.setText("");
	            tf_name.setText("");
	            tf_addr.setText("");
	            tf_phone.setText("");
	            tf_email.setText("");
	            tf_main_major.setText("");
	            tf_sub_major.setText("");
	            tf_student.setText("");
	            tf_lecture.setText("");
	            

	        } catch (SQLException e1) {
	        	System.out.println(e1.getErrorCode());
	        		ta_state.setText("필수 정보를 모두 입력해주세요.");
	        		System.out.println(e1);
	        }


	    }
	         
	    closeDB();
	}
}
