package tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFPrintSetup;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import algorithm.WriteCroisement;

public class ExcelTools {

	
		//workbook creation
		public static  XSSFWorkbook CreateWorkbook (String sheetName){
			XSSFWorkbook wb =new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet(sheetName);
			
			// define margin 
			sheet.setMargin(Sheet.BottomMargin, 0);
			sheet.setMargin(Sheet.TopMargin, 0);
			sheet.setMargin(Sheet.LeftMargin, 0);
			sheet.setMargin(Sheet.RightMargin, 1.45); //1.45 inch = 3.7 cm
			
			//defined print area
			PrintSetup ps = sheet.getPrintSetup();
			ps.setLandscape(false);
			ps.setPaperSize(XSSFPrintSetup.A4_PAPERSIZE);
			sheet.setFitToPage(true);
			ps.setFitWidth( (short) 1);
			ps.setFitHeight( (short) 1);
			return wb;                          
		}
		
		//merge cells
		public static void merge (XSSFWorkbook wb, int rowFrom, int rowTo, int colFrom, int colTo){
			wb.getSheetAt(0).addMergedRegion(new CellRangeAddress(rowFrom,rowTo,colFrom,colTo));
		}
		
		//check if a folder with Year for name exist and create it if not; return address + "\\" Year folder
		public static String yearDirectory (String adress){
			Date date  = new Date();
			String yearFolder = ExcelTools.convertDateToString(date, "YYYY"); 
			String completPath = adress +"/" + yearFolder;
			File file = new File (completPath);
			
			if (!file.isDirectory()){
				file.mkdirs();				
			}

			return completPath;
		}
		
		//create if a folder with name :  Year_MM-dd  and return  adress+"\\"+ today Folder
		public static String todayDirectory (String adress){
			Date date  = new Date();
			String todayFolder = ExcelTools.convertDateToString(date, "YYYY_MM_dd"); 
			String completPath = adress +"/" + todayFolder;
			File file = new File (completPath);
			
			if (!file.isDirectory()){
				file.mkdirs();				
			}

			return completPath;
		}
		
		// save workbook
		public static void saveWorkbook (XSSFWorkbook wb, String adress, String fileName) throws IOException{
			FileOutputStream fileOut = null;
			try{
				String partielAdress = yearDirectory (adress);
				String completAdress = todayDirectory (partielAdress);
				fileOut = new FileOutputStream(new File (completAdress + "/" + fileName + ".xlsx"));
				wb.write(fileOut);
				fileOut.close();
			}
			catch (IOException e){
				System.out.println(e.getMessage());
			}
			finally {
				if (fileOut != null){
					fileOut.close();
				}
			}	
		}
		
		// save workbook
		public static void saveWorkbook (Workbook wb, String adress, String fileName) throws IOException{
			FileOutputStream fileOut = null;
			try{
				String completAdress = yearDirectory (adress);
				fileOut = new FileOutputStream(new File (completAdress + "/" + fileName + ".xls"));
				wb.write(fileOut);
				fileOut.close();
			}
			catch (IOException e){
				System.out.println(e.getMessage());
			}
			finally {
				if (fileOut != null){
					fileOut.close();
				}	
			}
		}
			
		
		//fill out cell with value at column position into a row 
		public static void setCellValue (Workbook wb, int row, int column, String value){
			wb.getSheetAt(0).getRow(row).getCell(column).setCellValue(value);
		}
		
		public static void setCellValue (Workbook wb, int row, int column, Date value){
			wb.getSheetAt(0).getRow(row).getCell(column).setCellValue(value);
		}
		
		public static void setCellValue (Workbook wb, int row, int column, int value){
			wb.getSheetAt(0).getRow(row).getCell(column).setCellValue(value);
		}
		
		public static void setCellValue (Workbook wb, int row, int column, double value){
			wb.getSheetAt(0).getRow(row).getCell(column).setCellValue(value);
		}
		
		
//======================================Croisement layout ==============================================================//
		//create row at the rowNumber position and create 11 cells per row and each row a a specific width
				public static void createRowCroisement(XSSFWorkbook wb, int rowNumber, boolean lastRow){
					Row row = wb.getSheetAt(0).createRow(rowNumber);
					int [] width = new int [] {18,13,33,33,11,13,10};
					for (int i = 0; i < 7; ++i){			
						wb.getSheetAt(0).setColumnWidth(i, width [i] * 257);
						row.createCell(i).setCellStyle(ExcelTools.croisementCellStyle (wb, rowNumber, i, lastRow));
					}
				}
				
