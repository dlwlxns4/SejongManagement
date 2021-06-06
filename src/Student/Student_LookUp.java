package Student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class Student_LookUp extends JFrame implements ActionListener{
	JPanel pn; 
	JLabel lecture_LookUp, circle_lookup, grade_lookup , label_grade, label_credit;
	JButton btn_lecture_lookup, btn_circle_lookup, btn_grade_lookup, btn_lectime;
	JLabel student_number, year, semester, circle_student_number, student_grade_number;
	JTextField tf_student_number, tf_year, tf_semester, tf_circle_student_number, tf_student_grade_number;
	
	
	JTextArea lecture_history_table, professor_table, lecture_table, circle_table, department_table, grade_table;
	JScrollPane lecture_history_scrollPane, professor_scrollPane, lecture_scrollPane, circle_scrollPane, department_scrollPane, grade_table_scrollPane;
	
	
	PreparedStatement pstmt = null; // SQL문을 DB에 보내기 위한 객체
    Statement stmt;
    ResultSet rs, rs2;
    String Driver = "";
    String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
    String userid = "madang";
    String pwd = "madang";
    
 
	Connection con;
	
	public Student_LookUp() {
		super("학생");
		layInit();
		this.setLayout(null);
		setBounds(0, 0, 1500, 1000);   
		
		
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
	
	public void layInit() {
        
		  
		  //shop = new RepairShop(con_adm);
		   
	      //관리자 화면
	      pn=new JPanel();
	      pn.setLayout(null);
	      pn.setBounds(0,0,1500,1300);
	      
	      
	      //년도 학기를 통해 수강내역 조회
	      btn_lecture_lookup = new JButton("조회");
	      btn_lecture_lookup.setBounds(310,10,100,30);
	      
	      student_number = new JLabel("학번");
	      student_number.setBounds(10, 10, 40, 30);
	      tf_student_number = new JTextField("");
	      tf_student_number.setBounds(45, 10, 70, 30);    
	      
	      year = new JLabel("년도");
	      year.setBounds(120, 10, 40, 30);
	      tf_year = new JTextField("");
	      tf_year.setBounds(155,10,50,30);
	      
	      semester = new JLabel("학기");
	      semester.setBounds(220, 10, 40, 30);
	      tf_semester = new JTextField("");
	      tf_semester.setBounds(255, 10, 50, 30);
	      
	      lecture_LookUp = new JLabel("강좌 조회");
	      lecture_LookUp.setBounds(420, 10, 100, 30);
	      
	      lecture_history_table = new JTextArea();
	      lecture_history_scrollPane = new JScrollPane(lecture_history_table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      lecture_history_scrollPane.setBounds(10, 40, 600, 200);
	      
	      btn_lecture_lookup.addActionListener(this);
	      
	      ///시간표
	      btn_lectime = new JButton("시간표");
	      btn_lectime.setBounds(650, 400, 100, 100);
	      btn_lectime.addActionListener(this);
	      
	      
	      pn.add(lecture_history_scrollPane);
	      pn.add(btn_lecture_lookup);
	      pn.add(tf_student_number);
	      pn.add(student_number);
	      pn.add(year);
	      pn.add(tf_year);
	      pn.add(semester);
	      pn.add(tf_semester);
	      pn.add(lecture_LookUp);
	      pn.add(btn_lectime);
	      //동아리조회
	      btn_circle_lookup = new JButton("조회");
	      btn_circle_lookup.setBounds(730, 10, 100, 30);
	      circle_student_number = new JLabel("학번");
	      circle_student_number.setBounds(620, 10, 40, 30);
	      tf_circle_student_number = new JTextField("");
	      tf_circle_student_number.setBounds(655, 10, 70, 30);

	      
	      circle_lookup = new JLabel("동아리조회");
	      circle_lookup.setBounds(850, 10, 100, 30);
	      
	      circle_table = new JTextArea();
	      circle_scrollPane = new JScrollPane(circle_table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      circle_scrollPane.setBounds(620, 40, 600, 200);
	      
	      
	      pn.add(btn_circle_lookup);
	      pn.add(circle_student_number);
	      pn.add(tf_circle_student_number);
	      pn.add(circle_lookup);
	      pn.add(circle_scrollPane);
	      
	      
	      
	      //성적 조회
	      btn_grade_lookup = new JButton("조회");
	      btn_grade_lookup.setBounds(120, 260, 100, 30);
	      student_grade_number = new JLabel("학번");
	      student_grade_number.setBounds(10, 260, 40, 30);
	      tf_student_grade_number = new JTextField("");
	      tf_student_grade_number.setBounds(45, 260, 70, 30);

	      
	      grade_lookup = new JLabel("성적조회");
	      grade_lookup.setBounds(420, 260, 100, 30);
	      
	      grade_table = new JTextArea();
	      grade_table_scrollPane = new JScrollPane(grade_table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      grade_table_scrollPane.setBounds(10, 300, 600, 200);
	      
	      label_credit = new JLabel("취득 학점 : ");
	      label_grade = new JLabel("평점 : (4.5만점)");
	      
	      label_credit.setBounds(10, 520, 200, 30);
	      label_grade.setBounds(240, 520, 200, 30);
	      
	      
	      
	      pn.add(btn_grade_lookup);
	      pn.add(student_grade_number);
	      pn.add(student_grade_number);
	      pn.add(tf_student_grade_number);
	      pn.add(grade_table_scrollPane);
	      pn.add(grade_lookup);
	      pn.add(label_credit);
	      pn.add(label_grade);
	      
	      
	      
	      
	      
	      
	      
	      
	      add(pn);
	      pn.setVisible(true);
	      
	      btn_lecture_lookup.addActionListener(this);
	      btn_circle_lookup.addActionListener(this);
	      btn_grade_lookup.addActionListener(this);
	   }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		conDB();
		// TODO Auto-generated method stub
		try {
			if(e.getSource() == btn_lecture_lookup) {

				
				
	            String query = "select * from lecture_history where Student_number = ? and year = ? and semester = ? ;";
	            

	    		pstmt = con.prepareStatement(query);
    			pstmt.setInt(1, Integer.parseInt( tf_student_number.getText()));
    			pstmt.setInt(2, Integer.parseInt( tf_year.getText()));
    			pstmt.setInt(3, Integer.parseInt( tf_semester.getText()));
	            
    			
	            lecture_history_table.setText("");
	            lecture_history_table.setText("번호                    출석점수              중간점수                기말점수            기타점수             총합점수             학점(A~F)            수강년도          수강학기             학생번호               교수번호                  강의번호\n");
            	
	            
	            rs = pstmt.executeQuery();
	            
	            while (rs.next()) {
	               String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" +  rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getString(7) + "\t" + rs.getString(8) + "\t"
	                     + rs.getString(9)+ "\t" +rs.getInt(10)+  "\t" + rs.getInt(11) + "\t" + rs.getInt(12) +"\n";
	               lecture_history_table.append(str);
//			            
//	            	}
				}
			}else if(e.getSource() == btn_circle_lookup) {
				String query = "select * from circles where number IN (select Circles_c_number from  student_has_circles where Student_s_number = ?) ";

	    		pstmt = con.prepareStatement(query);
    			pstmt.setInt(1, Integer.parseInt( tf_circle_student_number.getText()));

    			circle_table.setText("");
    			 
	            rs = pstmt.executeQuery();
	            

    			circle_table.setText("번호                  이름                  학생 수                    회장                   지도교수               동아리방\n");

	    		
	            while (rs.next()) {
	            	circle_table.append(rs.getInt(1) + "\t" + rs.getString(2) +"\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" +rs.getString(6) + "\n");
	            	
	            	query = "select * from student where number IN (select Student_s_number from student_has_circles where Circles_c_number = ?) ";

		    		pstmt = con.prepareStatement(query);
	    			pstmt.setInt(1, rs.getInt(1));

	    			 
		            rs2 = pstmt.executeQuery();
	            	
		            
		           
		            if(rs.getInt(4) == Integer.parseInt(tf_circle_student_number.getText())) {
		            	circle_table.append("\n\n동아리 회장입니다. 모든 학생의 정보를 표시합니다.");
		            	circle_table.append("\n번호           학생이름                  주소                       전화번호               이메일                    전공            부전공           지도교수      계좌정보\n");
		            	 while(rs2.next()) {
					            
					            circle_table.append(rs2.getInt(1) + "\t" + rs2.getString(2) +"\t" + rs2.getString(3) + "\t" + rs2.getString(4) + "\t" + rs2.getString(5) + "\t" +rs2.getString(6) + "\t" + rs2.getString(6) +"\t" + rs2.getString(8) +"\t" + rs2.getString(9) + "\n");
				            	
				            }  	
				            circle_table.append("\n");

		            }
		        }

			}else if(e.getSource() == btn_grade_lookup){
					int total_credit=0;
				    float avg_grade=0;
				    double caculate_grade=0;
				
				 	String query = "select Lecture_number, grade  from lecture_history where Student_number = ? ;";
		    		pstmt = con.prepareStatement(query);
	    			pstmt.setInt(1, Integer.parseInt( tf_student_grade_number.getText()));
		            rs = pstmt.executeQuery();
		           
		            grade_table.setText("과목번호              과목명                 취득학점               평점\n");
	            	
		            


	            	
		            
		            while (rs.next()) {
		            	String query2 = "select grade,name from lecture where number IN ( select Lecture_number from lecture_history where Lecture_number = ?)";
			            pstmt = con.prepareStatement(query2);
		    			pstmt.setInt(1, rs.getInt(1));
			            rs2 = pstmt.executeQuery();
			            rs2.next();

		            	
		               String str = rs.getInt(1) + "\t" +  rs2.getString(2) + "\t" + rs2.getInt(1) + "\t" + rs.getString(2) + "\n";
		               grade_table.append(str);

		               String A = rs.getString(2);
		               if(rs.getString(2).equals("A")) {
			               total_credit += rs2.getInt(1);
		            	   caculate_grade += ((double) (rs2.getInt(1) * 4.5));
		               }else  if(rs.getString(2).equals("B")) {
			               total_credit += rs2.getInt(1);
		            	   caculate_grade += ((double) (rs2.getInt(1) * 3.5));
		               }else  if(rs.getString(2).equals("C")) {
			               total_credit += rs2.getInt(1);
		            	   caculate_grade += ((double) (rs2.getInt(1) * 2.5));
		               }else  if(rs.getString(2).equals("D")) {
			               total_credit += rs2.getInt(1);
		            	   caculate_grade += ((double) (rs2.getInt(1) * 1.5));
		               }else if(rs.getString(2).equals("F")) {
		            	   caculate_grade += ((double) (rs2.getInt(1) * 0));
		               }else {
		            	   System.out.println(A);
		               }
		            }
		            avg_grade = (float) (caculate_grade / total_credit);
		            label_credit.setText("취득 학점 : " + total_credit);
		            label_grade.setText("평점 : " + avg_grade + "(4.5만점)");
			}else if(e.getSource() == btn_lectime) {

			      Show_Lectime1 lectime = new Show_Lectime1();
			      lectime.setVisible(true);
			}
		}catch(Exception e1) {
			System.out.println(e1);
		}
		
		closeDB();
	}
}
