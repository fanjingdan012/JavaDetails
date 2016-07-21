/**
 * Project Sudokhowu Next!
 * @author pRobE
 * @last update 2010-1-8
 */
package gui.ui;

import gui.MenuButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import util.SudokuController;
import util.data.ImageManager;

import core.SudokuModel;
import core.SudokuNext;
import core.SudokuPanel;

/**
 * SudokuFrame类 
 * 游戏界面
 * @version 0.1
 */
public class SudokuMainPanel extends JPanel{

	public SudokuMainPanel(SudokuModel _model)
	{
		this.showAnswer=false;
		this.setLayout(null);
		this.setBounds(0, 0, 800, 600);
		SudokuNext.getHighScore().newLocalGame();
		sudokuPanel = new SudokuPanel( _model, _model.sedge);
		sudokuPanel.setSize(550, 550);
		this.add(sudokuPanel);
		
		iundo = new MenuButton(SudokuNext.getResources().iundo);
		iundo.addMouseListener(new MouseListener()
		{
				public void mouseClicked(MouseEvent e) {
					if (showAnswer) return;
					SudokuPanel.clearAndRemove();
					int t=SudokuPanel.getsModel().undoSudoku();
					if (t!=-1)
					{
						String v=(t/10000==0?"":Integer.toString(t/10000));
						t=t%10000;
						SudokuPanel.getsButton()[t/100][t%100].setText(v);
					}else JOptionPane.showMessageDialog(null, "oops......已后退至开局状态","Sudoku Game",JOptionPane.INFORMATION_MESSAGE);				
				}
				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				public void mousePressed(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}		
		});
		this.add(iundo);
		
		inew = new MenuButton(SudokuNext.getResources().inew);
		inew.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) {
				SudokuPanel.clearAndRemove();
				SudokuNext.switchNewGameMenu();}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		this.add(inew);
		
		isave = new MenuButton(SudokuNext.getResources().isave);
		isave.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) {
				if (showAnswer) return;
				SudokuPanel.clearAndRemove();
				SudokuController.saveSudoku(SudokuPanel.getsModel());}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		this.add(isave);
		
		islove = new MenuButton(SudokuNext.getResources().islove);
		islove.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) {
				if (showAnswer) return;
				SudokuPanel.clearAndRemove();
				if (JOptionPane.showConfirmDialog(null, "确定要投降吗？","Sudoku Next!",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
				{
				if (SudokuController.gameStatueVal()==0||SudokuController.gameStatueVal()==-2)
				{
					JOptionPane.showMessageDialog(null, "无法完成数独","Sudoku Next!",JOptionPane.INFORMATION_MESSAGE);
				}else if (SudokuController.gameStatueVal()==-1)
				{
					SudokuModel x=SudokuPanel.getsModel().clone();
					if (SudokuController.solveSudoku(x))
					{
					setShowAnswer(true);
					SudokuPanel.setsModel(x);
					SudokuPanel.updatePanel();
					}else
					JOptionPane.showMessageDialog(null, "无法完成数独","Sudoku Next!",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		this.add(islove);

		ireturn = new MenuButton(SudokuNext.getResources().ireturn);
		ireturn.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) {
				SudokuPanel.clearAndRemove();
				SudokuNext.switchMainMenu();
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		this.add(ireturn);
		
		timeLabel = new JLabel("");
		timeLabel.setFont(new Font("楷体",Font.PLAIN,32));
		timeLabel.setLocation(20, 500);
		timeLabel.setSize(200,50);
		timeLabel.setForeground(SudokuNext.getResources().sbr.getRegular());
		add(timeLabel);
		
		ActionListener timerPerformer = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if (!showAnswer)
		    	{
		    		SudokuPanel.getsModel().globalTimer++;
		    		String temp=SudokuPanel.getsModel().globalTimer/60==0?"":""+SudokuPanel.getsModel().globalTimer/60+"分";
		    		timeLabel.setText(""+temp+SudokuPanel.getsModel().globalTimer%60+"秒");
		    	}else
		    		timeLabel.setText("答案查看中...");
		    }
		};
		if ((!(global==null))&&global.isRunning()) global.stop();
		global=new Timer(GTIMER, timerPerformer);
		global.start();
		
		mainLabel = new JLabel(new ImageIcon(ImageManager.getImage(SudokuNext.getTheme(),"background")));
		mainLabel.setBounds(0, 0, 800, 600);
		add (mainLabel);
		
	}
	
	
	//胜利
	public static void win() {
		String str = "\n";
		global.stop();
		if (SudokuPanel.getsModel().globalTimer < SudokuNext.getHighScore().getRecordTime(SudokuPanel.getsModel().diff))
		{
			str+="新记录诞生！";
			SudokuNext.getHighScore().updateRecord( SudokuPanel.getsModel().getDiff(), new Date(), SudokuPanel.getsModel().globalTimer);
		}
		JOptionPane.showMessageDialog(null, "您太牛了!!!您成功解开了这个数独!\n花费时间："+SudokuPanel.getsModel().globalTimer/60+"分"+SudokuPanel.getsModel().globalTimer%60+"秒"+str+"\n按确定开始新的游戏","Congratulations",JOptionPane.INFORMATION_MESSAGE);
		SudokuNext.getHighScore().winLocalGame();
		SudokuNext.switchNewGameMenu();
	}
	
	//无数可填
	public static void inputlose() {
		JOptionPane.showMessageDialog(null, "这个数独剩下的方格已无法填入数字，您可以:\n1.后退至上一步\n2.修改或删除某些方格的内容","Sudoku Next!",JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	/**
	 * 是否已显示答案
	 * @param x
	 */
	public static void setShowAnswer(boolean x)
	{
		showAnswer=x;
	}	
	
	/**
	 * showAnswer的Get方法
	 * @return the showAnswer
	 */
	public static boolean isShowAnswer() {
		return showAnswer;
	}
	
	public static void globalStart()
	{
		global.start();
	}
	
	final private int GTIMER=1000; //时间计数器



	private SudokuPanel sudokuPanel;	
	private MenuButton iundo;
	private MenuButton inew;
	private MenuButton isave;
	private MenuButton islove;
	private MenuButton ireturn;
	private JLabel timeLabel;
	
	
	private static boolean showAnswer=false;
	private JLabel mainLabel = null;
	private static Timer global;	
}
