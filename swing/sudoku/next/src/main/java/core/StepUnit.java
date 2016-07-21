/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-8
 */
package core;

import java.io.Serializable;

/**
 * SudokuUnit类 
 * 历史记录单元
 * @version 0.1
 */
class StepUnit implements Serializable
{
	public StepUnit (int a, int b, int bx, int by)
	{
		this.source=a;
		this.target=b;
		this.x=bx;
		this.y=by;
	}
	int source;
	int target;
	int x;
	int y;
}