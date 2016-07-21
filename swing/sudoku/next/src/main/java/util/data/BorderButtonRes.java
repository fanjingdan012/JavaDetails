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
 * 数独边框按钮资源
 * @version 0.1
 */
public class BorderButtonRes implements Serializable{
	
	public BorderButtonRes(String _theme, Color _r, Color _rb)
	{
		theme = _theme;
		regular = _r;
		regularbg = _rb;
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
	public Color getRegularbg() {
		return regularbg;
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
	private Color regularbg; //通常状况 背景
}
