package mice;

import java.io.IOException;
import java.util.ArrayList;

import algorithm.WriteCroisement;

public class ProcessCroisement extends Process{
	
	private ArrayList<CageCroisement> cage;

	public ProcessCroisement(ArrayList<CageCroisement> cage, String key) throws IOException {
		super(key);
		this.cage = cage;
	}


		@Override
		protected void compute() {
			try {
				WriteCroisement.generateExcelFile(super.getKey(), cage);
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}

}

