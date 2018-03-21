package algorithm;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import exception.UpdateException;
import mice.CageCroisement;
import tools.Check;
import tools.ExternallyFile;
import tools.OpenBrowser;
import tools.Update;


public class RunCroisement extends Run{
	
	protected static Map<String, List<CageCroisement>> mapProcess;

	public RunCroisement (Enum<?> animalFacility) throws EncryptedDocumentException, InvalidFormatException, IOException, ClassNotFoundException {
		super (animalFacility);

					
		//READ
		//read the Excel workbook source
		boolean checkFile = false;
		boolean checkExcelFile = false;
		ReadCroisement source = null;
		String readPath;
		try{
			do {
				do{
					readPath = OpenBrowser.selectExcelFile();
					checkFile = Check.checkFile (readPath);			
				} while (!checkFile) ;
				
				if (!readPath.equals("")){
					source = new ReadCroisement (readPath, "croisement");
					checkExcelFile = Check.checkExcelFile (source.getWb(), false);
				} else {
					checkExcelFile =true;
				}
			} while (!checkExcelFile);
		}catch (NullPointerException e){
			readPath = "";
		}	
		
		//check if all column are good or if this programm need an update
		if (!readPath.equals("")){
			if (!Check.checkUpdate(source.getWb(), "croisement")){
				long startTime = System.currentTimeMillis();
				if (!readPath.equals("")){	
					
					 source.readSource();
					
					//SORTING
					//crate a map with processName for key and CageList for value
					
					mapProcess = SortingCroisement.sortProcess(source.getCageList());
					

					Set cles = mapProcess.keySet();
					Iterator it = cles.iterator();
					while (it.hasNext()){
					   Object cle = it.next(); // tu peux typer plus finement ici
					   Object valeur = mapProcess.get(cle); // tu peux typer plus finement ici
					   System.out.println("process = " + cle);
					}
					
					//WRITE
					//Create an excel file for each Process
					new ScanMap("croisement");
	
					JOptionPane.showMessageDialog(null, "L'export est fini, temps : " + (System.currentTimeMillis() - startTime) + " ms.", "Information", JOptionPane.INFORMATION_MESSAGE);		
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
