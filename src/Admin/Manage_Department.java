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

public class Manage_Department extends JFrame implements ActionListener{
	JLabel num,name,phone,office,lecture,head, information_del, information_update; 
    JTextField tf_num,tf_name,tf_phone,tf_office,tf_lecture, tf_head;
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
    
	
	public Manage_Department() {
		 super("학과관리");

		  
		  pn=new JPanel();
		  pn_btn = new JPanel();
		  pn_inf = new JPanel();
		  pn_inf.setPreferredSize(new Dimension(500,55));
		  
		  
	      num = new JLabel("학과번호(필수)");
	      name=new JLabel("학과명(필수)");
	      phone=new JLabel("학과전화번호(필수)");
	      office=new JLabel("학과사무실(필수)");
	      lecture=new JLabel("강좌(필수)");
	      head=new JLabel("학과장(필수)");
	      information_del = new JLabel("삭제하는 경우 삭제할 회사 번호만 입력하면 삭제완료");
	      information_update = new JLabel("수정하는 경우 학생 번호 입력후 모든 필수 정보 입력하면 수정완료");
	      
	      pn_inf.add(information_del);
	      pn_inf.add(information_update);
	      
	      tf_num=new JTextField();
	      tf_name=new JTextField();
	      tf_phone=new JTextField();
	      tf_office=new JTextField();
	      tf_lecture=new JTextField();
	      tf_head=new JTextField();
	      btn_input=new JButton("입력");
	      btn_del=new JButton("삭제");
	      btn_update=new JButton("수정");
	      
	      pn.setLayout(new GridLayout(7,2));
		  pn_btn.setLayout(new GridLayout(1,3));
		   
		  pn.add(num);	   
		  pn.add(tf_num);
		   
		  pn.add(name);	
		  pn.add(tf_name);
		   
		  pn.add(phone);
		  pn.add(tf_phone);	
		   
		  pn.add(office);	   
		  pn.add(tf_office);
		   
		  pn.add(lecture);	
		  pn.add(tf_lecture);
		   
		  pn.add(head);
		  pn.add(tf_head);
	      
		  
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
		    	sql = "insert into department(number, name, phone_num, office, lecture, head) value(?, ?, ?, ?, ?, ?)";
		    	
		    	
		    	conDB();
		    	try {  
		    		pstmt = con.prepareStatement(sql);
		    		
		    		if(tf_num.getText().length()!=0)
		    			pstmt.setInt(1, Integer.parseInt(tf_num.getText()));
		    		if(tf_name.getText().length()!=0)
		    			pstmt.setString(2, tf_name.getText());
		    		if(tf_phone.getText().length()!=0)
		    			pstmt.setString(3, tf_phone.getText());
		    		if(tf_office.getText().length()!=0)
		    			pstmt.setString(4, tf_office.getText());
		    		if(tf_lecture.getText().length()!=0)
		    			pstmt.setString(5, tf_lecture.getText());
		    		if(tf_head.getText().length()!=0)
		    			pstmt.setInt(6, Integer.parseInt(tf_head.getText()));
		            pstmt.executeUpdate();
		            

		            ta_state.setText("학과정보가 입력되었습니다.");
		           	            
		            tf_num.setText("");
		            tf_name.setText("");
		            tf_phone.setText("");
		            tf_phone.setText("");
		            tf_office.setText("");
		            tf_lecture.setText("");
		            tf_head.setText("");
		            
		            
		         }catch (SQLException e1) {
		        	 if(e1.getErrorCode() == 1062)
		        		 ta_state.setText("중복된 학과번호입니다.");
		        	 
		              
		             else {
		            	   ta_state.setText("필수 정보를 모두 입력해주세요.");
		               }
		         }catch(Exception e1) {
		        	 System.out.println(e1.toString());
		         }
		    	
		    }else if(e.getSource() == btn_del) {
		    	conDB();
		        sql = "delete from department where number = ?";

		        try {
		        	
		            pstmt = con.prepareStatement(sql);
		            pstmt.setInt(1, Integer.parseInt(tf_num.getText()));
		            int check = pstmt.executeUpdate();
		            
		            if(check == 1)
		            	ta_state.setText(Integer.parseInt(tf_num.getText()) + "의 학과정보가 삭제되었습니다.");
		            else
		            	ta_state.setText("정보가 없습니다.");
		        } catch (SQLException e1) {
		            System.out.println(e1.getErrorCode());
		        } catch (Exception e1) {
		        	ta_state.setText("옳지않은 정보입니다.");
		        }
		        

		        
		        tf_num.setText("");
	            tf_name.setText("");
	            tf_phone.setText("");
	            tf_office.setText("");
	            tf_lecture.setText("");
	            tf_head.setText("");


		    }else if(e.getSource() == btn_update) {
		    	conDB();
		        sql = "update department set name = ?, phone_num = ?, office = ?, lecture = ?, head = ? where number = ?";
		        
		        
		        try {
		        	
		        	
		            pstmt = con.prepareStatement(sql);

		            if(tf_num.getText().length()!=0)
		    			pstmt.setInt(6, Integer.parseInt(tf_num.getText()));
		    		if(tf_name.getText().length()!=0)
		    			pstmt.setString(1, tf_name.getText());
		    		if(tf_phone.getText().length()!=0)
		    			pstmt.setString(2, tf_phone.getText());
		    		if(tf_office.getText().length()!=0)
		    			pstmt.setString(3, tf_office.getText());
		    		if(tf_lecture.getText().length()!=0)
		    			pstmt.setString(4, tf_lecture.getText());
		    		if(tf_head.getText().length()!=0)
		    			pstmt.setInt(5, Integer.parseInt(tf_head.getText()));
		            
		            int check = pstmt.executeUpdate();
		            System.out.println(check);
		            if(check == 0)
		            	ta_state.setText("번호를 올바르게 입력해주세요.");
		            else
		            	ta_state.setText(Integer.parseInt(tf_num.getText()) + "의 학과정보가 수정되었습니다.");
		            
		            
		            tf_num.setText("");
		            tf_name.setText("");
		            tf_phone.setText("");
		            tf_office.setText("");
		            tf_lecture.setText("");
		            tf_head.setText("");
		            

		        } catch (SQLException e1) {
		        	System.out.println(e1.getErrorCode());
		        		ta_state.setText("필수 정보를 모두 입력해주세요.");
		        		System.out.println(e1);
		        }


		    }
		         
		    closeDB();
		}
}
