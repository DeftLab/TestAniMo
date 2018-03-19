package options;

import enumeration.AnimalFacility;


public class Mice105Options extends MiceOptions {


	private AnimalFacility animalFacility;

	public Mice105Options(boolean maleNum, boolean maleLine, boolean maleDate, boolean maleGeno, boolean maleGene, boolean maleCode,
							boolean femaleNum, boolean femaleLine, boolean femaleDate, boolean femaleGeno,boolean femaleGene, boolean femaleCode) {
		super(maleNum, maleLine, maleDate, maleGeno, maleGene, maleCode,
			  femaleNum, femaleLine, femaleDate, femaleGeno, femaleGene, femaleCode);
		this.animalFacility = AnimalFacility.a105;
	}
	
	public AnimalFacility getAnimalFacility (){
		return animalFacility;
	}
	

}
