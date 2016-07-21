/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-8
 */
package core;

import java.io.Serializable;

/**
 * SudokuUnit类 
 * 数独单元
 * @version 0.1
 */
class SudokuUnit implements Serializable
{
	public SudokuUnit(int a, boolean b)
	{
		this.value=a;
		this.isInitial=b;
		this.isFlag=false;
	}
	
	@Override
	public SudokuUnit clone()
	{
		SudokuUnit u=new SudokuUnit(this.value,this.isInitial);
		return u;
	}
	
	int value;
	boolean isInitial;
	boolean isFlag;
}
