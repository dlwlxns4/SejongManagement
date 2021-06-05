package Admin;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class Admin_Table_LookUp extends JFrame implements ActionListener{
	JPanel pn; 
	JButton btn_student, btn_professor, btn_lecture, btn_circle, btn_department;
	JTextArea student_table, professor_table, lecture_table, circle_table, department_table;
	JScrollPane student_scrollPane, professor_scrollPane, lecture_scrollPane, circle_scrollPane, department_scrollPane;
	
	
	PreparedStatement pstmt = null; // SQL문을 DB에 보내기 위한 객체
    Statement stmt;
    ResultSet rs;
    String Driver = "";
    String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
    String userid = "madang";
    String pwd = "madang";
	Connection con;
    
    
	public Admin_Table_LookUp() {
		super("전체 테이블조회");
		layInit();
		this.setLayout(null);
		setBounds(0, 0, 1500, 1000);   
		
		
	}
	
	public void closeDB() { // 데이터 베이스 접속 종료
        try {
            stmt.close();
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


	public void layInit() {
        
		  
		  //shop = new RepairShop(con_adm);
		   
	      //관리자 화면
	      pn=new JPanel();
	      pn.setLayout(null);
	      pn.setBounds(0,0,1500,1300);
	      
	      
	      //student
	      btn_student = new JButton("학생조회");
	      btn_student.setBounds(10, 10, 90, 25);
	      student_table = new JTextArea();
	      student_scrollPane = new JScrollPane(student_table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      student_scrollPane.setBounds(10, 40, 600, 200);
	      
	      pn.add(student_scrollPane);
	      pn.add(btn_student);
	      btn_student.addActionListener(this);
	      
	    //professor
	      btn_professor = new JButton("교수조회");
	      btn_professor.setBounds(650, 10, 90, 25);
	      professor_table = new JTextArea();
	      professor_scrollPane = new JScrollPane(professor_table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      professor_scrollPane.setBounds(650, 40, 600, 200);
	      pn.add(professor_scrollPane);
	      pn.add(btn_professor);
	      btn_professor.addActionListener(this);
	      
	      //lecture
	      btn_lecture = new JButton("강의조회");
	      btn_lecture.setBounds(10, 245, 90, 25);
	      lecture_table = new JTextArea();
	      lecture_scrollPane = new JScrollPane(lecture_table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      lecture_scrollPane.setBounds(10, 275, 600, 200);
	      pn.add(lecture_scrollPane);
	      pn.add(btn_lecture);
	      btn_lecture.addActionListener(this);
	      
	      //circle
	      btn_circle = new JButton("동아리조회");
	      btn_circle.setBounds(650, 245, 100, 25);
	      circle_table = new JTextArea();
	      circle_scrollPane = new JScrollPane(circle_table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      circle_scrollPane.setBounds(650, 275, 600, 200);
	      pn.add(circle_scrollPane);
	      pn.add(btn_circle);
	      btn_circle.addActionListener(this);
	      
	    //Department
	      btn_department = new JButton("동아리조회");
	      btn_department.setBounds(10, 480, 100, 25);
	      department_table = new JTextArea();
	      department_scrollPane = new JScrollPane(department_table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      department_scrollPane.setBounds(10, 510, 600, 200);
	      pn.add(department_scrollPane);
	      pn.add(btn_department);
	      btn_department.addActionListener(this);

	      
	      
	      add(pn);
	      
	      pn.setVisible(true);
	      
	     
	   }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		conDB();
		try {
			if(e.getSource() == btn_student ) {
				
	
	            String query = "SELECT * FROM student";
	            stmt = con.createStatement();
	            

	            student_table.setText("");
	            student_table.setText("번호           학생이름                  주소            전화번호      이메일              전공        부전공       지도교수      계좌정보       동아리\n");
            	
	            rs = stmt.executeQuery(query);
	            while (rs.next()) {
	               String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" +  rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getString(7) + "\t" + rs.getInt(8) + "\t"
	                     + rs.getString(9)+ "\t" +rs.getInt(10)+"\n";
	               student_table.append(str);
		            
		        }
			}else if(e.getSource() == btn_professor) {
				String query = "SELECT * FROM professor";
	            stmt = con.createStatement();
	            

	            professor_table.setText("");
	            professor_table.setText("NUMBER           NAME                ADDRESS            PHONE          EMAIL               MAIN_MAJOR       SUB_MAJOR     STUDENT\n");
            	
	            rs = stmt.executeQuery(query);
	            while (rs.next()) {
	               String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" +  rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getString(7) + "\t" + rs.getInt(8)
	                     +"\n";
	               professor_table.append(str);
		            
	            }
			}else if(e.getSource() == btn_lecture) {
				String query = "SELECT * FROM lecture";
	            stmt = con.createStatement();
	            

	            lecture_table.setText("");
	            lecture_table.setText("번호                    분반                   교수                     강의명                   요일                  교시                     학점                  강좌시간                 개설학과               강의실정보\n");
            	  
	            rs = stmt.executeQuery(query);
	            while (rs.next()) {
	               String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" +  rs.getInt(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getString(7) + "\t" + rs.getInt(8) + "\t"+ rs.getString(9) + "\t" + rs.getString(10)  
	                     +"\n";
	               lecture_table.append(str);
		            
	            }
			}else if(e.getSource() == btn_circle) {
				String query = "SELECT * FROM circle";
	            stmt = con.createStatement();
	            

	            circle_table.setText("");
	            circle_table.setText("번호                    이름                   회원 수                     회장                   지도교수                  사무실\n");
            	  
	            rs = stmt.executeQuery(query);
	            while (rs.next()) {
	               String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" +  rs.getInt(3) + "\t" + rs.getInt(4) + "\t" + rs.getInt(5) + "\t" + rs.getString(6)
	                     +"\n";
	               circle_table.append(str);
		            
	            }
			}else if(e.getSource() == btn_department) {
				String query = "SELECT * FROM department";
	            stmt = con.createStatement();
	            

	            department_table.setText("");
	            department_table.setText("번호                    이름                   전화번호                     사무실                   강의                  학과장\n");
            	  
	            rs = stmt.executeQuery(query);
	            while (rs.next()) {
	               String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" +  rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getInt(5) + "\t" + rs.getInt(6)
	                     +"\n";
	               department_table.append(str);
		            
	            }
			}
		}catch(Exception e1) {
			System.out.println(e1);
		}
		closeDB();
	}
}
