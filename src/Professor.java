import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Professor extends JFrame {
	JPanel pn; 
	JButton btn_init;
	
	public Professor() {
		super("교수");
		layInit();
		this.setLayout(null);
		setBounds(0, 0, 1500, 1000);   
		
		
	}
	
	public void layInit() {
        
		  
		  //shop = new RepairShop(con_adm);
		   
	      //관리자 화면
	      pn=new JPanel();
	      pn.setLayout(null);
	      pn.setBounds(0,0,1500,1300);
	      
	      
	      //btn
	      btn_init = new JButton("DB초기화");
	      btn_init.setBounds(10,10,100,30);
	     
	      
	      add(pn);
	      pn.add(btn_init);
	      
	      pn.setVisible(true);
	      
	     
	   }
}
