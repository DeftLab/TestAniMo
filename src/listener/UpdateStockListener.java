package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.record.RecordInputStream.LeftoverDataException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import IHM.WindowsUpdate;
import tools.Check;
import tools.ExternallyFile;
import tools.OpenBrowser;
import tools.Update;
import workbook.HeaderLabel;

public class UpdateStockListener implements ActionListener {
	private Workbook wb;	
	private WindowsUpdate wu;

	
	public UpdateStockListener (WindowsUpdate wu){
	this.wu = wu;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean checkFile = false;
		boolean checkExcelFile = false;
		String sourcePath = null;
		try{
			do {
				do{
					sourcePath = OpenBrowser.selectExcelFile();
					checkFile = Check.checkFile (sourcePath);			
				} while (!checkFile) ;
				
				if (!sourcePath.equals("")){
					try{
						wb = WorkbookFactory.create (new BufferedInputStream(
								new DataInputStream(
									new FileInputStream(
											new File(sourcePath)))));
					}catch (EncryptedDocumentException er){
						JOptionPane .showMessageDialog(null, er.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
						
					}catch (LeftoverDataException er){
						JOptionPane .showMessageDialog(null, er.getMessage()
								+ "\n le fichier Excel est vérouillé, il faut l'ouvrir, cliquer sur \"enable editing \", sauvegarder et quitter avant de relancer le tri", "Erreur", JOptionPane.ERROR_MESSAGE);
					
					}catch (Exception er){
						er.getMessage();
					}
					checkExcelFile = Check.checkExcelFile (wb, true);
				} else {
					checkExcelFile =true;
				}
			} while (!checkExcelFile);
		}catch (NullPointerException error){
			sourcePath = "";
		}
		
		if (!sourcePath.equals("")){
			HeaderLabel headerLabel = new HeaderLabel (wb, true);
			
			wu.setColumnPosition(Update.compareLabel (headerLabel.getLabelList(), ExternallyFile.config.getStockColumnLabel(), true));			
			wu.setUpdatePanel(true);
			wu.setStockHeader(headerLabel);
			
			try {
				wb.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}	
	}
	


}
