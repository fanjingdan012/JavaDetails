/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-5
 */
package gui.ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import pariticles.ParticlesSnow;

import core.SudokuNext;

import util.data.ImageManager;

/**
 * AboutPanel类 
 * 关于窗口Panel
 * @version 0.1
 */
public class StatPanel extends JPanel {

	ParticlesSnow flower = new ParticlesSnow(0,0,800,600);
	
	/**
	 * 关于窗口
	 */
	public StatPanel()
	{
		showOn = 0;
		gTimer = new Timer(100,new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				flower.update(1);
				if (showOn<10 || showOn>=90) showOn++;
				if (showOn==100) { gTimer.stop(); SudokuNext.switchMainMenu(); }
				repaint();
			}
		});
		gTimer.start();
		this.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				if (showOn<90) showOn = 90;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
	
	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
		g2.drawImage(ImageManager.getImage(SudokuNext.getTheme(),"background"), 0, 0, null);
		if (showOn<=10)
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.05f*showOn));
		else if (showOn>=90)
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.05f*(100-showOn)));
		else
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));
		g2.fillRoundRect(50, 50, 700, 500, 25, 25);
		
		g2.setFont(new Font("楷体",Font.PLAIN,32));
		g2.setColor(Color.WHITE);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
		g2.drawString(SudokuNext.getHighScore().getPlayerName()+"同学的光荣榜", 250 ,120);
		g2.drawString("太容易啦 :"+Integer.toString(SudokuNext.getHighScore().getRecordTime(0))+" "+SudokuNext.getHighScore().getRecordData(0),100,180);
		g2.drawString("还算容易 :"+Integer.toString(SudokuNext.getHighScore().getRecordTime(1))+" "+SudokuNext.getHighScore().getRecordData(1),100,220);
		g2.drawString("有点难度 :"+Integer.toString(SudokuNext.getHighScore().getRecordTime(2))+" "+SudokuNext.getHighScore().getRecordData(2),100,260);
		g2.drawString("比较头疼 :"+Integer.toString(SudokuNext.getHighScore().getRecordTime(3))+" "+SudokuNext.getHighScore().getRecordData(3),100,300);
		g2.drawString("令人发狂 :"+Integer.toString(SudokuNext.getHighScore().getRecordTime(4))+" "+SudokuNext.getHighScore().getRecordData(4),100,340);
		
		g2.drawString("我的战绩", 100, 390);
		g2.drawString("单机 胜"+Integer.toString(SudokuNext.getHighScore().getWinLocal())+",共"+Integer.toString(SudokuNext.getHighScore().getTotalLocal())+",完成度"+SudokuNext.getHighScore().getLocalWinRate(), 100, 430);
		g2.drawString("对战 胜"+Integer.toString(SudokuNext.getHighScore().getWinLan())+",负"+Integer.toString(SudokuNext.getHighScore().getLoseLan())+",共"+Integer.toString(SudokuNext.getHighScore().getTotalLan())+",胜率"+SudokuNext.getHighScore().getLanWinRate(), 100, 470);
		g2.drawString("提示：在设置页面可以删除战绩（重新创建用户）", 50, 520);
		flower.draw(g2);
	}
	
	private int showOn=0;
	private Timer gTimer;
}
