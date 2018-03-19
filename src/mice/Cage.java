package mice;

public class Cage {
	private String codeCage, processName ;
	
		
	public Cage (String codeCage, String processName){
		this.codeCage = codeCage;
		this.processName = processName;
	}
	
	public String getProcessName (){
		return processName;
	}
	
	public String getCodeCage (){
		return codeCage;
	}
	
	public void setCodeCage (String codeCage){
		this.codeCage = codeCage;
	}
}
