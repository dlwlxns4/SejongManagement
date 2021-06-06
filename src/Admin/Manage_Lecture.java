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

public class Manage_Lecture extends JFrame implements ActionListener{
	JLabel num,_class,professor,name,day,period,credit,time,department,office, information_del, information_update, year, semester; 
    JTextField tf_num,tf__class,tf_professor,tf_name,tf_day,tf_period, tf_credit, tf_time, tf_department, tf_office, tf_year, tf_semester;
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
    
	public Manage_Lecture() {	
		
		  super("강좌관리");

		  
		  pn=new JPanel();
		  pn_btn = new JPanel();
		  pn_inf = new JPanel();
		  pn_inf.setPreferredSize(new Dimension(500,55));
		  
		  
	      num = new JLabel("강좌 번호(필수)");
	      _class=new JLabel("분반 번호(필수)");
	      professor=new JLabel("강의 교수(필수)");
	      name=new JLabel("강좌 이름(필수)");
	      day=new JLabel("강의 요일(필수)");
	      period=new JLabel("강의 교시(필수)");
	      credit=new JLabel("취득 학점(필수)");
	      time = new JLabel("강좌 시간(필수)");
	      department = new JLabel("개설 학과(필수)");
	      office = new JLabel("강의실 정보(필수)");
	      year = new JLabel("년도");
	      semester = new JLabel("학기");
	      information_del = new JLabel("삭제하는 경우 삭제할 회사 번호만 입력하면 삭제완료");
	      information_update = new JLabel("수정하는 경우 학생 번호 입력후 모든 필수 정보 입력하면 수정완료");
	      
	      pn_inf.add(information_del);
	      pn_inf.add(information_update);
	      
	      tf_num=new JTextField();
	      tf__class=new JTextField();
	      tf_professor=new JTextField();
	      tf_name=new JTextField();
	      tf_day=new JTextField();
	      tf_period=new JTextField();
	      tf_credit=new JTextField();
	      tf_time = new JTextField();
	      tf_department = new JTextField();
	      tf_office = new JTextField();
	      tf_semester = new JTextField();
	      tf_year = new JTextField();
	      btn_input=new JButton("입력");
	      btn_del=new JButton("삭제");
	      btn_update=new JButton("수정");
	      
	      pn.setLayout(new GridLayout(13,2));
		  pn_btn.setLayout(new GridLayout(1,3));
		   
		  pn.add(num);	   
		  pn.add(tf_num);
		   
		  pn.add(_class);	
		  pn.add(tf__class);

		  pn.add(name);	   
		  pn.add(tf_name);
		  

		  pn.add(day);	
		  pn.add(tf_day);
		   
		  pn.add(period);
		  pn.add(tf_period);
		  

		  pn.add(credit);
		  pn.add(tf_credit);
		  

		  pn.add(time);
		  pn.add(tf_time);
		  

		  pn.add(department);
		  pn.add(tf_department);

		  
		  pn.add(office);
		  pn.add(tf_office);
		  
		  pn.add(year);
		  pn.add(tf_year);
		  
		  pn.add(semester);
		  pn.add(tf_semester);
		  
		  pn.add(professor);
		  pn.add(tf_professor);	
		   
		   
	      
		  
		  
		  
		  
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
	      
	      setBounds(200, 200, 500, 800);
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
	    	sql = "insert into lecture(number, class_num, professor, name, class_day, class_time, grade, lecture_time, department, office, year, semester) value(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
	    	
	    	conDB();
	    	try {  
	    		pstmt = con.prepareStatement(sql);
	    		
	    		if(tf_num.getText().length()!=0)
	    			pstmt.setInt(1, Integer.parseInt(tf_num.getText()));
	    		if(tf__class.getText().length()!=0)
	    			pstmt.setString(2, tf_name.getText());
	    		if(tf_professor.getText().length()!=0)
	    			pstmt.setInt(3, Integer.parseInt(tf_professor.getText()));
	    		if(tf_name.getText().length()!=0)
	    			pstmt.setString(4, tf_name.getText());
	    		if(tf_day.getText().length()!=0)
	    			pstmt.setString(5, tf_day.getText());
	    		if(tf_period.getText().length()!=0)
	    			pstmt.setString(6, tf_period.getText());
	    		if(tf_credit.getText().length()!=0)
	    			pstmt.setString(7, tf_credit.getText());
	    		if(tf_time.getText().length()!=0)
	    			pstmt.setString(8,  tf_time.getText());
	    		if(tf_department.getText().length()!=0)
	    			pstmt.setString(9, tf_department.getText());
	    		if(tf_office.getText().length()!=0)
	    			pstmt.setString(10, tf_office.getText());
	    		if(tf_year.getText().length()!=0)
	    			pstmt.setString(11, tf_year.getText());
	    		if(tf_semester.getText().length()!=0)
	    			pstmt.setString(12, tf_semester.getText());
	            pstmt.executeUpdate();
	            

	            ta_state.setText("강좌가 입력되었습니다.");
	           	            
	            tf_num.setText("");
	            tf__class.setText("");
	            tf_professor.setText("");
	            tf_name.setText("");
	            tf_day.setText("");
	            tf_period.setText("");
	            tf_credit.setText("");
	            tf_time.setText("");
	            tf_department.setText("");
	            tf_office.setText("");
	            tf_year.setText("");
	            tf_semester.setText("");
	            
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
	        sql = "delete from lecture where number = ?";

	        try {
	        	
	            pstmt = con.prepareStatement(sql);
	            pstmt.setInt(1, Integer.parseInt(tf_num.getText()));
	            int check = pstmt.executeUpdate();
	            
	            if(check == 1)
	            	ta_state.setText(Integer.parseInt(tf_num.getText()) + "의 강좌정보가 삭제되었습니다.");
	            else
	            	ta_state.setText("정보가 없습니다.");
	        } catch (SQLException e1) {
	        	if(e1.getErrorCode()==1451) {
	        		ta_state.setText("학과강의관계의정보를 삭제후 삭제해주세요.");
	        	}System.out.println(e1.getErrorCode());
	            System.out.println(e1);
	        } catch (Exception e1) {
	        	ta_state.setText("옳지않은 정보입니다.");
	        }
	        

	        
	        tf_num.setText("");
            tf__class.setText("");
            tf_professor.setText("");
            tf_name.setText("");
            tf_day.setText("");
            tf_period.setText("");
            tf_credit.setText("");
            tf_time.setText("");
            tf_department.setText("");
            tf_office.setText("");
            tf_year.setText("");
            tf_semester.setText("");
            

	    }else if(e.getSource() == btn_update) {
	    	conDB();
	        sql = "update lecture set class_num = ?, professor = ?, name = ?, class_day = ?, class_time = ?, grade = ?, lecture_time = ?, department = ?, office = ?, year=?, semester = ? where number = ?";
	        
	        
	        try {
	        	
	        	
	            pstmt = con.prepareStatement(sql);

	    		if(tf_num.getText().length()!=0)
	    			pstmt.setInt(12, Integer.parseInt(tf_num.getText()));
	    		if(tf__class.getText().length()!=0)
	    			pstmt.setString(1, tf__class.getText());
	    		if(tf_professor.getText().length()!=0)
	    			pstmt.setInt(2, Integer.parseInt(tf_professor.getText()));
	    		if(tf_name.getText().length()!=0)
	    			pstmt.setString(3, tf_name.getText());
	    		if(tf_day.getText().length()!=0)
	    			pstmt.setString(4, tf_day.getText());
	    		if(tf_period.getText().length()!=0)
	    			pstmt.setString(5, tf_period.getText());
	    		if(tf_credit.getText().length()!=0)
	    			pstmt.setString(6, tf_credit.getText());
	    		if(tf_time.getText().length()!=0)
	    			pstmt.setString(7, tf_time.getText());
	    		if(tf_department.getText().length()!=0)
	    			pstmt.setString(8, tf_department.getText());
	    		if(tf_year.getText().length()!=0)
	    			pstmt.setString(9, tf_year.getText());
	    		if(tf_semester.getText().length()!=0)
	    			pstmt.setString(10, tf_semester.getText());
	    		if(tf_office.getText().length()!=0)
	    			pstmt.setString(11, tf_office.getText());
	            
	            int check = pstmt.executeUpdate();
	            if(check == 0)
	            	ta_state.setText("번호를 올바르게 입력해주세요.");
	            else
	            	ta_state.setText(Integer.parseInt(tf_num.getText()) + "의 강좌정보가 수정되었습니다.");
	            
	            
	            tf_num.setText("");
	            tf__class.setText("");
	            tf_professor.setText("");
	            tf_name.setText("");
	            tf_day.setText("");
	            tf_period.setText("");
	            tf_credit.setText("");
	            tf_time.setText("");
	            tf_department.setText("");
	            tf_office.setText("");
	            tf_year.setText("");
	            tf_semester.setText("");
	            

	        } catch (SQLException e1) {
	        	System.out.println(e1.getErrorCode());
	        	System.out.println(e1);
	        	ta_state.setText("필수 정보를 모두 입력해주세요.");
	        }


	    }
	         
	    closeDB();
	}
}
