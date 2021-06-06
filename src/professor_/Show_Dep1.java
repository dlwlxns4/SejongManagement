package professor_;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class Show_Dep1 extends JFrame implements ActionListener{
	JPanel pn; 
	JLabel lecture_LookUp, circle_lookup, grade_lookup , label_grade, label_credit;
	JButton btn_lecture_lookup, btn_circle_lookup, btn_grade_lookup;
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
	
	public Show_Dep1() {
		super("학과정보");
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
        
	      //관리자 화면
	      pn=new JPanel();
	      pn.setLayout(null);
	      pn.setBounds(0,0,1500,1300);
	      
	      
	      //년도 학기를 통해 수강내역 조회
	      btn_lecture_lookup = new JButton("조회");
	      btn_lecture_lookup.setBounds(310,10,100,30);
	      
	      student_number = new JLabel("학과번호");
	      student_number.setBounds(10, 10, 70, 30);
	      tf_student_number = new JTextField("");
	      tf_student_number.setBounds(100, 10, 70, 30);    
	      
	      year = new JLabel("년도");
	      year.setBounds(120, 10, 40, 30);
	      tf_year = new JTextField("");
	      tf_year.setBounds(155,10,50,30);
	      
	      semester = new JLabel("학기");
	      semester.setBounds(220, 10, 40, 30);
	      tf_semester = new JTextField("");
	      tf_semester.setBounds(255, 10, 50, 30);
	      
	      lecture_LookUp = new JLabel("소속학과 조회");
	      lecture_LookUp.setBounds(420, 10, 100, 30);
	      
	      lecture_history_table = new JTextArea();
	      lecture_history_scrollPane = new JScrollPane(lecture_history_table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      lecture_history_scrollPane.setBounds(10, 40, 600, 200);
	      
	      btn_lecture_lookup.addActionListener(this);
	      
	      pn.add(lecture_history_scrollPane);
	      pn.add(btn_lecture_lookup);
	      pn.add(tf_student_number);
	      pn.add(student_number);
//	      pn.add(year);
//	      pn.add(tf_year);
//	      pn.add(semester);
//	      pn.add(tf_semester);
	      pn.add(lecture_LookUp);
	       
	      add(pn);
	      pn.setVisible(true);
	      
	      btn_lecture_lookup.addActionListener(this);
	   }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		conDB();
		// TODO Auto-generated method stub
		try {
			if(e.getSource() == btn_lecture_lookup) {

				
				
	            String query = "select * from Department where number = (select dep_num from Professor where number = ?)";
	            

	    		pstmt = con.prepareStatement(query);
    			pstmt.setInt(1, Integer.parseInt( tf_student_number.getText()));
//    			pstmt.setInt(2, Integer.parseInt( tf_year.getText()));
//    			pstmt.setInt(3, Integer.parseInt( tf_semester.getText()));
	            
    			
	            lecture_history_table.setText("");
	            lecture_history_table.setText("학과번호                  학과명                  학과전화번호                   강좌                        학과장                  학과사무실\n");
            	
	            
	            rs = pstmt.executeQuery();
	            
	            while (rs.next()) {
	               String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" +  rs.getString(3) + "\t\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) +"\n";
	               lecture_history_table.append(str);
				}
			}
		}catch(Exception e1) {
			System.out.println(e1);
		}
		
		closeDB();
	}
}