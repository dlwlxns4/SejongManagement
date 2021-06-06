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
		
		  super("���°���");

		  
		  pn=new JPanel();
		  pn_btn = new JPanel();
		  pn_inf = new JPanel();
		  pn_inf.setPreferredSize(new Dimension(500,55));
		  
		  
	      num = new JLabel("���� ��ȣ(�ʼ�)");
	      _class=new JLabel("�й� ��ȣ(�ʼ�)");
	      professor=new JLabel("���� ����(�ʼ�)");
	      name=new JLabel("���� �̸�(�ʼ�)");
	      day=new JLabel("���� ����(�ʼ�)");
	      period=new JLabel("���� ����(�ʼ�)");
	      credit=new JLabel("��� ����(�ʼ�)");
	      time = new JLabel("���� �ð�(�ʼ�)");
	      department = new JLabel("���� �а�(�ʼ�)");
	      office = new JLabel("���ǽ� ����(�ʼ�)");
	      year = new JLabel("�⵵");
	      semester = new JLabel("�б�");
	      information_del = new JLabel("�����ϴ� ��� ������ ȸ�� ��ȣ�� �Է��ϸ� �����Ϸ�");
	      information_update = new JLabel("�����ϴ� ��� �л� ��ȣ �Է��� ��� �ʼ� ���� �Է��ϸ� �����Ϸ�");
	      
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
	      btn_input=new JButton("�Է�");
	      btn_del=new JButton("����");
	      btn_update=new JButton("����");
	      
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
	            

	            ta_state.setText("���°� �ԷµǾ����ϴ�.");
	           	            
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
	        		 ta_state.setText("�ߺ��� �а� ���� ���� ���� ��ȣ�Դϴ�.");
	        	 }else if(e1.getErrorCode() == 1452) {
	        		 ta_state.setText("���������ʴ� �а� �Ǵ� ���ǹ�ȣ�Դϴ�.");
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
	        sql = "delete from lecture where number = ?";

	        try {
	        	
	            pstmt = con.prepareStatement(sql);
	            pstmt.setInt(1, Integer.parseInt(tf_num.getText()));
	            int check = pstmt.executeUpdate();
	            
	            if(check == 1)
	            	ta_state.setText(Integer.parseInt(tf_num.getText()) + "�� ���������� �����Ǿ����ϴ�.");
	            else
	            	ta_state.setText("������ �����ϴ�.");
	        } catch (SQLException e1) {
	        	if(e1.getErrorCode()==1451) {
	        		ta_state.setText("�а����ǰ����������� ������ �������ּ���.");
	        	}System.out.println(e1.getErrorCode());
	            System.out.println(e1);
	        } catch (Exception e1) {
	        	ta_state.setText("�������� �����Դϴ�.");
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
	            	ta_state.setText("��ȣ�� �ùٸ��� �Է����ּ���.");
	            else
	            	ta_state.setText(Integer.parseInt(tf_num.getText()) + "�� ���������� �����Ǿ����ϴ�.");
	            
	            
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
	        	ta_state.setText("�ʼ� ������ ��� �Է����ּ���.");
	        }


	    }
	         
	    closeDB();
	}
}
