package mice;

import java.util.Date;

public class MouseCroisement extends Mouse{
	private String gene;

	public MouseCroisement(String line, String name, Date birthDate, String genotype, String strain, String gene) {
		super(line, name, birthDate, genotype, strain);
		this.gene = gene;
	}
	public MouseCroisement (){
		super();
		this.gene = null;
	}
	
	public String getGene() {
		return gene;
	}
	
	public void setGene (String gene){
		this.gene = gene;
	}
	
	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
