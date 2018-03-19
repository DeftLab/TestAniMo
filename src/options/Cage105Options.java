package options;

import enumeration.AnimalFacility;

public class Cage105Options extends CageOptions {



	private  AnimalFacility animalFacility;

	
	public Cage105Options (boolean codeBreeding, boolean numBreeding, boolean numberCage){
		super (codeBreeding,  numBreeding, numberCage);
		this.animalFacility = AnimalFacility.a105; 
	}


	public AnimalFacility getAnimalFacility() {
		return animalFacility;
	}
	
	

}
