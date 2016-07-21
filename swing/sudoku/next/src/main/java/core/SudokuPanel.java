/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-8
 */
package core;

import java.awt.GridLayout;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import util.SudokuController;
import util.data.BorderButtonRes;
import util.data.SudokuButtonRes;

import gui.*;
import gui.ui.SudokuMainPanel;

/**
 * SudokuPanel类 
 * 显示数独游戏内容的核心区域
 * @version 0.3
 */
public class SudokuPanel extends JPanel{

	public SudokuPanel(SudokuModel model, int sedge)
	{
		this.setLayout(null);
		parentPanel = new JPanel();
		switch(sedge){
		case 2:
			parentPanel.setLayout(new GridLayout(6,6)); //TODO
			parentPanel.setSize(558, 558); //558 540			
			this.setSize(558,558);
			break;
		case 3:
			parentPanel.setLayout(new GridLayout(11,11)); //TODO
			parentPanel.setSize(561, 561); //558 1540			
			this.setSize(561,561);
			break;
		case 4:
			parentPanel.setLayout(new GridLayout(18,18)); //TODO
			parentPanel.setSize(558, 558); //558 540			
			this.setSize(558,558);
			break;
		}
		
		
		this.setLocation(225,25);

		SudokuPanel.sModel = model;
		

		//添加输入框
		inputBox=new InputPanel(sModel.getFontsize(), sModel.edge);
		add(inputBox);
		inputBox.setVisible(false);
		//添加选择面板
		selectBox=new SelectPanel(sedge);
		add(selectBox);
		selectBox.setVisible(false);
		
		
		
		this.add(parentPanel);
		res = SudokuNext.getResources().sbr;
		resb = SudokuNext.getResources().bbr;
		drawSudoku(sModel);
		
		
		//冲突显示完毕对于的ActionListener
		ActionListener conPerformer = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	clearRedText();
		    	conflication.stop();
		    }
		};	
		conflication=new Timer(CTIMER, conPerformer);
	}

	//画出数独
	private void drawSudoku (SudokuModel sModel)
	{
		//第一行
		parentPanel.add(new BorderButton("", resb, sModel.getFontsize()));
		for (int i=0;i<sModel.edge;i++) parentPanel.add(new BorderButton(Integer.toString(i+1), resb, sModel.getFontsize()));;
		parentPanel.add(new BorderButton("", resb, sModel.getFontsize()));
		//第二行-第十行
		for (int i=0;i<sModel.edge;i++)
		{
			parentPanel.add(new BorderButton(Character.toString((char) ('A'+i)), resb, sModel.getFontsize()));
			for (int j=0;j<sModel.edge;j++)
			{
				sButton[i][j] = new SudokuButton(SudokuController.showValue(sModel.getValue(i, j)), i, j, res, sModel.getFontsize(), sModel.sedge);
				if (sModel.isInitial(i, j))
					sButton[i][j].setInitial();
				else 
					sButton[i][j].setRegular();
				sButton[i][j].addMouseListener(new ClickAction());
				parentPanel.add(sButton[i][j]);
			}
			parentPanel.add(new BorderButton("", resb, sModel.getFontsize()));
		}
		//第十一行
		for (int i=0;i<sModel.edge + 2;i++) parentPanel.add(new BorderButton("", resb, sModel.getFontsize()));
		this.repaint();
	}
	
	/**
	 * sButton的Get方法
	 * @return the sButton
	 */
	public static SudokuButton[][] getsButton() {
		return sButton;
	}

	/**
	 * sModel的Get方法
	 * @return the sModel
	 */
	public static SudokuModel getsModel() {
		return sModel;
	}

	/**
	 * 清除显示样式
	 */
	public static void clearAndRemove()
	{
		inputBox.inputer.setText("");
		inputBox.setVisible(false);
		selectBox.setVisible(false);
		clearRedText();
	}
	
	/**
	 * 设置所有方格的初始状态
	 * 起这个名字仅仅是为了对源代码尽量少的更改
	 */
	public static void clearRedText()
	{
		for (int i=0;i<sModel.edge*sModel.edge;i++) 
    	{
			if (sModel.isInitial(i/sModel.edge, i%sModel.edge))
				sButton[i/sModel.edge][i%sModel.edge].setInitial();
			else sButton[i/sModel.edge][i%sModel.edge].setRegular();
    	}
	}
	
	/**
	 * sModel的Set方法
	 * @param sModel 需要设置的字段
	 */
	public static void setsModel(SudokuModel sModel) {
		SudokuPanel.sModel = sModel;
	}
	
	/**
	 * 设置InputPanel可见性
	 * 供外部调用
	 */
	public static void setInputPanelVisible(boolean b) {
		inputBox.setVisible(b);
	}	
	
	
	//单击数独元素的操作
	private class ClickAction implements MouseListener
	{
		public void mouseClicked(MouseEvent e) 
		{	
			if (!SudokuMainPanel.isShowAnswer())
			{
				clearAndRemove();
				int x=((SudokuButton) e.getSource()).getAssocatedX();
				int y=((SudokuButton) e.getSource()).getAssocatedY();
				if (x>=0&&y>=0&&!sModel.isInitial(x, y))
				{
					if (e.getButton()==MouseEvent.BUTTON1) //鼠标左键--输入
					{
						for (int i=0;i<sModel.edge;i++)
						{
							sButton[x][i].setHint();
							sButton[i][y].setHint();
						}
						for (int i=0;i<sModel.sedge;i++)
							for (int j=0;j<sModel.sedge;j++)
								sButton[x/sModel.sedge*sModel.sedge+i][y/sModel.sedge*sModel.sedge+j].setHint();
						inputBox.resetInputer(sButton[x][y].getText(), x, y, sButton[x][y].getLocation().x, sButton[x][y].getLocation().y, sModel.getFontsize(), sModel.edge);
						inputBox.inputer.grabFocus();
						inputBox.inputer.select(0,0);					
					}
					if (e.getButton()==MouseEvent.BUTTON3) //鼠标右键--选择
					{
						List<Integer> t = SudokuController.getAvailable(x, y,sModel);
						if (t.size()!=0)
						{
							selectBox.resetPanel(x, y, sButton[x][y].getLocation().x, sButton[x][y].getLocation().y, t);
							selectBox.grabFocus();
						}
					}
				}
			}
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
    }
	
	/**
	 * 更新数独
	 */
	public static void updatePanel() {
			clearAndRemove();
			SudokuMainPanel.globalStart();
			for (int i=0;i<sModel.edge;i++)
				for (int j=0;j<sModel.edge;j++)
				{
					sButton[i][j].setText(Integer.toString(sModel.getValue(i, j)));
					if (sModel.isInitial(i, j))
						sButton[i][j].setInitial();
					else sButton[i][j].setRegular();
				}		
	}
	
	final private int CTIMER=1300; //冲突提示时间长度
	public static Timer conflication;
	private static JPanel parentPanel;
	private static InputPanel inputBox;
	private static SelectPanel selectBox;
	private SudokuButtonRes res;
	private BorderButtonRes resb;
	private static SudokuModel sModel;
	public static SudokuButton[][] sButton=new SudokuButton[16][16];

}
