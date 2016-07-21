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
 * SudokuNext类
 * 数独入口
 * @version 1.2
 */
public class SudokuNext{
	public static GraphicsDevice device;
	
	/**
	 * main 方法
	 * @param args 运行参数（未使用）
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
				Dimension screenSize = kit.getScreenSize(); //使窗体显示在屏幕正中(假设任务栏40px)
				frame.setTitle("Sudoku Next!");
				frame.setIconImage(kit.getImage("./theme/public/icon.png"));
				//先设定一个窗体大小
				frame.setBounds(screenSize.width/2-400,screenSize.height/2-300, 800, 620);
				//自动缩放组件，这时标题栏的大小也已确定
				frame.pack();
				//重设组件，高度加上标题栏（但是按照600pt会有误差（个人猜测是其他窗口装饰物计算问题），所以设定为598px）
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
		if (frame.getComponentZOrder(loadBlankPanel) != -1) //如果frame已包含Panel，在再次插入前需要删除以节省资源 
			frame.remove(loadBlankPanel);
		loadBlankPanel = new LoadBlankPanel();
		frame.add(loadBlankPanel,"load");
		cardLayout.show(container, "load");
	}
	
	/**
	 * settings的Set方法
	 * @param settings 需要设置的字段
	 */
	public static void setSettings(SudokuSettings settings) {
		SudokuNext.settings = settings;
	}


	public static void switchMainMenu()
	{
		resources = SudokuResources.getSudokuResources(settings.getThemepackFilename());
		if (frame.getComponentZOrder(mainMenuPanel) != -1) //如果frame已包含Panel，在再次插入前需要删除以节省资源 
			frame.remove(mainMenuPanel);
		mainMenuPanel = new MainMenuPanel();
		frame.add(mainMenuPanel,"main");
		cardLayout.show(container, "main");
	}
	
	public static void switchAboutMenu()
	{
		if (frame.getComponentZOrder(aboutPanel) != -1) //如果frame已包含Panel，在再次插入前需要删除以节省资源 
			frame.remove(aboutPanel);
		aboutPanel = new AboutPanel();
		frame.add(aboutPanel,"about");
		cardLayout.show(container, "about");
	}
	
	public static void switchStatMenu()
	{
		if (frame.getComponentZOrder(statPanel) != -1) //如果frame已包含Panel，在再次插入前需要删除以节省资源 
			frame.remove(statPanel);
		statPanel = new StatPanel();
		frame.add(statPanel,"stat");
		cardLayout.show(container, "stat");
	}
	
	public static void switchOptionMenu()
	{
		if (frame.getComponentZOrder(optionPanel) != -1) //如果frame已包含Panel，在再次插入前需要删除以节省资源 
			frame.remove(optionPanel);
		optionPanel = new OptionPanel();
		frame.add(optionPanel,"opt");
		cardLayout.show(container, "opt");
	}
	
	public static void switchNewGameMenu()
	{
		if (frame.getComponentZOrder(newGamePanel) != -1) //如果frame已包含Panel，在再次插入前需要删除以节省资源 
			frame.remove(newGamePanel);
		newGamePanel = new NewGamePanel();
		frame.add(newGamePanel,"new");
		cardLayout.show(container, "new");
	}
	
	public static void switchSudokuGame(SudokuModel _model, int edge)
	{
		if (frame.getComponentZOrder(sudokuFrame) != -1) //如果frame已包含Panel，在再次插入前需要删除以节省资源 
			frame.remove(sudokuFrame);
		sudokuFrame = new SudokuMainPanel(_model);
		_model.setEdge(edge);
		frame.add(sudokuFrame,"game");
		cardLayout.show(container, "game");
	}
	
	/**
	 * resources的Get方法
	 * @return the resources
	 */
	public static SudokuResources getResources() {
		return resources;
	}


	/**
	 * @param text HighScore文件位置
	 * 刷新HighScore
	 */
	public static void refresh(String text) {
		settings.setHighscore(new HighScore(text));
	}
	
	
	/**
	 * settings的Get方法
	 * @return the settings
	 */
	public static String getTheme() {
		return settings.getThemepackFilename();
	}

	/**
	 * HighScore的Get方法
	 * @return the HighScore
	 */
	public static HighScore getHighScore() {
		settings.setHighscore(new HighScore());
		return settings.getHighscore();
	}
	
	/**
	 * settings的Get方法
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

