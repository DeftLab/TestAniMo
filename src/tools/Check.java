package tools;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Workbook;

import algorithm.Read;

public class Check {
	
	public static boolean checkFile (String sourcePath){
		File file = new File (sourcePath);
		String fileName = file.getName();
		String str = fileName.substring(fileName.indexOf('.') + 1);
		
		boolean check = true;
		if (!(str.equals("xls") || str.equals("xlsx") || str.equals(""))){
			check = false;
			JOptionPane.showMessageDialog(null, "Le fichier n'est pas un fichier excel.", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	return 	check;
	}


	public static boolean checkExcelFile (Workbook wb, boolean isStock) {
		boolean check = true;
			if (isStock){
				String test = wb.getSheetAt(0).getRow(0).getCell(ExternallyFile.config.getStockColumnPosition().get(0)).getStringCellValue();
				if (!test.equals(ExternallyFile.config.getStockColumnLabel().get(0))){
					check = false;
					JOptionPane .showMessageDialog(null, "Le fichier n'est pas un fichier stock.", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				String test2 = wb.getSheetAt(0).getRow(1).getCell(ExternallyFile.config.getCroisColumnPosition().get(0)).getStringCellValue();
				
				if (!test2.equals(ExternallyFile.config.getCroisColumnLabel().get(0))){
					check = false;
					JOptionPane .showMessageDialog(null, "Le fichier n'est pas un fichier breeding.", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}				
		return check;	
	}

	public static boolean checkUpdate (Workbook wb, String breedingOrStock){
	    Boolean needUpdate = false;
		ArrayList<String> label = new ArrayList<String>();
		int i = 0;
		int lastColumn = wb.getSheetAt(0).getRow(0).getLastCellNum();
		
		if (breedingOrStock.equals("stock")){
			do {
				label.add(wb.getSheetAt(0).getRow(0).getCell(i).getStringCellValue());
				++i;
			}while (i != lastColumn);
			needUpdate = needUpdate (label, true);
			
		} else if (breedingOrStock.equals("croisement")){
			do {
				label.add(wb.getSheetAt(0).getRow(1).getCell(i).getStringCellValue());
				++i;
			}while (i != lastColumn);
			needUpdate = needUpdate (label, false);
		}
		
		if (needUpdate){
			ArrayList <String> letterList = Update.ExcelLabelFactory();
			Update.updatingColumnLetter = letterList.get(Update.updatingColumn);				
		}
		
		return needUpdate;
	}
	
	//check if all config label are at the same position as excel label
	private static boolean needUpdate (ArrayList<String> excelLabel, boolean isStock){
		ArrayList <String> configLabel;
		ArrayList <Integer> configPosition;
		
		if (isStock){
			configLabel = ExternallyFile.config.getStockColumnLabel();
			configPosition = ExternallyFile.config.getStockColumnPosition();
		}else {
			configLabel = ExternallyFile.config.getCroisColumnLabel();
			configPosition = ExternallyFile.config.getCroisColumnPosition();
		}
		
		boolean needUpdate;
		int i = 0;
		do {
			while (!isStock && (i==1 || i == 2)){
				++i;
			}
			needUpdate = true;
			if (configLabel.get(i).equals(excelLabel.get(configPosition.get(i)))){
				needUpdate = false;
			}
			Update.updatingColumn = configPosition.get(i);
			Update.updatingColumnLabel = configLabel.get(i);
			++i;
		}while (!needUpdate && i < configLabel.size());
		
		return needUpdate;
	}
	
	// return true if value = A to ZZ like excel Label
	public static boolean checkEntry (String value){
		boolean verification ;
		ArrayList <String> listLetter = Update.ExcelLabelFactory();
		int i = 0;
		do {
			verification = false;
			if (value.equals(listLetter.get(i))){
				verification = true;
			}
			++i;
		}while (i < listLetter.size() && !verification);
		
		return verification;
	}
	
	
}
