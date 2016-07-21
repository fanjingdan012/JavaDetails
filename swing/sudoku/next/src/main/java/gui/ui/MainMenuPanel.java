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
import javax.swing.Timer;

import core.SudokuNext;

import util.data.ImageManager;

/**
 * MainMenuPanelÀà 
 * Ö÷²Ëµ¥Panel
 * @version 0.1
 */
public class MainMenuPanel extends JPanel {
	
	
	public MainMenuPanel()
	{
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 800, 600);
		this.setLayout(null);
		newgame = new MenuButton(SudokuNext.getResources().bnew);
		newgame.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) {SudokuNext.switchNewGameMenu();}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		mainPanel.add (newgame);
		langame = new MenuButton(SudokuNext.getResources().blan);
		mainPanel.add(langame);
		stat = new MenuButton(SudokuNext.getResources().bstat);
		stat.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) {SudokuNext.switchStatMenu();}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		mainPanel.add(stat);
		option = new MenuButton(SudokuNext.getResources().boption);
		option.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) {SudokuNext.switchOptionMenu();}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		mainPanel.add(option);
		about = new MenuButton(SudokuNext.getResources().babout);
		about.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) {SudokuNext.switchAboutMenu();}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		mainPanel.add(about);
		exit = new MenuButton(SudokuNext.getResources().bexit);
		exit.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) {System.exit(0);}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		mainPanel.add(exit);
		mainLabel = new JLabel(new ImageIcon(ImageManager.getImage(SudokuNext.getTheme(),"background")));
		mainLabel.setBounds(0, 0, 800, 600);
		mainPanel.add (mainLabel);
		add(mainPanel);
	}
	
	private JPanel mainPanel = null;
	private Timer gTimer = null;
	private JLabel mainLabel = null;
	private MenuButton langame = null;
	private MenuButton stat = null;
	private MenuButton option = null;
	private MenuButton about = null;
	private MenuButton exit = null;
	private MenuButton newgame = null;
	
}
