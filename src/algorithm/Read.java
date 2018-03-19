package algorithm;


import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.record.RecordInputStream.LeftoverDataException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import enumeration.AnimalFacility;
import enumeration.SortingType;
import folderAndFile.PathSave;
import tools.ExcelTools;
import tools.ExternallyFile;



public abstract class Read {
	protected Workbook wb;
	private String sortingType;
	
	
	public Read(String sourcePath, String sortingType) throws EncryptedDocumentException, InvalidFormatException, LeftoverDataException, IOException{
		this.sortingType = sortingType;
		try {
			
		wb = WorkbookFactory.create (new BufferedInputStream(
                    						new DataInputStream(
                    							new FileInputStream(
                    									new File(sourcePath)))));
		
		}
	
		catch (EncryptedDocumentException e){
			JOptionPane .showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			
		}
		catch (LeftoverDataException e){
			JOptionPane .showMessageDialog(null, e.getMessage()
					+ "\n le fichier Excel est vérouillé, il faut l'ouvrir, cliquer sur \"enable editing \", sauvegarder et quitter avant de relancer le tri", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		catch (Exception e){
			e.getMessage();
		}
	}
	
	protected void saveWbIntoSourceFolder (Workbook wb) throws FileNotFoundException, ClassNotFoundException, IOException{
		Date date = new Date();
		String type ="";
		if (sortingType.equals(SortingType.croisement.name())){
			type = "Br";
		}else if (sortingType.equals(SortingType.stock.name())){
			type = "Stock";
		}
		String fileName = ExcelTools.convertDateToString(date, "YYYY_MM_dd ") + type;
		PathSave path = ExternallyFile.config.getInstallPath();
		
		
		String adress ="";
		if (Run.animalFacility == AnimalFacility.Nac){
			adress = path.getNacSourceFolder();
		}else if (Run.animalFacility == AnimalFacility.a105) {
			adress = path.getA105SourceFolder();
		}
		ExcelTools.saveWorkbook((Workbook) wb, adress, fileName);
	}
	
	public Workbook getWb (){
		return wb;
	}
}
