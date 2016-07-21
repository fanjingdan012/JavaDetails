/*
 *  Copyright 2009 GaoJiawei. All rights reserved.
 *******************************************************
 *   Sudoku 3.1                                        *
 *                                                     *
 *   New Features:                                     *
 *     Using more user-friendly GUI,                   *
 *     Using Dancing Links algorithm to solve Sudoku,  *
 *     Able to create random problems,                 *
 *     Solved some little bugs in Sudoku 2.1,          *
 *     and...and so on -_-||                           *
 *******************************************************
 *   Welcome to my web site: http://www.gaojiawei.com
 *   My Email: 09302010076@fudan.edu.cn
 */


package gaojiawei;


import javax.swing.JOptionPane;

/**
 * @author Gaojiawei(高嘉蔚09302010076)
 * @version 3.1
 */
public class Sudoku
{
	/**problem array**/
	static int[][] prob;
	
	/**the main map**/
	static int[][] map;
	
	/**answer to the problem**/
	static int[][] ans;
	
	/**the number of numbers already filled**/
	private static int filled=0;
	
	/**difficulty of the problem**/
	private static int difficulty=0;
	
	/**current state**/
	private static boolean customize=false;
	
	/***/
	private static int current=1;
	
	/***/
	private static boolean saved=false;
	
	
	Sudoku()
	{
		prob=new int[9][9];
		map=new int[9][9];
		ans=new int[9][9];
		newGame();
	}
	
	static void refresh()
	{
		for(int i=0;i<9;i++)for(int j=0;j<9;j++)map[i][j]=prob[i][j];
		filled=0;
		for(int i=0;i!=9;++i)
		for(int j=0;j!=9;++j)
			if(map[i][j]!=0)filled++;
	}
		
	static void fillIn(int x,int y,int n)
	{
		if(customize)
		{
			int tmp=prob[x][y];
			prob[x][y]=n;
			if(Solver.solve(prob,ans)==0)
			{
				JOptionPane.showMessageDialog(null,"您的题目无解，请返回修改","Warning",JOptionPane.ERROR_MESSAGE);
				prob[x][y]=tmp;
			}
			map[x][y]=prob[x][y];
		}
		else
		{
			if(map[x][y]==0)filled++;
			map[x][y]=n;
		}
		saved=false;
	}
	
	static void hint(int x,int y)
	{
		if(prob[x][y]!=0)return;
		if(map[x][y]==0)filled++;
		map[x][y]=ans[x][y];
		for(int i=0;i<9;i++)for(int j=0;j<9;j++)
		if(map[i][j]==map[x][y] && (i!=x || j!=y) && (i==x || j==y || i/3*3+j/3==x/3*3+y/3))
		{
			map[i][j]=0;
			filled--;
		}
		saved=false;
	}

	static void delete(int x,int y)
	{
		if((customize ||((!customize) && prob[x][y]==0))&& map[x][y]!=0)
		{
			map[x][y]=0;
			if(customize)prob[x][y]=0;
			filled--;
		}
		saved=false;
	}
	
	static void newGame()
	{
		prob=Solver.newGame(difficulty,ans);
		customize=false;
		saved=false;
	}
	
	static void arrayClear(int[][] a)
	{
		for(int i=0;i!=9;++i) for(int j=0;j!=9;++j) a[i][j]=0;
	}
	
	static boolean win()
	{
		return filled==81;
	}

	static void setDifficulty(int difficulty)
	{
		Sudoku.difficulty=difficulty;
	}
	static int getDifficulty()
	{
		return difficulty;
	}
	
	static void setCurrent(int current)
	{
		Sudoku.current=current;
	}
	static int getCurrent()
	{
		return current;
	}
	
	public static void setSaved(boolean saved)
	{
		Sudoku.saved = saved;
	}
	public static boolean isSaved()
	{
		return saved;
	}

	static void setCustomize(boolean customize)
	{
		if(customize)
		{	
			arrayClear(map);
			arrayClear(prob);
			filled=0;
		}
		Sudoku.customize=customize;
	}
	static boolean getCustomize()
	{
		return customize;
	}
}