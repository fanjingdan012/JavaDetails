package sudoku;

import java.awt.Color;

import javax.swing.JOptionPane;

public class Cell {
	static Cell[][]list = new Cell[9][9];	
	private int row;
    private int column;
	private int value;
	protected int answer;
	boolean isFocused = false; //判断是否被选中
	private boolean isInProblem;//true就不能更改它的值，且显示红色	
	protected int[][] linkCells = new int[20][2];
	int [] possibleValues = new int[10];
	private int length = 0;//possibleValue的个数		
	public Cell(int newRow,int newColumn){
		row = newRow;
		column = newColumn;
		this.setLinkCells();		
	}
	private void setLinkCells(){
		int a = 0;
		int k = 0;
		for (k = 0;k <= 8;k++){
			if (k == column){
				    a++;    
				    continue;					
			}
			else{ 
				    linkCells[k - a][0] = row;
	        	    linkCells[k - a][1] = k;
			}
		}
		a = 0;
		for (k = 0;k <= 8;k++){
			if (k == row){
				    a++;    
				    continue;				
			}
			else {
			        linkCells[k + 8 - a][0] = k;
					linkCells[k + 8 - a][1] = column;
			}
		}
		a = 0;
		for (k = 0;k <= 8;k++){
			if ((row / 3 * 3 + k / 3 == row) || (column / 3 * 3 + k % 3 == column)){
				    a++;
			        continue;
			}
			else{
				    linkCells[k + 16 - a][0] = row / 3 * 3 + k / 3;
					linkCells[k + 16 - a][1] = column / 3 * 3 + (k % 3);
			}
		}
	}
	public void setInProblem(boolean inProblem){
		isInProblem = inProblem;
		if(inProblem == true){
			MainFrame.picCells[row][column].jtfCell.setForeground(Color.RED);
			MainFrame.picCells[row][column].jtfCell.setEditable(false);
		}
		else{
			MainFrame.picCells[row][column].jtfCell.setForeground(Color.BLACK);
			MainFrame.picCells[row][column].jtfCell.setEditable(true);
		}
	}
	public boolean getInProblem(){
		return isInProblem;
	}
	protected int getNumberOfPossibleValues(){
		length = 0;
		for (int k = 1;k <= 9;k++){
			if(possibleValues[k] == 0){
				length++;
			}
		}
		return length;
	}
	public void setValue(int newValue){
		if(isInProblem){
			JOptionPane.showMessageDialog(null, "题目，不能更改");
		}
		else{
			deleteValue();		
		
		    //if newValue belongs to possibleElements do next thing
		    value = newValue;
		    for (int k = 0;k <= 19;k++){
			    list[linkCells[k][0]][linkCells[k][1]].possibleValues[value]++;//set? //list static?Sudoku.list?
			    list[linkCells[k][0]][linkCells[k][1]].length--;
		    }
		    MainFrame.picCells[row][column].jtfCell.setText((value == 0) ? "" : value + "");
		}
	}
	public int getValue(){
		return value;
	}
	public void deleteValue(){        
		if(isInProblem){
			JOptionPane.showMessageDialog(null, "题目，不能更改");
		}
		else{
		    if(value != 0){
		        for (int k = 0;k <= 19;k++){
			        list[linkCells[k][0]][linkCells[k][1]].possibleValues[value]--;//set? //list static?Sudoku.list?
			        list[linkCells[k][0]][linkCells[k][1]].length++;
		        }
		    }		
		    value = 0;
		    MainFrame.picCells[row][column].jtfCell.setText("");
		}
	}
    public void rule(){
    	boolean isRight = true;
    	for(int k = 0;k <= 19;k++){
    		if(list[linkCells[k][0]][linkCells[k][1]].getValue() == value){
    			isRight = false;
    			MainFrame.picCells[linkCells[k][0]][linkCells[k][1]].jtfCell.setBackground(Color.CYAN);
    	    }
    	}
    	if(!(isRight)){
    		deleteValue();
    	}
    }
    
}
