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

public class Manage_student_circle extends JFrame implements ActionListener{
	JLabel number, student_number, circle_number, information_del, information_update; 
    JTextField tf_number,tf_student_number,tf_circle_number;
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
    
    public Manage_student_circle() {
		 super("���Ƹ��л�����");

		  
		  pn=new JPanel();
		  pn_btn = new JPanel();
		  pn_inf = new JPanel();
		  pn_inf.setPreferredSize(new Dimension(500,55));
		  

		  number=new JLabel("��ȣ(�ʼ�)");
		  student_number=new JLabel("�л� ��ȣ(�ʼ�)");
		  circle_number=new JLabel("���Ƹ� ��ȣ(�ʼ�)");
	      information_del = new JLabel("�����ϴ� ��� ������ ��ȣ�� �Է��ϸ� �����Ϸ�");
	      information_update = new JLabel("�����ϴ� ��� ��ȣ �Է��� ��� �ʼ� ���� �Է��ϸ� �����Ϸ�");
	      
	      pn_inf.add(information_del);
	      pn_inf.add(information_update);
	      
	      tf_number = new JTextField();
	      tf_student_number=new JTextField();
	      tf_circle_number=new JTextField();
	      btn_input=new JButton("�Է�");
	      btn_del=new JButton("����");
	      btn_update=new JButton("����");
	      
	      pn.setLayout(new GridLayout(4,2));
		  pn_btn.setLayout(new GridLayout(1,3));
		   
		  pn.add(number);	   
		  pn.add(tf_number);
		  
		  pn.add(student_number);	   
		  pn.add(tf_student_number);
		   
		   

		  pn.add(circle_number);
		  pn.add(tf_circle_number);	
		   
		   
		  
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
		    	sql = "insert into student_has_circles(sc_num, Student_s_number, Circles_c_number) value(?, ?, ?)";
		    	
		    	conDB();
		    	try {  
		    		pstmt = con.prepareStatement(sql);

		    		if(tf_number.getText().length()!=0)
		    			pstmt.setInt(1, Integer.parseInt(tf_number.getText()));
		    		if(tf_student_number.getText().length()!=0)
		    			pstmt.setString(2, tf_student_number.getText());
		    		if(tf_circle_number.getText().length()!=0)
		    			pstmt.setString(3, tf_circle_number.getText());
		            pstmt.executeUpdate();
		            

		            ta_state.setText("���Ƹ� �л� ���� ������ �ԷµǾ����ϴ�.");
		           	            
		            tf_number.setText("");
		            tf_student_number.setText("");
		            tf_circle_number.setText("");
		            
		            
		         }catch (SQLException e1) {
		        	 if(e1.getErrorCode() == 1062) {
		        		 ta_state.setText("�ߺ��� ���Ƹ� �л� ���� ���� ��ȣ�Դϴ�.");
		        	 }else if(e1.getErrorCode() == 1452) {
		        		 ta_state.setText("���������ʴ� ���Ƹ� �Ǵ� �л���ȣ�Դϴ�.");
		        	 }
		              
		             else {
		            	 System.out.println(e1);
		            	 System.out.println(e1.getErrorCode());
		            	   ta_state.setText("�ʼ� ������ ��� �Է����ּ���.");
		               }
		         }catch(Exception e1) {
		        	 System.out.println(e1.toString());
		         }
		    	
		    }else if(e.getSource() == btn_del) {
		    	conDB();
		        sql = "delete from student_has_circles where sc_num = ?";

		        try {
		        	
		            pstmt = con.prepareStatement(sql);
		            pstmt.setInt(1, Integer.parseInt(tf_number.getText()));
		            int check = pstmt.executeUpdate();
		            
		            if(check == 1)
		            	ta_state.setText(Integer.parseInt(tf_number.getText()) + "�� ���Ƹ� �л� ���� ������ �����Ǿ����ϴ�.");
		            else
		            	ta_state.setText("������ �����ϴ�.");
		        } catch (SQLException e1) {
		            System.out.println(e1);
		            System.out.println(e1.getErrorCode());
		        } catch (Exception e1) {
		        	ta_state.setText("�������� �����Դϴ�.");
		        }
		        

		        
		        tf_number.setText("");
	            tf_student_number.setText("");
	            tf_circle_number.setText("");


		    }else if(e.getSource() == btn_update) {
		    	conDB();
		        sql = "update student_has_circles set Student_s_number= ?, Circles_c_number = ? where sc_num = ?";
		        
		        
		        try {
		        	
		        	
		            pstmt = con.prepareStatement(sql);

		  
		    		if(tf_student_number.getText().length()!=0)
		    			pstmt.setInt(1, Integer.parseInt(tf_student_number.getText()));
		    		if(tf_circle_number.getText().length()!=0)
		    			pstmt.setInt(2, Integer.parseInt(tf_circle_number.getText()));
		            if(tf_number.getText().length()!=0)
		    			pstmt.setInt(3, Integer.parseInt(tf_number.getText()));
		            
		            int check = pstmt.executeUpdate();
		            System.out.println(check);
		            if(check == 0)
		            	ta_state.setText("���Ƹ� �л� ���� ��ȣ�� �ùٸ��� �Է����ּ���.");
		            else
		            	ta_state.setText(Integer.parseInt(tf_number.getText()) + "�� ���Ƹ� �л� ���� ������ �����Ǿ����ϴ�.");
		            
		            tf_number.setText("");
		            tf_student_number.setText("");
		            tf_circle_number.setText("");
		            

		        } catch (SQLException e1) {
		        	 if(e1.getErrorCode() == 1452) {
		        		 ta_state.setText("���������ʴ� ���Ƹ� �Ǵ� �л���ȣ�Դϴ�.");
		        	 }
		              
		             else {
		            	 System.out.println(e1);
		            	 System.out.println(e1.getErrorCode());
		            	   ta_state.setText("�ʼ� ������ ��� �Է����ּ���.");
		               }
		         }


		    }
		         
		    closeDB();
		}
}
