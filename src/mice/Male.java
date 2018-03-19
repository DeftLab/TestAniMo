package mice;

import java.util.Date;

public class Male extends MouseCroisement {
	private String code, rowCode;

	public Male(String code, String line, String name, Date birthDate, String genotype, String strain, String gene) {
		super(line, name, birthDate, genotype, strain, gene);
		this.code = code.substring(code.indexOf('-') +1 );
		rowCode = code;
	}
	
	public String getCode (){
		return code;
	}
	
	public String getRowCode(){
		return rowCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((rowCode == null) ? 0 : rowCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Male other = (Male) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (rowCode == null) {
			if (other.rowCode != null)
				return false;
		} else if (!rowCode.equals(other.rowCode))
			return false;
		return true;
	}
	
	
}
