package IHM;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import folderAndFile.Installation;

public class WindowsInstall extends Windows implements ActionListener{
	

		private JPanel pan1 = new JPanel ();
		private JPanel pan2 = new JPanel ();
		private JPanel pan3 = new JPanel ();
		private JButton installButton = new JButton("Création des fichiers");
		private JLabel text = new JLabel ("Où souhaitez vous créer les fichiers d'archivages ?");
	
	
	public WindowsInstall (){
		super ();
		this.setSize(400, 115);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width)/2,(screen.height - this.getSize().height)/2);
		
		pan1.add(text);
		pan2.add(installButton);
		
		pan3.add(pan1);
		pan3.add(pan2);
		this.setContentPane(pan3);
		this.installButton.addActionListener(this);
		this.validate();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.installButton){
			this.setVisible(false);
			new Installation();
			new WindowsSorting ();		
		}
		
		
	}

}
