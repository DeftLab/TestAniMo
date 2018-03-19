package mice;

import java.io.IOException;
import java.util.ArrayList;

import algorithm.WriteStock;

public class ProcessStock extends Process{
	
	private ArrayList<CageStock> cage;

	public ProcessStock(ArrayList<CageStock> cage, String key) throws IOException {
		super(key);
		this.cage = cage;
	}


		@Override
		protected void compute() {
			try {
				WriteStock.generateExcelFile(super.getKey(), cage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}

}
