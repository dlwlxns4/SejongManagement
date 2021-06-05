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
	JButton btn_lecture_lookup;
	JLabel student_number, year, semester;
	JTextField tf_student_number, tf_year, tf_semester;
	
	
	JTextArea lecture_history_table, professor_table, lecture_table, circle_table, department_table;
	JScrollPane lecture_history_scrollPane, professor_scrollPane, lecture_scrollPane, circle_scrollPane, department_scrollPane;
	
	
	PreparedStatement pstmt = null; // SQL���� DB�� ������ ���� ��ü
    Statement stmt;
    ResultSet rs;
    String Driver = "";
    String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
    String userid = "madang";
    String pwd = "madang";
	Connection con;
	
	public Student_LookUp() {
		super("�л�");
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
        
		  
		  //shop = new RepairShop(con_adm);
		   
	      //������ ȭ��
	      pn=new JPanel();
	      pn.setLayout(null);
	      pn.setBounds(0,0,1500,1300);
	      
	      
	      //�⵵ �б⸦ ���� �������� ��ȸ
	      btn_lecture_lookup = new JButton("��ȸ");
	      btn_lecture_lookup.setBounds(310,10,100,30);
	      
	      student_number = new JLabel("�й�");
	      student_number.setBounds(10, 10, 40, 30);
	      tf_student_number = new JTextField("");
	      tf_student_number.setBounds(45, 10, 70, 30);    
	      
	      year = new JLabel("�⵵");
	      year.setBounds(120, 10, 40, 30);
	      tf_year = new JTextField("");
	      tf_year.setBounds(155,10,50,30);
	      
	      semester = new JLabel("�б�");
	      semester.setBounds(220, 10, 40, 30);
	      tf_semester = new JTextField("");
	      tf_semester.setBounds(255, 10, 50, 30);
	      
	      lecture_history_table = new JTextArea();
	      lecture_history_scrollPane = new JScrollPane(lecture_history_table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      lecture_history_scrollPane.setBounds(10, 40, 600, 200);
	      
	      btn_lecture_lookup.addActionListener(this);
	      
	      pn.add(lecture_history_scrollPane);
	      pn.add(btn_lecture_lookup);
	      pn.add(tf_student_number);
	      pn.add(student_number);
	      pn.add(year);
	      pn.add(tf_year);
	      pn.add(semester);
	      pn.add(tf_semester);
	      
	      

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

				
				
	            String query = "select * from lecture_history where student_number = ? and year_taken = ? and semester_taken = ? ;";
	            

	    		pstmt = con.prepareStatement(query);
    			pstmt.setInt(1, Integer.parseInt( tf_student_number.getText()));
    			pstmt.setInt(2, Integer.parseInt( tf_year.getText()));
    			pstmt.setInt(3, Integer.parseInt( tf_semester.getText()));
	            
    			
	            lecture_history_table.setText("");
	            lecture_history_table.setText("��ȣ                    �й�                     ���ǹ�ȣ                 ������ȣ             �⼮����                �߰�����              �⸻����            ��Ÿ����           ��������             ����(A~F)               �����⵵            �����б�\n");
            	
	            
	            rs = pstmt.executeQuery();
	            
	            while (rs.next()) {
	               String str = rs.getInt(1) + "\t" + rs.getInt(2) + "\t" +  rs.getString(3) + "\t" + rs.getInt(4) + "\t" + rs.getInt(5) + "\t" + rs.getInt(6) + "\t" + rs.getInt(7) + "\t" + rs.getInt(8) + "\t"
	                     + rs.getInt(9)+ "\t" +rs.getString(10)+  "\t" + rs.getInt(11) + "\t" + rs.getInt(12) +"\n";
	               lecture_history_table.append(str);
//			            
//	            	}
				}
			}
		}catch(Exception e1) {
			System.out.println(e1);
		}
		
		closeDB();
	}
}
