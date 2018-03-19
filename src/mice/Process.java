 package mice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveAction;

import algorithm.WriteStock;

public class Process extends RecursiveAction{

	private String key;

	
	public Process (String key) throws IOException{;
		this.key = key;		
	}

	@Override
	protected void compute() {
		
	}

	public String getKey(){
		return key;
	}
}
