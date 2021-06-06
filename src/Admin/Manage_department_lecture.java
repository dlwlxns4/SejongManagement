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

public class Manage_department_lecture extends JFrame implements ActionListener {
	JLabel number, department_number, lecture_number, information_del, information_update; 
    JTextField tf_number,tf_department_number,tf_lecture_number;
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
    
    public Manage_department_lecture() {
		 super("학과강의관계");

		  
		  pn=new JPanel();
		  pn_btn = new JPanel();
		  pn_inf = new JPanel();
		  pn_inf.setPreferredSize(new Dimension(500,55));
		  

		  number=new JLabel("번호(필수)");
		  department_number=new JLabel("학과 번호(필수)");
		  lecture_number=new JLabel("강의 번호(필수)");
	      information_del = new JLabel("삭제하는 경우 삭제할 번호만 입력하면 삭제완료");
	      information_update = new JLabel("수정하는 경우 번호 입력후 모든 필수 정보 입력하면 수정완료");
	      
	      pn_inf.add(information_del);
	      pn_inf.add(information_update);
	      
	      tf_number = new JTextField();
	      tf_department_number=new JTextField();
	      tf_lecture_number=new JTextField();
	      btn_input=new JButton("입력");
	      btn_del=new JButton("삭제");
	      btn_update=new JButton("수정");
	      
	      pn.setLayout(new GridLayout(4,2));
		  pn_btn.setLayout(new GridLayout(1,3));
		   
		  pn.add(number);	   
		  pn.add(tf_number);
		  
		  pn.add(department_number);	   
		  pn.add(tf_department_number);
		   
		   

		  pn.add(lecture_number);
		  pn.add(tf_lecture_number);	
		   
		   
		  
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
		    	sql = "insert into department_has_lecture(number, Department_number, Lecture_number) value(?, ?, ?)";
		    	
		    	conDB();
		    	try {  
		    		pstmt = con.prepareStatement(sql);

		    		if(tf_number.getText().length()!=0)
		    			pstmt.setInt(1, Integer.parseInt(tf_number.getText()));
		    		if(tf_department_number.getText().length()!=0)
		    			pstmt.setString(2, tf_department_number.getText());
		    		if(tf_lecture_number.getText().length()!=0)
		    			pstmt.setString(3, tf_lecture_number.getText());
		            pstmt.executeUpdate();
		            

		            ta_state.setText("학과 강의 관계 정보가 입력되었습니다.");
		           	            
		            tf_number.setText("");
		            tf_department_number.setText("");
		            tf_lecture_number.setText("");
		            
		            
		         }catch (SQLException e1) {
		        	 if(e1.getErrorCode() == 1062) {
		        		 ta_state.setText("중복된 학과 강의 관계 정보 번호입니다.");
		        	 }else if(e1.getErrorCode() == 1452) {
		        		 ta_state.setText("존재하지않는 학과 또는 강의번호입니다.");
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
		        sql = "delete from department_has_lecture where number = ?";

		        try {
		        	
		            pstmt = con.prepareStatement(sql);
		            pstmt.setInt(1, Integer.parseInt(tf_number.getText()));
		            int check = pstmt.executeUpdate();
		            
		            if(check == 1)
		            	ta_state.setText(Integer.parseInt(tf_number.getText()) + "의 학과 강의 관계 정보가 삭제되었습니다.");
		            else
		            	ta_state.setText("정보가 없습니다.");
		        } catch (SQLException e1) {
		            System.out.println(e1);
		            System.out.println(e1.getErrorCode());
		        } catch (Exception e1) {
		        	ta_state.setText("옳지않은 정보입니다.");
		        }
		        

		        
		        tf_number.setText("");
	            tf_department_number.setText("");
	            tf_lecture_number.setText("");


		    }else if(e.getSource() == btn_update) {
		    	conDB();
		        sql = "update department_has_lecture set Department_number= ?, Lecture_number = ? where number = ?";
		        
		        
		        try {
		        	
		        	
		            pstmt = con.prepareStatement(sql);

		  
		    		if(tf_department_number.getText().length()!=0)
		    			pstmt.setInt(1, Integer.parseInt(tf_department_number.getText()));
		    		if(tf_lecture_number.getText().length()!=0)
		    			pstmt.setInt(2, Integer.parseInt(tf_lecture_number.getText()));
		            if(tf_number.getText().length()!=0)
		    			pstmt.setInt(3, Integer.parseInt(tf_number.getText()));
		            
		            int check = pstmt.executeUpdate();
		            System.out.println(check);
		            if(check == 0)
		            	ta_state.setText("학과 강좌 관계 번호를 올바르게 입력해주세요.");
		            else
		            	ta_state.setText(Integer.parseInt(tf_number.getText()) + "의 학과 강좌 관계 정보가 수정되었습니다.");
		            
		            tf_number.setText("");
		            tf_department_number.setText("");
		            tf_lecture_number.setText("");
		            

		        } catch (SQLException e1) {
		        	 if(e1.getErrorCode() == 1452) {
		        		 ta_state.setText("존재하지않는 학과 또는 강의번호입니다.");
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
