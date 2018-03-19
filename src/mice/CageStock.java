package mice;

import java.util.ArrayList;

public class CageStock extends Cage{

	private ArrayList<MouseStock> cageMouseList;
	private boolean reproducer = false;
	
	
	public CageStock (ArrayList <MouseStock> cageMouse, String cageName){
		super (cageName, cageMouse.get(0).getProcess());
		cageMouseList = cageMouse;	
	}

	public ArrayList<MouseStock> getCageMouseList() {
		return cageMouseList;
	}


	public boolean isReproducer() {
		return reproducer;
	}
	
	
}
