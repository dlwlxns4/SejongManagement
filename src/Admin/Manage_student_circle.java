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

public class Manage_student_circle extends JFrame implements ActionListener{
	JLabel number, student_number, circle_number, information_del, information_update; 
    JTextField tf_number,tf_student_number,tf_circle_number;
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
    
    public Manage_student_circle() {
		 super("동아리학생관계");

		  
		  pn=new JPanel();
		  pn_btn = new JPanel();
		  pn_inf = new JPanel();
		  pn_inf.setPreferredSize(new Dimension(500,55));
		  

		  number=new JLabel("번호(필수)");
		  student_number=new JLabel("학생 번호(필수)");
		  circle_number=new JLabel("동아리 번호(필수)");
	      information_del = new JLabel("삭제하는 경우 삭제할 번호만 입력하면 삭제완료");
	      information_update = new JLabel("수정하는 경우 번호 입력후 모든 필수 정보 입력하면 수정완료");
	      
	      pn_inf.add(information_del);
	      pn_inf.add(information_update);
	      
	      tf_number = new JTextField();
	      tf_student_number=new JTextField();
	      tf_circle_number=new JTextField();
	      btn_input=new JButton("입력");
	      btn_del=new JButton("삭제");
	      btn_update=new JButton("수정");
	      
	      pn.setLayout(new GridLayout(4,2));
		  pn_btn.setLayout(new GridLayout(1,3));
		   
		  pn.add(number);	   
		  pn.add(tf_number);
		  
		  pn.add(student_number);	   
		  pn.add(tf_student_number);
		   
		   

		  pn.add(circle_number);
		  pn.add(tf_circle_number);	
		   
		   
		  
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
		    	sql = "insert into student_has_circles(sc_num, Student_s_number, Circles_c_number) value(?, ?, ?)";
		    	
		    	conDB();
		    	try {  
		    		pstmt = con.prepareStatement(sql);

		    		if(tf_number.getText().length()!=0)
		    			pstmt.setInt(1, Integer.parseInt(tf_number.getText()));
		    		if(tf_student_number.getText().length()!=0)
		    			pstmt.setString(2, tf_student_number.getText());
		    		if(tf_circle_number.getText().length()!=0)
		    			pstmt.setString(3, tf_circle_number.getText());
		            pstmt.executeUpdate();
		            

		            ta_state.setText("동아리 학생 관계 정보가 입력되었습니다.");
		           	            
		            tf_number.setText("");
		            tf_student_number.setText("");
		            tf_circle_number.setText("");
		            
		            
		         }catch (SQLException e1) {
		        	 if(e1.getErrorCode() == 1062) {
		        		 ta_state.setText("중복된 동아리 학생 관계 정보 번호입니다.");
		        	 }else if(e1.getErrorCode() == 1452) {
		        		 ta_state.setText("존재하지않는 동아리 또는 학생번호입니다.");
		        	 }
		              
		             else {
		            	 System.out.println(e1);
		            	 System.out.println(e1.getErrorCode());
		            	   ta_state.setText("필수 정보를 모두 입력해주세요.");
		               }
		         }catch(Exception e1) {
		        	 System.out.println(e1.toString());
		         }
		    	
		    }else if(e.getSource() == btn_del) {
		    	conDB();
		        sql = "delete from student_has_circles where sc_num = ?";

		        try {
		        	
		            pstmt = con.prepareStatement(sql);
		            pstmt.setInt(1, Integer.parseInt(tf_number.getText()));
		            int check = pstmt.executeUpdate();
		            
		            if(check == 1)
		            	ta_state.setText(Integer.parseInt(tf_number.getText()) + "의 동아리 학생 관계 정보가 삭제되었습니다.");
		            else
		            	ta_state.setText("정보가 없습니다.");
		        } catch (SQLException e1) {
		            System.out.println(e1);
		            System.out.println(e1.getErrorCode());
		        } catch (Exception e1) {
		        	ta_state.setText("옳지않은 정보입니다.");
		        }
		        

		        
		        tf_number.setText("");
	            tf_student_number.setText("");
	            tf_circle_number.setText("");


		    }else if(e.getSource() == btn_update) {
		    	conDB();
		        sql = "update student_has_circles set Student_s_number= ?, Circles_c_number = ? where sc_num = ?";
		        
		        
		        try {
		        	
		        	
		            pstmt = con.prepareStatement(sql);

		  
		    		if(tf_student_number.getText().length()!=0)
		    			pstmt.setInt(1, Integer.parseInt(tf_student_number.getText()));
		    		if(tf_circle_number.getText().length()!=0)
		    			pstmt.setInt(2, Integer.parseInt(tf_circle_number.getText()));
		            if(tf_number.getText().length()!=0)
		    			pstmt.setInt(3, Integer.parseInt(tf_number.getText()));
		            
		            int check = pstmt.executeUpdate();
		            System.out.println(check);
		            if(check == 0)
		            	ta_state.setText("동아리 학생 관계 번호를 올바르게 입력해주세요.");
		            else
		            	ta_state.setText(Integer.parseInt(tf_number.getText()) + "의 동아리 학생 관계 정보가 수정되었습니다.");
		            
		            tf_number.setText("");
		            tf_student_number.setText("");
		            tf_circle_number.setText("");
		            

		        } catch (SQLException e1) {
		        	 if(e1.getErrorCode() == 1452) {
		        		 ta_state.setText("존재하지않는 동아리 또는 학생번호입니다.");
		        	 }
		              
		             else {
		            	 System.out.println(e1);
		            	 System.out.println(e1.getErrorCode());
		            	   ta_state.setText("필수 정보를 모두 입력해주세요.");
		               }
		         }


		    }
		         
		    closeDB();
		}
}
