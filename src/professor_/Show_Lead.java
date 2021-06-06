package professor_;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Show_Lead extends JFrame implements ActionListener, MouseListener{
	JPanel pn; 
	JButton btn_lecture_lookup, btn_check;
//	JLabel student_number, 
	JLabel year, semester, professor;
//	JTextField tf_student_number, 
	JTextField tf_year, tf_semester, tf_professor;
	JTextArea area;
	
	JTextArea lecture_history_table, professor_table, lecture_table, circle_table, department_table;
	JScrollPane lecture_history_scrollPane, professor_scrollPane, lecture_scrollPane, circle_scrollPane, department_scrollPane;
	
	// 테이블
	JTable productTable;
	String tableHeader[] = {"number","name","address","phone_number","email","main_major","sub_major","professor","account"}, tableContents[][];
	DefaultTableModel tableModel;
	JScrollPane scroll1;
	
	PreparedStatement pstmt = null; // SQL문을 DB에 보내기 위한 객체
    Statement stmt;
    ResultSet rs;
    String Driver = "";
    String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
    String userid = "madang";
    String pwd = "madang";
	Connection con;
	
	public Show_Lead() {
		super("지도하는 학생에대한 정보");
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
	      btn_check = new JButton("확인");
	      btn_check.setBounds(420,10,100,30);
	 
	      professor = new JLabel("번호");
	      professor.setBounds(10,10,40,30);
	      
	      tf_professor = new JTextField("");
	      tf_professor.setBounds(60 ,10, 50, 30);
	      
	      year = new JLabel("년도");
	      year.setBounds(120, 10, 40, 30);
	 
	      
	      tf_year = new JTextField("");
	      tf_year.setBounds(155,10,50,30);
	      
	      
	      semester = new JLabel("학기");
	      semester.setBounds(220, 10, 40, 30);
	      tf_semester = new JTextField("");
	      tf_semester.setBounds(255, 10, 50, 30);
	      
	      lecture_history_table = new JTextArea();
	      lecture_history_scrollPane = new JScrollPane(lecture_history_table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      lecture_history_scrollPane.setBounds(10, 40, 600, 200);
	      
	      btn_lecture_lookup.addActionListener(this);
	      
	      //pn.add(lecture_history_scrollPane);
	      pn.add(btn_lecture_lookup);
//	      pn.add(tf_student_number);
//	      pn.add(student_number);
	      pn.add(tf_professor);
//	      pn.add(year);
//	      pn.add(tf_year);
//	      pn.add(semester);
//	      pn.add(tf_semester);
	      pn.add(professor);
//	      pn.add(btn_check);
	      
	      tableModel = new DefaultTableModel(tableHeader,0);

	      productTable = new JTable(tableModel);
	      scroll1 = new JScrollPane(productTable);
	      scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	      scroll1.setBounds(10, 50,800,200);
	      area = new JTextArea();
	      area.setBounds(10, 270, 800, 200);
	      pn.add(area);
	      pn.add(scroll1);
	      add(pn);
	      pn.setVisible(true);
	      
	      btn_lecture_lookup.addActionListener(this);
	      productTable.addMouseListener(this);
	     
	   }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		conDB();
		// TODO Auto-generated method stub
		try {
			if(e.getSource() == btn_lecture_lookup) {

	            String query = "select * from Student where number IN (select Student_s_number from Student_has_Professor where Professor_p_number = ?)";
	            
	    		pstmt = con.prepareStatement(query);
    			pstmt.setInt(1, Integer.parseInt( tf_professor.getText()));                  
	            
	            rs = pstmt.executeQuery();
	            Object record[] = new Object[9];
	            tableModel.setNumRows(0);
	            while (rs.next()) {
	            	record[0] = rs.getInt(1);
	            	record[1] = rs.getString(2);
	            	record[2] = rs.getString(3);
	            	record[3] = rs.getString(4);
	            	record[4] = rs.getString(5);
	            	record[5] = rs.getInt(6);
	            	record[6] = rs.getInt(7);
	            	record[7] = rs.getInt(8);
	            	record[8] = rs.getString(9);
	            	tableModel.addRow(record);
//			            
//	            	}
				}
	            
	            
	            
			}
		}catch(Exception e1) {
			System.out.println(e1);
		}
		
		closeDB();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = productTable.getSelectedRow();
		int bufferedInt = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
		conDB();
		try {
			String sql = "select attendance_score, midterm_score, final_score, else_score, total_score, grade from lecture_history where Student_number = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bufferedInt);                
	        rs = pstmt.executeQuery();
	        area.setText("");
	        area.setText("출석점수          중간고사            기말고사                기타                 총점(0~100)      평점(A~F)\n"); // mysql에서 동아리 안뺐음
			while(rs.next()) {
				 String str = rs.getString(1) + "\t" + rs.getString(2) + "\t" +  rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) +  "\n";
				 area.append(str);
	        }
//			System.out.println("tq3" + bufferedInt);
		} catch(Exception e1) {
			System.out.println("sql 조회 중 오류 : " + e);
		}
		closeDB();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}