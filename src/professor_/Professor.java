package professor_;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Professor extends JFrame implements ActionListener{
	JPanel pn; 
	JButton btn_init, btn_all, btn_lead, btn_dep, btn_lectime, btn_grade;
	
	public Professor() {
		super("����");
		layInit();
		this.setLayout(null);
//		setBounds(0, 0, 1500, 1000);   
		setBounds(200, 200, 700, 550); 
		
	}
	
	public void layInit() {
		   
	      //������ ȭ��
	      pn=new JPanel();
	      pn.setLayout(null);
	      pn.setBounds(0,0,1500,1300);
	      
	      
	      //btn
	      btn_init = new JButton("DB�ʱ�ȭ");
	      btn_init.setBounds(10,10,100,30);
	      btn_all = new JButton("��� ����");
	      btn_all.setBounds(120,10,100,30);
	      btn_lead = new JButton("���� ����");
	      btn_lead.setBounds(230,10,100,30);
	      btn_dep = new JButton("�а� ����");
	      btn_dep.setBounds(340,10,100,30);
	      btn_lectime = new JButton("���ǽð�ǥ");
	      btn_lectime.setBounds(450,10,100,30);
	      btn_grade = new JButton("�����Է�");
	      btn_grade.setBounds(560,10,100,30);
	      
	      add(pn);
//	      pn.add(btn_init);
	      pn.add(btn_all);
	      pn.add(btn_lead);
	      pn.add(btn_dep);
	      pn.add(btn_lectime);
	      pn.add(btn_grade);
	      
	      pn.setVisible(true);
	      
	     btn_all.addActionListener(this);
	     btn_lead.addActionListener(this);
	     btn_dep.addActionListener(this);
	     btn_lectime.addActionListener(this);
	     btn_grade.addActionListener(this);
	   }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
	         if (e.getSource() == btn_all){
	        	 Show_All show_All = new Show_All();
	        	 show_All.setVisible(true);
	         }else if(e.getSource() == btn_lead){
	        	 Show_Lead show_Lead = new Show_Lead();
	        	 show_Lead.setVisible(true);
	         }else if(e.getSource() == btn_dep) {
	        	 Show_Dep1 show_Dep = new Show_Dep1();
	        	 show_Dep.setVisible(true);
	         }else if(e.getSource() == btn_lectime) {
	        	 Show_Lectime1 show_Lectime = new Show_Lectime1();
	        	 show_Lectime.setVisible(true);
	         }else if(e.getSource() == btn_grade) {
	        	 Show_Grade show_Grade = new Show_Grade();
	        	 show_Grade.setVisible(true);
	         }
	      } catch (Exception e2) {
	         System.out.println("Main ���� �б� ���� :" + e2);
	         System.out.println("���� �߻�!"); 
	      }
	}
}
