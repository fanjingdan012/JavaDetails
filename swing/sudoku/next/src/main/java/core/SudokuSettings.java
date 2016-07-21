/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-4
 */
package core;

import util.XMLFactory;
import util.data.HighScore;


/**
 * SudokuSettings类
 * 	用来保存SudokuNext!相关的配置，用户信息，最高成绩，数独题库
 * @version 0.4
 */
public class SudokuSettings {
	
	/**
	 * 构造函数 
	 */
	public SudokuSettings(String _XMLFileName)
	{
		XMLFactory.getSudokuSettings(this, _XMLFileName);
	}
	
	public SudokuSettings()
	{
		fullScreen=false;
		BGMVolume=70;
		SEVolume=70;
		themepackFilename="summer";
	}
	
	
	/**
	 * fullScreen的Get方法
	 * @return the fullScreen
	 */
	public boolean isFullScreen() {
		return fullScreen;
	}

	/**
	 * BGMVolume的Get方法
	 * @return the bGMVolume
	 */
	public int getBGMVolume() {
		return BGMVolume;
	}

	/**
	 * SEVolume的Get方法
	 * @return the sEVolume
	 */
	public int getSEVolume() {
		return SEVolume;
	}

	/**
	 * themepackFilename的Get方法
	 * @return the themepackFilename
	 */
	public String getThemepackFilename() {
		return themepackFilename;
	}

	/**
	 * highscore的Get方法
	 * @return the highscore
	 */
	public HighScore getHighscore() {
		return highscore;
	}


	/**
	 * highscore的Set方法
	 * @param highscore 需要设置的字段
	 */
	public void setHighscore(HighScore highscore) {
		this.highscore = highscore;
	}

	/**
	 * fullScreen的Set方法
	 * @param fullScreen 需要设置的字段
	 */
	public void setFullScreen(boolean fullScreen) {
		this.fullScreen = fullScreen;
	}



	/**
	 * BGMVolume的Set方法
	 * @param BGMVolume 需要设置的字段
	 */
	public void setBGMVolume(int _BGMVolume) {
		BGMVolume = _BGMVolume;
	}



	/**
	 * SEVolume的Set方法
	 * @param SEVolume 需要设置的字段
	 */
	public void setSEVolume(int _SEVolume) {
		SEVolume = _SEVolume;
	}


	/**
	 * themepackFilename的Set方法
	 * @param themepackFilename 需要设置的字段
	 */
	public void setThemepackFilename(String themepackFilename) {
		this.themepackFilename = themepackFilename;
	}
	
	public static void update()
	{
		XMLFactory.setSudokuSettings(SudokuNext.getSettings(), "./settings/settings.xml");
	}


	private boolean fullScreen;
	private int BGMVolume;
	private int SEVolume;
	private String themepackFilename;
	private HighScore highscore;
	


	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args)
	{
		SudokuSettings settings = new SudokuSettings("./settings/settings.xml");
	}
}
