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
 * ����ģ��
 * SudokuModel�� 
 * @version 0.1
 */
public class SudokuModel implements Serializable
{
	
	//��ʼ������������
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
	
	public void setBoard(int a, int b, int v, boolean f) //��������
	{
		board[a][b].value=v;
		board[a][b].isInitial=f;
	}

	public int getValue(int x, int y) //��ȡ������ֵ
	{
		return board[x][y].value; 
	}

	public boolean isFlag(int x, int y) //��ȡ�����Ƿ񱻱��
	{
		return board[x][y].isFlag;
	}
	
	public void setFlag(int x, int y) //�������
	{
		board[x][y].isFlag=!board[x][y].isFlag;
	}
	
	public boolean isInitial(int x, int y) //��ȡ�����Ƿ�ΪԤ�����ֵ
	{
		return board[x][y].isInitial;
	}
	
	public void setInitial(int x, int y, boolean t) //��ȡ�����Ƿ�ΪԤ�����ֵ
	{
		board[x][y].isInitial=t;
	}
	
	public void setSudoku(int x, int y, int value, int p) //��������
	{
		if (value==p) return;
		board[x][y].value=value;
		history.add(new StepUnit(p,value,x,y));
	}
	
	public int undoSudoku() //����
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
	 * diff��Get����
	 * @return the diff
	 */
	public int getDiff() {
		return diff;
	}

	/**
	 * diff��Set����
	 * @param diff ��Ҫ���õ��ֶ�
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
