package algorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mice.CageCroisement;
import mice.CageStock;
import mice.ProcessCroisement;
import mice.ProcessStock;

public class ScanMap {

	public ScanMap(String stockOrCroisement) throws IOException {
		// Create an excel file for each Process
		if (stockOrCroisement.equals("stock")) {
			runStock();
		} else if (stockOrCroisement.equals("croisement")) {
			runCroisement();
		}

	}

	private void runStock() throws IOException {
		for (Map.Entry<String, List<CageStock>> entry : RunStock.mapProcess.entrySet()) {
			ArrayList<CageStock> cage = (ArrayList<CageStock>) entry.getValue();
			String key = entry.getKey();
			ProcessStock process = new ProcessStock(cage, key);
			process.fork();
		}

	}

	private void runCroisement() throws IOException {
		for (Map.Entry<String, List<CageCroisement>> entry : RunCroisement.mapProcess.entrySet()) {
			// cage are sorted with Br number
			ArrayList<CageCroisement> sortedCage = SortingCroisement
					.sortingCage((ArrayList<CageCroisement>) entry.getValue());
			String key = entry.getKey();
			ProcessCroisement process = new ProcessCroisement(sortedCage, key);
			process.fork();
		}

	}

}
