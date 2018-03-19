package workbook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import algorithm.Run;
import algorithm.WriteCroisement;
import enumeration.AnimalFacility;
import mice.Female;
import mice.Litter;
import mice.Male;
import mice.Mouse;
import options.CageOptions;
import options.MiceOptions;
import tools.DateComparator;
import tools.ExcelTools;
import tools.ExternallyFile;

public class BodyCroisement {
	private XSSFWorkbook wb;
	MiceOptions miceOptions;
	CageOptions cageOptions;

	public BodyCroisement(XSSFWorkbook wb) throws FileNotFoundException, ClassNotFoundException, IOException{
		this.wb = wb;
		
		if (Run.animalFacility == AnimalFacility.Nac){
			miceOptions = ExternallyFile.config.getMiceNacOptions();
			cageOptions = ExternallyFile.config.getCageNacOptions();
		}else if (Run.animalFacility == AnimalFacility.a105){
			miceOptions = ExternallyFile.config.getMice105Options();
			cageOptions = ExternallyFile.config.getCage105Options();
		}
		
		WriteCroisement.setNbrParameters(nbrParametersTrue ());
	}
	
	public void addRowBody (int rowNumber, boolean lastRow){
		ExcelTools.createRowCroisement (wb, rowNumber, lastRow);
	}
	
	//Create one Row of Body
	public void fillBodyLitter (int rowNumber, Litter litter){
		String bornAndDeath = litter.getNewBornsNumber() + " (-" + litter.getNewBornDeathNUmber() + ")"; 
		ExcelTools.setCellValue(wb, rowNumber, 4, bornAndDeath);
		ExcelTools.setCellValue(wb, rowNumber, 5, litter.getNewBornsDate());
		if (litter.getNewBornsDate() != null){
			ExcelTools.setCellValue(wb, rowNumber, 6, DateComparator.differenceBetween2Dates(litter.getNewBornsDate()));
		}
	}	
	
	//Complete female information
	public void fillBodyParents (int rowNumber, Mouse mouse, int column){
		boolean num, date, line, geno, gene, code ;
		
		if (mouse instanceof Female){
			mouse = (Female) mouse;
			num  = miceOptions.isFemaleNum();
			date = miceOptions.isFemaleDate();
			line = miceOptions.isFemaleLine();
			geno = miceOptions.isFemaleGeno();
			gene = miceOptions.isFemaleGene();
			code = miceOptions.isFemaleCode();
		}else {
			mouse = (Male) mouse;
			num  = miceOptions.isMaleNum();
			date = miceOptions.isMaleDate();
			line = miceOptions.isMaleLine();
			geno = miceOptions.isMaleGeno();
			gene = miceOptions.isMaleGene();
			code = miceOptions.isMaleCode();
		}
		mouseInformation (mouse, rowNumber, column, num, date, line, geno, gene, code);	
	}
	
	//  female or male information, add boolean for each parameters and create option
	private void mouseInformation (Mouse mouse, int rowNumber, int column, boolean num, boolean date, boolean line, boolean geno, boolean gene, boolean  code){
		String str ;
		if (!mouse.getCode().equals("")){
			if (num){
				str = "Num : " + mouse.getName();
				ExcelTools.setCellValue(wb, rowNumber, column, str);
				++rowNumber;
			}
			
			if (date){
				str = ExcelTools.convertDateToString(mouse.getBirthDate(), "dd/MM/YYYY");
				ExcelTools.setCellValue(wb, rowNumber, column, str);
				++rowNumber;
			}
			
			if (line){
				str = "Line : " + mouse.getLine();
				ExcelTools.setCellValue(wb, rowNumber, column, str);
				++rowNumber;
			}
			 
			if (geno){
				str = "Geno : " + mouse.getGenotype();
				ExcelTools.setCellValue(wb, rowNumber, column, str);
				++rowNumber;
			}
			
			if (gene){
				str = "Gene : " + mouse.getGene();
				ExcelTools.setCellValue(wb, rowNumber, column, str);
				++rowNumber;
			}
			
			if (code){
				str = "Code : " + mouse.getCode();
				ExcelTools.setCellValue(wb, rowNumber, column, str);
				++rowNumber;
			}
		}	
	}
	
	public void fillBodyOpeningDate (Date date, int startRow, int endRow){
		ExcelTools.setCellValue(wb, startRow, 1, date);
		ExcelTools.merge(wb, startRow, endRow, 1, 1);
	}
	
	public void fillBodyCageInformation (String codeBr, String numBr, String numCage, int startRow, int endRow){
		String str ="" ;
		if (cageOptions.isNumBreeding()){
			str += "Num Br: " + numBr + "\n";
		}
		if (cageOptions.isCodeBreeding()){
			str += "Code Br: " +codeBr + "\n";
		}
		if (cageOptions.isNumberCage()){
			str += "Num cage: " + numCage + "\n";
		}
		ExcelTools.setCellValue(wb, startRow, 0, str);
		ExcelTools.merge(wb, startRow, endRow, 0, 0);
	}
	
	public int nbrParametersTrue (){
		int nbrPar ;
		int nbrParM = nbrParametersM ();
		int nbrParF = nbrParametersF ();
		
		if (nbrParM > nbrParF){
			nbrPar = nbrParM;
		}else {
			nbrPar = nbrParF;
		}
		
		if (nbrPar < 3){
			nbrPar = 3;
		}
		
		return nbrPar;
	}

	public int nbrParametersM (){
		int nbrParameters = 0;
		if (miceOptions.isMaleCode()){
			++nbrParameters;
		}	
		if (miceOptions.isMaleDate()){
			++nbrParameters;
		}
		if (miceOptions.isMaleGene()){
			++nbrParameters;
		}
		if (miceOptions.isMaleGeno()){
			++nbrParameters;
		}
		if (miceOptions.isMaleLine()){
			++nbrParameters;
		}
		if (miceOptions.isMaleNum()){
			++nbrParameters;
		}
		return nbrParameters;
	}
	
	public int nbrParametersF (){
		int nbrParameters = 0;
		if (miceOptions.isFemaleCode()){
			++nbrParameters;
		}	
		if (miceOptions.isFemaleDate()){
			++nbrParameters;
		}
		if (miceOptions.isFemaleGene()){
			++nbrParameters;
		}
		if (miceOptions.isFemaleGeno()){
			++nbrParameters;
		}
		if (miceOptions.isFemaleLine()){
			++nbrParameters;
		}
		if (miceOptions.isFemaleNum()){
			++nbrParameters;
		}
		return nbrParameters;
	}
		
}
