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

public class Manage_Lecture_History extends JFrame implements ActionListener{
	JLabel number, student_number, lecture_number, professor_number,
			attendance_score, midterm_score, final_score, else_score, total, grade, information_del, information_update; 
    JTextField tf_number, tf_student_number,tf_lecture_number,tf_professor_number,
    			tf_attendance_score,tf_midterm_score, tf_final_score,tf_else_score,tf_total,tf_grade;
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
    
	
	public Manage_Lecture_History() {
		 super("������������");

		  
		  pn=new JPanel();
		  pn_btn = new JPanel();
		  pn_inf = new JPanel();
		  pn_inf.setPreferredSize(new Dimension(500,55));
		  
		  
		  number = new JLabel("�������� ��ȣ(�ʼ�)");
		  student_number = new JLabel("�л� ��ȣ(�ʼ�)");
		  lecture_number=new JLabel("���� ��ȣ(�ʼ�)");
		  professor_number=new JLabel("���� ��ȣ(�ʼ�)");
		  attendance_score=new JLabel("�⼮ ����(�ʼ�)");
		  midterm_score=new JLabel("�߰����� ����(�ʼ�)");
		  final_score=new JLabel("�⸻���� ����(�ʼ�)");
		  else_score=new JLabel("��Ÿ ����(�ʼ�)");
		  total=new JLabel("����(�ʼ�)");
		  grade=new JLabel("����(�ʼ�)");
		  
		  
	      information_del = new JLabel("�����ϴ� ��� ������  ��ȣ�� �Է��ϸ� �����Ϸ�");
	      information_update = new JLabel("�����ϴ� ��� �л� ��ȣ �Է��� ��� �ʼ� ���� �Է��ϸ� �����Ϸ�");
	      
	      pn_inf.add(information_del);
	      pn_inf.add(information_update);
	      
	      tf_number=new JTextField();
	      tf_student_number=new JTextField();
	      tf_lecture_number=new JTextField();
	      tf_professor_number=new JTextField();
	      tf_attendance_score=new JTextField();
	      tf_midterm_score=new JTextField();
	      tf_final_score=new JTextField();
	      tf_else_score=new JTextField();
	      tf_total=new JTextField();
	      tf_grade=new JTextField();
	      
	      btn_input=new JButton("�Է�");
	      btn_del=new JButton("����");
	      btn_update=new JButton("����");
	      
	      pn.setLayout(new GridLayout(11,2));
		  pn_btn.setLayout(new GridLayout(1,3));
		   
		  pn.add(number);	   
		  pn.add(tf_number);
		   
		  pn.add(student_number);	
		  pn.add(tf_student_number);
		   
		  pn.add(lecture_number);
		  pn.add(tf_lecture_number);	
		   
		  pn.add(professor_number);	   
		  pn.add(tf_professor_number);
		   
		  pn.add(attendance_score);	
		  pn.add(tf_attendance_score);
		   
		  pn.add(midterm_score);
		  pn.add(tf_midterm_score);
		  
		  pn.add(final_score);
		  pn.add(tf_final_score);	
		   
		  pn.add(else_score);	   
		  pn.add(tf_else_score);
		   
		  pn.add(total);	
		  pn.add(tf_total);
		   
		  pn.add(grade);
		  pn.add(tf_grade);
	      
		  
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
	
	public void closeDB() { // ������ ���̽� ���� ����
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
		         System.out.println("����̹� �ε� ����");
		      } catch (ClassNotFoundException e) {
		         e.printStackTrace();
		      }
		      
		      try { /* �����ͺ��̽��� �����ϴ� ���� */
		          System.out.println("�����ͺ��̽� ���� �غ�...");
		          con = DriverManager.getConnection(url, userid, pwd);
		          System.out.println("�����ͺ��̽� ���� ����");
		       } catch (SQLException e1) {
		          e1.printStackTrace();
		       }
	   }
	
