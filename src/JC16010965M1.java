

import java.awt.Dimension;
import java.awt.event.*;
//import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;


public class JC16010965M1 extends JFrame implements ActionListener {
   String customerInit[] = {
		   "INSERT INTO Customer VALUES (1, '������', '���� ��ü��Ÿ', '000-5000-0001')",
		   "INSERT INTO Customer VALUES (2, '�迬��', '���ѹα� ����', '000-6000-0001')",
		   "INSERT INTO Customer VALUES (3, '��̶�', '���ѹα� ������', '000-7000-0001')",
		   "INSERT INTO Customer VALUES (4, '�߽ż�', '�̱� Ŭ������', '000-8000-0001')",
		   "INSERT INTO Customer VALUES (5, '�ڼ���', '���ѹα� ����',  NULL)"
   };
	String ordersInit[] = {
			"INSERT INTO Orders VALUES (1, 1, 1, 6000, STR_TO_DATE('2014-07-01','%Y-%m-%d'))",
			"INSERT INTO Orders VALUES (2, 1, 3, 21000, STR_TO_DATE('2014-07-03','%Y-%m-%d'))",
			"INSERT INTO Orders VALUES (3, 2, 5, 8000, STR_TO_DATE('2014-07-03','%Y-%m-%d'))",
			"INSERT INTO Orders VALUES (4, 3, 6, 6000, STR_TO_DATE('2014-07-04','%Y-%m-%d'))",
			"INSERT INTO Orders VALUES (5, 4, 7, 20000, STR_TO_DATE('2014-07-05','%Y-%m-%d'))",
			"INSERT INTO Orders VALUES (6, 1, 2, 12000, STR_TO_DATE('2014-07-07','%Y-%m-%d'))",
			"INSERT INTO Orders VALUES (7, 4, 8, 13000, STR_TO_DATE( '2014-07-07','%Y-%m-%d'))",
			"INSERT INTO Orders VALUES (8, 3, 10, 12000, STR_TO_DATE('2014-07-08','%Y-%m-%d')) ",
			"INSERT INTO Orders VALUES (9, 2, 10, 7000, STR_TO_DATE('2014-07-09','%Y-%m-%d'))",
			"INSERT INTO Orders VALUES (10, 3, 8, 13000, STR_TO_DATE('2014-07-10','%Y-%m-%d'))"
			};
	String bookInit[]  = {
			"INSERT INTO Book VALUES(1, '�౸�� ����', '�½�����', 7000)",
			"INSERT INTO Book VALUES(2, '�౸�ƴ� ����', '������', 13000)",
			"INSERT INTO Book VALUES(3, '�౸�� ����', '���ѹ̵��', 22000)",
			"INSERT INTO Book VALUES(4, '���� ���̺�', '���ѹ̵��', 35000)",
			"INSERT INTO Book VALUES(5, '�ǰ� ����', '�½�����', 8000)",
			"INSERT INTO Book VALUES(6, '���� �ܰ躰���', '�½�����', 6000)",
			"INSERT INTO Book VALUES(7, '�߱��� �߾�', '�̻�̵��', 20000)",
			"INSERT INTO Book VALUES(8, '�߱��� ��Ź��', '�̻�̵��', 13000)",
			"INSERT INTO Book VALUES(9, '�ø��� �̾߱�', '�Ｚ��', 7500)",
			"INSERT INTO Book VALUES(10, 'Olympic Champions', 'Pearson', 13000)"
	};
	
	JLabel orderID, custID, bookID, salePrice, orderDate;
	JTextField orderIDTF, custIDTF, bookIDTF, salePriceTF, orderDateTF;
	JButton btnOk, btnReset, btnOrders, btnCustomers, btnAddOrder, btnInit, btnNotJisung, btnJisungPublisherCounts, btnJisungBuyBookCounts;
   JTextArea txtResult, txtStatus;
   JPanel pn1;
   boolean errCustomerFK=false, errBookFK=false, errOrderIDPK=false;

   static Connection con;
   PreparedStatement pstmt = null; // SQL���� DB�� ������ ���� ��ü
   Statement stmt;
   ResultSet rs;
   String Driver = "";
   String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
   String userid = "madang";
   String pwd = "madang";

