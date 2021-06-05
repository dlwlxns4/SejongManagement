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

public class Manage_Circles extends JFrame implements ActionListener{
	JLabel number,name, member_number, chairman, professor, office, information_del, information_update; 
    JTextField tf_number,tf_name,tf_member_number, tf_chairman, tf_professor, tf_office;
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
    
    public Manage_Circles() {
		 super("���Ƹ�����");

		  
		  pn=new JPanel();
		  pn_btn = new JPanel();
		  pn_inf = new JPanel();
		  pn_inf.setPreferredSize(new Dimension(500,55));
		  
		  
		  number = new JLabel("���Ƹ���ȣ(�ʼ�)");
	      name=new JLabel("���Ƹ��̸�(�ʼ�)");
	      member_number=new JLabel("�Ҽ��л�����(�ʼ�)");
	      chairman=new JLabel("ȸ���л�����(�ʼ�)");
	      professor=new JLabel("���Ƹ�������(�ʼ�)");
	      office=new JLabel("���Ƹ���(�ʼ�)");
	      information_del = new JLabel("�����ϴ� ��� ������ ȸ�� ��ȣ�� �Է��ϸ� �����Ϸ�");
	      information_update = new JLabel("�����ϴ� ��� �л� ��ȣ �Է��� ��� �ʼ� ���� �Է��ϸ� �����Ϸ�");
	      
	      pn_inf.add(information_del);
	      pn_inf.add(information_update);
	      
	      tf_number=new JTextField();
	      tf_name=new JTextField();
	      tf_member_number=new JTextField();
	      tf_chairman=new JTextField();
	      tf_professor=new JTextField();
	      tf_office=new JTextField();
	      btn_input=new JButton("�Է�");
	      btn_del=new JButton("����");
	      btn_update=new JButton("����");
	      
	      pn.setLayout(new GridLayout(7,2));
		  pn_btn.setLayout(new GridLayout(1,3));
		   
		  pn.add(number);	   
		  pn.add(tf_number);
		   
		  pn.add(name);	
		  pn.add(tf_name);
		   
		  pn.add(member_number);
		  pn.add(tf_member_number);	
		   
		  pn.add(chairman);	   
		  pn.add(tf_chairman);
		   
		  pn.add(professor);	
		  pn.add(tf_professor);
		   
		  pn.add(office);
		  pn.add(tf_office);
	      
		  
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
		    	sql = "insert into circles(number, name, s_num, chairman_num, professor, office) value(?, ?, ?, ?, ?, ?)";
		    	
		    	conDB();
		    	try {  
		    		pstmt = con.prepareStatement(sql);
		    		
		    		if(tf_number.getText().length()!=0)
		    			pstmt.setInt(1, Integer.parseInt(tf_number.getText()));
		    		if(tf_name.getText().length()!=0)
		    			pstmt.setString(2, tf_name.getText());
		    		if(tf_member_number.getText().length()!=0)
		    			pstmt.setInt(3, Integer.parseInt(tf_member_number.getText()) );
		    		if(tf_chairman.getText().length()!=0)
		    			pstmt.setInt(4, Integer.parseInt(tf_chairman.getText()) );
		    		if(tf_professor.getText().length()!=0)
		    			pstmt.setInt(5, Integer.parseInt(tf_professor.getText()) );
		    		if(tf_office.getText().length()!=0)
		    			pstmt.setString(6, tf_office.getText());
		            pstmt.executeUpdate();
		            

		            ta_state.setText("���Ƹ� ������ �ԷµǾ����ϴ�.");
		           	            
		            tf_number.setText("");
		            tf_name.setText("");
		            tf_member_number.setText("");
		            tf_chairman.setText("");
		            tf_professor.setText("");
		            tf_office.setText("");
		            
		            
		         }catch (SQLException e1) {
		        	 if(e1.getErrorCode() == 1062)
		        		 ta_state.setText("�ߺ��� ���Ƹ� ��ȣ�Դϴ�.");
		        	 
		              
		             else {
		            	   ta_state.setText("�ʼ� ������ ��� �Է����ּ���.");
		               }
		         }catch(Exception e1) {
		        	 System.out.println(e1.toString());
		         }
		    	
		    }else if(e.getSource() == btn_del) {
		    	conDB();
		        sql = "delete from circles where number = ?";

		        try {
		        	
		            pstmt = con.prepareStatement(sql);
		            pstmt.setInt(1, Integer.parseInt(tf_number.getText()));
		            int check = pstmt.executeUpdate();
		            
		            if(check == 1)
		            	ta_state.setText(Integer.parseInt(tf_number.getText()) + "�� ���Ƹ� ������ �����Ǿ����ϴ�.");
		            else
		            	ta_state.setText("������ �����ϴ�.");
		        } catch (SQLException e1) {
		            System.out.println(e1.getErrorCode());
		        } catch (Exception e1) {
		        	ta_state.setText("�������� �����Դϴ�.");
		        }
		        

		        
		        tf_number.setText("");
	            tf_name.setText("");
	            tf_member_number.setText("");
	            tf_chairman.setText("");
	            tf_professor.setText("");
	            tf_office.setText("");


		    }else if(e.getSource() == btn_update) {
		    	conDB();
		        sql = "update circles set name = ?, s_num = ?, chairman_num = ?, professor = ?, office = ? where number = ?";
		        
		        
		        try {
		        	
		        	
		            pstmt = con.prepareStatement(sql);

		            if(tf_number.getText().length()!=0)
		    			pstmt.setInt(6, Integer.parseInt(tf_number.getText()));
		    		if(tf_name.getText().length()!=0)
		    			pstmt.setString(1, tf_name.getText());
		    		if(tf_member_number.getText().length()!=0)
		    			pstmt.setInt(2, Integer.parseInt(tf_member_number.getText()) );
		    		if(tf_chairman.getText().length()!=0)
		    			pstmt.setInt(3, Integer.parseInt(tf_chairman.getText()) );
		    		if(tf_professor.getText().length()!=0)
		    			pstmt.setInt(4, Integer.parseInt(tf_professor.getText()) );
		    		if(tf_office.getText().length()!=0)
		    			pstmt.setString(5, tf_office.getText());
		            
		            int check = pstmt.executeUpdate();
		            System.out.println(check);
		            if(check == 0)
		            	ta_state.setText("��ȣ�� �ùٸ��� �Է����ּ���.");
		            else
		            	ta_state.setText(Integer.parseInt(tf_number.getText()) + "�� ���Ƹ� ������ �����Ǿ����ϴ�.");
		            
		            
			        tf_number.setText("");
		            tf_name.setText("");
		            tf_member_number.setText("");
		            tf_chairman.setText("");
		            tf_professor.setText("");
		            tf_office.setText("");

		            

		        } catch (SQLException e1) {
		        	System.out.println(e1.getErrorCode());
		        		ta_state.setText("�ʼ� ������ ��� �Է����ּ���.");
		        		System.out.println(e1);
		        }


		    }
		         
		    closeDB();
		}
}
