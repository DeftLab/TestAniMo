package mice;

import java.util.Date;

public abstract class Mouse {
	
	private String line, genotype, strain, name ;
	private Date birthDate;
	
	public Mouse(String line, String name, Date birthDate, String genotype, String strain){
		this.line = line;
		this.birthDate = birthDate;
		this.genotype = genotype;
		this.strain = strain;		
		this.name = name;
	}
	public Mouse (){
		this.line = null;
		this.birthDate = null;
		this.genotype = null;
		this.strain = null;		
		this.name = null;
	}

	public String getLine() {
		return line;
	}

	public String getGenotype() {
		return genotype;
	}

	public String getStrain() {
		return strain;
	}


	public String getName() {
		return name;
	}

	public Date getBirthDate() {
		return birthDate;
	}
	
	public abstract String getGene();
	
	public abstract String getCode();
	
	public void setLine(String line) {
		this.line = line;
	}
	public void setGenotype(String genotype) {
		this.genotype = genotype;
	}
	public void setStrain(String strain) {
		this.strain = strain;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setName (String name){
		this.name = name;
	}
}
