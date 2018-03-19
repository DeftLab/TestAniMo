package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import IHM.WindowsUpdate;

public class UpdateQuitButtonListener extends ButtonListener implements ActionListener {
	private JFrame frame;
	
	public UpdateQuitButtonListener(WindowsUpdate wu) {
		super(wu);
		frame = wu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		frame.setVisible(false);
	}

}
