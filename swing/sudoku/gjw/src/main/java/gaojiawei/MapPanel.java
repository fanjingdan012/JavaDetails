package gaojiawei;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MapPanel extends JPanel
{	
	private static final long serialVersionUID = 1L;
	
	MyText[][] txtGrid;
	Color probColor=Color.red,
		mapColor=Color.black,
		highlightColor=new Color(255,255,128),
		errorColor=Color.green,
		frameColor=Color.blue,
		backColor=Color.white;
	
	public MapPanel()
	{
		setBackground(backColor);
		setLayout(null);		
		txtGrid=new MyText[9][9];
		for(int i=0;i<9;i++)for(int j=0;j<9;j++)add(txtGrid[i][j]=new MyText("",i,j));
		setColor();
		setText();
	}
	
	public void setText()
	{
		for(int i=0;i<9;i++)for(int j=0;j<9;j++)
		txtGrid[i][j].setText(Sudoku.map[i][j]==0?"":""+Sudoku.map[i][j]);
	}
	
	public void setColor()
	{
		for(int i=0;i<9;i++)for(int j=0;j<9;j++)
		txtGrid[i][j].setForeground(Sudoku.prob[i][j]==0?mapColor:probColor);
	}
	
	public void setHighlight()
	{
		int current=Sudoku.getCurrent();
		for(int i=0;i<9;i++)for(int j=0;j<9;j++)
		if(Sudoku.map[i][j]==current)
			txtGrid[i][j].setBackground(highlightColor);
		else txtGrid[i][j].setBackground(null);
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		int size=50;
		int x=getWidth()/2-225;
		int y=getHeight()/2-225;
		
		for(int i=0;i<9;i++)for(int j=0;j<9;j++)
		txtGrid[i][j].setBounds(size*j+x+2,size*i+y+2,size-3,size-3);
		
		g.setColor(frameColor);
		for(int i=1;i<9;i++)
		{
			g.drawLine(x,size*i+y,size*9+x,size*i+y);
			g.drawLine(size*i+x,y,size*i+x,size*9+y);
		}
		for(int i=0;i<=3;i++)
		{
			g.fillRect(x,size*3*i-1+y,size*9+2,3);
			g.fillRect(size*3*i-1+x,y,3,size*9+2);
		}		
	}
}