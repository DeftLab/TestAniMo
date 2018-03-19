package algorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import enumeration.SortingType;
import mice.MouseStock;
import tools.ExternallyFile;


public class ReadStock extends Read {
	private ArrayList<MouseStock> miceList;
	private ArrayList<Integer> columnPosition;
	
	public ReadStock (String sourcePath, String croisementOrStock) throws EncryptedDocumentException, InvalidFormatException, IOException, ClassNotFoundException {
		super (sourcePath, SortingType.stock.name());
		saveWbIntoSourceFolder (wb);
		columnPosition = ExternallyFile.config.getStockColumnPosition();
	}

	
	public ArrayList<MouseStock> getMiceList() {
		return miceList;
	}



	public void setMiceList(ArrayList<MouseStock> miceList) {
		this.miceList = miceList;
	}



	//read an excell file and complete the Mice ArrayList  
	public void readSource () throws IOException{	
			int rowNumber = wb.getSheetAt(0).getLastRowNum();
			miceList = new ArrayList<MouseStock>();
			for (int i = 0; i < rowNumber ; ++i){
				miceList.add(newMouse(i+1));
			}
			wb.close();
	}
	
	//create new Mice ojbect with information read in Workbook and return a new Mouse; 
	private MouseStock newMouse (int i) {
	
				String code        = wb.getSheetAt(0).getRow(i).getCell(columnPosition.get(0)).getStringCellValue();
				String line        = wb.getSheetAt(0).getRow(i).getCell(columnPosition.get(1)).getStringCellValue();
				String name        = wb.getSheetAt(0).getRow(i).getCell(columnPosition.get(2)).getStringCellValue();
				String sex         = wb.getSheetAt(0).getRow(i).getCell(columnPosition.get(3)).getStringCellValue();
				Date   birthDate   = wb.getSheetAt(0).getRow(i).getCell(columnPosition.get(4)).getDateCellValue();
				String genotype    = wb.getSheetAt(0).getRow(i).getCell(columnPosition.get(5)).getStringCellValue();				
				boolean breeding   = wb.getSheetAt(0).getRow(i).getCell(columnPosition.get(6)).getBooleanCellValue();
				String strain      = wb.getSheetAt(0).getRow(i).getCell(columnPosition.get(7)).getStringCellValue();
				String procedure   = wb.getSheetAt(0).getRow(i).getCell(columnPosition.get(8)).getStringCellValue();
				String cage        = wb.getSheetAt(0).getRow(i).getCell(columnPosition.get(9)).getStringCellValue();
				String origin      = wb.getSheetAt(0).getRow(i).getCell(columnPosition.get(10)).getStringCellValue();
				String service     = wb.getSheetAt(0).getRow(i).getCell(columnPosition.get(11)).getStringCellValue();
				String BrOrigin ="";
				if (origin.length() > 0){
					 BrOrigin    = catchBrNumber(origin);
				}
							
		return	new MouseStock (code, line, name, sex, birthDate, genotype, breeding, strain, cage, origin, procedure, service, BrOrigin);
	}
	
	//catch Br number into String Origin
	//output string Br number
	private String catchBrNumber (String origin){
		int start = origin.lastIndexOf('/') +1;	
		return origin.substring(start);
	}
		
}