				//generate complete cell Style for Croisement
				public static CellStyle croisementCellStyle (XSSFWorkbook wb, int rows, int column, boolean lastRow){
					XSSFCellStyle style = wb.createCellStyle();
					if (rows < 4) {
						colorStyle(style, false);
						fontStyle(wb, style);
					}
					if ((rows == 2 && column == 2) || (rows > 3 && column == 5) || (rows > 3 && column == 1)){
						dateStyle (style, wb);
					}
					if ((rows > 3) && (column == 0 || column == 2 || column == 3)){
						carriageStyle (style);
					}
					borderStyle (style, rows, column, lastRow, false);
					AlignmentStyle (style);
					return style;
				}
						

//======================================Stock layout ===================================================================//
		
		//create row at the rowNumber position and create 11 cells per row and each row a a specific width
		public static void createRowStock (XSSFWorkbook wb, int rowNumber, boolean lastRow){
			Row row = wb.getSheetAt(0).createRow(rowNumber);
			int [] width = new int [] {10,10,4,6,11,9,12,14,13,9,14};
			for (int i = 0; i < 11; ++i){			
				wb.getSheetAt(0).setColumnWidth(i, width [i] *256);
				row.createCell(i).setCellStyle(ExcelTools.stockCellStyle(wb, rowNumber, i, lastRow));
			}
		}
	
		
		//generate complete cell Style for Stock
		public static CellStyle stockCellStyle (XSSFWorkbook wb, int rows, int column, boolean lastRow){
			XSSFCellStyle style = wb.createCellStyle();
			if (rows < 3) {
				colorStyle(style, true);
				fontStyle(wb, style);
			}
			if ((rows == 1 && column == 2) || (rows >= 3 && column == 8)) {
				dateStyle (style, wb);
			}
			if ((rows == 2 && column == 7) || (rows > 2 && column == 0)){
				carriageStyle (style);
			}
			borderStyle (style, rows, column, lastRow, true);
			AlignmentStyle (style);
			return style;
		}
		
		
//==========================       CellStyle          ==========================================================//				
		//Border Style
		private static XSSFCellStyle borderStyle (XSSFCellStyle style, int rows, int column, boolean lastRow, boolean isStock){
			int tmp1 ;
			int tmp2 ;
			int nbrParameters = WriteCroisement.getNbrParameters();
			
			if (!isStock){
				tmp1 = 4;
				tmp2 = 6;
			}else {
				tmp1 = 3;
				tmp2 = 10;
			}
			
			if (rows == 0 || rows == tmp1){
				style.setBorderTop(BorderStyle.MEDIUM);
			} else if (isStock){
				style.setBorderTop(BorderStyle.THIN);
			} else {
				if ((rows > 3 && (rows % nbrParameters == 4)) || !(rows > 3 && (column == 2 || column ==3))){
					style.setBorderTop(BorderStyle.THIN);
				}	
			}
					
						
			if (column == 0){
				style.setBorderLeft(BorderStyle.MEDIUM);
			} else { 
				style.setBorderLeft(BorderStyle.THIN);
			}
			
			if (column == tmp2){
				style.setBorderRight(BorderStyle.MEDIUM);
			}
					
			if (lastRow){
				style.setBorderBottom(BorderStyle.MEDIUM);
			}
			
		return style;	
		}
						
		// align text 
		private static XSSFCellStyle AlignmentStyle (XSSFCellStyle style){
			style.setAlignment(HorizontalAlignment.CENTER);
	    	style.setVerticalAlignment(VerticalAlignment.CENTER);
			return style;
		}				

	    //make a carriage return
	    private static XSSFCellStyle carriageStyle (XSSFCellStyle style){
	    	style.setWrapText(true);
	    	return style;
	    }
	    	    
	    //Color cell background 
		public static XSSFCellStyle colorStyle (XSSFCellStyle style, boolean isStock){
			if (isStock){
				style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
			}else {
				style.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
			}
	    	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    	return style;
	    }
	    
	    //Font Style
		private static XSSFCellStyle fontStyle (XSSFWorkbook wb, XSSFCellStyle style){    	
			XSSFFont font = wb.createFont();
			font.setBold(true);
			style.setFont(font);
	    	return style;
	    }
	    
	  //defined date : "dd/mm/yyyy"
	    private static XSSFCellStyle dateStyle (XSSFCellStyle style, XSSFWorkbook wb){
			XSSFDataFormat xssfDataFormat = (XSSFDataFormat) wb.createDataFormat();
			style.setDataFormat(xssfDataFormat.getFormat("dd/mm/yyyy"));
			return style;
	    }

	    //convert date to string, format = "dd/mm/yyyy" or "YYYY" etc
	    public static String convertDateToString (Date date, String format){
	    	DateFormat df = new SimpleDateFormat(format);
	    	String str ="";
	    	if (date != null){
	    	str = df.format(date);
	    	}
			return str;
	    } 
	    		
}
