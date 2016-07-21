/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-8
 */
package gui;

import gui.ui.SudokuMainPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JTextField;

import util.SudokuController;

import core.SudokuModel;
import core.SudokuPanel;

/**
 * 数独输入框
 * SudokuInputBox类 
 * 
 * @version 0.1
 */
public class SudokuInputBox extends JTextField
{
	public SudokuInputBox(String x,int a, int b, int fontsize, int edge) //x=该按钮原来的值
	{
		super();
		list=new LinkedList<Integer>();
		this.setMargin(new Insets(0,0,0,0));
		this.edge = edge;
		//this.setSize(50, 50);
		this.setFont(new Font("Arial", Font.PLAIN, fontsize));
		this.setText("  ");
		this.setBackground(Color.WHITE);
		this.setAssocated(a, b);
		this.original=(x==""?0:Integer.parseInt(x));
		MouseMotionListener mml=new MouseRollover();
		this.addMouseMotionListener(mml);
	}
	
	
	private class MouseRollover implements MouseMotionListener //鼠标移动重画组件
	{
		public void mouseDragged(MouseEvent e) {}
		public void mouseMoved(MouseEvent e) { ((SudokuInputBox) e.getSource()).repaint(); ((SudokuInputBox) e.getSource()).grabFocus(); }
	}
	
	public void setAssocated(int x, int y) //设置与输入框关联的按钮
	{
		buttonX=x;
		buttonY=y;
	}
	
	@Override
	public void processKeyEvent(KeyEvent e)
	{
		if (e.getKeyLocation()==0)
		{
			char c=e.getKeyChar();
			if (c>'0'&&c<='9' || c >= 'a' && c <= 'g')   //输入了一个数字
			{
				int value = (c>='a'?10+c-'a':c - '0');
				if (value > edge)
					return;
				SudokuPanel.clearRedText();				
				this.setText("  "+ value);
				SudokuModel s=SudokuPanel.getsModel();
				
				list=SudokuController.validate(buttonX, buttonY, value,s);
				if (list.isEmpty()) //可以填入数字
				{
					s.setSudoku(buttonX,buttonY,value,original);
					original=c-'0';
					SudokuPanel.sButton[buttonX][buttonY].setText(Integer.toString(value));
					int t=SudokuController.gameStatueVal();
					if (t>0) SudokuMainPanel.win();
					if (t<-1) SudokuMainPanel.inputlose();
					SudokuPanel.setInputPanelVisible(false);
				}
				else 
				{
					for (int i=0;i<list.size();i++) //显示冲突
					{
						int t=list.get(i);
						SudokuPanel.sButton[t/edge][t%edge].setWarning();
					}
					SudokuPanel.conflication.start();
				}
				
			}
			else if (c=='0')  //删除
			{
				SudokuPanel.clearRedText();
				SudokuPanel.getsModel().setSudoku(buttonX, buttonY, 0, original);
				original=0;
				SudokuPanel.sButton[buttonX][buttonY].setText("");
				SudokuPanel.setInputPanelVisible(false);
			}
		}
	}
	public static List<Integer> list;
	private int buttonX;
	private int buttonY;
	private int original;
	private int edge;
}
