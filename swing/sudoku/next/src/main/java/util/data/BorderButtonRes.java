/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-8
 */
package util.data;

import java.awt.Color;
import java.io.Serializable;

/**
 * SudokuButtonRes�� 
 * �����߿�ť��Դ
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
	 * regular��Get����
	 * @return the regular
	 */
	public Color getRegular() {
		return regular;
	}
	/**
	 * regularbg��Get����
	 * @return the regularbg
	 */
	public Color getRegularbg() {
		return regularbg;
	}
	
	/**
	 * theme��Get����
	 * @return the theme
	 */
	public String getTheme() {
		return theme;
	}



	private String theme; //������
	private Color regular; //ͨ��״��
	private Color regularbg; //ͨ��״�� ����
}
