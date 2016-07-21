/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-7
 */
package gui;

import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;

import util.data.BorderButtonRes;


/**
 * SudokuButton类 
 * 边框按钮
 * @version 0.1
 */
public class BorderButton extends JButton{
	
	/**
	 * 构造函数
	 * @param s 放置的内容
	 * @param x 关联的x
	 * @param y 关联的y
	 * @param _res 颜色资源
	 */
	public BorderButton(String s,BorderButtonRes _res, int fs)
	{
		super(s);
		res = _res;
		this.setFont(new Font("Times New Roman", Font.PLAIN, fs));
		this.setMargin(new Insets(0,0,0,0));
		//this.setSize(50, 50);
		this.setFocusable(false);
		this.setForeground(res.getRegular());
		this.setBackground(res.getRegularbg());
	}
	
	
	private BorderButtonRes res;
}
