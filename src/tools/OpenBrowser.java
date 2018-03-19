package tools;

import javax.swing.JFileChooser;

public class OpenBrowser {
	
		public static String selectExcelFile (){
			//Create a file chooser
			final JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
			//In response to a button click:
			int folderTarget = fc.showDialog(fc, "Select Ecxel File");
			
			String path ="";
			if (folderTarget == JFileChooser.APPROVE_OPTION){
				path = fc.getSelectedFile().getAbsolutePath();
				return path;
			} else if (folderTarget == JFileChooser.CANCEL_OPTION){
				fc.setVisible(false);
			}	
			return path;
		}	
}
