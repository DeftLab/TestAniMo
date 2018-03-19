package algorithm;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import exception.UpdateException;
import mice.CageStock;
import tools.Check;
import tools.ExternallyFile;
import tools.OpenBrowser;
import tools.Update;



public class RunStock extends Run  {

	public static Map<String,List<CageStock>> mapProcess; 
	
	public RunStock (Enum<?> animalFacility)  throws EncryptedDocumentException, InvalidFormatException, IOException, ClassNotFoundException {
		super (animalFacility);

	
		//READ
		//read the Excel workbook source
		boolean checkFile = false;
		boolean checkExcelFile = false;
		String readPath;
		ReadStock source = null;
		try {
			do {
				do {
					readPath = OpenBrowser.selectExcelFile();
					checkFile = Check.checkFile (readPath);
				} while (!checkFile);
				if (!readPath.equals("")){
					source = new ReadStock (readPath, "stock");	
					checkExcelFile = Check.checkExcelFile (source.wb, true);
				}else{
					checkExcelFile = true;
				}
			} while (!checkExcelFile);
		}catch (NullPointerException e){
			readPath = "";
		}
		
		//check if all column are good or if this programm need an update
		if (!readPath.equals("")){
				if (!Check.checkUpdate(source.wb, "stock")){
					
					long startTime = System.currentTimeMillis();
					if (!readPath.equals("")){
							
						source.readSource();
						
						//SORTING
						//Generate Cage with an mice arrayList for each Cage 
						ArrayList<CageStock> allCageSort =  SortingStock.cageConstructor (source.getMiceList());
						mapProcess = SortingStock.sortProcess(allCageSort);
															
						//WRITE	
					    new ScanMap("stock");
					   
						JOptionPane.showMessageDialog(null, "L'export est fini, temps : " + (System.currentTimeMillis() - startTime) +" ms.", "Information", JOptionPane.INFORMATION_MESSAGE);
					}	
					
				} else {

					try {
						throw new UpdateException (Update.updatingColumnLetter, Update.updatingColumnLabel);
								
					}catch (UpdateException e){
						
					}
				}
		}	
	}
		
}
