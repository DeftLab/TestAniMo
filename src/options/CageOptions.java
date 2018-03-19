package options;

import java.io.Serializable;


public class CageOptions implements Serializable{
	

	private boolean codeBreeding, numBreeding, numberCage ;

	public CageOptions(boolean codeBreeding, boolean numBreeding, boolean numberCage) {
		this.codeBreeding = codeBreeding;
		this.numBreeding = numBreeding;
		this.numberCage = numberCage;
	}

	//getters
	public boolean isCodeBreeding() {
		return codeBreeding;
	}

	public boolean isNumBreeding() {
		return numBreeding;
	}

	public boolean isNumberCage() {
		return numberCage;
	}
	
	// setters
	public void setCodeBreeding(boolean codeBreeding) {
		this.codeBreeding = codeBreeding;
	}

	public void setNumBreeding(boolean numBreeding) {
		this.numBreeding = numBreeding;
	}

	public void setNumberCage(boolean numberCage) {
		this.numberCage = numberCage;
	}
}	
		

