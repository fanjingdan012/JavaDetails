package sudoku;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Listener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		for (int k = 0;k <= 9;k++){
			if (e.getSource() == MainFrame.jbtNums[k]){
				if (k == 0){      		    
			            MainFrame.game.list[MainFrame.game.currentX][MainFrame.game.currentY].deleteValue();
				}
           	    else{      		        
           	    	    
           	    	    MainFrame.game.list[MainFrame.game.currentX][MainFrame.game.currentY].setValue(k);
           	    	    Cell.list[MainFrame.game.currentX][MainFrame.game.currentY].rule();	
           	    	    MainFrame.game.isWin();
           	    	
           	    }
			}
	    }
			
		if (e.getSource() == MainFrame.jrbYellow){
		    	 for (int k = 0;k <= 6;k++){		    	    	
			    	 MainFrame.jpEasts[k].setBackground(new Color(255,255,35 * k));		    	    	
		   	     }
		}
		if (e.getSource() == MainFrame.jrbBlue){
				for (int k = 0;k <= 6;k++){		    	    	
					MainFrame.jpEasts[k].setBackground(new Color(35 * k,255,255));		    	    	
		    	 }
		}	
		if (e.getSource() == MainFrame.jrbGreen){
				for (int k = 0;k <= 6;k++){		    	    	
					MainFrame.jpEasts[k].setBackground(new Color(35 * k,255,35 * k));		    	    	
		    	}
	    }	
    	if (e.getSource() == MainFrame.jrby){
				for (int i = 0;i <= 8;i++){
    				for (int j = 0;j <= 8;j++){
    					MainFrame.picCells[i][j].jtfCell.setEditable(false);    					
    				}
				}
				MainFrame.game.candidateModel = true;
		}	
		if (e.getSource() == MainFrame.jrbn){
				for (int i = 0;i <= 8;i++){
    				for (int j = 0;j <= 8;j++){
    					if(Cell.list[i][j].getInProblem() == false){
    						MainFrame.picCells[i][j].jtfCell.setEditable(true);
    					}
    				}
				}
				MainFrame.game.candidateModel = false;
		}
		if (e.getSource() == MainFrame.jbtHint){
				if(MainFrame.game.hints == 1){
		    			MainFrame.jbtHint.setVisible(false);
				}
				else{
					MainFrame.game.hints--;	
					MainFrame.jbtHint.setText("hint(" + MainFrame.game.hints + ")");
				    if(Sudoku.answer(0,0,MainFrame.game.list) == false)
					    JOptionPane.showMessageDialog(null, "This problem can't be solved");
					else{
						outer:
						for (int i = 0;i <= 8;i++){
						    for (int j = 0;j <= 8;j++){
							    if(MainFrame.game.list[i][j].getValue() == 0){
							    	MainFrame.game.list[i][j].setValue(MainFrame.game.list[i][j].answer);						    	
							    	break outer;
								}
							}
						 }
				    }
				}		
				
        }
			
			
		   	
    }
}
