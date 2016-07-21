package gaojiawei;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class MyWindow extends MyFrame
{
	private static final long serialVersionUID = 8445793241684580784L;
	Timer timer;
	
	public static void main(String[] args)
	{
		new Solver();
		new Sudoku();
		MyWindow window=new MyWindow();
		window.setLocationRelativeTo(null);
	}
	
	public MyWindow()
	{
		super();
		
		timer=new Timer(1000,new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				timePanel.repaint();
				timePanel.s++;
				if(timePanel.s==60){timePanel.s=0;++timePanel.m;}
				if(timePanel.m==60){timePanel.m=0;++timePanel.h;}
			}
		});
		
		newGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(Sudoku.isSaved()
						|| JOptionPane.showConfirmDialog(null,"Are you going to leave without saving?","Save",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
					Sudoku.newGame();
				refresh();
			}
		});
		
		choose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					openFile();
				}
				catch (IOException e1)
				{
					JOptionPane.showMessageDialog(null,"Unexpected error while opening","RP problem!!",JOptionPane.ERROR_MESSAGE);
				}
				refresh();
			}
		});
		
		replay.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				refresh();
			}
		});
		
		solve.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				for(int i=0;i<9;i++)for(int j=0;j<9;j++)Sudoku.map[i][j]=Sudoku.ans[i][j];
				
				mapPanel.setText();
				timer.stop();
			}
		});
		
		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		
		addGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(Sudoku.isSaved()
						|| JOptionPane.showConfirmDialog(null,"Are you going to leave without saving?","Save",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
					Sudoku.setCustomize(true);
				refresh();
			}
		});
		
		save.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					save();
				}
				catch (IOException e1)
				{
					JOptionPane.showMessageDialog(null,"Unexpected error while saving","RP problem!!",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		sudokuHelp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null,helpMsg,"Help",JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		about.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null,aboutMsg,"About",JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		combo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Sudoku.setDifficulty(combo.getSelectedIndex());
				Sudoku.newGame();
				refresh();
			}
		});	
		
		for(int i=0;i<9;i++)for(int j=0;j<9;j++)
		mapPanel.txtGrid[i][j].addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e) 
			{			
				int current=Sudoku.getCurrent();
				mapPanel.setHighlight();
				
				MyText source=(MyText)e.getSource();
				int x=source.x,y=source.y;
				if(!Sudoku.getCustomize() && Sudoku.prob[x][y]!=0)return;
				
				if(e.getButton()==1)
				{
					boolean canFill=true;
					
					for(int i=0;i!=9;++i) for(int j=0;j!=9;++j)
					if(Sudoku.map[i][j]==current &&(i!=x || j!=y)&& (i==x || j==y || i/3*3+j/3==x/3*3+y/3))
					{
						mapPanel.txtGrid[i][j].setBackground(mapPanel.errorColor);
						canFill=false;
					}
					
					if(canFill)
					{
						Sudoku.fillIn(x,y,current);
						mapPanel.txtGrid[x][y].setBackground(mapPanel.highlightColor);
						if(Sudoku.getCustomize())mapPanel.txtGrid[x][y].setForeground(mapPanel.probColor);
					}
				}				
				else if(e.getButton()==3)
				{
					if(!Sudoku.getCustomize())Sudoku.hint(x,y);
					mapPanel.setHighlight();
					timePanel.s+=20;
					timePanel.m+=timePanel.s/60;timePanel.s=timePanel.s%60;
					timePanel.h+=timePanel.m/60;timePanel.m=timePanel.m%60;
				}
				else Sudoku.delete(x,y);
				
				mapPanel.setText();
				if(Sudoku.win())
				{
					timer.stop();
					JOptionPane.showMessageDialog(null,"you win!");
					if(Sudoku.getDifficulty()!=2)Sudoku.setDifficulty(Sudoku.getDifficulty()+1);
				}
			}
		});
		
		for(int i=0;i<9;i++)
		buttons.btGrid[i].addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Sudoku.setCurrent(  ( (MyButton)e.getSource() ).n  );
				mapPanel.setHighlight();
			}
		});
		
		addWindowListener(new WindowListener()
		{
			public void windowClosing(WindowEvent e)
			{
				int state=JOptionPane.showConfirmDialog(null,"Are you sure to exit the game?","",JOptionPane.YES_NO_OPTION);
				switch(state)
				{
					case JOptionPane.YES_OPTION:/*save!!!*/dispose();break;
					case JOptionPane.NO_OPTION:break;
				}
			}

			public void windowActivated(WindowEvent e){}
			public void windowClosed(WindowEvent e){}
			public void windowDeactivated(WindowEvent e){}
			public void windowIconified(WindowEvent e){}
			public void windowDeiconified(WindowEvent e){}
			public void windowOpened(WindowEvent e){}
		});

		refresh();
	}
	
	void openFile()throws IOException
	{
		JFileChooser jf=new JFileChooser(".\\problem");
		File file;
		if(jf.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)file=jf.getSelectedFile();else return;
		Scanner in=new Scanner(file);
		for(int i=0;i<9;i++)for(int j=0;j<9;j++)
		{
			if(in.hasNext())Sudoku.prob[i][j]=in.nextInt();
			if(Sudoku.prob[i][j]<0||Sudoku.prob[i][j]>9)
			{
				JOptionPane.showMessageDialog(null,"文件格式错误！","Error",JOptionPane.ERROR_MESSAGE);
				Sudoku.arrayClear(Sudoku.prob);
			}	
		}
		int answers=Solver.solve(Sudoku.prob,Sudoku.ans);
		if(answers==0)JOptionPane.showMessageDialog(null,"This problem doesn't have an answer. Please choose another one.","Message",JOptionPane.INFORMATION_MESSAGE);
		else if(answers>1)JOptionPane.showMessageDialog(null,"This problem has more than one answer. I recommand you choose another one.","Message",JOptionPane.INFORMATION_MESSAGE);
		Sudoku.setCustomize(false);
	}
	
	void save() throws IOException
	{
		JFileChooser jf=new JFileChooser(".\\problem");
		File file;
		if(jf.showSaveDialog(this)==JFileChooser.APPROVE_OPTION)file=jf.getSelectedFile();else return;
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter(file)));
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)out.print(Sudoku.prob[i][j]+" ");
			out.println();
		}
		out.close();
		Sudoku.setSaved(true);
	}
	
	void refresh()
	{
		Sudoku.refresh();
		mapPanel.setColor();
		mapPanel.setText();
		mapPanel.setHighlight();
		timePanel.h=timePanel.m=timePanel.s=0;
		timePanel.repaint();
		timer.start();
	}
}