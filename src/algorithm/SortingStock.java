package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

import mice.CageStock;
import mice.MouseStock;
import tools.TreeMapComparator;

public class SortingStock {
	
	//input ArrayList of Mouse 
	//return map with key = "Procedure" and value for each key :"ArrayList <Mouse>" 
	public static  Map<String,List<CageStock>> sortProcess (ArrayList <CageStock> cageList){	 
		return cageList.stream().collect(Collectors.groupingBy((CageStock cage) -> cage.getProcessName()));
	}
	
	
	
	//input ArrayList of Mouse 
	//return map with key = Double "CageNumber" and value for each key :"ArrayList <Mouse>" 
	public static Map<String,List<MouseStock>> createMapCage (ArrayList <MouseStock>miceList){	 
		return (Map<String, List<MouseStock>>) miceList.stream().collect(Collectors.groupingBy((MouseStock mickey) -> mickey.getCage()));
	}
		
	public static ArrayList<CageStock> cageConstructor (ArrayList <MouseStock> miceList){
		Map<String,List<MouseStock>> cageMap = createMapCage(miceList);
		TreeMap<String, List<MouseStock>> sortedCageMap = new TreeMap <String, List<MouseStock>>(new TreeMapComparator ());		
		sortedCageMap.putAll(cageMap);

		ArrayList <CageStock> allCage = new ArrayList<CageStock>();
		 for (Entry<String, List<MouseStock>> entry : sortedCageMap.entrySet()){
			 ArrayList <MouseStock> mouse = (ArrayList<MouseStock>) entry.getValue();
			 CageStock cage = new CageStock (mouse, entry.getKey());
			 allCage.add(cage);
		 }
		 
		return allCage;
	}



}
