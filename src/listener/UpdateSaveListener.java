package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import IHM.WindowsUpdate;
import tools.Check;
import tools.ExternallyFile;
import tools.Update;

public class UpdateSaveListener extends ButtonListener implements ActionListener {
	private JTable stockTable, croisTable;
	private ArrayList <Integer> stockNumberPositionList;
	private ArrayList <Integer> croisNumberPositionList;
	private ArrayList <String> stockLabelPositionList;
	private ArrayList <String> croisLabelPositionList;
	
	public UpdateSaveListener(WindowsUpdate wu){
		super(wu);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		stockTable = wu.getStockTable();
		croisTable = wu.getCroisTable();
			
		boolean wrongEntry = false;
		ArrayList <String> stockPositionList = new ArrayList <String>();
		ArrayList <String> croisPositionList = new ArrayList <String>();
		
		
		if (stockTable != null) {
			for (int i = 0; i< stockTable.getColumnCount() ; ++i){
				String cellValue = (String) stockTable.getValueAt(0, i);
				
				cellValue = cellValue.toUpperCase();
				if (!Check.checkEntry(cellValue)){
					wrongEntry = true;
					JOptionPane.showMessageDialog(null, "Dans le tableau stock :" + "\n" +
							"La valeur \"" + cellValue + "\" dans la colonne \"" + stockTable.getColumnName(i) + "\" ne correspond pas à une colonne d'excel", "ereur", JOptionPane.ERROR_MESSAGE);
				}else {
					stockPositionList.add(cellValue.toUpperCase());
				}
			}
		}
		
		if (croisTable != null){
			for (int i = 0; i < croisTable.getColumnCount() ; ++i){
				String cellValue = (String) croisTable.getValueAt(0, i);
				cellValue = cellValue.toUpperCase();
				if (!Check.checkEntry(cellValue)){
					wrongEntry = true;
					JOptionPane.showMessageDialog(null, "Dans le tableau croisement :" + "\n" +
														"La valeur \"" + cellValue + "\" dans la colonne \"" + croisTable.getColumnName(i) + "\" ne correspond pas à une colonne d'excel", "ereur", JOptionPane.ERROR_MESSAGE);
				}else {
					croisPositionList.add(cellValue);
				}
				
			}
			
		}
			
		
		if ((stockTable == null) && (croisTable == null)){
			JOptionPane.showMessageDialog(null, "Aucune mise à jour à sauvegarder", "ereur", JOptionPane.ERROR_MESSAGE);
		} else 	if (!wrongEntry){
			
			ArrayList<String> listLetter = Update.ExcelLabelFactory();
			
			if (wu.getStockHeader() != null){
				//find column number	
				stockNumberPositionList = Update.whatColumnNumber(stockPositionList, listLetter);
				
				//find column label with column number
				stockLabelPositionList = Update.whatColumnLabel(wu.getStockHeader().getLabelList(), stockNumberPositionList, true);
				
				//modfication of data config file
				ExternallyFile.config.setStockColumnLabel(stockLabelPositionList);
				ExternallyFile.config.setStockColumnPosition(stockNumberPositionList);		
				
			}

			if (wu.getCroisHeader() != null){
				
				//find column number	
				croisNumberPositionList = Update.whatColumnNumber(croisPositionList, listLetter);
				
				//find column label with column number
				croisLabelPositionList = Update.whatColumnLabel(wu.getCroisHeader().getLabelList(), croisNumberPositionList, false);
								
				//modfication of data config file				
				ExternallyFile.config.setCroisColumnLabel(croisLabelPositionList);
				ExternallyFile.config.setCroisColumnPosition(croisNumberPositionList);			
			}	
			
			//Saving of config modification
			try {
				ExternallyFile.saveObjectIntoTextFile(ExternallyFile.config, "ressources/config.ini");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//information message
			JOptionPane.showMessageDialog(null, "La mise à jour à été réalisée avec succès.", "Information", JOptionPane.INFORMATION_MESSAGE);
		}

		
		
	}
	

}
