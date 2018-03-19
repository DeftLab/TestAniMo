package workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import mice.CageStock;
import mice.MouseStock;
import tools.DateComparator;
import tools.ExcelTools;

public class BodyStock {
	private XSSFWorkbook wb;
	
	public BodyStock(XSSFWorkbook wb){
		this.wb = wb;
	}
	
	public void addRowBody (int rowNumber, boolean lastRow){
		ExcelTools.createRowStock (wb, rowNumber, lastRow);
	}
	
	//Create one Row of Body
	public void fillBody (int rowNumber, MouseStock mouse){
	   String inBr ="";
	   
	   if (mouse.isInBreeding()) {
		   inBr = "X";
	   }		
		ExcelTools.setCellValue(wb, rowNumber, 1, mouse.getLine());
		ExcelTools.setCellValue(wb, rowNumber, 2, mouse.getSex());
		ExcelTools.setCellValue(wb, rowNumber, 3, inBr);
		ExcelTools.setCellValue(wb, rowNumber, 4, mouse.getCode());
		ExcelTools.setCellValue(wb, rowNumber, 5, mouse.getName());
		ExcelTools.setCellValue(wb, rowNumber, 6, mouse.getGenotype());
		ExcelTools.setCellValue(wb, rowNumber, 7, mouse.getOrigin());
		ExcelTools.setCellValue(wb, rowNumber, 8, mouse.getBirthDate());
		ExcelTools.setCellValue(wb, rowNumber, 9, DateComparator.differenceBetween2Dates(mouse.getBirthDate()));
		ExcelTools.setCellValue(wb, rowNumber, 10, mouse.getService());
	}
	
	public void fillBodyCageInformation (int rowNumber, CageStock cage, boolean reproducerInCage, boolean descendingInCage, String brOrigin){
		   String cageInfo = cage.getCodeCage ();
		   if (cageInfo.equals("Unk cage " + cage.getProcessName())){
			   cageInfo = cageInfo.substring(0, 8);
		   }
		   
		   if (reproducerInCage && descendingInCage && !cageInfo.equals("Unk cage")){
			   cageInfo += " (" + brOrigin +")";
		   }
		   
			ExcelTools.setCellValue(wb, rowNumber, 0, cageInfo);
	}
	

}
