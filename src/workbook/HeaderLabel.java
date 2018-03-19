package workbook;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Workbook;

public class HeaderLabel {
	
	private ArrayList <String> labelList = new ArrayList<String>();
	private ArrayList <Integer> positionList = new ArrayList<Integer>();
	
	public HeaderLabel (Workbook wb, boolean isStock){
		int lastColumn = wb.getSheetAt(0).getRow(0).getLastCellNum();
		int index;
		if (isStock){
			index = 0;
		}else {
			index = 1;
		}
		
		for (int i = 0; i < lastColumn; ++i){
			labelList.add(wb.getSheetAt(0).getRow(index).getCell(i).getStringCellValue());
			positionList.add(i);
		}
	}

	public ArrayList<String> getLabelList() {
		return labelList;
	}

	public void setLabelList(ArrayList<String> labelList) {
		this.labelList = labelList;
	}

	public ArrayList<Integer> getPositionList() {
		return positionList;
	}

	public void setPositionList(ArrayList<Integer> positionList) {
		this.positionList = positionList;
	}
	
	
}
