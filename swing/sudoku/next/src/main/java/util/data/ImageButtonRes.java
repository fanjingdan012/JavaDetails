/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-7
 */
package util.data;

import java.awt.Dimension;
import java.io.Serializable;

/**
 * ImageButton类 
 * 图像按钮资源
 * @version 0.1
 */
public class ImageButtonRes implements Serializable{
	/**
	 * 创建图形按钮资源
	 * @param a 正常
	 * @param b 鼠标在上
	 * @param x1 位置x
	 * @param y1 位置y
	 */
	public ImageButtonRes(String theme, String a, String b, int x1, int y1)
	{
		def = a;
		on = b;
		pos = new Dimension(x1,y1);
		this.theme = theme;
	}
	
	/**
	 * def的Set方法
	 * @param def 需要设置的字段
	 */
	public void setDef(String def) {
		this.def = def;
	}

	/**
	 * on的Set方法
	 * @param on 需要设置的字段
	 */
	public void setOn(String on) {
		this.on = on;
	}

	/**
	 * def的Get方法
	 * @return the def
	 */
	public String getDef() {
		return def;
	}
	/**
	 * on的Get方法
	 * @return the on
	 */
	public String getOn() {
		return on;
	}
	/**
	 * pos的Get方法
	 * @return the pos
	 */
	public Dimension getPos() {
		return pos;
	}

	/**
	 * theme的Set方法
	 * @param theme 需要设置的字段
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}

	/**
	 * theme的Get方法
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
