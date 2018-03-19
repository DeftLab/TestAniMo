package options;

import enumeration.AnimalFacility;

public class CageNacOptions extends CageOptions {


	private  AnimalFacility animalFacility;
	
	public CageNacOptions (boolean codeBreeding, boolean numBreeding, boolean numberCage){
		super (codeBreeding,  numBreeding, numberCage);
		this.animalFacility = AnimalFacility.Nac; 
	}

	public AnimalFacility getAnimalFacility() {
		return animalFacility;
	}
	

}
