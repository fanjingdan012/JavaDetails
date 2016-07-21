/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2012-3-18
 */
package util;

/**
 * SudokuSolver类 
 * 
 * @version 0.1
 */
public class SudokuSolver {
	private static int N = 9, M = 3;
	
	/**被我们求完全覆盖的那个大矩阵的行数列数*/
	private static int R=N*N*N,C=N*N*4;
	
	/**一个引领这个矩阵的、自己没啥意义的辅助结点*/
	private static Node head;
	
	/**帮助把每一行串起来用的*/
	private static Node row[];
	
	/**每一列的表头*/
	private static Node col[];
	
	/**每列有几个元素*/
	private static int cnt[];
	
	/**记录解的位置*/
	private static int mem[];
	
	/**0表示无解，1表示唯一解，大于1表示有多解*/
	private static int solves=0;
	
	
	/**a和b都是生成新题目时用的辅助数组*/
	//private static int a[][],b[][];

	/**
	 * 就是个Dancing Links啦，没什么好看的……
	 * 
	 * @param map 题目
	 * @param ans 记录答案
	 * @return 解的个数
	 */
	public static int solve(int _N_, int[][] map, int[][] ans)
	{
		N = _N_; M = (int)Math.sqrt(N); R = N*N*N; C = N*N*4;
		
		int i,j;
		for(i=0;i<N;i++)for(j=0;j<N;j++)ans[i][j]=map[i][j];
		
		row=new Node[R];
		col=new Node[C];
		cnt=new int[C];
		mem=new int[R];
	
		head=new Node();
		head.left=head;
		head.right=head;
		head.up=head;
		head.down=head;
		head.r=R;
		head.c=C;
	
		for(i=C-1;i>=0;i--)
		{
			col[i]=new Node();
			col[i].c=i;
			col[i].r=R;
			col[i].up=col[i];
			col[i].down=col[i];
			col[i].left=head;
			col[i].right=head.right;
			col[i].left.right=col[i];
			col[i].right.left=col[i];
		}
	
		for(i=R-1;i>=0;i--)
		{
			row[i]=new Node();
			row[i].r=i;
			row[i].c=C;
			row[i].left=row[i];
			row[i].right=row[i];
			row[i].up=head;
			row[i].down=head.down;
			row[i].up.down=row[i];
			row[i].down.up=row[i];
		}
	
		for(i=0;i<R;i++)
		{
			int r=i/N/N;
			int c=i/N%N;
			int n=i%N+1;
			if(map[r][c]==0 || map[r][c]==n)
			{
				link(i,r*N+n-1);
				link(i,N*N+c*N+n-1);
				link(i,N*N*2+(r/M*M+c/M)*N+n-1);
				link(i,N*N*3+r*N+c);
			}
		}
	
		for(i=0;i<R;i++)
		{
			row[i].left.right=row[i].right;
			row[i].right.left=row[i].left;
		}
		solves=0;
		search(0);
		for(i=0;i<N*N;i++)ans[mem[i]/N/N][mem[i]/N%N]=mem[i]%N+1;		
		return solves;
	}
	
	/**
	 * 连接一个结点到第r行第c列
	 * @param r 行数
	 * @param c 列数
	 */
	private static void link(int r,int c)
	{
		cnt[c]++;
		Node t=new Node();
		t.r=r;
		t.c=c;
		t.left=row[r];
		t.right=row[r].right;
		t.left.right=t;
		t.right.left=t;
		t.up=col[c];
		t.down=col[c].down;
		t.up.down=t;
		t.down.up=t;
	}
	
	/**
	 * 暂时覆盖第c列
	 * @param c 要覆盖的列数
	 */
	private static void remove(int c)
	{
		Node t,tt;
		col[c].right.left=col[c].left;
		col[c].left.right=col[c].right;
		for(t=col[c].down;t!=col[c];t=t.down)
		{
			for(tt=t.right;tt!=t;tt=tt.right)
			{
				cnt[tt.c]--;
				tt.up.down=tt.down;
				tt.down.up=tt.up;
			}
			t.left.right=t.right;
			t.right.left=t.left;
		}
	}
	
	/**
	 * 重新把第c列连回去
	 * @param c 要连回去的列数数
	 */
	private static void resume(int c)
	{
		Node t,tt;
		col[c].left.right=col[c];
		col[c].right.left=col[c];
		for(t=col[c].down;t!=col[c];t=t.down)
		{
			t.left.right=t;
			t.right.left=t;
			for(tt=t.left;tt!=t;tt=tt.left)
			{
				cnt[tt.c]++;
				tt.up.down=tt;
				tt.down.up=tt;
			}
		}
	}
	
	/**
	 * 这个就是个搜索啦……╮(╯_╰)╭
	 * 
	 * @param k 搜索层数
	 * @return 解的个数
	 */
	private static int search(int k)
	{
		if(solves>=1)return solves;
		if(head.right==head)
		{
			solves++;
			return solves;
		}
		Node t,tt;
		int min=2000000000,minc=0;
		
		/*
		 * 找当前元素最少的一列minc
		 */
		for(t=head.right;t!=head;t=t.right)
		{
			if(cnt[t.c]<min)
			{
				min=cnt[t.c];
				minc=t.c;
				if(min<=1)break;
			}
		}
		remove(minc);
		for(t=col[minc].down;t!=col[minc];t=t.down)
		{
			mem[k]=t.r;
			t.left.right=t;
			for(tt=t.right;tt!=t;tt=tt.right)remove(tt.c);
			t.left.right=t.right;
			search(k+1);
			if(solves>=1)return solves;
			t.right.left=t;
			for(tt=t.left;tt!=t;tt=tt.left)resume(tt.c);
		}
		resume(minc);
		return solves;
	}
	
	/*
	 ************************************************************************
	 ************************************************************************
	 ************************************************************************
	 */
	
	/**
	 * 生成个题目
	 * @param difficulty 题目难度
	 * @param ans 当然是答案啦
	 * @return return的俨然是生成出来的题目……
	 */
	/*public static int[][] newGame(int difficulty,int[][] ans)
	{
		int i,j;
		Random random=new Random(System.currentTimeMillis());
		a=new int[9][9];
		b=new int[9][9];
		a[random.nextInt(9)][random.nextInt(9)]=random.nextInt(9)+1;
		solve(a,b);
		
		//blank是空格数，根据difficulty来取值
		for(i=0;i<9;i++)for(j=0;j<9;j++)a[i][j]=ans[i][j]=b[i][j];
		int blank=difficulty==2?60:difficulty==1?53:41;
		
		while(dig(blank)>=5);
		return a;
	}*/

}

/**
 * 节点类……没啥好说的
 * @author gjw
 */
class Node
{
	/**在那个大的729*324矩阵中的位置，r是行，c是列*/
	int r,c;
	/**连接上下左右的相当于指针的reference*/
	Node up,down,left,right;
}