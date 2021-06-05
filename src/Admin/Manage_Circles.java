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

public class Manage_Circles extends JFrame implements ActionListener{
	JLabel number,name, member_number, chairman, professor, office, information_del, information_update; 
    JTextField tf_number,tf_name,tf_member_number, tf_chairman, tf_professor, tf_office;
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
    
    public Manage_Circles() {
		 super("동아리관리");

		  
		  pn=new JPanel();
		  pn_btn = new JPanel();
		  pn_inf = new JPanel();
		  pn_inf.setPreferredSize(new Dimension(500,55));
		  
		  
		  number = new JLabel("동아리번호(필수)");
	      name=new JLabel("동아리이름(필수)");
	      member_number=new JLabel("소속학생숫자(필수)");
	      chairman=new JLabel("회장학생정보(필수)");
	      professor=new JLabel("동아리지도교(필수)");
	      office=new JLabel("동아리방(필수)");
	      information_del = new JLabel("삭제하는 경우 삭제할 회사 번호만 입력하면 삭제완료");
	      information_update = new JLabel("수정하는 경우 학생 번호 입력후 모든 필수 정보 입력하면 수정완료");
	      
	      pn_inf.add(information_del);
	      pn_inf.add(information_update);
	      
	      tf_number=new JTextField();
	      tf_name=new JTextField();
	      tf_member_number=new JTextField();
	      tf_chairman=new JTextField();
	      tf_professor=new JTextField();
	      tf_office=new JTextField();
	      btn_input=new JButton("입력");
	      btn_del=new JButton("삭제");
	      btn_update=new JButton("수정");
	      
	      pn.setLayout(new GridLayout(7,2));
		  pn_btn.setLayout(new GridLayout(1,3));
		   
		  pn.add(number);	   
		  pn.add(tf_number);
		   
		  pn.add(name);	
		  pn.add(tf_name);
		   
		  pn.add(member_number);
		  pn.add(tf_member_number);	
		   
		  pn.add(chairman);	   
		  pn.add(tf_chairman);
		   
		  pn.add(professor);	
		  pn.add(tf_professor);
		   
		  pn.add(office);
		  pn.add(tf_office);
	      
		  
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
		    	sql = "insert into circles(number, name, s_num, chairman_num, professor, office) value(?, ?, ?, ?, ?, ?)";
		    	
		    	conDB();
		    	try {  
		    		pstmt = con.prepareStatement(sql);
		    		
		    		if(tf_number.getText().length()!=0)
		    			pstmt.setInt(1, Integer.parseInt(tf_number.getText()));
		    		if(tf_name.getText().length()!=0)
		    			pstmt.setString(2, tf_name.getText());
		    		if(tf_member_number.getText().length()!=0)
		    			pstmt.setInt(3, Integer.parseInt(tf_member_number.getText()) );
		    		if(tf_chairman.getText().length()!=0)
		    			pstmt.setInt(4, Integer.parseInt(tf_chairman.getText()) );
		    		if(tf_professor.getText().length()!=0)
		    			pstmt.setInt(5, Integer.parseInt(tf_professor.getText()) );
		    		if(tf_office.getText().length()!=0)
		    			pstmt.setString(6, tf_office.getText());
		            pstmt.executeUpdate();
		            

		            ta_state.setText("동아리 정보가 입력되었습니다.");
		           	            
		            tf_number.setText("");
		            tf_name.setText("");
		            tf_member_number.setText("");
		            tf_chairman.setText("");
		            tf_professor.setText("");
		            tf_office.setText("");
		            
		            
		         }catch (SQLException e1) {
		        	 if(e1.getErrorCode() == 1062)
		        		 ta_state.setText("중복된 동아리 번호입니다.");
		        	 
		              
		             else {
		            	   ta_state.setText("필수 정보를 모두 입력해주세요.");
		               }
		         }catch(Exception e1) {
		        	 System.out.println(e1.toString());
		         }
		    	
		    }else if(e.getSource() == btn_del) {
		    	conDB();
		        sql = "delete from circles where number = ?";

		        try {
		        	
		            pstmt = con.prepareStatement(sql);
		            pstmt.setInt(1, Integer.parseInt(tf_number.getText()));
		            int check = pstmt.executeUpdate();
		            
		            if(check == 1)
		            	ta_state.setText(Integer.parseInt(tf_number.getText()) + "의 동아리 정보가 삭제되었습니다.");
		            else
		            	ta_state.setText("정보가 없습니다.");
		        } catch (SQLException e1) {
		            System.out.println(e1.getErrorCode());
		        } catch (Exception e1) {
		        	ta_state.setText("옳지않은 정보입니다.");
		        }
		        

		        
		        tf_number.setText("");
	            tf_name.setText("");
	            tf_member_number.setText("");
	            tf_chairman.setText("");
	            tf_professor.setText("");
	            tf_office.setText("");


		    }else if(e.getSource() == btn_update) {
		    	conDB();
		        sql = "update circles set name = ?, s_num = ?, chairman_num = ?, professor = ?, office = ? where number = ?";
		        
		        
		        try {
		        	
		        	
		            pstmt = con.prepareStatement(sql);

		            if(tf_number.getText().length()!=0)
		    			pstmt.setInt(6, Integer.parseInt(tf_number.getText()));
		    		if(tf_name.getText().length()!=0)
		    			pstmt.setString(1, tf_name.getText());
		    		if(tf_member_number.getText().length()!=0)
		    			pstmt.setInt(2, Integer.parseInt(tf_member_number.getText()) );
		    		if(tf_chairman.getText().length()!=0)
		    			pstmt.setInt(3, Integer.parseInt(tf_chairman.getText()) );
		    		if(tf_professor.getText().length()!=0)
		    			pstmt.setInt(4, Integer.parseInt(tf_professor.getText()) );
		    		if(tf_office.getText().length()!=0)
		    			pstmt.setString(5, tf_office.getText());
		            
		            int check = pstmt.executeUpdate();
		            System.out.println(check);
		            if(check == 0)
		            	ta_state.setText("번호를 올바르게 입력해주세요.");
		            else
		            	ta_state.setText(Integer.parseInt(tf_number.getText()) + "의 동아리 정보가 수정되었습니다.");
		            
		            
			        tf_number.setText("");
		            tf_name.setText("");
		            tf_member_number.setText("");
		            tf_chairman.setText("");
		            tf_professor.setText("");
		            tf_office.setText("");

		            

		        } catch (SQLException e1) {
		        	System.out.println(e1.getErrorCode());
		        		ta_state.setText("필수 정보를 모두 입력해주세요.");
		        		System.out.println(e1);
		        }


		    }
		         
		    closeDB();
		}
}