	public void actionPerformed(ActionEvent e) {    
		   ta_state.setText("");
		    if (e.getSource() == btn_input){
		    	sql = "insert into lecture_history(number, student_number, lecture_number, professor_number"
		    			+ ", attendance_score, midterm_score, final_score, else_score, total_score,"
		    			+ "grade) value(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		    	
		    	conDB();
		    	try {  
		    		pstmt = con.prepareStatement(sql);
		    		
		    		if(tf_number.getText().length()!=0)
		    			pstmt.setInt(1, Integer.parseInt(tf_number.getText()));
		    		if(tf_student_number.getText().length()!=0)
		    			pstmt.setInt(2, Integer.parseInt(tf_student_number.getText()) );
		    		if(tf_lecture_number.getText().length()!=0)
		    			pstmt.setInt(3, Integer.parseInt(tf_lecture_number.getText()) );
		    		if(tf_professor_number.getText().length()!=0)
		    			pstmt.setInt(4, Integer.parseInt(tf_professor_number.getText()) );
		    		if(tf_attendance_score.getText().length()!=0)
		    			pstmt.setInt(5, Integer.parseInt( tf_attendance_score.getText()));
		    		if(tf_midterm_score.getText().length()!=0)
		    			pstmt.setInt(6, Integer.parseInt(tf_midterm_score.getText()));
		    		if(tf_final_score.getText().length()!=0)
		    			pstmt.setInt(7, Integer.parseInt(tf_final_score.getText()));
		    		if(tf_else_score.getText().length()!=0)
		    			pstmt.setInt(8, Integer.parseInt(tf_else_score.getText()) );
		    		if(tf_total.getText().length()!=0)
		    			pstmt.setInt(9, Integer.parseInt(tf_total.getText()));
		    		if(tf_grade.getText().length()!=0)
		    			pstmt.setString(10, (tf_grade.getText()));
		            pstmt.executeUpdate();
		            

		            ta_state.setText("���������� �ԷµǾ����ϴ�.");
		           	            
		            tf_number.setText("");
		            tf_student_number.setText("");
		            tf_lecture_number.setText("");
		            tf_professor_number.setText("");
		            tf_attendance_score.setText("");
		            tf_midterm_score.setText("");
		            tf_final_score.setText("");
		            tf_else_score.setText("");
		            tf_total.setText("");
		            tf_grade.setText("");
		            
		            
		         }catch (SQLException e1) {
		        	 if(e1.getErrorCode() == 1062)
		        		 ta_state.setText("�ߺ��� �������� ��ȣ�Դϴ�.");
		        	 
		              
		             else {
		            	   ta_state.setText("�ʼ� ������ ��� �Է����ּ���.");
		               }
		         }catch(Exception e1) {
		        	 System.out.println(e1.toString());
		         }
		    	
		    }else if(e.getSource() == btn_del) {
		    	conDB();
		        sql = "delete from lecture_history where number = ?";

		        try {
		        	
		            pstmt = con.prepareStatement(sql);
		            pstmt.setInt(1, Integer.parseInt(tf_number.getText()));
		            int check = pstmt.executeUpdate();
		            
		            if(check == 1)
		            	ta_state.setText(Integer.parseInt(tf_number.getText()) + "�� �������� ������ �����Ǿ����ϴ�.");
		            else
		            	ta_state.setText("������ �����ϴ�.");
		        } catch (SQLException e1) {
		            System.out.println(e1.getErrorCode());
		        } catch (Exception e1) {
		        	ta_state.setText("�������� �����Դϴ�.");
		        }
		        

		        
		        tf_number.setText("");
	            tf_student_number.setText("");
	            tf_lecture_number.setText("");
	            tf_professor_number.setText("");
	            tf_attendance_score.setText("");
	            tf_midterm_score.setText("");
	            tf_final_score.setText("");
	            tf_else_score.setText("");
	            tf_total.setText("");
	            tf_grade.setText("");


		    }else if(e.getSource() == btn_update) {
		    	conDB();
		        sql = "update lecture_history set student_number = ?, lecture_number = ?, professor_number = ?, attendance_score = ?,"
		        		+ " midterm_score = ?,  final_score = ?,  else_score = ?, total_score = ?, grade = ?  where number = ?";
		        
		        
		        try {
		        	
		        	
		            pstmt = con.prepareStatement(sql);

		            if(tf_number.getText().length()!=0)
		    			pstmt.setInt(10, Integer.parseInt(tf_number.getText()));
		    		if(tf_student_number.getText().length()!=0)
		    			pstmt.setInt(1, Integer.parseInt(tf_student_number.getText()) );
		    		if(tf_lecture_number.getText().length()!=0)
		    			pstmt.setInt(2, Integer.parseInt(tf_lecture_number.getText()) );
		    		if(tf_professor_number.getText().length()!=0)
		    			pstmt.setInt(3, Integer.parseInt(tf_professor_number.getText()) );
		    		if(tf_attendance_score.getText().length()!=0)
		    			pstmt.setInt(4, Integer.parseInt( tf_attendance_score.getText()));
		    		if(tf_midterm_score.getText().length()!=0)
		    			pstmt.setInt(5, Integer.parseInt(tf_midterm_score.getText()));
		    		if(tf_final_score.getText().length()!=0)
		    			pstmt.setInt(6, Integer.parseInt(tf_final_score.getText()));
		    		if(tf_else_score.getText().length()!=0)
		    			pstmt.setInt(7, Integer.parseInt(tf_else_score.getText()) );
		    		if(tf_total.getText().length()!=0)
		    			pstmt.setInt(8, Integer.parseInt(tf_total.getText()));
		    		if(tf_grade.getText().length()!=0)
		    			pstmt.setString(9, (tf_grade.getText()));
		            
		            int check = pstmt.executeUpdate();
		            System.out.println(check);
		            if(check == 0)
		            	ta_state.setText("��ȣ�� �ùٸ��� �Է����ּ���.");
		            else
		            	ta_state.setText(Integer.parseInt(tf_number.getText()) + "�� �а������� �����Ǿ����ϴ�.");
		            
		            
		            tf_number.setText("");
		            tf_student_number.setText("");
		            tf_lecture_number.setText("");
		            tf_professor_number.setText("");
		            tf_attendance_score.setText("");
		            tf_midterm_score.setText("");
		            tf_final_score.setText("");
		            tf_else_score.setText("");
		            tf_total.setText("");
		            tf_grade.setText("");
		            

		        } catch (SQLException e1) {
		        	System.out.println(e1.getErrorCode());
		        		ta_state.setText("�ʼ� ������ ��� �Է����ּ���.");
		        		System.out.println(e1);
		        } catch (Exception e1) {
		        	ta_state.setText("�ùٸ� ������ ������ �Է����ּ���");
		        }


		    }
		         
		    closeDB();
		}
}