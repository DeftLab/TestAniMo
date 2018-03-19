package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

import mice.CageCroisement;

public class SortingCroisement {
	
	public static  Map<String,List<CageCroisement>> sortProcess (ArrayList <CageCroisement> cageList){	 
		return cageList.stream().collect(Collectors.groupingBy((CageCroisement cage) -> cage.getProcessName()));
	}
	
	public static ArrayList<CageCroisement> sortingCage (ArrayList <CageCroisement> cageList){
			TreeMap<String, CageCroisement> sortedTreeMap = new TreeMap <String, CageCroisement>();
			
			int increment = 1;
			for (int i = 0; i < cageList.size(); ++i){				
				String key = cageList.get(i).breedingName();
				if (key.equals("Ukn")){
					cageList.get(i).setBrName("Ukn" + increment);
					key = cageList.get(i).breedingName();
					++increment;
				}			
				sortedTreeMap.put(key, cageList.get(i));
			}

			ArrayList<CageCroisement> sortedCage = new ArrayList <CageCroisement>();
			CageCroisement cage ;
			for (Entry <String, CageCroisement> entry : sortedTreeMap.entrySet()){
				cage = entry.getValue();
				sortedCage.add(cage); 
			}

		return sortedCage;
	}

}
