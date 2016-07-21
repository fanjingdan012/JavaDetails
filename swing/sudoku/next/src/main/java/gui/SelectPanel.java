/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-9
 */
package gui;

import gui.ui.SudokuMainPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import util.SudokuController;

import core.SudokuPanel;

/**
 * SelectPanel类 
 * 选择面板
 * @version 0.1
 */
public class SelectPanel extends JPanel 
{
	int sedge;
	int sx;
	public SelectPanel(int x)
	{
		sx = x;
		switch (x){
		case 2:
			sedge = 93;
			break;
		case 3:
			sedge = 51;
			break;
		case 4:
			sedge = 31;
			break;
		}
		MouseListener padClick = new PadAction();
		this.setLayout(new GridLayout(x,x));
		for (int i=1;i<x*x+1;i++)
		{
			JButton t=new JButton(Integer.toString(i));
			t.setMargin(new Insets(0,0,0,0));
			t.addMouseListener(padClick);
			switch(x){
			case 2 :
				t.setFont(new Font(t.getFont().getName(), Font.ITALIC, 32));break;
			case 3 :
				t.setFont(new Font(t.getFont().getName(), Font.ITALIC, 22));break;
			case 4 :
				t.setFont(new Font(t.getFont().getName(), Font.ITALIC, 12));break;
			}
			
			t.addMouseMotionListener(new MouseRollover());
			selectPad[i-1]=t;
			this.add(t);
		}
	}
	
	private class MouseRollover implements MouseMotionListener
	{
		public void mouseDragged(MouseEvent e) {}
		public void mouseMoved(MouseEvent e) { ((JButton) e.getSource()).repaint(); }
	}
	
	private class PadAction implements MouseListener
	{
		public void mouseClicked(MouseEvent e) {
			if (e.getButton()==MouseEvent.BUTTON1)
			{
				if (((JButton) e.getSource()).isEnabled())
				{
					SudokuPanel.getsModel().setSudoku(thisX, thisY, Integer.parseInt(((JButton) e.getSource()).getText()), SudokuPanel.getsModel().getValue(thisX, thisY));
					SudokuPanel.getsButton()[thisX][thisY].setText(((JButton) e.getSource()).getText());
					SudokuPanel.getsButton()[thisX][thisY].setForeground(Color.BLACK);
					SudokuPanel.clearAndRemove();
					int t=SudokuController.gameStatueVal();
					if (t>0) SudokuMainPanel.win();
					if (t<-1) SudokuMainPanel.inputlose();
				}
			}
			
			if (e.getButton()==MouseEvent.BUTTON3) SudokuPanel.clearAndRemove();
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
	
	//重设面板
	public void resetPanel(int a,int b,int bi, int bj, List<Integer> t) {
		super.setBounds(bi-sedge, bj-sedge, sedge * sx, sedge * sx);
		this.setVisible(true);
		for (int i=0;i<sx * sx;i++) selectPad[i].setEnabled(false);
		for (int i=0;i<t.size();i++) selectPad[t.get(i)-1].setEnabled(true);
		thisX=a;
		thisY=b;
		}
	
	private JButton[] selectPad=new JButton[16];
	private int thisX;
	private int thisY;
}