package algorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import enumeration.AnimalFacility;
import folderAndFile.PathSave;
import mice.CageCroisement;
import mice.Female;
import mice.Litter;
import mice.Male;
import tools.ExcelTools;
import tools.ExternallyFile;
import workbook.BodyCroisement;
import workbook.HeaderCroisement;

public class WriteCroisement {
	private static int nbrParameters ;
	
		public static void setNbrParameters (int nbr){
			WriteCroisement.nbrParameters = nbr;
		}
		
		public static int getNbrParameters(){
			return nbrParameters;
		}

		//fill out excel file : produce a complete Excel file, merge, data, justify etc...
		// input : Cage cage, ArrayList <Mouse> mouse , String fileName
		public static void generateExcelFile (String processName, ArrayList<CageCroisement> cageList) throws IOException, ClassNotFoundException {
			
			XSSFWorkbook wb = (XSSFWorkbook) ExcelTools.CreateWorkbook(processName);
			XSSFSheet sheet = wb.getSheetAt(0);
			sheet.setDisplayGridlines(false);
			
			
			//create header, and body empty cell
			HeaderCroisement head = new HeaderCroisement (wb);
			BodyCroisement body = new BodyCroisement (wb);
						
			int counterRows = 4;				
			//fill out the excel file
			for (int i = 0; i < cageList.size(); ++i){					
				boolean lastRow = false;																			
				int numberOfFemale = cageList.get(i).getFemaleList().size();
				int numberOfMale = cageList.get(i).getMaleList().size();
				ArrayList <Litter> litterList = (ArrayList<Litter>) cageList.get(i).getLitterList();
				int numberOfLitter = litterList.size();					
				
				
				//all female have 6 rows at least
				if (numberOfFemale != 0){
					if (numberOfMale <= numberOfFemale){
						numberOfLitter = numberOfFemale * nbrParameters;
					} else {
						numberOfLitter = numberOfMale * nbrParameters;
					}
				}
				//All cage have 2 * nbrParameters rows at least 
				if (numberOfLitter < litterList.size()){
					numberOfLitter = litterList.size();
				}
		
				
				//fill the litter information
				for (int j = 0; j < numberOfLitter; ++j){
					int rowNumber = counterRows;
					
					if (j == numberOfLitter -1){
						lastRow = true;
					}
					
					body.addRowBody(rowNumber, lastRow);
					if (j < litterList.size()){
						body.fillBodyLitter(rowNumber, litterList.get(j));
					}									
					++counterRows;
				}
									
				//fill female informations
				int startRowFemale = counterRows - numberOfLitter;
				for (int j = 0; j < numberOfFemale; ++j){
					Female female = cageList.get(i).getFemaleList().get(j);
					body.fillBodyParents(startRowFemale, female, 3);
					startRowFemale += nbrParameters;
				}
				
				//fill male informations
				int startRowMale = counterRows - numberOfLitter;
				for (int j = 0; j < numberOfMale; ++j){
					Male male = cageList.get(i).getMaleList().get(j);
					body.fillBodyParents(startRowMale, male, 2);
					startRowMale += nbrParameters;
				}

				
				//fill cage information 
				int startCageRow = counterRows - numberOfLitter;
				int endCageRow   = counterRows - 1;
				
				//opening date
				Date date = cageList.get(i).getBreedingOpeningDate();
				body.fillBodyOpeningDate(date, startCageRow, endCageRow);
				
				//others cage  information
				String codeBr = cageList.get(i).getCodeCage();
				String numBr = cageList.get(i).breedingName();
				String numCage = cageList.get(i).cageNumber();
				body.fillBodyCageInformation(codeBr, numBr, numCage, startCageRow, endCageRow);
			}
						
			//
			String line = cageList.get(0).getLine();
			int numberOfCage = cageList.size();
			
			//fill out header and merge cells : wb, rowFrom, rowTo, colFrom, colTo	
			head.layoutHeader();
			head.fillHeader(processName, line, numberOfCage);
			
			
			//load saveAdress
			String adress ="";
			
			PathSave path = ExternallyFile.config.getInstallPath();
			if (Run.animalFacility == AnimalFacility.Nac){
				adress = path.getNacBreedingFolder();
			}else if (Run.animalFacility == AnimalFacility.a105) {
				adress = path.getA105BreedingFolder();
				System.out.println("ici " + adress);
			}
			
			
			//save
			try {
				ExcelTools.saveWorkbook(wb, adress, processName);
			} catch (IOException e){
				JOptionPane .showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			}

		}
}