   public JC16010965M1() {
      super("16010965/��â��");
      layInit();
      conDB(); //JDBC �ڵ�
      setVisible(true);
      setBounds(200, 200, 400, 900); //������ġ,������ġ,���α���,���α���
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   public void layInit() {
      btnOk = new JButton("select * from book");
      btnReset = new JButton("ȭ���ʱ�ȭ");
      btnJisungBuyBookCounts = new JButton("������ ������ ������ ��");
      
      txtResult = new JTextArea();
      txtStatus = new JTextArea(5,30);
      


      //�гο� add
      pn1 = new JPanel();
      pn1.setPreferredSize(new Dimension(400,300));

     
   }

   public void conDB() {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         //System.out.println("����̹� �ε� ����");
         txtStatus.append("����̹� �ε� ����...\n");
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
      
      try { /* �����ͺ��̽��� �����ϴ� ���� */
          //System.out.println("�����ͺ��̽� ���� �غ�...");
    	  txtStatus.append("�����ͺ��̽� ���� �غ�...\n");
          con = DriverManager.getConnection(url, userid, pwd);
          //System.out.println("�����ͺ��̽� ���� ����");
          txtStatus.append("�����ͺ��̽� ���� ����...\n");
       } catch (SQLException e1) {
          e1.printStackTrace();
       }
   }

   @Override
   public void actionPerformed(ActionEvent e) {
    
      try {
         stmt = con.createStatement();
         if (e.getSource() == btnOk) {

            String query = "SELECT * FROM Book ";
            txtResult.setText("");
            txtResult.setText("BOOKNO           BOOK NAME       PUBLISHER      PRICE\n");
            rs = stmt.executeQuery(query);
            while (rs.next()) {
               String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getInt(4)
                     + "\n";
               txtResult.append(str);
            }
         } else if (e.getSource() == btnReset) {
            txtResult.setText("");
         } else if (e.getSource() == btnOrders) {
        	 String query = "SELECT * FROM orders ";
             txtResult.setText("");
             txtResult.setText("ORDERID          CUSTID              BOOKID             SALEPRICE         ORDERDATE\n");
             rs = stmt.executeQuery(query);
             while (rs.next()) {
                String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getInt(4) + "\t" + rs.getString(5)
                      + "\n";
                txtResult.append(str);
             } 
         } else if (e.getSource() == btnCustomers) {
        	 String query = "SELECT * FROM customer ";
             txtResult.setText("");
             txtResult.setText("CUSTID              NAME                  ADDRESS                PHONE\n");
             rs = stmt.executeQuery(query);
           
             while (rs.next()) {
                String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t     " + rs.getString(4)
                      + "\n";
                txtResult.append(str);
             }
         } else if (e.getSource() == btnAddOrder) {
        	 String str = "";
        	 
             
        	 String query = "INSERT INTO orders(orderid, custid, bookid, saleprice, orderdate) values(?, ?, ?, ?, ?)";
        	 
        	 try {
        	 pstmt = con.prepareStatement(query);
        	 pstmt.setInt(1, Integer.parseInt(orderIDTF.getText().toString()));
        	 pstmt.setInt(2, Integer.parseInt(custIDTF.getText().toString()));
        	 pstmt.setInt(3, Integer.parseInt(bookIDTF.getText().toString()));
        	 pstmt.setInt(4, Integer.parseInt(salePriceTF.getText().toString()));
        	 pstmt.setString(5, orderDateTF.getText().toString());
        	 
        	 pstmt.executeUpdate();
        	 
        	 orderIDTF.setText("");
        	 custIDTF.setText("");
        	 bookIDTF.setText("");
        	 salePriceTF.setText("");
        	 orderDateTF.setText("");
        	 
        	 txtResult.setText("Order�� �߰��Ǿ����ϴ�.");
         
        	 }catch(NumberFormatException e5) { // ���� �Է� ������ �� 
        		 str += "������ ��� �Է����ּ���";
        		 txtResult.setText(str);

        		 System.out.print("������ �Է����ּ���\n" );         
        	 }catch(SQLException e3) {
        		boolean errBookFK=false, errcustomerFK=false;
        		  
        		 if(e3.getErrorCode() == 1062) { // Duplicate PrimaryKey �����ڵ�
        			 str += "�ߺ��� Primary Key�Դϴ�.(OrderID)\n";
        			 
        			 query = "SELECT custid from customer";
        			 rs = stmt.executeQuery(query);
        			 while (rs.next()) { // BookFK ����
        				 if(rs.getInt(1) == Integer.parseInt(custIDTF.getText().toString())) {
        					 errcustomerFK = true;
        				 }
        			 }
        			 if(errcustomerFK == false)
        				 str += "�Է� CustID�� �ش��ϴ� ����Ű�� �����ϴ�.\n";
        			 /////////////////////////////////////////////////// Customer ID �˻�
        			 
        			 query = "SELECT bookid from book";
        			 rs = stmt.executeQuery(query);
        			 while (rs.next()) { // BookFK ����
        				 if(rs.getInt(1) == Integer.parseInt(bookIDTF.getText().toString())) {
        					 errBookFK = true;
        				 }
        			 }
        			 if(errBookFK == false)
        				 str += "�Է� BookID�� �ش��ϴ� ����Ű�� �����ϴ�.\n";
        			 ////////////////////////////////////////////////// Book ID �˻�
        			 

        			 txtResult.setText(str);
        		 }else if(e3.getErrorCode() == 1452) { // �ܷ�Ű ���� �����ڵ�
        			 query = "SELECT custid from customer";
        			 rs = stmt.executeQuery(query);
        			 while (rs.next()) { // BookFK ����
        				 if(rs.getInt(1) == Integer.parseInt(custIDTF.getText().toString())) {
        					 errcustomerFK = true;
        				 }
        			 }
        			 if(errcustomerFK == false)
        				 str += "�Է� CustID�� �ش��ϴ� ����Ű�� �����ϴ�.\n";
        			 /////////////////////////////////////////////////// Customer ID �˻�
        			 
        			 query = "SELECT bookid from book";
        			 rs = stmt.executeQuery(query);
        			 while (rs.next()) { // BookFK ����
        				 if(rs.getInt(1) == Integer.parseInt(bookIDTF.getText().toString())) {
        					 errBookFK = true;
        				 }
        			 }
        			 if(errBookFK == false)
        				 str += "�Է� BookID�� �ش��ϴ� ����Ű�� �����ϴ�.\n";
        			 ////////////////////////////////////////////////// Book ID �˻�
        			 
        			 
        			 
        			 txtResult.setText(str);
        		 }else {
        			 
        			 query = "SELECT orderid FROM orders ";
        			 rs = stmt.executeQuery(query);
        			 while(rs.next()) {
        				 if(rs.getInt(1) == Integer.parseInt(orderIDTF.getText().toString())) {
        					 errOrderIDPK = true;
        				 }
        			 }
        			 if(errOrderIDPK == true)
        				 str += "�ߺ��� Primary Key�Դϴ�.(OrderID)\n";
        			 /////////////////////////////////////////////////// Order ID �˻�
        			 
        			 
        			 query = "SELECT custid from customer";
        			 rs = stmt.executeQuery(query);
        			 while (rs.next()) { // BookFK ����
        				 if(rs.getInt(1) == Integer.parseInt(custIDTF.getText().toString())) {
        					 errcustomerFK = true;
        				 }
        			 }
        			 if(errcustomerFK == false)
        				 str += "�Է� CustID�� �ش��ϴ� ����Ű�� �����ϴ�.\n";
        			 /////////////////////////////////////////////////// Customer ID �˻�
        			 
        			 query = "SELECT bookid from book";
        			 rs = stmt.executeQuery(query);
        			 while (rs.next()) { // BookFK ����
        				 if(rs.getInt(1) == Integer.parseInt(bookIDTF.getText().toString())) {
        					 errBookFK = true;
        				 }
        			 }
        			 if(errBookFK == false)
        				 str += "�Է� BookID�� �ش��ϴ� ����Ű�� �����ϴ�.\n";
        			 ////////////////////////////////////////////////// Book ID �˻�
        			 
        			 
        			 str += "OrderDate���� Ȯ�����ּ��� (�Է°� : YYMMDD)";
        			 
        			 
        			 
        			 
        			 txtResult.setText(str);
        		 }
        		 
        		 System.out.print(e3.getErrorCode());
        	 }catch(Exception e7) {
        		 System.out.print(e7);
        	 }
        	 
             errOrderIDPK = false;
             
             
             //////////////////////////////////////////////////////
         }else if(e.getSource() == btnInit) { //�ʱ�ȭ
        	 String query = "DELETE FROM Orders "; 
        	 stmt.executeUpdate(query); // Orders ���̺� ���ڵ� ����
        	 query = "DELETE FROM Customer";
        	 stmt.executeUpdate(query); // customer ���̺� ���ڵ� ����
        	 query = "DELETE FROM book";
        	 stmt.executeUpdate(query); // book ���̺� ���ڵ� ����
        	 
        	 for(int i=0; i<customerInit.length; i++) { // Customer ���̺�  ����� ������ �ֱ�
        		 query = customerInit[i];
        		 stmt.executeUpdate(query);
        	 }
        	 
        	 for(int i=0; i<bookInit.length; i++) { // book ���̺� ����� ������ �ֱ�
        		 query = bookInit[i];
        		 stmt.executeUpdate(query);
        	 }
        	 for(int i=0; i<ordersInit.length; i++) { // orders ���̺� ����� ������ �ֱ�
        		 query = ordersInit[i];
        		 stmt.executeUpdate(query);
        	 }
        	 
        	 txtResult.setText("DB�� �ʱ�ȭ �Ǿ����ϴ�.");
         }else if(e.getSource() == btnNotJisung) {
        	 String query = "SELECT bookname FROM book WHERE NOT EXISTS"
        	 		+ "( SELECT bookname FROM orders WHERE orders.bookid = book.bookid AND "
        	 		+ "orders.custid = (SELECT custid FROM customer WHERE NAME ='������'))"; 
        	 rs = stmt.executeQuery(query);
        	 
        	 txtResult.setText("");

        	 String str= "query\n" + "SELECT bookname FROM book WHERE NOT EXISTS\n"
         	 		+ "( SELECT bookname FROM orders WHERE orders.bookid = book.bookid AND\n "
         	 		+ "orders.custid = (SELECT custid FROM customer WHERE NAME ='������'))\n" + "\n\n";
         	 str =str+ "�����̰� �������� ���� å�� �̸�\n";
             txtResult.append(str);
             
             while (rs.next()) {
                str = rs.getString(1) + "\n" ;
                txtResult.append(str);
             } 
             
         }else if(e.getSource() == btnJisungPublisherCounts) {
        	 txtResult.setText("");
        	 
        	 String query = "SELECT count(distinct publisher) FROM book "
        	 		+ "WHERE bookid IN (SELECT bookid FROM orders "
        	 		+ "WHERE custid = (SELECT custid FROM customer WHERE name ='������'))";
        	 
        	 rs= stmt.executeQuery(query);
        	 rs.next();
        	 
        	 String str="query\n" +  "SELECT count(distinct publisher) FROM book\n "
         	 		+ "WHERE bookid IN (SELECT bookid FROM orders \n"
         	 		+ "WHERE custid = (SELECT custid FROM customer WHERE name ='������'))\n" + "\n\n";
        	 str = str + "�����̰� ������  ������ ����\n" + rs.getLong(1) + "��\n";
        	 txtResult.setText(str);
         
         }else if(e.getSource() == btnJisungBuyBookCounts) {
        	 txtResult.setText("");
        	 
        	 String query = "SELECT COUNT(*) FROM orders "
        	 		+ "WHERE orders.custid = 1";
        	 rs= stmt.executeQuery(query);
        	 rs.next();
        	 String str= "query\n" + query + "\n\n";
        	 
        	 str = str + "�����̰� ������  ������ ����\n" + rs.getLong(1) + "��\n";
        	 txtResult.setText(str);
         }
      } catch (Exception e2) {
         System.out.println("���� �б� ���� :" + e2); 
/*      } finally {
         try {
            if (rs != null)
               rs.close();
            if (stmt != null)
               stmt.close();
            if (con != null)
               con.close();
         } catch (Exception e3) {
            // TODO: handle exception
         }
  */
      }

   }

   public static void main(String[] args) {
	   JC16010965M1 BLS = new JC16010965M1();
      
      //BLS.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      //BLS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      BLS.addWindowListener(new WindowAdapter() {
    	  public void windowClosing(WindowEvent we) {
    		try {
    			con.close();
    		} catch (Exception e4) { 	}
    		System.out.println("���α׷� ���� ����!");
    		System.exit(0);
    	  }
    	});
   }
}
