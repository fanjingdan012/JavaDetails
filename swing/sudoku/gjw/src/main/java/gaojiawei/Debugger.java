package gaojiawei;

public class Debugger
{
	static void trace(int[][] a)
	{
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)System.out.print(a[i][j]+" ");
			System.out.println();
		}
		System.out.println("**********************");
	}
}
