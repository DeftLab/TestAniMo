package mice;

import java.util.Date;

//Croisement : Mâle Num	Marque mâle	Souche	Lignée mâle	Gène mâle Génotype mâle	Génération	Naissance



public class MouseStock extends Mouse{
	private String origin, sex, process, service, BrOrigin, cage, code;
	private boolean inBreeding, cageIsEmpty;

	
	
	public MouseStock(String code, String line, String name, String sex,  Date birthDate, String genotype, boolean breeding, String strain, String cage, String origin, String process, String service, String BrOrigin){
		super (line, name, birthDate, genotype, strain);
		this.origin = origin;
		this.process = process;
		this.sex = sex.substring(0,1);
		this.inBreeding = breeding;
		this.service = service;
		this.BrOrigin = BrOrigin;
		if (cage.equals("")){
			this.cage ="Unk cage " + process;
			cageIsEmpty = true;
		}else {
			this.cage = cage;
		}
		this.code = code.substring(code.indexOf('-')+1);
	}



	public String getOrigin() {
		return origin;
	}



	public String getSex() {
		return sex;
	}



	public String getProcess() {
		return process;
	}



	public String getService() {
		return service;
	}



	public String getBrOrigin() {
		return BrOrigin;
	}



	public boolean isInBreeding() {
		return inBreeding;
	}



	public String getCage() {
		return cage;
	}



	public boolean isCageIsEmpty() {
		return cageIsEmpty;
	}


	public String getCode (){
		return code;
	}



	@Override
	public String getGene() {
		return null;
	}


	

}
