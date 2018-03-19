package mice;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class CageCroisement extends Cage {
	private String cageNumber, line, breedingName;
	private HashSet <Male> maleSet ;
	private ArrayList <Male> maleList;
	private ArrayList <Female> femaleList;
	private ArrayList <Litter> litterList;
	private Date breedingOpeningDate;
	
	public CageCroisement(String codeCage, String processName, String line, String cageNumber, String breedingName, Date breedingOpeningDate) {
		super (codeCage, processName);
		this.cageNumber = cageNumber;
		this.maleSet = new HashSet <Male>();
		this.femaleList = new ArrayList <Female>();
		this.litterList = new ArrayList <Litter>();
		this.breedingOpeningDate =breedingOpeningDate;
		this.line = line;
		if (breedingName.equals("")){
			breedingName = "Ukn";
		}
		this.breedingName = breedingName;
	}

	public String cageNumber() {
		return cageNumber;
	}
	
	public String breedingName() {
		return breedingName;
	}
	
	public String getLine (){
		return line;
	}

	public ArrayList<Female> getFemaleList() {
		return femaleList;
	}

	
	public HashSet<Male> getMaleSet() {
		return maleSet;
	}

	public ArrayList<Litter> getLitterList() {
		return litterList;
	}

	public Date getBreedingOpeningDate() {
		return breedingOpeningDate;
	}
	
	public ArrayList <Male> getMaleList () {
		return maleList;
	}
	
	//convert HashSet to ArrayList
	public void setMaleList (){
		maleList = new ArrayList <Male>(maleSet);
	}
	
	public void setBrName (String newName){
		this.breedingName = newName;
	}
		
	private void newObjectIntoList (Object ob){
		if (ob instanceof Male){
			maleSet.add((Male)ob);
			setMaleList ();
		}else if (ob instanceof Female){
			femaleList.add((Female)ob);
		}else {
			litterList.add((Litter) ob);
		}
	}
	
	//create a new male and add it into arraylist
	public void newMale (String code, String line, String name, Date birthDate, String genotype, String strain, String gene){
		newObjectIntoList (new Male (code, line, name, birthDate, genotype, strain, gene));		
	}
	
	//create a new female and add it into arraylist
	public void newFemale (String line, String name, Date birthDate, String genotype, String strain, String gene, String code){
		newObjectIntoList (new Female (line, name, birthDate, genotype, strain, gene, code));		
	}
	
	//create a new litter and add it into arraylist
	public void newLitter (String litterCode, int newBornsNumber, int weanedMiceNumber, Date newBornsDate){
		newObjectIntoList (new Litter (litterCode, newBornsNumber, weanedMiceNumber, newBornsDate));		
	}
}
