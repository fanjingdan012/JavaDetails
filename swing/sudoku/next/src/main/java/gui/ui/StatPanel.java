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
 * AboutPanel�� 
 * ���ڴ���Panel
 * @version 0.1
 */
public class StatPanel extends JPanel {

	ParticlesSnow flower = new ParticlesSnow(0,0,800,600);
	
	/**
	 * ���ڴ���
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
		
		g2.setFont(new Font("����",Font.PLAIN,32));
		g2.setColor(Color.WHITE);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
		g2.drawString(SudokuNext.getHighScore().getPlayerName()+"ͬѧ�Ĺ��ٰ�", 250 ,120);
		g2.drawString("̫������ :"+Integer.toString(SudokuNext.getHighScore().getRecordTime(0))+" "+SudokuNext.getHighScore().getRecordData(0),100,180);
		g2.drawString("�������� :"+Integer.toString(SudokuNext.getHighScore().getRecordTime(1))+" "+SudokuNext.getHighScore().getRecordData(1),100,220);
		g2.drawString("�е��Ѷ� :"+Integer.toString(SudokuNext.getHighScore().getRecordTime(2))+" "+SudokuNext.getHighScore().getRecordData(2),100,260);
		g2.drawString("�Ƚ�ͷ�� :"+Integer.toString(SudokuNext.getHighScore().getRecordTime(3))+" "+SudokuNext.getHighScore().getRecordData(3),100,300);
		g2.drawString("���˷��� :"+Integer.toString(SudokuNext.getHighScore().getRecordTime(4))+" "+SudokuNext.getHighScore().getRecordData(4),100,340);
		
		g2.drawString("�ҵ�ս��", 100, 390);
		g2.drawString("���� ʤ"+Integer.toString(SudokuNext.getHighScore().getWinLocal())+",��"+Integer.toString(SudokuNext.getHighScore().getTotalLocal())+",��ɶ�"+SudokuNext.getHighScore().getLocalWinRate(), 100, 430);
		g2.drawString("��ս ʤ"+Integer.toString(SudokuNext.getHighScore().getWinLan())+",��"+Integer.toString(SudokuNext.getHighScore().getLoseLan())+",��"+Integer.toString(SudokuNext.getHighScore().getTotalLan())+",ʤ��"+SudokuNext.getHighScore().getLanWinRate(), 100, 470);
		g2.drawString("��ʾ��������ҳ�����ɾ��ս�������´����û���", 50, 520);
		flower.draw(g2);
	}
	
	private int showOn=0;
	private Timer gTimer;
}
