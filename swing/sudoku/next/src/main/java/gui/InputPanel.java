/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-9
 */
package gui;

import java.awt.GridLayout;

import javax.swing.JPanel;


/**
 * InputPanel类 
 * 输入框容器，放置输入框
 * @version 0.1
 */
public class InputPanel extends JPanel
{
	int tedge;
	public InputPanel(int fontsize, int edge)
	{
		switch (edge){
		case 4:
			tedge = 93;
			break;
		case 9:
			tedge = 51;
			break;
		case 16:
			tedge = 31;
			break;
		}
		this.setLayout(new GridLayout(1,1));
		inputer=new SudokuInputBox("",-1,-1, fontsize, edge);
		this.add(inputer);	
	}
	
	public void setBounds(int i, int j) //设置InputPanel的位置 
	{
			super.setBounds(i, j+80, tedge + 4, tedge + 1);
	}
	
	public void resetInputer(String v,int a,int b,int bi, int bj,int fontsize, int edge) //重设 
	{
		this.remove(inputer);
		inputer=new SudokuInputBox(v,a,b,fontsize,edge);
		this.add(inputer);
		super.setBounds(bi, bj, tedge, tedge);
		inputer.setBounds(super.getBounds());
		this.setVisible(true);
		}
	public SudokuInputBox inputer;
	
}