package sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerEndListener implements ActionListener{
    public void actionPerformed(ActionEvent e){    		
    		if(MainFrame.frame.getHeight() > 100){
    		    MainFrame.frame.setSize(MainFrame.frame.getWidth() - 100,MainFrame.frame.getHeight() - 100);
    		}
    		else
    			System.exit(0);
    		
    }
      
}
