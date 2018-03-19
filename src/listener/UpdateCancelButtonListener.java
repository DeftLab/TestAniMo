package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;

import IHM.WindowsUpdate;

public class UpdateCancelButtonListener extends ButtonListener implements ActionListener{

	
	public UpdateCancelButtonListener(WindowsUpdate wu) {
		super(wu);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		wu.setCroisHeader(null);
		wu.setStockHeader(null);
		wu.getContentPanel().removeAll();
		wu.initContent ();
		wu.getContentPane().revalidate();
	}

}
