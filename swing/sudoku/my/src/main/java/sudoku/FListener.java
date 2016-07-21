package sudoku;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

//import javax.swing.JOptionPane;

public class FListener implements FocusListener {
	public void focusGained(FocusEvent e){			
		for (int i = 0;i <= 8;i++){
			for (int j = 0;j <= 8;j++){	
				if(e.getSource() == MainFrame.picCells[i][j].jtfCell){
						MainFrame.game.currentX = i;
						MainFrame.game.currentY = j;
						MainFrame.picCells[i][j].jtfCell.setBackground(new Color(255,0,255));
				
	    				if (MainFrame.game.candidateModel == true){						
							for(int k = 1;k <=9;k++){
								if(MainFrame.game.list[i][j].possibleValues[k] != 0){
									MainFrame.jbtNums[k].setVisible(false);
								}
							}
	    				}
					
				}
			}
		}
	}
	public void focusLost(FocusEvent e){
    	for (int i = 0;i <= 8;i++){
			for (int j = 0;j <= 8;j++){	
				if(e.getSource() == MainFrame.picCells[i][j].jtfCell){    					
					    MainFrame.picCells[i][j].jtfCell.setBackground(MainFrame.picCells[i][j].getOriginalColor());
    					for(int k = 0;k <= 19;k++){
    						MainFrame.picCells[Cell.list[i][j].linkCells[k][0]][Cell.list[i][j].linkCells[k][1]].jtfCell.setBackground(MainFrame.picCells[Cell.list[i][j].linkCells[k][0]][Cell.list[i][j].linkCells[k][1]].getOriginalColor());
    					}
					    if (MainFrame.game.candidateModel == true){
							for(int k = 1;k <=9;k++){							
									MainFrame.jbtNums[k].setVisible(true);								
							}
						}
					
				}
			}
		}
	}
}
