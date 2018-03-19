package algorithm;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import enumeration.AnimalFacility;
import folderAndFile.PathSave;
import mice.CageStock;
import mice.MouseStock;
import tools.ExcelTools;
import tools.ExternallyFile;
import workbook.BodyStock;
import workbook.HeaderStock;

public class WriteStock {
	
	//fill out excel file : produce a complete Excel file, merge, data, justify etc...
		// input : Cage cage, ArrayList <Mouse> mouse , String fileName
		public static void generateExcelFile (String processName, ArrayList<CageStock> cageList) throws IOException {
			
			XSSFWorkbook wb = (XSSFWorkbook) ExcelTools.CreateWorkbook(processName);
			XSSFSheet sheet = wb.getSheetAt(0);
			sheet.setDisplayGridlines(false);
			
			 //create header, and body empty cell
			HeaderStock head = new HeaderStock (wb);
			BodyStock body = new BodyStock (wb);
			
			int n = 3;
			int rowFrom = 0;
			int rowTo = 0;
			for (int i = 0; i < cageList.size(); ++i){
				ArrayList <MouseStock> mouseCage = cageList.get(i).getCageMouseList();
				boolean reproducerInCage = false;
				boolean descendingInCage = false;
				boolean lastRow = false;
				String brOrigin ="";
				int m = 0;
				for (int j = 0; j < mouseCage.size(); ++j){
					
					//fill out Excel file 					
					 if (j == mouseCage.size()-1){
							 lastRow = true;
							 brOrigin = mouseCage.get(j).getBrOrigin();
					 }
	
					 if (mouseCage.get(j).isInBreeding()){
						 reproducerInCage = true;
					 }else {
						 descendingInCage = true;
					 }
					 					 
					 body.addRowBody(n, lastRow); 
					 body.fillBody(n, mouseCage.get(j));
					 	
					 if ((j == 0) && (i == 0)){
						 rowFrom = n; 
					 }					
					 
					 if (lastRow){
						 rowTo = rowFrom + mouseCage.size() -1 ;
						 if (rowTo != rowFrom){
							 ExcelTools.merge (wb, rowFrom, rowTo, 0, 0);
						 }	
						 m = rowFrom;
						 rowFrom = rowTo + 1;
					 }					 
					 ++n;
				}	
				
				//fill cage information
				body.fillBodyCageInformation(m, cageList.get(i), reproducerInCage, descendingInCage, brOrigin);
			}	
					   							 
		    //fill out header and merge cells : wb, rowFrom, rowTo, colFrom, colTo	
			int nbrCage = cageList.size();
			head.layoutHeader();
			head.fillHeader(processName, nbrCage);
			
			
			//adjust size of column 1 and 6
			sheet.autoSizeColumn(1, true);
			sheet.autoSizeColumn(6, true);
			sheet.autoSizeColumn(10, true);
			
			String adress ="";
			//load saveAdress
			
			PathSave path = ExternallyFile.config.getInstallPath();
			if (Run.animalFacility == AnimalFacility.Nac){
				adress = path.getNacStockFolder();
			}else if (Run.animalFacility == AnimalFacility.a105) {
				adress = path.getA105StockFolder();
			}
			
			//save
			try {	
				ExcelTools.saveWorkbook(wb, adress, processName);
			} catch (IOException e){
				JOptionPane .showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			
			
		}
		
}
