/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-5
 */
package gui.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gui.MenuButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.SudokuModel;
import core.SudokuNext;

import util.SudokuController;
import util.data.ImageManager;

/**
 * NewGamePanel类 
 * 难度选择
 * @version 0.1
 */
public class NewGamePanel extends JPanel {
	
	
	public NewGamePanel()
	{
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 800, 600);
		this.setLayout(null);
		lev1 = new MenuButton(SudokuNext.getResources().lev1);
		lev1.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) {SudokuNext.switchSudokuGame(SudokuController.getRandomSudoku(1), 9);}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		mainPanel.add(lev1);
		lev2 = new MenuButton(SudokuNext.getResources().lev2);
		lev2.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) {SudokuNext.switchSudokuGame(SudokuController.getRandomSudoku(2), 9);}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		mainPanel.add(lev2);
		lev3 = new MenuButton(SudokuNext.getResources().lev3);
		lev3.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) {SudokuNext.switchSudokuGame(SudokuController.getRandomSudoku(3), 16);}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		mainPanel.add(lev3);
		lev4 = new MenuButton(SudokuNext.getResources().lev4);
		lev4.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) {SudokuNext.switchSudokuGame(SudokuController.getRandomSudoku(4), 16);}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		mainPanel.add(lev4);
		lev0 = new MenuButton(SudokuNext.getResources().lev0);
		lev0.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) {SudokuNext.switchSudokuGame(SudokuController.getRandomSudoku(0), 4);}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		mainPanel.add(lev0);
		levload = new MenuButton(SudokuNext.getResources().levload);
		levload.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) {
				SudokuModel smodel = SudokuController.loadFile();
				if (smodel!=null)
					SudokuNext.switchSudokuGame(smodel, smodel.edge);
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		mainPanel.add(levload);

		
		mainLabel = new JLabel(new ImageIcon(ImageManager.getImage(SudokuNext.getTheme(),"background")));
		mainLabel.setBounds(0, 0, 800, 600);
		mainLabel.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) {
				if (e.getButton()==MouseEvent.BUTTON3)
					SudokuNext.switchMainMenu();				
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		mainPanel.add (mainLabel);
		add(mainPanel);
	}
	
	
	private MenuButton lev1;
	private MenuButton lev2;
	private MenuButton lev3;
	private MenuButton lev4;
	private MenuButton lev0;
	private MenuButton levload;
	private JPanel mainPanel = null;
	private JLabel mainLabel = null;
	
}
