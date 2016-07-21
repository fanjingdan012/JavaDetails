package sudoku;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

public class CellKeyListener implements KeyListener{

		public void keyPressed(KeyEvent e) {		
		}

		public void keyReleased(KeyEvent e) {			
		}

		public void keyTyped(KeyEvent e) {
			for (int i = 0;i <= 8;i++){
				for (int j = 0;j <= 8;j++){	
					if(e.getSource() == MainFrame.picCells[i][j].jtfCell){
						for(int k = 0;k <= 19;k++){
							MainFrame.picCells[Cell.list[i][j].linkCells[k][0]][Cell.list[i][j].linkCells[k][1]].jtfCell.setBackground(MainFrame.picCells[Cell.list[i][j].linkCells[k][0]][Cell.list[i][j].linkCells[k][1]].getOriginalColor());
    					}
						String numStr = MainFrame.picCells[i][j].jtfCell.getText();
						if ((numStr.equals("1")) || (numStr.equals("2")) || (numStr.equals("3")) || (numStr.equals("4")) || (numStr.equals("5")) || (numStr.equals("6")) || (numStr.equals("7")) || (numStr.equals("8")) || (numStr.equals("9")) ){ 
							Cell.list[i][j].setValue(Integer.parseInt(numStr));
							Cell.list[i][j].rule();
							//MainFrame.game.isWin();
						}						
						else if(numStr.equals("")){
						}
						else {
							JOptionPane.showMessageDialog(null, "你输入的格式不正确\n请重新输入");
							MainFrame.picCells[i][j].jtfCell.setText("");   
						}						
					}
					//?????????????????????????????????????????????
					//if(Sudoku.answer(0, 0) == false){
						//JOptionPane.showMessageDialog(null, "游戏陷入死局!");
						//Cell.list[i][j].deleteValue();
					//}
				}
			}
			
		}
		
	}

