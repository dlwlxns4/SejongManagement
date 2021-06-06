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

public class Show_Lectime1 extends JFrame implements ActionListener{
	JPanel pn; 
	JLabel lecture_LookUp, circle_lookup, grade_lookup , label_grade, label_credit;
	JButton btn_lecture_lookup, btn_circle_lookup, btn_grade_lookup;
	JLabel student_number, year, semester, circle_student_number, student_grade_number;
	JTextField tf_student_number, tf_year, tf_semester, tf_circle_student_number, tf_student_grade_number;
	
	
	JTextArea lecture_history_table, professor_table, lecture_table, circle_table, department_table, grade_table;
	JScrollPane lecture_history_scrollPane, professor_scrollPane, lecture_scrollPane, circle_scrollPane, department_scrollPane, grade_table_scrollPane;
	JTable tb;
	
	PreparedStatement pstmt = null; // SQL���� DB�� ������ ���� ��ü
    Statement stmt;
    ResultSet rs, rs2;
    String Driver = "";
    String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
    String userid = "madang";
    String pwd = "madang";
    
 
	Connection con;
	
	public Show_Lectime1() {
		super("���ǽð�ǥ");
		layInit();
		this.setLayout(null);
		setBounds(0, 0, 1500, 1000);   
		
		
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
	
	public void layInit() {
        
	      //������ ȭ��
	      pn=new JPanel();
	      pn.setLayout(null);
	      pn.setBounds(0,0,1500,1300);
	      
	      
	      //�⵵ �б⸦ ���� �������� ��ȸ
	      btn_lecture_lookup = new JButton("��ȸ");
	      btn_lecture_lookup.setBounds(310,10,100,30);
	      
	      student_number = new JLabel("�л���ȣ");
	      student_number.setBounds(10, 10, 70, 30);
	      tf_student_number = new JTextField("");
	      tf_student_number.setBounds(60, 10, 40, 30);    
	      
	      year = new JLabel("�⵵");
	      year.setBounds(120, 10, 40, 30);
	      tf_year = new JTextField("");
	      tf_year.setBounds(155,10,50,30);
	      
	      semester = new JLabel("�б�");
	      semester.setBounds(220, 10, 40, 30);
	      tf_semester = new JTextField("");
	      tf_semester.setBounds(255, 10, 50, 30);
	      
	      lecture_LookUp = new JLabel("���ǽð�ǥ");
	      lecture_LookUp.setBounds(420, 10, 100, 30);
	      
	      lecture_history_table = new JTextArea();
	      lecture_history_scrollPane = new JScrollPane(lecture_history_table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      lecture_history_scrollPane.setBounds(10, 40, 600, 200);
	      
	      btn_lecture_lookup.addActionListener(this);
	      
	      //�ð�ǥ
	      String header[] = {"����","��","ȭ","��","��","��"};
	    	String contents [][] = {
	    			{"1���� (10:30~12:00)", "","","","",""},
	    			{"2���� (12:00~13:30)", "","","","",""},
	    			{"3���� (13:30~15:00)", "","","","",""},
	    			{"4���� (15:00~16:30)", "","","","",""},
	    			{"5���� (16:30~18:00)", "","","","",""},
	    			
	    	};
	    	tb = new JTable(contents, header);
	    	tb.setRowHeight(100);
//	    	tb.getColumn("��").setPreferredWidth(200);
//	    	tb.getColumn("ȭ").setPreferredWidth(200);
//	    	tb.getColumn("��").setPreferredWidth(200);
//	    	tb.getColumn("��").setPreferredWidth(200);
//	    	tb.getColumn("��").setPreferredWidth(200);
	    	JScrollPane tb1 = new JScrollPane(tb);
	    	tb1.setLocation(50, 50);
	        tb1.setSize(800,525);
//	      pn.add(lecture_history_scrollPane);
	      pn.add(btn_lecture_lookup);
	      pn.add(tf_student_number);
	      pn.add(student_number);
	      pn.add(year);
	      pn.add(tf_year);
	      pn.add(semester);
	      pn.add(tf_semester);
	      pn.add(lecture_LookUp);
	      pn.add(tb1);
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

				
				
	            String query = "select name, office, class_day, lecture_time from Lecture where number IN (select Lecture_number from lecture_history where Student_number = ? and year = ? and semester = ?) ";
	            

	    		pstmt = con.prepareStatement(query);
	    		pstmt.setInt(1, Integer.parseInt(tf_student_number.getText()));
    			pstmt.setString(2, tf_year.getText());
    			pstmt.setString(3, tf_semester.getText());
    			
//    			pstmt.setInt(2, Integer.parseInt( tf_year.getText()));
//    			pstmt.setInt(3, Integer.parseInt( tf_semester.getText()));
	            
	            rs = pstmt.executeQuery();
	            for(int i=0; i<=4; i++) {
	            	   for(int j=1; j<=5; j++) {
	            		   tb.setValueAt("", i, j);
	            	   }
	               }
	            while (rs.next()) {
//	               String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" +  rs.getString(3) + "\t\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) +"\n";
	               
	       
	               int col=0, row = 0;
//	               System.out.println(rs.getString(3) + " : " + rs.getString(4));
	               if ((rs.getString(3)).equals("Mon")) {
	            	   col = 1;
	               }else if((rs.getString(3)).equals("Tues")) {
	            	   col = 2;
	               }else if((rs.getString(3)).equals("Wed")) {
	            	   col = 3;
	               }else if((rs.getString(3)).equals("Thu")) {
	            	   col = 4;
	               }else if((rs.getString(3)).equals("Fri")) {
	            	   col = 5;
	               }
	               
	               if((rs.getString(4)).equals("10:30~12:00")) {
	            	   row = 0;
	               }else if((rs.getString(4)).equals("12:00~13:30")) {
	            	   row = 1;
	               }else if((rs.getString(4)).equals("13:30~15:00")) {
	            	   row = 2;
	               }else if((rs.getString(4)).equals("15:00~16:30")) {
	            	   row = 3;
	               }else if((rs.getString(4)).equals("16:30~18:00")) {
	            	   row = 4;
	               }
	         
	               String ar = rs.getString(1) + "\n" + rs.getString(2);
	               tb.setValueAt(ar, row, col);
//	               lecture_history_table.append(str);
				}
			}
		}catch(Exception e1) {
			System.out.println(e1);
		}
		
		closeDB();
	}
}