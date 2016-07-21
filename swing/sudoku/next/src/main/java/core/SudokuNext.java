package core;

/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-4
 */
import gui.ui.AboutPanel;
import gui.ui.LoadBlankPanel;
import gui.ui.MainMenuPanel;
import gui.ui.NewGamePanel;
import gui.ui.OptionPanel;
import gui.ui.StatPanel;
import gui.ui.SudokuMainPanel;

import java.awt.*;

import javax.swing.*;

import core.ScreenManager;
import core.SudokuModel;
import core.SudokuSettings;

import util.data.HighScore;
import util.data.SudokuResources;


/**
 * SudokuNext��
 * �������
 * @version 1.2
 */
public class SudokuNext{
	public static GraphicsDevice device;
	
	/**
	 * main ����
	 * @param args ���в�����δʹ�ã�
	 */
	public static void main(final String[] args){
		
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
				device = environment.getDefaultScreenDevice();
				ScreenManager.getDisplayMode(device);
				
				frame=new JFrame();
				Toolkit kit=Toolkit.getDefaultToolkit();
				Dimension screenSize = kit.getScreenSize(); //ʹ������ʾ����Ļ����(����������40px)
				frame.setTitle("Sudoku Next!");
				frame.setIconImage(kit.getImage("./theme/public/icon.png"));
				//���趨һ�������С
				frame.setBounds(screenSize.width/2-400,screenSize.height/2-300, 800, 620);
				//�Զ������������ʱ�������Ĵ�СҲ��ȷ��
				frame.pack();
				//����������߶ȼ��ϱ����������ǰ���600pt���������˲²�����������װ����������⣩�������趨Ϊ598px��
				frame.setBounds(screenSize.width/2-400,screenSize.height/2-300, 800, 598+frame.getRootPane().getY());
				frame.setLayout(cardLayout);
				frame.setResizable(false);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				container = frame.getContentPane();
				frame.setVisible(true);
				switchLoadGame();
			}
		});
	}	
	
	
	public static void switchLoadGame()
	{
		settings = new SudokuSettings("./settings/settings.xml");
		if (settings.isFullScreen())
			ScreenManager.setFullScreen(frame, device);
		else
			ScreenManager.setWindow(frame, device);
		if (frame.getComponentZOrder(loadBlankPanel) != -1) //���frame�Ѱ���Panel�����ٴβ���ǰ��Ҫɾ���Խ�ʡ��Դ 
			frame.remove(loadBlankPanel);
		loadBlankPanel = new LoadBlankPanel();
		frame.add(loadBlankPanel,"load");
		cardLayout.show(container, "load");
	}
	
	/**
	 * settings��Set����
	 * @param settings ��Ҫ���õ��ֶ�
	 */
	public static void setSettings(SudokuSettings settings) {
		SudokuNext.settings = settings;
	}


	public static void switchMainMenu()
	{
		resources = SudokuResources.getSudokuResources(settings.getThemepackFilename());
		if (frame.getComponentZOrder(mainMenuPanel) != -1) //���frame�Ѱ���Panel�����ٴβ���ǰ��Ҫɾ���Խ�ʡ��Դ 
			frame.remove(mainMenuPanel);
		mainMenuPanel = new MainMenuPanel();
		frame.add(mainMenuPanel,"main");
		cardLayout.show(container, "main");
	}
	
	public static void switchAboutMenu()
	{
		if (frame.getComponentZOrder(aboutPanel) != -1) //���frame�Ѱ���Panel�����ٴβ���ǰ��Ҫɾ���Խ�ʡ��Դ 
			frame.remove(aboutPanel);
		aboutPanel = new AboutPanel();
		frame.add(aboutPanel,"about");
		cardLayout.show(container, "about");
	}
	
	public static void switchStatMenu()
	{
		if (frame.getComponentZOrder(statPanel) != -1) //���frame�Ѱ���Panel�����ٴβ���ǰ��Ҫɾ���Խ�ʡ��Դ 
			frame.remove(statPanel);
		statPanel = new StatPanel();
		frame.add(statPanel,"stat");
		cardLayout.show(container, "stat");
	}
	
	public static void switchOptionMenu()
	{
		if (frame.getComponentZOrder(optionPanel) != -1) //���frame�Ѱ���Panel�����ٴβ���ǰ��Ҫɾ���Խ�ʡ��Դ 
			frame.remove(optionPanel);
		optionPanel = new OptionPanel();
		frame.add(optionPanel,"opt");
		cardLayout.show(container, "opt");
	}
	
	public static void switchNewGameMenu()
	{
		if (frame.getComponentZOrder(newGamePanel) != -1) //���frame�Ѱ���Panel�����ٴβ���ǰ��Ҫɾ���Խ�ʡ��Դ 
			frame.remove(newGamePanel);
		newGamePanel = new NewGamePanel();
		frame.add(newGamePanel,"new");
		cardLayout.show(container, "new");
	}
	
	public static void switchSudokuGame(SudokuModel _model, int edge)
	{
		if (frame.getComponentZOrder(sudokuFrame) != -1) //���frame�Ѱ���Panel�����ٴβ���ǰ��Ҫɾ���Խ�ʡ��Դ 
			frame.remove(sudokuFrame);
		sudokuFrame = new SudokuMainPanel(_model);
		_model.setEdge(edge);
		frame.add(sudokuFrame,"game");
		cardLayout.show(container, "game");
	}
	
	/**
	 * resources��Get����
	 * @return the resources
	 */
	public static SudokuResources getResources() {
		return resources;
	}


	/**
	 * @param text HighScore�ļ�λ��
	 * ˢ��HighScore
	 */
	public static void refresh(String text) {
		settings.setHighscore(new HighScore(text));
	}
	
	
	/**
	 * settings��Get����
	 * @return the settings
	 */
	public static String getTheme() {
		return settings.getThemepackFilename();
	}

	/**
	 * HighScore��Get����
	 * @return the HighScore
	 */
	public static HighScore getHighScore() {
		settings.setHighscore(new HighScore());
		return settings.getHighscore();
	}
	
	/**
	 * settings��Get����
	 * @return the settings
	 */
	public static SudokuSettings getSettings() {
		return settings;
	}
	
	private static SudokuSettings settings = null;
	private static SudokuResources resources = null;
	private static CardLayout cardLayout = new CardLayout();
	private static MainMenuPanel mainMenuPanel = null;
	private static LoadBlankPanel loadBlankPanel = null;
	private static OptionPanel optionPanel = null;
	private static NewGamePanel newGamePanel = null;
	private static SudokuMainPanel sudokuFrame = null;
	private static StatPanel statPanel = null;
	private static AboutPanel aboutPanel = null;
	private static Container container = null;
	public static JFrame frame = null;
}

