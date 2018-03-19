package workbook;

import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tools.ExcelTools;

public class HeaderStock  {	
	private XSSFWorkbook wb;
	private String animalerie = "NAC";
	
	public HeaderStock (XSSFWorkbook wb){
		this.wb = wb;
		for (int i = 0; i < 3; ++i){
			ExcelTools.createRowStock(wb, i, false);
		}
	}
		
	//fill out header 
	public void fillHeader (String process, int cageCounter){
		rowHeader0(process);
		rowHeader1(cageCounter);
		rowHeader2();
	}
	
	//merge some cells of header
	public void layoutHeader (){
		ExcelTools.merge(wb, 0, 0, 1, 9);
		ExcelTools.merge(wb, 1, 1, 8, 9);
		ExcelTools.merge(wb, 1, 1, 0, 1);
		ExcelTools.merge(wb, 1, 1, 2, 4);
	}
	
	//create the first row of the header with ExcelTools.setCellValue (rowPosition, columnPosition, value)
	protected void rowHeader0 (String process){
		ExcelTools.setCellValue(wb, 0, 0, animalerie);
		ExcelTools.setCellValue(wb, 0, 1, process);
		ExcelTools.setCellValue(wb, 0, 10, "Procédure");
	}
	
	//create the second row of the header with ExcelTools.setCellValue (rowPosition, columnPosition, value)
	protected  void rowHeader1 (int cageCounter){
		ExcelTools.setCellValue(wb, 1, 0, "Date (dd/mm/yyyy):");
		ExcelTools.setCellValue(wb, 1, 2, new Date());
		ExcelTools.setCellValue(wb, 1, 8, "Nbr of Cage");
		ExcelTools.setCellValue(wb, 1, 10, cageCounter);
	}
	
	//create the third row of the header with ExcelTools.setCellValue (rowPosition, columnPosition, value)
	private  void rowHeader2 (){
		ExcelTools.setCellValue(wb, 2, 0, "Cage");
		ExcelTools.setCellValue(wb, 2, 1, "Line");
		ExcelTools.setCellValue(wb, 2, 2, "Sex");
		ExcelTools.setCellValue(wb, 2, 3, "in Br");
		ExcelTools.setCellValue(wb, 2, 4, "Code Name");
		ExcelTools.setCellValue(wb, 2, 5, "Name");
		ExcelTools.setCellValue(wb, 2, 6, "Genotype");
		ExcelTools.setCellValue(wb, 2, 7, "Origin Cage//\nOrigin Br");
		ExcelTools.setCellValue(wb, 2, 8, "Date of Birth :");
		ExcelTools.setCellValue(wb, 2, 9, "Age W.");
		ExcelTools.setCellValue(wb, 2, 10, "services");
		
	}

}
