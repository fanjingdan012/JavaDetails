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
 * ������ť��Դ
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
	public Color getRegularbg1() {
		return regularbg1;
	}
	/**
	 * conflict��Get����
	 * @return the conflict
	 */
	public Color getConflict() {
		return confliction;
	}
	/**
	 * conflictbg��Get����
	 * @return the conflictbg
	 */
	public Color getRegularbg2() {
		return regularbg2;
	}
	/**
	 * initial��Get����
	 * @return the initial
	 */
	public Color getInitial() {
		return initial;
	}
	/**
	 * markonbg��Get����
	 * @return the markonbg
	 */
	public Color getMarkonbg() {
		return markonbg;
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
	private Color regularbg1; //ͨ��״��1 ����
	private Color confliction; //��ͻǰ��
	private Color regularbg2; //ͨ��״��2 ����
	private Color initial; //��ʼ ����Ŀ�����ķ���
	private Color markonbg; //��ʾ ����
}
