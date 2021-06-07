package Admin;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class Admin_Table_LookUp extends JFrame implements ActionListener{
	JPanel pn; 
	JButton btn_student, btn_professor, btn_lecture, btn_circle, btn_department, btn_init, btn_payment, btn_lecture_history, btn_student_professor, btn_student_circle, btn_lecture_department;
	JTextArea student_table, professor_table, lecture_table, circle_table, department_table, payment_table, lecture_history_table, student_professor_table, student_circle_table, lecture_department_table;
	JScrollPane student_scrollPane, professor_scrollPane, lecture_scrollPane, circle_scrollPane, department_scrollPane, payment_scrollPane, lecture_history_scrollPane, student_professor_scrollPane, student_circle_scrollPane, lecture_department_scrollPane;
	
	
	PreparedStatement pstmt = null; // SQL문을 DB에 보내기 위한 객체
    Statement stmt;
    ResultSet rs;
    String Driver = "";
    String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
    String userid = "madang";
    String pwd = "madang";
	Connection con;
    
    
	public Admin_Table_LookUp() {
		super("전체 테이블조회");
		layInit();
		this.setLayout(null);
		setBounds(0, 0, 1800, 1030);   
		
		
	}
	
	public void closeDB() { // 데이터 베이스 접속 종료
        try {
            stmt.close();
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
        
		  
		   
	      pn=new JPanel();
	      pn.setLayout(null);
	      pn.setBounds(0,0,1800,1350);
	      
	      
	      //student
	      btn_student = new JButton("학생조회");
	      btn_student.setBounds(10, 10, 90, 25);
	      student_table = new JTextArea();
	      student_scrollPane = new JScrollPane(student_table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      student_scrollPane.setBounds(10, 40, 600, 200);
	      
	      pn.add(student_scrollPane);
	      pn.add(btn_student);
	      btn_student.addActionListener(this);
	      
	    //professor
	      btn_professor = new JButton("교수조회");
	      btn_professor.setBounds(650, 10, 90, 25);
	      professor_table = new JTextArea();
	      professor_scrollPane = new JScrollPane(professor_table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      professor_scrollPane.setBounds(650, 40, 600, 200);
	      pn.add(professor_scrollPane);
	      pn.add(btn_professor);
	      btn_professor.addActionListener(this);
	      
	      //btn_init
	      btn_init = new JButton("DB초기화");
	      btn_init.setBounds(1350, 100, 360, 250);
	      pn.add(btn_init);
	      btn_init.addActionListener(this);
	      
	      //lecture
	      btn_lecture = new JButton("강의조회");
	      btn_lecture.setBounds(10, 245, 90, 25);
	      lecture_table = new JTextArea();
	      lecture_scrollPane = new JScrollPane(lecture_table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      lecture_scrollPane.setBounds(10, 275, 600, 200);
	      pn.add(lecture_scrollPane);
	      pn.add(btn_lecture);
	      btn_lecture.addActionListener(this);
	      
	      //circle
	      btn_circle = new JButton("동아리조회");
	      btn_circle.setBounds(650, 245, 100, 25);
	      circle_table = new JTextArea();
	      circle_scrollPane = new JScrollPane(circle_table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      circle_scrollPane.setBounds(650, 275, 600, 200);
	      pn.add(circle_scrollPane);
	      pn.add(btn_circle);
	      btn_circle.addActionListener(this);
	      
	    //Department
	      btn_department = new JButton("학과조회");
	      btn_department.setBounds(10, 480, 100, 25);
	      department_table = new JTextArea();
	      department_scrollPane = new JScrollPane(department_table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      department_scrollPane.setBounds(10, 510, 600, 200);
	      pn.add(department_scrollPane);
	      pn.add(btn_department);
	      btn_department.addActionListener(this);
	      
	    //payment
	      btn_payment = new JButton("납부내역조회");
	      btn_payment.setBounds(650, 480, 200, 25);
	      payment_table = new JTextArea();
	      payment_scrollPane = new JScrollPane(payment_table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      payment_scrollPane.setBounds(650, 510, 600, 200);
	      pn.add(payment_scrollPane);
	      pn.add(btn_payment);
	      btn_payment.addActionListener(this);
	      
	    //lecture_history
	      btn_lecture_history = new JButton("강의내력조회");
	      btn_lecture_history.setBounds(10, 715, 200, 25);
	      lecture_history_table = new JTextArea();
	      lecture_history_scrollPane = new JScrollPane(lecture_history_table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      lecture_history_scrollPane.setBounds(10, 745, 600, 200);
	      pn.add(lecture_history_scrollPane);
	      pn.add(btn_lecture_history);
	      btn_lecture_history.addActionListener(this);
	      
	      //student has professor
	      btn_student_professor = new JButton("학생교수관게조회");
	      btn_student_professor.setBounds(650, 715, 200, 25);
	      student_professor_table = new JTextArea();
	      student_professor_scrollPane = new JScrollPane(student_professor_table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      student_professor_scrollPane.setBounds(650, 745, 600, 200);
	      pn.add(student_professor_scrollPane);
	      pn.add(btn_student_professor);
	      btn_student_professor.addActionListener(this);

	    //circle has student
	      btn_student_circle = new JButton("동아리학생관계조회");
	      btn_student_circle.setBounds(1280, 480, 200, 25);
	      student_circle_table = new JTextArea();
	      student_circle_scrollPane = new JScrollPane(student_circle_table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      student_circle_scrollPane.setBounds(1280, 510, 500, 200);
	      pn.add(student_circle_scrollPane);
	      pn.add(btn_student_circle);
	      btn_student_circle.addActionListener(this);
	      
	    //lecture has department
	      btn_lecture_department = new JButton("강의학과관계조회");
	      btn_lecture_department.setBounds(1280, 715, 200, 25);
	      lecture_department_table = new JTextArea();
	      lecture_department_scrollPane = new JScrollPane(lecture_department_table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      lecture_department_scrollPane.setBounds(1280, 745, 500, 200);
	      pn.add(lecture_department_scrollPane);
	      pn.add(btn_lecture_department);
	      btn_lecture_department.addActionListener(this);


	      
	      
	      add(pn);
	      
	      pn.setVisible(true);
	      
	     
	   }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		conDB();
		try {
			if(e.getSource() == btn_student ) {
				
	
	            String query = "SELECT * FROM student";
	            stmt = con.createStatement();
	            

	            student_table.setText("");
	            student_table.setText("번호                    학생이름               주소                     전화번호            이메일                                              전공                  부전공                 지도교수                계좌정보\n");
            	
	            rs = stmt.executeQuery(query);
	            while (rs.next()) {
	               String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" +  rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getString(7) + "\t" + rs.getInt(8) + "\t"
	                     + rs.getString(9)+ "\t" +"\n";
	               student_table.append(str);
		            
		        }
			}else if(e.getSource() == btn_professor) {
				String query = "SELECT * FROM professor";
	            stmt = con.createStatement();
	            

	            professor_table.setText("");
	            professor_table.setText("번호                     이름                    주소                     번호                 이메일                                                       주전공             부전공              학생               강의\n");
            	 
	            rs = stmt.executeQuery(query);
	            while (rs.next()) {
	               String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" +  rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getString(7) + "\t" + rs.getInt(8) + "\t" + rs.getString(9)
	                     +"\n";
	               professor_table.append(str);
		            
	            }
			}else if(e.getSource() == btn_lecture) {
				String query = "SELECT * FROM lecture";
	            stmt = con.createStatement();
	            

	            lecture_table.setText("");
	            lecture_table.setText("번호                    분반                    이름                    요일                     교시                   학점                  강의시간                     부서                  강의실                  년도                  학기               교수\n");
            	  
	            rs = stmt.executeQuery(query);
	            while (rs.next()) {
	               String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" +  rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getString(7) + "\t" + rs.getString(8) + "\t"+ rs.getString(9) + "\t" + rs.getString(10) + "\t" + rs.getString(11)+ "\t" + rs.getInt(12) 
	                     +"\n";
	               lecture_table.append(str);
		            
	            }
			}else if(e.getSource() == btn_circle) {
				String query = "SELECT * FROM circles";
	            stmt = con.createStatement();
	            

	            circle_table.setText("");
	            circle_table.setText("번호                    이름                   회원 수                     회장                   지도교수                  사무실\n");
            	  
	            rs = stmt.executeQuery(query);
	            while (rs.next()) {
	               String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" +  rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6)
	                     +"\n";
	               circle_table.append(str);
		            
	            }
			}else if(e.getSource() == btn_department) {
				String query = "SELECT * FROM department";
	            stmt = con.createStatement();
	            

	            department_table.setText("");
	            department_table.setText("번호                    이름                   전화번호                 사무실                        강의                       학과장\n");
            	  
	            rs = stmt.executeQuery(query);
	            while (rs.next()) {
	               String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" +  rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getInt(5) + "\t" + rs.getInt(6)
	                     +"\n";
	               department_table.append(str);
		            
	            }
			}else if(e.getSource() == btn_payment) {
				String query = "SELECT * FROM payment";
	            stmt = con.createStatement();
	            

	            payment_table.setText("");
	            payment_table.setText("번호                    년도                      학기                    등록금                     지불금액             학생번호               마지막납부일\n");
            	  
	            rs = stmt.executeQuery(query);
	            while (rs.next()) {
	               String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" +  rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getInt(6) + "\t" + rs.getString(7) +"\n";
	               payment_table.append(str);
		            
	            }
			}else if(e.getSource()==btn_student_professor) {
				String query = "SELECT * FROM Student_has_Professor";
	            stmt = con.createStatement();
	            

	            student_professor_table.setText("");
	            student_professor_table.setText("번호                    현재학년                현재학기              학생번호              교수번호\n");
            	  
	            rs = stmt.executeQuery(query);
	            while (rs.next()) {
	               String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" +  rs.getString(3) + "\t" + rs.getInt(4) + "\t" + rs.getInt(5)  +"\n";
	               student_professor_table.append(str);
		            
	            }
			}else if(e.getSource()==btn_lecture_history) {
				String query = "SELECT * FROM lecture_history";
	            stmt = con.createStatement();
	            

	            lecture_history_table.setText("");
	            lecture_history_table.setText("번호                    출석점수               중간점수            기말점수              기타점수            총합점수               평점                년도               학시                 학생번호              교수번호               강의번호\n");
            	
	            rs = stmt.executeQuery(query);
	            while (rs.next()) {
	               String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" +  rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getInt(6) + "\t" + rs.getString(7) + "\t" + rs.getString(8) + "\t" + rs.getString(9) + "\t" + rs.getInt(10) + "\t" + rs.getInt(11) + "\t" + rs.getInt(12) +"\n";
	               lecture_history_table.append(str);
		            
	            }
			}else if(e.getSource()==btn_student_circle) {
				String query = "SELECT * FROM student_has_circles";
	            stmt = con.createStatement();
	            

	            student_circle_table.setText("");
	            student_circle_table.setText("번호                    학생번호               동아리번호\n");
            	
	            rs = stmt.executeQuery(query);
	            while (rs.next()) {
	               String str = rs.getInt(1) + "\t" + rs.getInt(2) + "\t" +  rs.getInt(3) + "\n";
	               student_circle_table.append(str);
		            
	            }
			}else if(e.getSource() == btn_lecture_department) {
				String query = "SELECT * FROM department_has_lecture";
	            stmt = con.createStatement();
	            

	            lecture_department_table.setText("");
	            lecture_department_table.setText("번호                   학과번호                강의번호\n");
            	
	            rs = stmt.executeQuery(query);
	            while (rs.next()) {

	            	String str = rs.getInt(1) + "\t" + rs.getInt(2) + "\t" +  rs.getInt(3) + "\n";
	            	lecture_department_table.append(str);
		            
	            }
			}else if(e.getSource() == btn_init) {
				stmt = con.createStatement();
	            

				System.out.println("DB초기화 시작");
				
	            stmt.execute("drop table if exists department_has_lecture, department, lecture_history, Lecture, student_has_Professor, payment,student_has_Circles, student, Circles,  Professor");
	            
	            stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`Department` (\r\n"
	            		+ "  `number` INT NOT NULL,\r\n"
	            		+ "  `name` VARCHAR(45) NOT NULL,\r\n"
	            		+ "  `phone_num` VARCHAR(45) NOT NULL,\r\n"
	            		+ "  `office` VARCHAR(45) NOT NULL,\r\n"
	            		+ "  `lecture` VARCHAR(45) NOT NULL,\r\n"
	            		+ "  `head` VARCHAR(45) NOT NULL,\r\n"
	            		+ "  PRIMARY KEY (`number`))\r\n"
	            		+ "ENGINE = InnoDB");
	            
	            stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`Professor` (\r\n"
	            		+ "  `number` INT NOT NULL,\r\n"
	            		+ "  `name` VARCHAR(45) NOT NULL,\r\n"
	            		+ "  `address` VARCHAR(45) NULL,\r\n"
	            		+ "  `phone_number` VARCHAR(45) NOT NULL,\r\n"
	            		+ "  `email` VARCHAR(45) NOT NULL,\r\n"
	            		+ "  `dep_num` INT NOT NULL,\r\n"
	            		+ "  `ddp` VARCHAR(45) NULL,\r\n"
	            		+ "  `res_student` VARCHAR(45) NOT NULL,\r\n"
	            		+ "  `lecture` VARCHAR(45) NULL,\r\n"
	            		+ "  PRIMARY KEY (`number`))\r\n"
	            		+ "ENGINE = InnoDB");
	            
	            
	            stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`Student` (\r\n"
	            		+ "  `number` INT NOT NULL,\r\n"
	            		+ "  `name` VARCHAR(45) NOT NULL,\r\n"
	            		+ "  `address` VARCHAR(45) NOT NULL,\r\n"
	            		+ "  `phone_number` VARCHAR(45) NOT NULL,\r\n"
	            		+ "  `email` VARCHAR(45) NULL,\r\n"
	            		+ "  `main_major` INT NOT NULL,\r\n"
	            		+ "  `sub_major` INT NULL,\r\n"
	            		+ "  `professor` INT NOT NULL,\r\n"
	            		+ "  `account` VARCHAR(45) NOT NULL,\r\n"
	            		+ "  PRIMARY KEY (`number`))\r\n"
	            		+ "ENGINE = InnoDB");

	            stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`Lecture` (\r\n"
	            		+ "  `number` INT NOT NULL,\r\n"
	            		+ "  `class_num` VARCHAR(45) NOT NULL,\r\n"
	            		+ "  `name` VARCHAR(45) NOT NULL,\r\n"
	            		+ "  `class_day` VARCHAR(45) NOT NULL,\r\n"
	            		+ "  `class_time` VARCHAR(45) NOT NULL,\r\n"
	            		+ "  `grade` VARCHAR(45) NOT NULL,\r\n"
	            		+ "  `lecture_time` VARCHAR(45) NOT NULL,\r\n"
	            		+ "  `department` VARCHAR(45) NOT NULL,\r\n"
	            		+ "  `office` VARCHAR(45) NOT NULL,\r\n"
	            		+ "  `year` VARCHAR(45) NULL,\r\n"
	            		+ "  `semester` VARCHAR(45) NULL,\r\n"
	            		+ "  `professor` INT NOT NULL,\r\n"
	            		+ "  PRIMARY KEY (`number`),\r\n"
	            		+ "  INDEX `fk_Lecture_Professor1_idx` (`professor` ASC) VISIBLE,\r\n"
	            		+ "  CONSTRAINT `fk_Lecture_Professor1`\r\n"
	            		+ "    FOREIGN KEY (`professor`)\r\n"
	            		+ "    REFERENCES `madang`.`Professor` (`number`)\r\n"
	            		+ "    ON DELETE NO ACTION\r\n"
	            		+ "    ON UPDATE NO ACTION)\r\n"
	            		+ "ENGINE = InnoDB");
	            
	            
	            stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`Student_has_Professor` (\r\n"
	            		+ "  `sp_num` INT NOT NULL,\r\n"
	            		+ "  `current_grade` VARCHAR(45) NULL,\r\n"
	            		+ "  `current_semester` VARCHAR(45) NULL,\r\n"
	            		+ "  `Student_s_number` INT NOT NULL,\r\n"
	            		+ "  `Professor_p_number` INT NOT NULL,\r\n"
	            		+ "  INDEX `fk_Student_has_Professor_Professor1_idx` (`Professor_p_number` ASC) VISIBLE,\r\n"
	            		+ "  INDEX `fk_Student_has_Professor_Student1_idx` (`Student_s_number` ASC) VISIBLE,\r\n"
	            		+ "  PRIMARY KEY (`sp_num`),\r\n"
	            		+ "  CONSTRAINT `fk_Student_has_Professor_Student1`\r\n"
	            		+ "    FOREIGN KEY (`Student_s_number`)\r\n"
	            		+ "    REFERENCES `madang`.`Student` (`number`)\r\n"
	            		+ "    ON DELETE NO ACTION\r\n"
	            		+ "    ON UPDATE NO ACTION,\r\n"
	            		+ "  CONSTRAINT `fk_Student_has_Professor_Professor1`\r\n"
	            		+ "    FOREIGN KEY (`Professor_p_number`)\r\n"
	            		+ "    REFERENCES `madang`.`Professor` (`number`)\r\n"
	            		+ "    ON DELETE NO ACTION\r\n"
	            		+ "    ON UPDATE NO ACTION)\r\n"
	            		+ "ENGINE = InnoDB");

	            
	            stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`lecture_history` (\r\n"
	            		+ "  `number` INT NOT NULL,\r\n"
	            		+ "  `attendance_score` VARCHAR(45) NULL,\r\n"
	            		+ "  `midterm_score` VARCHAR(45) NULL,\r\n"
	            		+ "  `final_score` VARCHAR(45) NULL,\r\n"
	            		+ "  `else_score` VARCHAR(45) NULL,\r\n"
	            		+ "  `total_score` VARCHAR(45) NULL,\r\n"
	            		+ "  `grade` VARCHAR(45) NULL,\r\n"
	            		+ "  `year` VARCHAR(45) NULL,\r\n"
	            		+ "  `semester` VARCHAR(45) NULL,\r\n"
	            		+ "  `Student_number` INT NOT NULL,\r\n"
	            		+ "  `Professor_number` INT NOT NULL,\r\n"
	            		+ "  `Lecture_number` INT NOT NULL,\r\n"
	            		+ "  PRIMARY KEY (`number`),\r\n"
	            		+ "  INDEX `fk_Record_lecture_Student1_idx` (`Student_number` ASC) VISIBLE,\r\n"
	            		+ "  INDEX `fk_Record_lecture_Professor1_idx` (`Professor_number` ASC) VISIBLE,\r\n"
	            		+ "  INDEX `fk_Record_lecture_Lecture1_idx` (`Lecture_number` ASC) VISIBLE,\r\n"
	            		+ "  CONSTRAINT `fk_Record_lecture_Student1`\r\n"
	            		+ "    FOREIGN KEY (`Student_number`)\r\n"
	            		+ "    REFERENCES `madang`.`Student` (`number`)\r\n"
	            		+ "    ON DELETE NO ACTION\r\n"
	            		+ "    ON UPDATE NO ACTION,\r\n"
	            		+ "  CONSTRAINT `fk_Record_lecture_Professor1`\r\n"
	            		+ "    FOREIGN KEY (`Professor_number`)\r\n"
	            		+ "    REFERENCES `madang`.`Professor` (`number`)\r\n"
	            		+ "    ON DELETE NO ACTION\r\n"
	            		+ "    ON UPDATE NO ACTION,\r\n"
	            		+ "  CONSTRAINT `fk_Record_lecture_Lecture1`\r\n"
	            		+ "    FOREIGN KEY (`Lecture_number`)\r\n"
	            		+ "    REFERENCES `madang`.`Lecture` (`number`)\r\n"
	            		+ "    ON DELETE NO ACTION\r\n"
	            		+ "    ON UPDATE NO ACTION)\r\n"
	            		+ "ENGINE = InnoDB");
	            
	            stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`Department_has_Lecture` (\r\n"
	            		+ "  `number` INT NOT NULL,\r\n"
	            		+ "  `Department_number` INT NOT NULL,\r\n"
	            		+ "  `Lecture_number` INT NOT NULL,\r\n"
	            		+ "  INDEX `fk_Department_has_Lecture_Lecture1_idx` (`Lecture_number` ASC) VISIBLE,\r\n"
	            		+ "  INDEX `fk_Department_has_Lecture_Department1_idx` (`Department_number` ASC) VISIBLE,\r\n"
	            		+ "  PRIMARY KEY (`number`),\r\n"
	            		+ "  CONSTRAINT `fk_Department_has_Lecture_Department1`\r\n"
	            		+ "    FOREIGN KEY (`Department_number`)\r\n"
	            		+ "    REFERENCES `madang`.`Department` (`number`)\r\n"
	            		+ "    ON DELETE NO ACTION\r\n"
	            		+ "    ON UPDATE NO ACTION,\r\n"
	            		+ "  CONSTRAINT `fk_Department_has_Lecture_Lecture1`\r\n"
	            		+ "    FOREIGN KEY (`Lecture_number`)\r\n"
	            		+ "    REFERENCES `madang`.`Lecture` (`number`)\r\n"
	            		+ "    ON DELETE NO ACTION\r\n"
	            		+ "    ON UPDATE NO ACTION)\r\n"
	            		+ "ENGINE = InnoDB");
	            
	            stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`Circles` (\r\n"
	            		+ "  `number` INT NOT NULL,\r\n"
	            		+ "  `name` VARCHAR(45) NOT NULL,\r\n"
	            		+ "  `s_num` VARCHAR(45) NULL,\r\n"
	            		+ "  `chairman_num` VARCHAR(45) NULL,\r\n"
	            		+ "  `professor` VARCHAR(45) NULL,\r\n"
	            		+ "  `office` VARCHAR(45) NULL,\r\n"
	            		+ "  PRIMARY KEY (`number`))\r\n"
	            		+ "ENGINE = InnoDB");
	            
	            

	            stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`payment` (\r\n"
	            		+ "  `number` INT NOT NULL,\r\n"
	            		+ "  `year` VARCHAR(45) NOT NULL,\r\n"
	            		+ "  `semester` VARCHAR(45) NOT NULL,\r\n"
	            		+ "  `tuition_price` VARCHAR(45) NOT NULL,\r\n"
	            		+ "  `pay_price` VARCHAR(45) NOT NULL,\r\n"
	            		+ "  `student_number` INT NOT NULL,\r\n"
	            		+ "  `last_payment_date` VARCHAR(45) NOT NULL,\r\n"
	            		+ "  PRIMARY KEY (`number`),\r\n"
	            		+ "  INDEX `fk_payment_Student1_idx` (`student_number` ASC) VISIBLE,\r\n"
	            		+ "  CONSTRAINT `fk_payment_Student1`\r\n"
	            		+ "    FOREIGN KEY (`student_number`)\r\n"
	            		+ "    REFERENCES `madang`.`Student` (`number`)\r\n"
	            		+ "    ON DELETE NO ACTION\r\n"
	            		+ "    ON UPDATE NO ACTION)\r\n"
	            		+ "ENGINE = InnoDB");
	            
	            

	            stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`Student_has_Circles` (\r\n"
	            		+ "  `sc_num` INT NOT NULL,\r\n"
	            		+ "  `Student_s_number` INT NOT NULL,\r\n"
	            		+ "  `Circles_c_number` INT NOT NULL,\r\n"
	            		+ "  INDEX `fk_Student_has_Circles_Circles1_idx` (`Circles_c_number` ASC) VISIBLE,\r\n"
	            		+ "  INDEX `fk_Student_has_Circles_Student_idx` (`Student_s_number` ASC) VISIBLE,\r\n"
	            		+ "  PRIMARY KEY (`sc_num`),\r\n"
	            		+ "  CONSTRAINT `fk_Student_has_Circles_Student`\r\n"
	            		+ "    FOREIGN KEY (`Student_s_number`)\r\n"
	            		+ "    REFERENCES `madang`.`Student` (`number`)\r\n"
	            		+ "    ON DELETE NO ACTION\r\n"
	            		+ "    ON UPDATE NO ACTION,\r\n"
	            		+ "  CONSTRAINT `fk_Student_has_Circles_Circles1`\r\n"
	            		+ "    FOREIGN KEY (`Circles_c_number`)\r\n"
	            		+ "    REFERENCES `madang`.`Circles` (`number`)\r\n"
	            		+ "    ON DELETE NO ACTION\r\n"
	            		+ "    ON UPDATE NO ACTION)\r\n"
	            		+ "ENGINE = InnoDB");

	            stmt.execute("insert into department value(1, '컴퓨터공학과', '010-0000-0001', 'A1', 1, 1)");
	            stmt.execute("insert into department value(2, '물리천문학과', '010-0000-0002', 'A2', 2, 2)");
	            stmt.execute("insert into department value(3, '건축공학과', '010-0000-0003', 'A3', 3, 3)");
	            stmt.execute("insert into department value(4, '정보보호학과', '010-0000-0004', 'A4', 4, 4)");
	            stmt.execute("insert into department value(5, '물리학과', '010-0000-0005', 'A5', 5, 5)");
	            stmt.execute("insert into department value(6, '천문학과', '010-0000-0006', 'A6', 6, 6)");
	            stmt.execute("insert into department value(7, '기계공학과', '010-0000-0007', 'A7', 7, 7 )");
	            stmt.execute("insert into department value(8, '호텔경영학과', '010-0000-0008', 'A8', 8, 8)");
	            stmt.execute("insert into department value(9, '국어국문학과', '010-0000-0009', 'A9', 9, 9)");
	            stmt.execute("insert into department value(10, '사회과학대학', '010-0000-0010', 'B1', 10, 10)");
	            stmt.execute("insert into department value(11, '경제학과', '010-0000-0011', 'B2', 11, 11)");
	            stmt.execute("insert into department value(12, '응용통계학과', '010-0000-0012', 'B3', 12, 12)");
	            stmt.execute("insert into department value(13, '화학과', '010-0000-0013', 'B4', 13, 13)");
	            stmt.execute("insert into department value(14, '인공지능학과', '010-0000-0014', 'B5', 14, 14)");
	            stmt.execute("insert into department value(15, '행정학과', '010-0000-0015', 'B6', 15, 15)");
	            stmt.execute("insert into department value(16, '역사학과', '010-0000-0016', 'B7', 16, 16)");
	            stmt.execute("insert into department value(17, '교육학과', '010-0000-0017', 'B8', 17, 17)");
	            stmt.execute("insert into department value(18, '글로벌조학과', '010-0000-0018', 'B9', 18, 18)");
	            stmt.execute("insert into department value(19, '패션디자인학과', '010-0000-0019', 'C1', 19, 19)");
	            stmt.execute("insert into department value(20, '무용과', '010-0000-0020', 'C2', 20, 20)");
	            stmt.execute("insert into department value(21, '체육학과', '010-0000-0021', 'C3', 21, 21)");
	            stmt.execute("insert into department value(22, '음악과', '010-0000-0022', 'C4', 22, 22)");
	            stmt.execute("insert into department value(23, '영어예술학과', '010-0000-0023', 'C5', 23, 23)");
	            stmt.execute("insert into department value(24, '지능기전학과', '010-0000-0024', 'C6', 24, 24)");
	            stmt.execute("insert into department value(25, '애니메이션학과', '010-0000-0025', 'C7', 25, 25)");

	            stmt.execute("insert into professor value(1, '가교수', 'ST01',  '010-0000-0000','AA@sejong.com', 1, '2', '1', '1')");
	            stmt.execute("insert into professor value(2, '나교수', 'ST02', '010-0000-0001','AB@sejong.com', 2, '3', '2', '2')");
	            stmt.execute("insert into professor value(3, '다교수', 'ST03', '010-0000-0002','AC@sejong.com', 3, '4', '3', '3')");
	            stmt.execute("insert into professor value(4, '라교수', 'ST04', '010-0000-0003','AD@sejong.com', 4, '5', '4', '4')");
	            stmt.execute("insert into professor value(5, '마교수', 'ST05', '010-0000-0004','AE@sejong.com', 5, '6', '5', '5')");
	            stmt.execute("insert into professor value(6, '바교수', 'ST06', '010-0000-0005','AF@sejong.com', 6, '7', '6', '6')");
	            stmt.execute("insert into professor value(7, '사교수', 'ST07', '010-0000-0006','AG@sejong.com', 7, '8', '7', '7')");
	            stmt.execute("insert into professor value(8, '아교수', 'ST08', '010-0000-0007','AH@sejong.com', 8, '9', '8', '8')");
	            stmt.execute("insert into professor value(9, '자교수', 'ST09', '010-0000-0008','AI@sejong.com', 9, '10', '9', '9')");
	            stmt.execute("insert into professor value(10, '차교수', 'ST10', '010-0000-0009','AJ@sejong.com', 10, '11', '10', '10')");
	            stmt.execute("insert into professor value(11, '카교수', 'ST11', '010-0000-0010','AK@sejong.com', 11, '12', '11', '11')");
	            stmt.execute("insert into professor value(12, '타교수', 'ST12', '010-0000-0011','AL@sejong.com', 12, '13', '12', '12')");
	            stmt.execute("insert into professor value(13, '하교수', 'ST13', '010-0000-0021','AM@sejong.com', 13, '14', '13', '13')");
	            stmt.execute("insert into professor value(14, '고교수', 'ST14', '010-0000-0031','AN@sejong.com', 14, '15', '14', '14')");
	            stmt.execute("insert into professor value(15, '노교수', 'ST15', '010-0000-0041','AO@sejong.com', 15, '16', '15', '15')");
	            stmt.execute("insert into professor value(16, '도교수', 'ST16', '010-0000-0051','AP@sejong.com', 16, '17', '16', '16')");
	            stmt.execute("insert into professor value(17, '로교수', 'ST17', '010-0000-0061','BA@sejong.com', 17, '18', '17', '17')");
	            stmt.execute("insert into professor value(18, '모교수', 'ST18', '010-0000-0071','BB@sejong.com', 18, '19', '18', '18')");
	            stmt.execute("insert into professor value(19, '보교수', 'ST19', '010-0000-0081','BC@sejong.com', 19, '20', '19', '19')");
	            stmt.execute("insert into professor value(20, '소교수', 'ST20', '010-0000-0091','BD@sejong.com', 20, '21', '20', '20')");
	            stmt.execute("insert into professor value(21, '오교수', 'ST21', '010-0000-0101','BE@sejong.com', 21, '22', '21', '21')");
	            stmt.execute("insert into professor value(22, '조교수', 'ST22', '010-0000-0201','BF@sejong.com', 22, '23', '22', '22')");
	            stmt.execute("insert into professor value(23, '초교수', 'ST23', '010-0000-0301','BG@sejong.com', 23, '24', '23', '23')");
	            stmt.execute("insert into professor value(24, '코교수', 'ST24', '010-0000-0401','BH@sejong.com', 24, '25', '24', '24')");
	            stmt.execute("insert into professor value(25, '토교수', 'ST25', '010-0000-0501','BI@sejong.com', 25, '1', '5', '25')");
	            

	            stmt.execute("insert into student value(1, '가학생', 'ST_S01', '000-0000-0001', 'S_AA@sejong.ac.kr', 1,2  , 1, '000-00-000' )");
	            stmt.execute("insert into student value(2, '나학생', 'ST_S02', '000-0000-0002', 'S_AB@sejong.ac.kr', 2,3  , 2, '000-00-000' )");
	            stmt.execute("insert into student value(3, '다학생', 'ST_S03', '000-0000-0003', 'S_AC@sejong.ac.kr', 3,4  , 3, '000-00-000' )");
	            stmt.execute("insert into student value(4, '라학생', 'ST_S04', '000-0000-0004', 'S_AD@sejong.ac.kr', 4,5  , 4, '000-00-000' )");
	            stmt.execute("insert into student value(5, '마학생', 'ST_S05', '000-0000-0005', 'S_AE@sejong.ac.kr', 5,6  , 5, '000-00-000' )");
	            stmt.execute("insert into student value(6, '바학생', 'ST_S06', '000-0000-0006', 'S_AF@sejong.ac.kr', 6,7  , 6, '000-00-000' )");
	            stmt.execute("insert into student value(7, '사학생', 'ST_S07', '000-0000-0007', 'S_AG@sejong.ac.kr', 7,8  , 7, '000-00-000' )");
	            stmt.execute("insert into student value(8, '아학생', 'ST_S08', '000-0000-0008', 'S_AH@sejong.ac.kr', 8,9  , 8, '000-00-000' )");
	            stmt.execute("insert into student value(9, '자학생', 'ST_S09', '000-0000-0009', 'S_AI@sejong.ac.kr', 9,10  , 9, '000-00-000' )");
	            stmt.execute("insert into student value(10, '차학생', 'ST_S10', '000-0000-0010',  'S_AJ@sejong.ac.kr', 10,11  , 10, '000-00-000' )");
	            stmt.execute("insert into student value(11, '카학생', 'ST_S11', '000-0000-0011', 'S_AK@sejong.ac.kr', 11,12  , 11, '000-00-000' )");
	            stmt.execute("insert into student value(12, '타학생', 'ST_S12', '000-0000-0012', 'S_AL@sejong.ac.kr', 12,13  , 12, '000-00-000' )");
	            stmt.execute("insert into student value(13, '파학생', 'ST_S13', '000-0000-0013', 'S_AM@sejong.ac.kr', 13,14  , 13, '000-00-000' )");
	            stmt.execute("insert into student value(14, '하학생', 'ST_S14', '000-0000-0014', 'S_AN@sejong.ac.kr', 14,15  , 14, '000-00-000' )");
	            stmt.execute("insert into student value(15, '고학생', 'ST_S15', '000-0000-0015', 'S_AO@sejong.ac.kr', 15,16  , 15, '000-00-000' )");
	            stmt.execute("insert into student value(16, '노학생', 'ST_S16', '000-0000-0016', 'S_AP@sejong.ac.kr', 16,17  , 16, '000-00-000' )");
	            stmt.execute("insert into student value(17, '도학생', 'ST_S17', '000-0000-0017', 'S_AQ@sejong.ac.kr', 17,18  , 17, '000-00-000' )");
	            stmt.execute("insert into student value(18, '로학생', 'ST_S18', '000-0000-0018', 'S_AR@sejong.ac.kr', 18,19  , 18, '000-00-000' )");
	            stmt.execute("insert into student value(19, '모학생', 'ST_S19', '000-0000-0019', 'S_AS@sejong.ac.kr', 19,20  , 19, '000-00-000' )");
	            stmt.execute("insert into student value(20, '보학생', 'ST_S20', '000-0000-0020', 'S_AT@sejong.ac.kr', 20,21  , 20, '000-00-000' )");
	            stmt.execute("insert into student value(21, '소학생', 'ST_S21', '000-0000-0021', 'S_AU@sejong.ac.kr', 21,22  , 21, '000-00-000' )");
	            stmt.execute("insert into student value(22, '오학생', 'ST_S22', '000-0000-0022', 'S_AV@sejong.ac.kr', 22,23  , 22, '000-00-000' )");
	            stmt.execute("insert into student value(23, '조학생', 'ST_S23', '000-0000-0023', 'S_AW@sejong.ac.kr', 23,24  , 23, '000-00-000' )");
	            stmt.execute("insert into student value(24, '초학생', 'ST_S24', '000-0000-0024', 'S_AX@sejong.ac.kr', 24,25  , 24, '000-00-000' )");
	            stmt.execute("insert into student value(25, '코학생', 'ST_S25', '000-0000-0025', 'S_AY@sejong.ac.kr', 25,1  , 25, '000-00-000' )");

	            stmt.execute("insert into lecture value(1, '001', '컴퓨터학', 'Mon', '1', '3', '10:30~12:00', '1', 'A001', '2021', '1', 1)");
	            stmt.execute("insert into lecture value(2, '001', '물리천문학', 'Mon', '2', '3', '12:00~13:30', '2', 'A002', '2021', '1', 2)");
	            stmt.execute("insert into lecture value(3, '001', '건축공학', 'Mon', '3', '4', '13:30~15:00', '3', 'A003', '2021', '1', 3)");
	            stmt.execute("insert into lecture value(4, '001', '정보보호학', 'Mon', '4', '3', '15:00~16:30', '4', 'A004', '2021', '1', 4)");
	            stmt.execute("insert into lecture value(5, '001', '물리학', 'Mon', '5', '3', '16:30~18:00', '5', 'A005', '2021', '1', 5)");
	            stmt.execute("insert into lecture value(6, '001', '천문학', 'Tues', '1', '3', '10:30~12:00', '6', 'A006', '2021', '1', 6)");
	            stmt.execute("insert into lecture value(7, '001', '기계학', 'Tues', '2', '3', '12:00~13:30', '7', 'A001', '2021', '1', 7)");
	            stmt.execute("insert into lecture value(8, '001', '호텔경영햑', 'Tues', '3', '3', '13:30~15:00', '8', 'A002', '2021', '1', 8)");
	            stmt.execute("insert into lecture value(9, '001', '국어문학', 'Tues', '4', '4', '15:00~16:30', '9', 'A003', '2021', '1', 9)");
	            stmt.execute("insert into lecture value(10, '001', '사회과학', 'Tues', '5', '3', '16:30~18:00', '10', 'A004', '2021', '1', 10)");
	            stmt.execute("insert into lecture value(11, '001', '경제학', 'Wed', '1', '3', '10:30~12:00', '11', 'A005', '2021', '1', 11)");
	            stmt.execute("insert into lecture value(12, '001', '통계학', 'Wed', '2', '3', '12:00~13:30', '12', 'A006', '2021', '1', 12)");
	            stmt.execute("insert into lecture value(13, '001', '화학', 'Wed', '3', '2', '13:30~15:00', '13', 'A002', '2021', '1', 13)");
	            stmt.execute("insert into lecture value(14, '001', '인공지능', 'Wed', '4', '4', '15:00~16:30', '14', 'A003', '2021', '1', 14)");
	            stmt.execute("insert into lecture value(15, '001', '행정학', 'Wed', '5', '3', '16:30~18:00', '15', 'A004', '2021', '1', 15)");
	            stmt.execute("insert into lecture value(16, '001', '역사학', 'Thu', '1', '3', '10:30~12:00', '16', 'A005', '2021', '1', 16)");
	            stmt.execute("insert into lecture value(17, '001', '교육학', 'Thu', '2', '3', '12:00~13:30', '17', 'A006', '2021', '1', 17)");
	            stmt.execute("insert into lecture value(18, '001', '글로벌조학', 'Thu', '3', '3', '13:30~15:00', '18', 'A002', '2021', '1', 18)");
	            stmt.execute("insert into lecture value(19, '001', '패션디자인학', 'Thu', '4', '4', '15:00~16:30', '19', 'A003', '2021', '1', 19)");
	            stmt.execute("insert into lecture value(20, '001', '무용학', 'Thu', '5', '3', '16:30~18:00', '20', 'A004', '2021', '1', 20)");
	            stmt.execute("insert into lecture value(21, '001', '체육학', 'Fri', '1', '3', '10:30~12:00', '21', 'A005', '2021', '1', 21)");
	            stmt.execute("insert into lecture value(22, '001', '화성학', 'Fri', '2', '3', '12:00~13:30', '22', 'A006', '2021', '1', 22)");
	            stmt.execute("insert into lecture value(23, '001', '영어예술학', 'Fri', '3', '3', '13:30~15:00', '23', 'A002', '2021', '1', 23)");
	            stmt.execute("insert into lecture value(24, '001', '지능기전학', 'Fri', '4', '4', '15:00~16:30', '24', 'A003', '2021', '1', 24)");
	            stmt.execute("insert into lecture value(25, '001', '애니메이션학', 'Fri', '5', '3', '16:30~18:00', '25', 'A004', '2021', '1', 25)");
	            
	            
				stmt.execute("insert into department_has_lecture value(1,1,1)");
				stmt.execute("insert into department_has_lecture value(2,2,2)");
				stmt.execute("insert into department_has_lecture value(3,3,3)");
				stmt.execute("insert into department_has_lecture value(4,4,4)");
				stmt.execute("insert into department_has_lecture value(5,5,5)");
				stmt.execute("insert into department_has_lecture value(6,6,6)");
				stmt.execute("insert into department_has_lecture value(7,7,7)");
				stmt.execute("insert into department_has_lecture value(8,8,8)");
				stmt.execute("insert into department_has_lecture value(9,9,9)");
				stmt.execute("insert into department_has_lecture value(10,10,10)");
				stmt.execute("insert into department_has_lecture value(11,11,11)");
				stmt.execute("insert into department_has_lecture value(12,12,12)");
				stmt.execute("insert into department_has_lecture value(13,13,13)");
				stmt.execute("insert into department_has_lecture value(14,14,14)");
				stmt.execute("insert into department_has_lecture value(15,15,15)");
				stmt.execute("insert into department_has_lecture value(16,16,16)");
				stmt.execute("insert into department_has_lecture value(17,17,17)");
				stmt.execute("insert into department_has_lecture value(18,18,18)");
				stmt.execute("insert into department_has_lecture value(19,19,19)");
				stmt.execute("insert into department_has_lecture value(20,20,20)");
				stmt.execute("insert into department_has_lecture value(21,21,21)");
				stmt.execute("insert into department_has_lecture value(22,22,22)");
				stmt.execute("insert into department_has_lecture value(23,23,23)");
				stmt.execute("insert into department_has_lecture value(24,24,24)");
				stmt.execute("insert into department_has_lecture value(25,25,25)");
				

	            stmt.execute("insert into lecture_history value(1, '100', '100', '100', '100', '100', 'A', '2021', '1', 1, 1, 1)");
	            stmt.execute("insert into lecture_history value(2, '85', '85', '85', '85', '85', 'B', '2021', '1', 2, 2, 2)");
	            stmt.execute("insert into lecture_history value(3, '60', '60', '60', '60', '100', 'C', '2021', '1', 3, 3, 3)");
	            stmt.execute("insert into lecture_history value(4, '50', '50', '50', '50', '50', 'D', '2021', '1', 4, 4, 4)");
	            stmt.execute("insert into lecture_history value(5, '10', '20', '15', '30', '20', 'F', '2021', '1', 5, 5, 5)");
	            stmt.execute("insert into lecture_history value(6, '95', '95', '95', '95', '95', 'A', '2021', '1', 6, 6, 6)");
	            stmt.execute("insert into lecture_history value(7, '90', '90', '90', '90', '90', 'A', '2021', '1', 7, 7, 7)");
	            stmt.execute("insert into lecture_history value(8, '80', '80', '90', '90', '85', 'B', '2021', '1', 8, 8, 8)");
	            stmt.execute("insert into lecture_history value(9, '80', '80', '80', '80', '80', 'B', '2021', '1', 9, 9, 9)");
	            stmt.execute("insert into lecture_history value(10, '60', '60', '70', '70', '65', 'C', '2021', '1', 10, 10, 10)");
	            stmt.execute("insert into lecture_history value(11, '65', '65', '65', '65', '65', 'C', '2021', '1', 11, 11, 11)");
	            stmt.execute("insert into lecture_history value(12, '40', '40', '50', '50', '45', 'D', '2021', '1', 12, 12, 12)");
	            stmt.execute("insert into lecture_history value(13, '45', '45', '45', '45', '45', 'D', '2021', '1', 13, 13, 13)");
	            stmt.execute("insert into lecture_history value(14, '10', '20', '10', '20', '15', 'F', '2021', '1', 14, 14, 14)");
	            stmt.execute("insert into lecture_history value(15, '80', '90', '80', '90', '85', 'B', '2021', '1', 15, 15, 15)");
	            stmt.execute("insert into lecture_history value(16, '100', '100', '100', '100', '100', 'A', '2021', '1', 16, 16, 16)");
	            stmt.execute("insert into lecture_history value(17, '60', '70', '60', '70', '65', 'C', '2021', '1', 17, 17, 17)");
	            stmt.execute("insert into lecture_history value(18, '30', '70', '40', '40', '45', 'D', '2021', '1', 18, 18, 18)");
	            stmt.execute("insert into lecture_history value(19, '0', '0', '0', '0', '0', 'F', '2021', '1', 19, 19, 19)");
	            stmt.execute("insert into lecture_history value(20, '100', '100', '100', '100', '100', 'A', '2021', '1', 20, 20, 20)");
	            stmt.execute("insert into lecture_history value(21, '100', '90', '90', '100', '95', 'A', '2021', '1', 21, 21, 21)");
	            stmt.execute("insert into lecture_history value(22, '100', '100', '100', '40', '85', 'B', '2021', '1', 22, 22, 22)");
	            stmt.execute("insert into lecture_history value(23, '100', '100', '60', '60', '80', 'B', '2021', '1', 23, 23, 23)");
	            stmt.execute("insert into lecture_history value(24, '50', '100', '80', '70', '75', 'C', '2021', '1', 24, 24, 24)");
	            stmt.execute("insert into lecture_history value(25, '60', '60', '60', '60', '60', 'C', '2021', '1', 25, 25, 25)");
				stmt.execute("insert into lecture_history value(26, '100', '0', '0', '0', '0', 'B', '2021', '1', 3, 10, 10)");
				stmt.execute("insert into lecture_history value(27, '100', '0', '0', '0', '0', 'B', '2021', '1', 3, 18, 18)");
	            
				stmt.execute("insert into student_has_professor value(1, '2021', '1', 1,1)");
				stmt.execute("insert into student_has_professor value(2, '2021', '1', 2,2)");
				stmt.execute("insert into student_has_professor value(3, '2021', '1', 3,3)");
				stmt.execute("insert into student_has_professor value(4, '2021', '1', 4,4)");
				stmt.execute("insert into student_has_professor value(5, '2021', '1', 5,5)");
				stmt.execute("insert into student_has_professor value(6, '2021', '1', 6,6)");
				stmt.execute("insert into student_has_professor value(7, '2021', '1', 7,7)");
				stmt.execute("insert into student_has_professor value(8, '2021', '1', 8,8)");
				stmt.execute("insert into student_has_professor value(9, '2021', '1', 9,9)");
				stmt.execute("insert into student_has_professor value(10, '2021', '1', 10,10)");
				stmt.execute("insert into student_has_professor value(11, '2021', '1', 11,11)");
				stmt.execute("insert into student_has_professor value(12, '2021', '1', 12,12)");
				stmt.execute("insert into student_has_professor value(13, '2021', '1', 13,13)");
				stmt.execute("insert into student_has_professor value(14, '2021', '1', 14,14)");
				stmt.execute("insert into student_has_professor value(15, '2021', '1', 15,15)");
				stmt.execute("insert into student_has_professor value(16, '2021', '1', 16,16)");
				stmt.execute("insert into student_has_professor value(17, '2021', '1', 17,17)");
				stmt.execute("insert into student_has_professor value(18, '2021', '1', 18,18)");
				stmt.execute("insert into student_has_professor value(19, '2021', '1', 19,19)");
				stmt.execute("insert into student_has_professor value(20, '2021', '1', 20,20)");
				stmt.execute("insert into student_has_professor value(21, '2021', '1', 21,21)");
				stmt.execute("insert into student_has_professor value(22, '2021', '1', 22,22)");
				stmt.execute("insert into student_has_professor value(23, '2021', '1', 23,23)");
				stmt.execute("insert into student_has_professor value(24, '2021', '1', 24,24)");
				stmt.execute("insert into student_has_professor value(25, '2021', '1', 25,25)");
//				
				stmt.execute("insert into Circles value(1, '동아리A', '1', '1', '1', 'OFFICE01')");
				stmt.execute("insert into Circles value(2, '동아리B', '2', '2', '2', 'OFFICE02')");
				stmt.execute("insert into Circles value(3, '동아리C', '3', '3', '3', 'OFFICE03')");
				stmt.execute("insert into Circles value(4, '동아리D', '4', '4', '4', 'OFFICE04')");
				stmt.execute("insert into Circles value(5, '동아리E', '5', '5', '5', 'OFFICE05')");
				stmt.execute("insert into Circles value(6, '동아리F', '6', '6', '6', 'OFFICE06')");
				stmt.execute("insert into Circles value(7, '동아리G', '7', '7', '7', 'OFFICE07')");
				stmt.execute("insert into Circles value(8, '동아리H', '8', '8', '8', 'OFFICE08')");
				stmt.execute("insert into Circles value(9, '동아리I', '9', '9', '9', 'OFFICE09')");
				stmt.execute("insert into Circles value(10, '동아리J', '10', '10', '10', 'OFFICE10')");
				stmt.execute("insert into Circles value(11, '동아리K', '11', '11', '11', 'OFFICE11')");
				stmt.execute("insert into Circles value(12, '동아리L', '12', '12', '12', 'OFFICE12')");
				stmt.execute("insert into Circles value(13, '동아리M', '13', '13', '13', 'OFFICE13')");
				stmt.execute("insert into Circles value(14, '동아리N', '14', '14', '14', 'OFFICE14')");
				stmt.execute("insert into Circles value(15, '동아리O', '15', '15', '15', 'OFFICE15')");
				stmt.execute("insert into Circles value(16, '동아리P', '16', '16', '16', 'OFFICE16')");
				stmt.execute("insert into Circles value(17, '동아리Q', '17', '17', '17', 'OFFICE17')");
				stmt.execute("insert into Circles value(18, '동아리R', '18', '18', '18', 'OFFICE18')");
				stmt.execute("insert into Circles value(19, '동아리S', '19', '19', '19', 'OFFICE19')");
				stmt.execute("insert into Circles value(20, '동아리T', '20', '20', '20', 'OFFICE201')");
				stmt.execute("insert into Circles value(21, '동아리U', '21', '21', '21', 'OFFICE21')");
				stmt.execute("insert into Circles value(22, '동아리V', '22', '22', '22', 'OFFICE22')");
				stmt.execute("insert into Circles value(23, '동아리W', '23', '23', '23', 'OFFICE23')");
				stmt.execute("insert into Circles value(24, '동아리X', '24', '24', '24', 'OFFICE24')");
				stmt.execute("insert into Circles value(25, '동아리Y', '25', '25', '25', 'OFFICE25')");

				stmt.execute("insert into payment value(1, '2021', '1', '5000000', '4000000', 1, '2021-06-06')");
				stmt.execute("insert into payment value(2, '2021', '1', '5000000', '4000000', 2, '2021-06-06')");
				stmt.execute("insert into payment value(3, '2021', '1', '5000000', '5000000', 3, '2021-06-06')");
				stmt.execute("insert into payment value(4, '2021', '1', '5000000', '5000000', 4, '2021-06-06')");
				stmt.execute("insert into payment value(5, '2021', '1', '5000000', '5000000', 5, '2021-06-06')");
				stmt.execute("insert into payment value(6, '2021', '1', '5000000', '5000000', 6, '2021-06-06')");
				stmt.execute("insert into payment value(7, '2021', '1', '5000000', '5000000', 7, '2021-06-06')");
				stmt.execute("insert into payment value(8, '2021', '1', '5000000', '3000000', 8, '2021-06-06')");
				stmt.execute("insert into payment value(9, '2021', '1', '5000000', '5000000', 9, '2021-06-06')");
				stmt.execute("insert into payment value(10, '2021', '1', '5000000', '5000000', 10, '2021-06-06')");
				stmt.execute("insert into payment value(11, '2021', '1', '5000000', '5000000', 11, '2021-06-06')");
				stmt.execute("insert into payment value(12, '2021', '1', '5000000', '5000000', 12, '2021-06-06')");
				stmt.execute("insert into payment value(13, '2021', '1', '5000000', '5000000', 13, '2021-06-06')");
				stmt.execute("insert into payment value(14, '2021', '1', '5000000', '5000000', 14, '2021-06-06')");
				stmt.execute("insert into payment value(15, '2021', '1', '5000000', '5000000', 15, '2021-06-06')");
				stmt.execute("insert into payment value(16, '2021', '1', '5000000', '3000000', 16, '2021-06-06')");
				stmt.execute("insert into payment value(17, '2021', '1', '5000000', '5000000', 17, '2021-06-06')");
				stmt.execute("insert into payment value(18, '2021', '1', '5000000', '5000000', 18, '2021-06-06')");
				stmt.execute("insert into payment value(19, '2021', '1', '5000000', '5000000', 19, '2021-06-06')");
				stmt.execute("insert into payment value(20, '2021', '1', '5000000', '5000000', 20, '2021-06-06')");
				stmt.execute("insert into payment value(21, '2021', '1', '5000000', '4000000', 21, '2021-06-06')");
				stmt.execute("insert into payment value(22, '2021', '1', '5000000', '5000000', 22, '2021-06-06')");
				stmt.execute("insert into payment value(23, '2021', '1', '5000000', '5000000', 23, '2021-06-06')");
				stmt.execute("insert into payment value(24, '2021', '1', '5000000', '5000000', 24, '2021-06-06')");
				stmt.execute("insert into payment value(25, '2021', '1', '5000000', '5000000', 25, '2021-06-06')");
				

				stmt.execute("insert into Student_has_Circles value(1,1,1)");
				stmt.execute("insert into Student_has_Circles value(2,2,2)");
				stmt.execute("insert into Student_has_Circles value(3,3,3)");
				stmt.execute("insert into Student_has_Circles value(4,4,4)");
				stmt.execute("insert into Student_has_Circles value(5,5,5)");
				stmt.execute("insert into Student_has_Circles value(6,6,6)");
				stmt.execute("insert into Student_has_Circles value(7,7,7)");
				stmt.execute("insert into Student_has_Circles value(8,8,8)");
				stmt.execute("insert into Student_has_Circles value(9,9,9)");
				stmt.execute("insert into Student_has_Circles value(10,10,10)");
				stmt.execute("insert into Student_has_Circles value(11,11,11)");
				stmt.execute("insert into Student_has_Circles value(12,12,12)");
				stmt.execute("insert into Student_has_Circles value(13,13,13)");
				stmt.execute("insert into Student_has_Circles value(14,14,14)");
				stmt.execute("insert into Student_has_Circles value(15,15,15)");
				stmt.execute("insert into Student_has_Circles value(16,16,16)");
				stmt.execute("insert into Student_has_Circles value(17,17,17)");
				stmt.execute("insert into Student_has_Circles value(18,18,18)");
				stmt.execute("insert into Student_has_Circles value(19,19,19)");
				stmt.execute("insert into Student_has_Circles value(20,20,20)");
				stmt.execute("insert into Student_has_Circles value(21,21,21)");
				stmt.execute("insert into Student_has_Circles value(22,22,22)");
				stmt.execute("insert into Student_has_Circles value(23,23,23)");
				stmt.execute("insert into Student_has_Circles value(24,24,24)");
				stmt.execute("insert into Student_has_Circles value(25,25,25)");
				stmt.execute("insert into Student_has_Circles value(26,24,3)");
				
				
				
				
				System.out.println("DB초기화 성공");
				
				
				
				
			}
		}catch(Exception e1) {
			System.out.println(e1);
		}
		closeDB();
	}
}
