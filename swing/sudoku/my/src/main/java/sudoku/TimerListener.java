package sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerListener implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		int hour = 0;
    		int min = 0;
    		int sec = 0;
    		long time = System.currentTimeMillis() / 1000 - Sudoku.totalMilliseconds;
   			String timeString = "";
   		    hour = (int)(time / 3600);
   		    timeString += (hour >= 10)? hour + "" : "0" + hour;
   		    min = (int)((time - hour * 3600) / 60);
   		    timeString += ":"+((min>=10)?min+"":"0"+min);
   		    sec = (int)(time % 60);
   		    timeString += ":"+((sec >=10)?sec+"":"0"+sec);
   		    MainFrame.game.times = time;
   		    MainFrame.jlbTime.setText(timeString);
    	}   
}
