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

public class Show_Grade extends JFrame implements ActionListener{
	JPanel pn; 
	JLabel lecture_LookUp, circle_lookup, grade_lookup , label_grade, label_credit, label_else, label_total, label_af;
	JButton btn_lecture_lookup, btn_circle_lookup, btn_grade_lookup;
	JLabel student_number, year, semester, circle_student_number, student_grade_number;
	JTextField tf_student_number, tf_year, tf_semester, tf_circle_student_number, tf_student_grade_number, tf_else, tf_total, tf_af;
	
	
	JTextArea lecture_history_table, professor_table, lecture_table, circle_table, department_table, grade_table;
	JScrollPane lecture_history_scrollPane, professor_scrollPane, lecture_scrollPane, circle_scrollPane, department_scrollPane, grade_table_scrollPane;
	
	//���ǹ�ȣ �Է�
	JLabel ln;
	JButton btn_ln;
	JTextField tf_ln;
	//�л���ȣ
	JLabel sn;
	JTextField tf_sn;
	
	PreparedStatement pstmt = null; // SQL���� DB�� ������ ���� ��ü
    Statement stmt;
    ResultSet rs, rs2;
    String Driver = "";
    String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
    String userid = "madang";
    String pwd = "madang";
    
 
	Connection con;
	
	public Show_Grade() {
		super("�����Է�");
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
	      
	      //���ǹ�ȣ UI
	      ln = new JLabel("���ǹ�ȣ �Է� :");
	      btn_ln = new JButton("�Է�");
	      tf_ln = new JTextField("");
	      ln.setBounds(10,10,100,30);
	      pn.add(ln);
	      tf_ln.setBounds(110, 10, 35, 30);
	      pn.add(tf_ln);
	      btn_ln.setBounds(150, 10, 65,30);
	      pn.add(btn_ln);
	      
	      sn = new JLabel("�л���ȣ");
	      tf_sn = new JTextField("");
	      sn.setBounds(10,100,100,30);
	      pn.add(sn);
	      tf_sn.setBounds(100,100,65,30);
	      pn.add(tf_sn);
	      
	      //�⵵ �б⸦ ���� �������� ��ȸ
	      btn_lecture_lookup = new JButton("�Է�");
	      btn_lecture_lookup.setBounds(520,50,70,30);
	      
	      student_number = new JLabel("�⼮");
	      student_number.setBounds(10, 50, 40, 30);
	      tf_student_number = new JTextField("");
	      tf_student_number.setBounds(45, 50, 35, 30);    
	      
	      year = new JLabel("�߰�");
	      year.setBounds(90, 50, 40, 30);
	      tf_year = new JTextField("");
	      tf_year.setBounds(120,50,35,30);
	      
	      semester = new JLabel("�⸻");
	      semester.setBounds(160, 50, 40, 30);
	      tf_semester = new JTextField("");
	      tf_semester.setBounds(190, 50, 35, 30);
	      
	      label_else = new JLabel("��Ÿ");
	      label_else.setBounds(230, 50, 40, 30);
	      tf_else = new JTextField("");
	      tf_else.setBounds(260, 50, 35, 30);
	      
	      label_total = new JLabel("����(0~100)");
	      label_total.setBounds(300,50,70,30);
	      tf_total = new JTextField("");
	      tf_total.setBounds(375,50,35,30);
	      
	      label_af = new JLabel("����(A~F)");
	      label_af.setBounds(415,50,60,30);
	      tf_af = new JTextField("");
	      tf_af.setBounds(475,50,35,30);
	      
	      lecture_LookUp = new JLabel("'����' ��  '�л���ȣ' �Է�");
	      lecture_LookUp.setBounds(600, 50, 150, 30);
	      
	      lecture_history_table = new JTextArea();
	      lecture_history_scrollPane = new JScrollPane(lecture_history_table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      lecture_history_scrollPane.setBounds(10, 160, 600, 200);
	      
	      btn_lecture_lookup.addActionListener(this);
	      
	      pn.add(lecture_history_scrollPane);
	      pn.add(btn_lecture_lookup);
	      pn.add(tf_student_number);
	      pn.add(student_number);
	      pn.add(year);
	      pn.add(tf_year);
	      pn.add(semester);
	      pn.add(tf_semester);
	      pn.add(lecture_LookUp);
	      pn.add(label_else);
	      pn.add(tf_else);
	      pn.add(label_total);
	      pn.add(tf_total);
	      pn.add(label_af);
	      pn.add(tf_af);
	
	      add(pn);
	      pn.setVisible(true);
	      
	      btn_lecture_lookup.addActionListener(this);
	      btn_ln.addActionListener(this);

	   }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		conDB();
		// TODO Auto-generated method stub
		try {
			if(e.getSource() == btn_lecture_lookup) {

	            String query = "update lecture_history set attendance_score=?, midterm_score=?, final_score=?, else_score=?, total_score=?, grade=? where Student_number=?";

	    		pstmt = con.prepareStatement(query);
    			pstmt.setString(1, tf_student_number.getText());
    			pstmt.setString(2, tf_year.getText());
    			pstmt.setString(3, tf_semester.getText());
    			pstmt.setString(4, tf_else.getText());
    			pstmt.setString(5, tf_total.getText());
    			pstmt.setString(6, tf_af.getText());
    			pstmt.setInt(7, Integer.parseInt(tf_sn.getText())); 
 
    			pstmt.executeUpdate();
			}else if(e.getSource() == btn_ln) { 
	            String query = "select attendance_score, midterm_score, final_score, else_score, total_score, grade, year, semester, Student_number from lecture_history where Lecture_number = ?;";

	            pstmt = con.prepareStatement(query);
    			pstmt.setInt(1, Integer.parseInt( tf_ln.getText()));
    			
    			lecture_history_table.setText("");
 	            lecture_history_table.setText("�⼮����          �߰�����            �⸻����         ��Ÿ����          ��������(0~100)    ����(A~F)       �����⵵            �����б�              �л���ȣ\n");
 	            
 	           rs = pstmt.executeQuery();
	            
	            while (rs.next()) {
	               String str = rs.getString(1) + "\t" + rs.getString(2) + "\t" +  rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getString(7) + "\t" + rs.getString(8) +
	            		   "\t" + rs.getInt(9) + "\n";
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
