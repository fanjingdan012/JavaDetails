/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-8
 */
package util.data;

import java.awt.Color;
import java.io.Serializable;

/**
 * SudokuButtonRes类 
 * 数独按钮资源
 * @version 0.1
 */
public class SudokuButtonRes implements Serializable{
	
	public SudokuButtonRes(String _theme, Color _r, Color _rb1, Color _rb2, Color _c, Color _i, Color _mb)
	{
		theme = _theme;
		regular = _r;
		regularbg1 = _rb1;
		confliction = _c;
		regularbg2 = _rb2;
		initial = _i;
		markonbg = _mb;
	}
	
	
	/**
	 * regular的Get方法
	 * @return the regular
	 */
	public Color getRegular() {
		return regular;
	}
	/**
	 * regularbg的Get方法
	 * @return the regularbg
	 */
	public Color getRegularbg1() {
		return regularbg1;
	}
	/**
	 * conflict的Get方法
	 * @return the conflict
	 */
	public Color getConflict() {
		return confliction;
	}
	/**
	 * conflictbg的Get方法
	 * @return the conflictbg
	 */
	public Color getRegularbg2() {
		return regularbg2;
	}
	/**
	 * initial的Get方法
	 * @return the initial
	 */
	public Color getInitial() {
		return initial;
	}
	/**
	 * markonbg的Get方法
	 * @return the markonbg
	 */
	public Color getMarkonbg() {
		return markonbg;
	}
	/**
	 * theme的Get方法
	 * @return the theme
	 */
	public String getTheme() {
		return theme;
	}



	private String theme; //主题名
	private Color regular; //通常状况
	private Color regularbg1; //通常状况1 背景
	private Color confliction; //冲突前景
	private Color regularbg2; //通常状况2 背景
	private Color initial; //初始 既题目给出的方格
	private Color markonbg; //提示 背景
}
