/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-7
 */
package util.data;

import java.awt.Dimension;
import java.io.Serializable;

/**
 * ImageButton�� 
 * ͼ��ť��Դ
 * @version 0.1
 */
public class ImageButtonRes implements Serializable{
	/**
	 * ����ͼ�ΰ�ť��Դ
	 * @param a ����
	 * @param b �������
	 * @param x1 λ��x
	 * @param y1 λ��y
	 */
	public ImageButtonRes(String theme, String a, String b, int x1, int y1)
	{
		def = a;
		on = b;
		pos = new Dimension(x1,y1);
		this.theme = theme;
	}
	
	/**
	 * def��Set����
	 * @param def ��Ҫ���õ��ֶ�
	 */
	public void setDef(String def) {
		this.def = def;
	}

	/**
	 * on��Set����
	 * @param on ��Ҫ���õ��ֶ�
	 */
	public void setOn(String on) {
		this.on = on;
	}

	/**
	 * def��Get����
	 * @return the def
	 */
	public String getDef() {
		return def;
	}
	/**
	 * on��Get����
	 * @return the on
	 */
	public String getOn() {
		return on;
	}
	/**
	 * pos��Get����
	 * @return the pos
	 */
	public Dimension getPos() {
		return pos;
	}

	/**
	 * theme��Set����
	 * @param theme ��Ҫ���õ��ֶ�
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}

	/**
	 * theme��Get����
	 * @return the theme
	 */
	public String getTheme() {
		return theme;
	}

	private String theme;
	private String def;
	private String on;
	private Dimension pos;
	
}
