package IHM;

import java.awt.FlowLayout;

import javax.swing.JFrame;


public class Windows extends JFrame{

	
	public Windows (){
	    this.setTitle("AnimotoMagic");     
	    this.getContentPane().setLayout(new FlowLayout());
	    this.setVisible(true);
	    this.setResizable(false);
		
	}
	
	
}
