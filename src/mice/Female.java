package mice;

import java.util.Date;

public class Female extends MouseCroisement {
	private String code;
	private String rowCode;

	public Female(String line, String name, Date birthDate, String genotype, String strain, String gene, String code) {
		super(line, name, birthDate, genotype, strain, gene);
		this.code = code.substring(code.indexOf('-') +1);
		this.rowCode = code;
	}

	public String getCode (){
		return code;
	}
	
	public String getRowCOde(){
		return rowCode;
	}
}
