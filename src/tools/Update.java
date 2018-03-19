package tools;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Workbook;

public class Update {
	
	public static int updatingColumn ;
	public static String updatingColumnLetter;
	public static String updatingColumnLabel;
	
	
	//create and return an arrayLIst with ExcelLabel : A to ZZ;
	public static ArrayList<String> ExcelLabelFactory (){
		ArrayList <String> listLetter = new ArrayList<String>();
		
		for (char alp1 ='A'; alp1 <='Z'; ++alp1){
			 listLetter.add(alp1+"");
		}
		
		for (char alp ='A'; alp <='Z'; ++alp){
			for (char alp1 ='A'; alp1 <='Z'; ++alp1){
				 listLetter.add(alp + "" + alp1);
			}
		}
		return listLetter;
	}
	
		
	//with an integer into a list this method return a letter like ExcelColumn Label A to ZZ. 
	public static String whatColumnLetter (ArrayList <Integer> list, int index, boolean fromError){		
		int i;
		if (fromError){
			index = updatingColumn;
			i = updatingColumn;
		}else {
			i = list.get(index);
		}


		ArrayList <String> listLetter = ExcelLabelFactory();
		
		String letter= "";
	
		if ((list.get(index) != 99)){
			letter = listLetter.get(i);
		}

		return letter;
	}
	
	
	//with an integer into a list this method return a letter like ExcelColumn Label A to ZZ. 
	public static ArrayList<Integer> whatColumnNumber (ArrayList <String> list, ArrayList<String> letterList){		
		
		 ArrayList <Integer> listNumber  = new ArrayList <Integer>();
		
		for (int i = 0; i< list.size(); ++i){
			boolean find = false;
			int j =0;
			do{
				if (list.get(i).equals(letterList.get(j))){
					find = true;
					listNumber.add(j);
				}
				++j;
			}while (!find);
		}
				
		return listNumber;
	}
	
	public static ArrayList<String> whatColumnLabel (ArrayList <String> list, ArrayList <Integer> numberPositionList, boolean isStock){
		ArrayList<String> labelList = new ArrayList<String>();
		
		for (int i =0; i<numberPositionList.size(); ++i){
			if (!isStock && i == 1){
				labelList.add("Code");
			}else if (!isStock && i == 2){
				labelList.add("Date ouverture");
			}else {
				labelList.add(list.get(numberPositionList.get(i)));
			}
			
		}	
		return labelList;
	}
	
	
	public static ArrayList <Integer> compareLabel (ArrayList<String> readLabelList, ArrayList<String> configLabelList, boolean isStock){
		
		String cellValue = "";
		ArrayList <Integer> columnPosition = new ArrayList <Integer>();
		
		int k = -1 ;
		int j = -1;		
		for (int i = 0; i < configLabelList.size(); ++i){			
			j = k;
			boolean notFind = true;
			boolean valid = false;
			do {
				++j;
				cellValue = readLabelList.get(j);
				if (cellValue.equals(configLabelList.get(i))) {
					notFind = false;
					valid = true;
				}	
				
			}while (j < (readLabelList.size() -1)  && !(valid));
			
			if (isStock){
				if (notFind){
					columnPosition.add(99);
				}else {
					k = j;
					columnPosition.add(k);
				}
				
			}else {
				if (notFind && (i !=1 && i !=2)){
					columnPosition.add(99);
				}else  {
					if ( i == 1 || i == 2 ){
						k = i;
					}else {
						k = j;
					}
					columnPosition.add(k);
				}			
			}
			k=-1;
		}
		return columnPosition;
	}
}
