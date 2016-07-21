/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-4
 */
package core;

import util.XMLFactory;
import util.data.HighScore;


/**
 * SudokuSettings��
 * 	��������SudokuNext!��ص����ã��û���Ϣ����߳ɼ����������
 * @version 0.4
 */
public class SudokuSettings {
	
	/**
	 * ���캯�� 
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
	 * fullScreen��Get����
	 * @return the fullScreen
	 */
	public boolean isFullScreen() {
		return fullScreen;
	}

	/**
	 * BGMVolume��Get����
	 * @return the bGMVolume
	 */
	public int getBGMVolume() {
		return BGMVolume;
	}

	/**
	 * SEVolume��Get����
	 * @return the sEVolume
	 */
	public int getSEVolume() {
		return SEVolume;
	}

	/**
	 * themepackFilename��Get����
	 * @return the themepackFilename
	 */
	public String getThemepackFilename() {
		return themepackFilename;
	}

	/**
	 * highscore��Get����
	 * @return the highscore
	 */
	public HighScore getHighscore() {
		return highscore;
	}


	/**
	 * highscore��Set����
	 * @param highscore ��Ҫ���õ��ֶ�
	 */
	public void setHighscore(HighScore highscore) {
		this.highscore = highscore;
	}

	/**
	 * fullScreen��Set����
	 * @param fullScreen ��Ҫ���õ��ֶ�
	 */
	public void setFullScreen(boolean fullScreen) {
		this.fullScreen = fullScreen;
	}



	/**
	 * BGMVolume��Set����
	 * @param BGMVolume ��Ҫ���õ��ֶ�
	 */
	public void setBGMVolume(int _BGMVolume) {
		BGMVolume = _BGMVolume;
	}



	/**
	 * SEVolume��Set����
	 * @param SEVolume ��Ҫ���õ��ֶ�
	 */
	public void setSEVolume(int _SEVolume) {
		SEVolume = _SEVolume;
	}


	/**
	 * themepackFilename��Set����
	 * @param themepackFilename ��Ҫ���õ��ֶ�
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
	 * ����
	 * @param args
	 */
	public static void main(String[] args)
	{
		SudokuSettings settings = new SudokuSettings("./settings/settings.xml");
	}
}
