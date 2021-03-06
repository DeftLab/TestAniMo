package algorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import enumeration.SortingType;
import mice.CageCroisement;
import tools.ExternallyFile;

public class ReadCroisement extends Read {
	private ArrayList<CageCroisement> cageList = new ArrayList<CageCroisement>();
	private CageCroisement cage;

	public ReadCroisement(String sourcePath, String croisementOrStock)
			throws EncryptedDocumentException, InvalidFormatException, IOException, ClassNotFoundException {
		super(sourcePath, SortingType.croisement.name());
		saveWbIntoSourceFolder(wb);
	}

	public ArrayList<CageCroisement> getCageList() {
		return cageList;
	}

	// read an excell file and complete the Mice ArrayList
	public void readSource() throws IOException {

		int rowNumber = wb.getSheetAt(0).getLastRowNum();

		for (int i = 2; i < rowNumber; ++i) {
			Cell cell = wb.getSheetAt(0).getRow(i).getCell(0);

			if (cell.getCellTypeEnum() == CellType.STRING) {

				if (cell.getStringCellValue().contains("Code")) {
					createCage(i, cell.getStringCellValue());
					cageList.add(cage);
				} else if (cell.getStringCellValue().contains("Femelle")) {
					createFemale(i);
				}

			} else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
				// Cr�ation du male
				String maleCode = wb.getSheetAt(0).getRow(i)
						.getCell(ExternallyFile.config.getCroisColumnPosition().get(5)).getStringCellValue();
				if (!maleCode.equals("")) {
					createMale(i);
				}
				// Litter information : create new Litter and add into
				createLitter(i);

			}

		}
				
	}

	// Create a new Male(String code, String line, String name, Date birthDate,
	// String genotype, String strain, String gene)
	private void createMale(int i) {
		String code = wb.getSheetAt(0).getRow(i).getCell(ExternallyFile.config.getCroisColumnPosition().get(3))
				.getStringCellValue(); // codeMale column AB
		String line = wb.getSheetAt(0).getRow(i).getCell(ExternallyFile.config.getCroisColumnPosition().get(6))
				.getStringCellValue();
		String name = wb.getSheetAt(0).getRow(i).getCell(ExternallyFile.config.getCroisColumnPosition().get(4))
				.getStringCellValue(); // Marque column AC
		Date birthDate = wb.getSheetAt(0).getRow(i).getCell(ExternallyFile.config.getCroisColumnPosition().get(9))
				.getDateCellValue();
		String genotype = wb.getSheetAt(0).getRow(i).getCell(ExternallyFile.config.getCroisColumnPosition().get(8))
				.getStringCellValue();
		String strain = wb.getSheetAt(0).getRow(i).getCell(ExternallyFile.config.getCroisColumnPosition().get(5))
				.getStringCellValue();
		String gene = wb.getSheetAt(0).getRow(i).getCell(ExternallyFile.config.getCroisColumnPosition().get(7))
				.getStringCellValue();

		cage.newMale(code, line, name, birthDate, genotype, strain, gene);
	}

	// Create and return a new Litter(String litterCode, int newBornsNumber, int
	// weanedMiceNumber, Date newBornsDate)
	// input : workbook, litter arrayList, int i
	private void createLitter(int i) {
		int litter = (int) wb.getSheetAt(0).getRow(i).getCell(ExternallyFile.config.getCroisColumnPosition().get(20))
				.getNumericCellValue();
		if (litter == 1) {
			String litterCode = wb.getSheetAt(0).getRow(i)
					.getCell(ExternallyFile.config.getCroisColumnPosition().get(18)).getStringCellValue();
			int newBornsNumber = (int) wb.getSheetAt(0).getRow(i)
					.getCell(ExternallyFile.config.getCroisColumnPosition().get(21)).getNumericCellValue();

			Cell cell = wb.getSheetAt(0).getRow(i).getCell(ExternallyFile.config.getCroisColumnPosition().get(22));
			int nbrDiedMice = 0;
			if (cell.getCellTypeEnum() == CellType.STRING) {
				nbrDiedMice = Integer.parseInt(
						"wb.getSheetAt(0).getRow(i).getCell(ExternallyFile.config.getCroisColumnPosition().get(22)).getNumericCellValue()");
			} else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
				nbrDiedMice = (int) wb.getSheetAt(0).getRow(i)
						.getCell(ExternallyFile.config.getCroisColumnPosition().get(22)).getNumericCellValue();
			}
			Date newBornsDate = wb.getSheetAt(0).getRow(i)
					.getCell(ExternallyFile.config.getCroisColumnPosition().get(19)).getDateCellValue();

			cage.newLitter(litterCode, newBornsNumber, nbrDiedMice, newBornsDate);
		}
	}

	// create a new female (line, name, birthDate, genotype, strain, gene, code
	// )
	private void createFemale(int i) {
		String line = wb.getSheetAt(0).getRow(i + 1).getCell(ExternallyFile.config.getCroisColumnPosition().get(14))
				.getStringCellValue();
		Date birthDate = wb.getSheetAt(0).getRow(i + 1).getCell(ExternallyFile.config.getCroisColumnPosition().get(17))
				.getDateCellValue();
		String genotype = wb.getSheetAt(0).getRow(i + 1).getCell(ExternallyFile.config.getCroisColumnPosition().get(16))
				.getStringCellValue();
		String name = wb.getSheetAt(0).getRow(i + 1).getCell(ExternallyFile.config.getCroisColumnPosition().get(18))
				.getStringCellValue(); // marque
		String strain = wb.getSheetAt(0).getRow(i + 1).getCell(ExternallyFile.config.getCroisColumnPosition().get(13))
				.getStringCellValue();
		String gene = wb.getSheetAt(0).getRow(i + 1).getCell(ExternallyFile.config.getCroisColumnPosition().get(15))
				.getStringCellValue();
		String code = wb.getSheetAt(0).getRow(i).getCell(ExternallyFile.config.getCroisColumnPosition().get(0))
				.getStringCellValue();

		cage.newFemale(line, name, birthDate, genotype, strain, gene, code);
	}

	// create a new cage
	private void createCage(int i, String tmp) {
		String processName = wb.getSheetAt(0).getRow(i + 2)
				.getCell(ExternallyFile.config.getCroisColumnPosition().get(11)).getStringCellValue();
		String line = wb.getSheetAt(0).getRow(i + 2).getCell(ExternallyFile.config.getCroisColumnPosition().get(10))
				.getStringCellValue();
		String codeCage = tmp.substring(tmp.indexOf(':') + 2); // ex : value =
																// "Code:
																// 975-16" ->
																// keep only
																// "975-16"
		String cageNumber = wb.getSheetAt(0).getRow(i + 2)
				.getCell(ExternallyFile.config.getCroisColumnPosition().get(2)).getStringCellValue();
		String breedingName = wb.getSheetAt(0).getRow(i + 2)
				.getCell(ExternallyFile.config.getCroisColumnPosition().get(1)).getStringCellValue();
		Date openingDate = wb.getSheetAt(0).getRow(i + 2).getCell(ExternallyFile.config.getCroisColumnPosition().get(0))
				.getDateCellValue();

		cage = new CageCroisement(codeCage, processName, line, cageNumber, breedingName, openingDate);
	}
}
