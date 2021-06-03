

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
		   "INSERT INTO Customer VALUES (1, '박지성', '영국 맨체스타', '000-5000-0001')",
		   "INSERT INTO Customer VALUES (2, '김연아', '대한민국 서울', '000-6000-0001')",
		   "INSERT INTO Customer VALUES (3, '장미란', '대한민국 강원도', '000-7000-0001')",
		   "INSERT INTO Customer VALUES (4, '추신수', '미국 클리블랜드', '000-8000-0001')",
		   "INSERT INTO Customer VALUES (5, '박세리', '대한민국 대전',  NULL)"
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
			"INSERT INTO Book VALUES(1, '축구의 역사', '굿스포츠', 7000)",
			"INSERT INTO Book VALUES(2, '축구아는 여자', '나무수', 13000)",
			"INSERT INTO Book VALUES(3, '축구의 이해', '대한미디어', 22000)",
			"INSERT INTO Book VALUES(4, '골프 바이블', '대한미디어', 35000)",
			"INSERT INTO Book VALUES(5, '피겨 교본', '굿스포츠', 8000)",
			"INSERT INTO Book VALUES(6, '역도 단계별기술', '굿스포츠', 6000)",
			"INSERT INTO Book VALUES(7, '야구의 추억', '이상미디어', 20000)",
			"INSERT INTO Book VALUES(8, '야구를 부탁해', '이상미디어', 13000)",
			"INSERT INTO Book VALUES(9, '올림픽 이야기', '삼성당', 7500)",
			"INSERT INTO Book VALUES(10, 'Olympic Champions', 'Pearson', 13000)"
	};
	
	JLabel orderID, custID, bookID, salePrice, orderDate;
	JTextField orderIDTF, custIDTF, bookIDTF, salePriceTF, orderDateTF;
	JButton btnOk, btnReset, btnOrders, btnCustomers, btnAddOrder, btnInit, btnNotJisung, btnJisungPublisherCounts, btnJisungBuyBookCounts;
   JTextArea txtResult, txtStatus;
   JPanel pn1;
   boolean errCustomerFK=false, errBookFK=false, errOrderIDPK=false;

   static Connection con;
   PreparedStatement pstmt = null; // SQL문을 DB에 보내기 위한 객체
   Statement stmt;
   ResultSet rs;
   String Driver = "";
   String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
   String userid = "madang";
   String pwd = "madang";

   public JC16010965M1() {
      super("16010965/윤창민");
      layInit();
      conDB(); //JDBC 코드
      setVisible(true);
      setBounds(200, 200, 400, 900); //가로위치,세로위치,가로길이,세로길이
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   public void layInit() {
      btnOk = new JButton("select * from book");
      btnReset = new JButton("화면초기화");
      btnJisungBuyBookCounts = new JButton("지성이 구매한 도서의 수");
      
      txtResult = new JTextArea();
      txtStatus = new JTextArea(5,30);
      


      //패널에 add
      pn1 = new JPanel();
      pn1.setPreferredSize(new Dimension(400,300));

     
   }

   public void conDB() {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         //System.out.println("드라이버 로드 성공");
         txtStatus.append("드라이버 로드 성공...\n");
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
      
      try { /* 데이터베이스를 연결하는 과정 */
          //System.out.println("데이터베이스 연결 준비...");
    	  txtStatus.append("데이터베이스 연결 준비...\n");
          con = DriverManager.getConnection(url, userid, pwd);
          //System.out.println("데이터베이스 연결 성공");
          txtStatus.append("데이터베이스 연결 성공...\n");
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
        	 
        	 txtResult.setText("Order가 추가되었습니다.");
         
        	 }catch(NumberFormatException e5) { // 정보 입력 안했을 떄 
        		 str += "정보를 모두 입력해주세요";
        		 txtResult.setText(str);

        		 System.out.print("정보를 입력해주세요\n" );         
        	 }catch(SQLException e3) {
        		boolean errBookFK=false, errcustomerFK=false;
        		  
        		 if(e3.getErrorCode() == 1062) { // Duplicate PrimaryKey 오류코드
        			 str += "중복된 Primary Key입니다.(OrderID)\n";
        			 
        			 query = "SELECT custid from customer";
        			 rs = stmt.executeQuery(query);
        			 while (rs.next()) { // BookFK 검증
        				 if(rs.getInt(1) == Integer.parseInt(custIDTF.getText().toString())) {
        					 errcustomerFK = true;
        				 }
        			 }
        			 if(errcustomerFK == false)
        				 str += "입력 CustID에 해당하는 참조키가 없습니다.\n";
        			 /////////////////////////////////////////////////// Customer ID 검사
        			 
        			 query = "SELECT bookid from book";
        			 rs = stmt.executeQuery(query);
        			 while (rs.next()) { // BookFK 검증
        				 if(rs.getInt(1) == Integer.parseInt(bookIDTF.getText().toString())) {
        					 errBookFK = true;
        				 }
        			 }
        			 if(errBookFK == false)
        				 str += "입력 BookID에 해당하는 참조키가 없습니다.\n";
        			 ////////////////////////////////////////////////// Book ID 검사
        			 

        			 txtResult.setText(str);
        		 }else if(e3.getErrorCode() == 1452) { // 외래키 참조 오류코드
        			 query = "SELECT custid from customer";
        			 rs = stmt.executeQuery(query);
        			 while (rs.next()) { // BookFK 검증
        				 if(rs.getInt(1) == Integer.parseInt(custIDTF.getText().toString())) {
        					 errcustomerFK = true;
        				 }
        			 }
        			 if(errcustomerFK == false)
        				 str += "입력 CustID에 해당하는 참조키가 없습니다.\n";
        			 /////////////////////////////////////////////////// Customer ID 검사
        			 
        			 query = "SELECT bookid from book";
        			 rs = stmt.executeQuery(query);
        			 while (rs.next()) { // BookFK 검증
        				 if(rs.getInt(1) == Integer.parseInt(bookIDTF.getText().toString())) {
        					 errBookFK = true;
        				 }
        			 }
        			 if(errBookFK == false)
        				 str += "입력 BookID에 해당하는 참조키가 없습니다.\n";
        			 ////////////////////////////////////////////////// Book ID 검사
        			 
        			 
        			 
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
        				 str += "중복된 Primary Key입니다.(OrderID)\n";
        			 /////////////////////////////////////////////////// Order ID 검사
        			 
        			 
        			 query = "SELECT custid from customer";
        			 rs = stmt.executeQuery(query);
        			 while (rs.next()) { // BookFK 검증
        				 if(rs.getInt(1) == Integer.parseInt(custIDTF.getText().toString())) {
        					 errcustomerFK = true;
        				 }
        			 }
        			 if(errcustomerFK == false)
        				 str += "입력 CustID에 해당하는 참조키가 없습니다.\n";
        			 /////////////////////////////////////////////////// Customer ID 검사
        			 
        			 query = "SELECT bookid from book";
        			 rs = stmt.executeQuery(query);
        			 while (rs.next()) { // BookFK 검증
        				 if(rs.getInt(1) == Integer.parseInt(bookIDTF.getText().toString())) {
        					 errBookFK = true;
        				 }
        			 }
        			 if(errBookFK == false)
        				 str += "입력 BookID에 해당하는 참조키가 없습니다.\n";
        			 ////////////////////////////////////////////////// Book ID 검사
        			 
        			 
        			 str += "OrderDate값을 확인해주세요 (입력값 : YYMMDD)";
        			 
        			 
        			 
        			 
        			 txtResult.setText(str);
        		 }
        		 
        		 System.out.print(e3.getErrorCode());
        	 }catch(Exception e7) {
        		 System.out.print(e7);
        	 }
        	 
             errOrderIDPK = false;
             
             
             //////////////////////////////////////////////////////
         }else if(e.getSource() == btnInit) { //초기화
        	 String query = "DELETE FROM Orders "; 
        	 stmt.executeUpdate(query); // Orders 테이블 레코드 삭제
        	 query = "DELETE FROM Customer";
        	 stmt.executeUpdate(query); // customer 테이블 레코드 삭제
        	 query = "DELETE FROM book";
        	 stmt.executeUpdate(query); // book 테이블 레코드 삭제
        	 
        	 for(int i=0; i<customerInit.length; i++) { // Customer 테이블  만들고 데이터 넣기
        		 query = customerInit[i];
        		 stmt.executeUpdate(query);
        	 }
        	 
        	 for(int i=0; i<bookInit.length; i++) { // book 테이블 만들고 데이터 넣기
        		 query = bookInit[i];
        		 stmt.executeUpdate(query);
        	 }
        	 for(int i=0; i<ordersInit.length; i++) { // orders 테이블 만들고 데이터 넣기
        		 query = ordersInit[i];
        		 stmt.executeUpdate(query);
        	 }
        	 
        	 txtResult.setText("DB가 초기화 되었습니다.");
         }else if(e.getSource() == btnNotJisung) {
        	 String query = "SELECT bookname FROM book WHERE NOT EXISTS"
        	 		+ "( SELECT bookname FROM orders WHERE orders.bookid = book.bookid AND "
        	 		+ "orders.custid = (SELECT custid FROM customer WHERE NAME ='박지성'))"; 
        	 rs = stmt.executeQuery(query);
        	 
        	 txtResult.setText("");

        	 String str= "query\n" + "SELECT bookname FROM book WHERE NOT EXISTS\n"
         	 		+ "( SELECT bookname FROM orders WHERE orders.bookid = book.bookid AND\n "
         	 		+ "orders.custid = (SELECT custid FROM customer WHERE NAME ='박지성'))\n" + "\n\n";
         	 str =str+ "지성이가 구매하지 않은 책의 이름\n";
             txtResult.append(str);
             
             while (rs.next()) {
                str = rs.getString(1) + "\n" ;
                txtResult.append(str);
             } 
             
         }else if(e.getSource() == btnJisungPublisherCounts) {
        	 txtResult.setText("");
        	 
        	 String query = "SELECT count(distinct publisher) FROM book "
        	 		+ "WHERE bookid IN (SELECT bookid FROM orders "
        	 		+ "WHERE custid = (SELECT custid FROM customer WHERE name ='박지성'))";
        	 
        	 rs= stmt.executeQuery(query);
        	 rs.next();
        	 
        	 String str="query\n" +  "SELECT count(distinct publisher) FROM book\n "
         	 		+ "WHERE bookid IN (SELECT bookid FROM orders \n"
         	 		+ "WHERE custid = (SELECT custid FROM customer WHERE name ='박지성'))\n" + "\n\n";
        	 str = str + "지성이가 구매한  출판의 숫자\n" + rs.getLong(1) + "개\n";
        	 txtResult.setText(str);
         
         }else if(e.getSource() == btnJisungBuyBookCounts) {
        	 txtResult.setText("");
        	 
        	 String query = "SELECT COUNT(*) FROM orders "
        	 		+ "WHERE orders.custid = 1";
        	 rs= stmt.executeQuery(query);
        	 rs.next();
        	 String str= "query\n" + query + "\n\n";
        	 
        	 str = str + "지성이가 구매한  도서의 숫자\n" + rs.getLong(1) + "권\n";
        	 txtResult.setText(str);
         }
      } catch (Exception e2) {
         System.out.println("쿼리 읽기 실패 :" + e2); 
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
    		System.out.println("프로그램 완전 종료!");
    		System.exit(0);
    	  }
    	});
   }
}
