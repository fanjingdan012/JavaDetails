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
		helpMsg= "��Ϸ����Ͳ���˵�˰�-_-ÿ��ÿ�к�ÿ���Ź������ҽ���\n" +
				"1-9����һ�����֡�\n\n" +
				"��������������ұߵİ�ťѡ��ǰҪ��������֣��ٵ����\n" +
				"�߱���еĿո������������ĸ��ӣ����־����ȥ�ˡ�����\n" +
				"��ĳ�����Ӵ𰸵���ʾ����������ϵ��Ҽ��Ϳ��ԡ�����ɾ��\n" +
				"ĳ���е����֣���������ϰ��������ּ��ɡ�\n\n" +
				"����ʱ���еĸ��ӱ��̣���ʾ��ǰҪ������͸ø��ӵ�������\n" +
				"��ͻ���뻻һ�������\n\n" +
				"ÿ����Ŀ��֤��Ψһ�⡣",
		
		aboutMsg="   #######Sudoku 3.0.1#######\n" +
				"   Author: �߼�ε 09302010076\n" +
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