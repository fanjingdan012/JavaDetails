/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2012-3-18
 */
package util;

/**
 * SudokuSolver�� 
 * 
 * @version 0.1
 */
public class SudokuSolver {
	private static int N = 9, M = 3;
	
	/**����������ȫ���ǵ��Ǹ���������������*/
	private static int R=N*N*N,C=N*N*4;
	
	/**һ�������������ġ��Լ�ûɶ����ĸ������*/
	private static Node head;
	
	/**������ÿһ�д������õ�*/
	private static Node row[];
	
	/**ÿһ�еı�ͷ*/
	private static Node col[];
	
	/**ÿ���м���Ԫ��*/
	private static int cnt[];
	
	/**��¼���λ��*/
	private static int mem[];
	
	/**0��ʾ�޽⣬1��ʾΨһ�⣬����1��ʾ�ж��*/
	private static int solves=0;
	
	
	/**a��b������������Ŀʱ�õĸ�������*/
	//private static int a[][],b[][];

	/**
	 * ���Ǹ�Dancing Links����ûʲô�ÿ��ġ���
	 * 
	 * @param map ��Ŀ
	 * @param ans ��¼��
	 * @return ��ĸ���
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
	 * ����һ����㵽��r�е�c��
	 * @param r ����
	 * @param c ����
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
	 * ��ʱ���ǵ�c��
	 * @param c Ҫ���ǵ�����
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
	 * ���°ѵ�c������ȥ
	 * @param c Ҫ����ȥ��������
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
	 * ������Ǹ������������r(�s_�t)�q
	 * 
	 * @param k ��������
	 * @return ��ĸ���
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
		 * �ҵ�ǰԪ�����ٵ�һ��minc
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
	 * ���ɸ���Ŀ
	 * @param difficulty ��Ŀ�Ѷ�
	 * @param ans ��Ȼ�Ǵ���
	 * @return return��ٲȻ�����ɳ�������Ŀ����
	 */
	/*public static int[][] newGame(int difficulty,int[][] ans)
	{
		int i,j;
		Random random=new Random(System.currentTimeMillis());
		a=new int[9][9];
		b=new int[9][9];
		a[random.nextInt(9)][random.nextInt(9)]=random.nextInt(9)+1;
		solve(a,b);
		
		//blank�ǿո���������difficulty��ȡֵ
		for(i=0;i<9;i++)for(j=0;j<9;j++)a[i][j]=ans[i][j]=b[i][j];
		int blank=difficulty==2?60:difficulty==1?53:41;
		
		while(dig(blank)>=5);
		return a;
	}*/

}

/**
 * �ڵ��࡭��ûɶ��˵��
 * @author gjw
 */
class Node
{
	/**���Ǹ����729*324�����е�λ�ã�r���У�c����*/
	int r,c;
	/**�����������ҵ��൱��ָ���reference*/
	Node up,down,left,right;
}