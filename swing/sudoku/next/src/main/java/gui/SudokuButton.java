/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-7
 */
package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;

import core.SudokuPanel;

import util.data.SudokuButtonRes;

/**
 * SudokuButton类 
 * 数独按钮控件
 * @version 0.1
 */
public class SudokuButton extends JButton{
	
	int fontsize;
	int edge;
	
	/**
	 * 构造函数
	 * @param s 放置的内容
	 * @param x 关联的x
	 * @param y 关联的y
	 * @param _res 颜色资源
	 */
	public SudokuButton(String s,int x,int y, SudokuButtonRes _res, int Fontsize, int edge)
	{
		super(s);
		res = _res;
		this.edge = edge;
		fontsize = Fontsize;
		this.setFont(new Font("Arial", Font.PLAIN, fontsize));
		this.setMargin(new Insets(0,0,0,0));
		//this.setSize(50, 50);
		this.setFocusable(false);
		this.AssocatedX=x;
		this.AssocatedY=y;
		this.setForeground(res.getRegular());
		this.setBackground(whatColorIsIt());
	}
	
	
	/**
	 * 重写setText
	 * 没有做非0是的检查(是否为1-9) 由调用它的语句来保证
	 */
	@Override
	public void setText(String str)
	{
		if (str.equals(""))
		{
			super.setText("");
			return;
		}
		if (Integer.parseInt(str)==0)
			super.setText("");
		else super.setText(str);
	}

	public void setWarning() //警告
	{
		this.setFont(new Font("Arial",Font.BOLD,fontsize));
		this.setForeground(res.getConflict());
		this.setBackground(whatColorIsIt());
	}
	
	public void setRegular() //通常
	{
		this.setFont(new Font("Arial",Font.PLAIN,fontsize));
		this.setForeground(res.getRegular());
		this.setBackground(whatColorIsIt());
	}
	
	public void setHint()  //提示
	{
		this.setFont(new Font("Arial",Font.PLAIN,fontsize));
		this.setForeground(initOrRegular());
		//this.setForeground(res.getMarkon());
		this.setBackground(res.getMarkonbg());
	}
	
	public void setInitial() //初始
	{
		this.setFont(new Font("Arial",Font.PLAIN,fontsize));
		this.setForeground(res.getInitial());
		this.setBackground(whatColorIsIt());
	}
	
	public Color whatColorIsIt()
	{
		
		Color x = ((AssocatedX/edge+AssocatedY/edge)%2 ==0)? res.getRegularbg1():res.getRegularbg2();
		return x;
	}
	
	public Color initOrRegular()
	{
		return SudokuPanel.getsModel().isInitial(AssocatedX, AssocatedY)?res.getInitial():res.getRegular();
	}
	
	
	public void setAssocatedX(int a) { this.AssocatedX=a; }
	public void setAssocatedY(int a) { this.AssocatedY=a; }
	public int getAssocatedX() { return this.AssocatedX; }
	public int getAssocatedY() { return this.AssocatedY; }
	private int AssocatedX;
	private int AssocatedY;
	private SudokuButtonRes res;
}
