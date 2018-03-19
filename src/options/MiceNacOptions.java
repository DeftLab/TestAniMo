package options;

import enumeration.AnimalFacility;


public class MiceNacOptions extends MiceOptions{



	private  AnimalFacility animalFacility;

	public MiceNacOptions (boolean maleNum, boolean maleLine, boolean maleDate, boolean maleGeno, boolean maleGene, boolean maleCode,
							boolean femaleNum, boolean femaleLine, boolean femaleDate, boolean femaleGeno,boolean femaleGene, boolean femaleCode) {
		super(maleNum, maleLine, maleDate, maleGeno, maleGene, maleCode,
			  femaleNum, femaleLine, femaleDate, femaleGeno, femaleGene, femaleCode);
		this.animalFacility = AnimalFacility.Nac; 
	}

	public AnimalFacility getAnimalFacility() {
		return animalFacility;
	}

	
}
