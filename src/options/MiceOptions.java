package options;

import java.io.Serializable;


public class MiceOptions implements Serializable {


	private boolean  maleNum,    maleLine,   maleDate,    maleGeno,   maleGene,   maleCode,
	                 femaleNum, femaleLine, femaleDate, femaleGeno, femaleGene, femaleCode ;

	
	public MiceOptions (boolean maleNum, boolean maleLine, boolean  maleDate, boolean   maleGeno, boolean  maleGene, boolean  maleCode,
			           boolean femaleNum, boolean femaleLine, boolean femaleDate, boolean femaleGeno, boolean femaleGene, boolean femaleCode){

		this.maleNum = maleNum;
		this.maleLine = maleLine;
		this.maleDate = maleDate;
		this.maleGeno = maleGeno;
		this.maleGene = maleGene;
		this.maleCode = maleCode;
		this.femaleNum = femaleNum;
		this.femaleLine = femaleLine;
		this.femaleDate = femaleDate;
		this.femaleGeno = femaleGeno;
		this.femaleGene = femaleGene;
		this.femaleCode = femaleCode;
	}


	//getters
	public boolean isMaleNum() {
		return maleNum;
	}

	public boolean isMaleLine() {
		return maleLine;
	}

	public boolean isMaleDate() {
		return maleDate;
	}

	public boolean isMaleGeno() {
		return maleGeno;
	}

	public boolean isMaleGene() {
		return maleGene;
	}

	public boolean isMaleCode() {
		return maleCode;
	}

	public boolean isFemaleNum() {
		return femaleNum;
	}

	public boolean isFemaleLine() {
		return femaleLine;
	}

	public boolean isFemaleDate() {
		return femaleDate;
	}

	public boolean isFemaleGeno() {
		return femaleGeno;
	}

	public boolean isFemaleGene() {
		return femaleGene;
	}

	public boolean isFemaleCode() {
		return femaleCode;
	}


	//Setters
	public void setMaleNum(boolean maleNum) {
		this.maleNum = maleNum;
	}

	public void setMaleLine(boolean maleLine) {
		this.maleLine = maleLine;
	}

	public void setMaleDate(boolean maleDate) {
		this.maleDate = maleDate;
	}

	public void setMaleGeno(boolean maleGeno) {
		this.maleGeno = maleGeno;
	}

	public void setMaleGene(boolean maleGene) {
		this.maleGene = maleGene;
	}

	public void setMaleCode(boolean maleCode) {
		this.maleCode = maleCode;
	}

	public void setFemaleNum(boolean femaleNum) {
		this.femaleNum = femaleNum;
	}

	public void setFemaleLine(boolean femaleLine) {
		this.femaleLine = femaleLine;
	}

	public void setFemaleDate(boolean femaleDate) {
		this.femaleDate = femaleDate;
	}

	public void setFemaleGeno(boolean femaleGeno) {
		this.femaleGeno = femaleGeno;
	}

	public void setFemaleGene(boolean femaleGene) {
		this.femaleGene = femaleGene;
	}

	public void setFemaleCode(boolean femaleCode) {
		this.femaleCode = femaleCode;
	}
		
}	