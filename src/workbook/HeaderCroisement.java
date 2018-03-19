package workbook;

import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tools.ExcelTools;

public class HeaderCroisement {
	private XSSFWorkbook wb;
	private String animalerie = "NAC";

	public HeaderCroisement(XSSFWorkbook wb) {
		this.wb = wb;
		for (int i = 0; i < 4; ++i){
			ExcelTools.createRowCroisement(wb, i, false);
		}
	}
	
	//fill out header 
		public void fillHeader (String process, String line, int cageCounter){
			rowHeader0(process);
			rowHeader1(line);
			rowHeader2(cageCounter);
			rowHeader3();
		}
		
		//merge some cells of header
		public void layoutHeader (){
			ExcelTools.merge(wb, 0, 1, 0, 0);
			ExcelTools.merge(wb, 0, 0, 2, 6);
			ExcelTools.merge(wb, 1, 1, 2, 6);
			ExcelTools.merge(wb, 2, 2, 0, 1);
			ExcelTools.merge(wb, 2, 2, 4, 5);
		}
		
		//create the first row of the header with ExcelTools.setCellValue (rowPosition, columnPosition, value)
		protected void rowHeader0 (String process){
			ExcelTools.setCellValue(wb, 0, 0, animalerie);
			ExcelTools.setCellValue(wb, 0, 1, "Process : ");
			ExcelTools.setCellValue(wb, 0, 2, process);
		}
		
		protected void rowHeader1 (String line){
			ExcelTools.setCellValue(wb, 1, 0, animalerie);
			ExcelTools.setCellValue(wb, 1, 1, "Line : ");
			ExcelTools.setCellValue(wb, 1, 2, line);
		}
		
		//create the second row of the header with ExcelTools.setCellValue (rowPosition, columnPosition, value)
		protected  void rowHeader2 (int cageCounter){
			ExcelTools.setCellValue(wb, 2, 0, "Date (dd/mm/yyyy):");
			ExcelTools.setCellValue(wb, 2, 2, new Date());
			ExcelTools.setCellValue(wb, 2, 4, "Nbr of Cage");
			ExcelTools.setCellValue(wb, 2, 6, cageCounter);
		}
		
		//create the third row of the header with ExcelTools.setCellValue (rowPosition, columnPosition, value)
		private  void rowHeader3 (){
			ExcelTools.setCellValue(wb, 3, 0, "Breeding");
			ExcelTools.setCellValue(wb, 3, 1, "Opening Date");
			ExcelTools.setCellValue(wb, 3, 2, "Male");
			ExcelTools.setCellValue(wb, 3, 3, "Female");
			ExcelTools.setCellValue(wb, 3, 4, "New Borns");
			ExcelTools.setCellValue(wb, 3, 5, "Date of birth");
			ExcelTools.setCellValue(wb, 3, 6, "Age (w)");
		}
}



	
	


	
	



