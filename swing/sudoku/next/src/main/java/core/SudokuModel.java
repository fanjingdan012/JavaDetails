/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-5
 */
package core;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * 数独模型
 * SudokuModel类 
 * @version 0.1
 */
public class SudokuModel implements Serializable
{
	
	//初始化数独的内容
	public SudokuModel(int edge)
	{
		board = new SudokuUnit[edge][edge];
		for (int i=0;i<edge;i++)
			for (int j=0;j<edge;j++)
			{
				board[i][j]=new SudokuUnit(0,false);
			}
		globalTimer = 0;
		setEdge(edge);
	}
	
	@Override
	public SudokuModel clone()
	{
		SudokuModel a=new SudokuModel(edge);
		a.setDiff(diff);
		a.setEdge(edge);
		for (int i=0;i<edge;i++)
			for (int j=0;j<edge;j++)
			{
				a.board[i][j]=this.board[i][j].clone();
			}
		return a;
	}
	
	public void setBoard(int a, int b, int v, boolean f) //设置数独
	{
		board[a][b].value=v;
		board[a][b].isInitial=f;
	}

	public int getValue(int x, int y) //获取数独的值
	{
		return board[x][y].value; 
	}

	public boolean isFlag(int x, int y) //获取数独是否被标记
	{
		return board[x][y].isFlag;
	}
	
	public void setFlag(int x, int y) //标记数独
	{
		board[x][y].isFlag=!board[x][y].isFlag;
	}
	
	public boolean isInitial(int x, int y) //获取数独是否为预定义的值
	{
		return board[x][y].isInitial;
	}
	
	public void setInitial(int x, int y, boolean t) //获取数独是否为预定义的值
	{
		board[x][y].isInitial=t;
	}
	
	public void setSudoku(int x, int y, int value, int p) //填入数独
	{
		if (value==p) return;
		board[x][y].value=value;
		history.add(new StepUnit(p,value,x,y));
	}
	
	public int undoSudoku() //撤销
	{
		if (history.size()>0)
		{
			StepUnit tUnit=history.get(history.size()-1);
			board[tUnit.x][tUnit.y].value=tUnit.source;
			history.remove(history.size()-1);
			return tUnit.source*10000+tUnit.x*100+tUnit.y;
		}
		else return -1;
	}
	
	public int diff;
	/**
	 * diff的Get方法
	 * @return the diff
	 */
	public int getDiff() {
		return diff;
	}

	/**
	 * diff的Set方法
	 * @param diff 需要设置的字段
	 */
	public void setDiff(int diff) {
		this.diff = diff;
	}
	
	public void setEdge(int _edge){
		edge = _edge;
		sedge = (int) Math.sqrt(_edge);
	}

	public int globalTimer;
	public int edge = 1;
	public int sedge = 1;
	SudokuUnit[][] board=null;
	List<StepUnit> history=new LinkedList<StepUnit>();
	/**
	 * @return
	 */
	public int getFontsize() {
		switch (edge){
		case 4:return 32;
		case 9:return 22;
		case 16:return 12;
		}
		return 8;
	}
}
