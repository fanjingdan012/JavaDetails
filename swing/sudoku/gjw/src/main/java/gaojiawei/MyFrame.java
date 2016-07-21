package gaojiawei;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MyFrame extends JFrame
{	
	private static final long serialVersionUID = -5021421177202464573L;

	String
		helpMsg= "游戏规则就不用说了吧-_-每行每列和每个九宫格都有且仅有\n" +
				"1-9其中一个数字。\n\n" +
				"操作方法：点击右边的按钮选择当前要填入的数字，再点击左\n" +
				"边表格中的空格或您已填过数的格子，数字就填进去了。若想\n" +
				"看某个格子答案的提示，在这个格上点右键就可以。若想删除\n" +
				"某格中的数字，在这个格上按下鼠标滚轮即可。\n\n" +
				"填数时若有的格子变绿，表示当前要填的数和该格子的数发生\n" +
				"冲突，请换一个格子填。\n\n" +
				"每道题目保证有唯一解。",
		
		aboutMsg="   #######Sudoku 3.0.1#######\n" +
				"   Author: 高嘉蔚 09302010076\n" +
				"   Email: dfs35123@hotmail.com\n" +
				"   Website: www.gaojiawei.com";
	
	JMenuBar menu;
	JMenu game,edit,help;
	JMenuItem newGame,choose,replay,solve,exit,
				addGame,save,
				sudokuHelp,about;
	
	JComboBox combo;
	
	MapPanel mapPanel;
	TimePanel timePanel;
	Buttons buttons;
	
	public MyFrame()
	{
		setTitle("####Sudoku - Christmas Version###    09302010076");
		setIconImage(new ImageIcon("logolittle.gif").getImage());
		setSize(600,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		menu=new JMenuBar();
		setJMenuBar(menu);
		
		game=new JMenu("Game");
		edit=new JMenu("Edit");
		help=new JMenu("Help");
		
		game.setMnemonic('G');
		edit.setMnemonic('E');
		help.setMnemonic('H');
		
		//set accelerator!!!!!!
		
		
		newGame=new JMenuItem("New Game",'R');
		choose=new JMenuItem("Choose Game",'C');
		replay=new JMenuItem("Replay",'R');
		solve=new JMenuItem("Solve",'S');
		exit=new JMenuItem("Exit",'E');
		game.add(newGame);
		game.add(choose);
		game.add(replay);
		game.addSeparator();
		game.add(solve);
		game.addSeparator();
		game.add(exit);
		
		addGame=new JMenuItem("Create New Game",'A');
		save=new JMenuItem("Save",'S');
		edit.add(addGame);
		edit.add(save);
		
		sudokuHelp=new JMenuItem("Sudoku Help",'H');
		about=new JMenuItem("About",'A');
		help.add(sudokuHelp);
		help.add(about);
		
		menu.add(game);
		menu.add(edit);
		menu.add(help);
		
		String[] comboTitle={"Easy","Normal","Hard"};
		combo=new JComboBox(comboTitle);		
		
		mapPanel=new MapPanel();
		add(mapPanel,BorderLayout.CENTER);
		mapPanel.setBounds(getWidth()/2-270, getHeight()/2-270, 540, 540);
		
		buttons=new Buttons();
		add(buttons,BorderLayout.EAST);
		
		JPanel bottom=new JPanel();
		bottom.setLayout(new FlowLayout(FlowLayout.CENTER,40,20));
		JLabel label=new JLabel("Difficulty:");
		bottom.add(label);
		bottom.add(combo);
		timePanel=new TimePanel();
		bottom.add(timePanel);
		add(bottom,BorderLayout.SOUTH);
	}
}