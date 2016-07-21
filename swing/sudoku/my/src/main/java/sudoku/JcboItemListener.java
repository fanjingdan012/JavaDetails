package sudoku;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class JcboItemListener implements ItemListener{
	public void itemStateChanged(ItemEvent e) {
		String difficultyStr = MainFrame.jcboDifficulty.getSelectedItem().toString();
		if (difficultyStr.equals("简单")){
			Sudoku.difficulty = Sudoku.EASY;
		}
		else if (difficultyStr.equals("普通")){
			Sudoku.difficulty = Sudoku.NORMAL;		
		}
		else if (difficultyStr.equals("困难")){
			Sudoku.difficulty = Sudoku.HARD;
		}
		else if (difficultyStr.equals("极难")){
			Sudoku.difficulty = Sudoku.EXHARD;
		}
		else if (difficultyStr.equals("骨灰")){
			Sudoku.difficulty = Sudoku.ASH;
		}
	}

}
