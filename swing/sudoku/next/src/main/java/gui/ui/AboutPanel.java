/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-5
 */
package gui.ui;

import java.awt.AlphaComposite;
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
public class AboutPanel extends JPanel {

	ParticlesSnow flower = new ParticlesSnow(0,0,800,600);
	
	/**
	 * 关于窗口
	 */
	public AboutPanel()
	{
		showOn = 0;
		gTimer = new Timer(100,new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				flower.update(1);
				showOn++;
				if (showOn==290) { gTimer.stop(); SudokuNext.switchMainMenu(); }
				repaint();
			}
		});
		gTimer.start();
		this.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				if (showOn<280) showOn = 280;
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
		else if (showOn>=280)
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.05f*(290-showOn)));
		else
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));
		g2.fillRoundRect(50, 50, 700, 500, 25, 25);
		if (showOn>=10)
		{
		if (((showOn-10)%90)>=0 && ((showOn-10)%90)<20) 
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.05f*((showOn-10)%90)));
		else if (((showOn-10)%90)>=20 && ((showOn-10)%90)<70) 
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
		else
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.05f*(90-((showOn-10)%90))));
		if (showOn>10 && showOn<=100)
		{
			g2.drawImage(ImageManager.getImage("public", "about0"),100,100,null);
		}else if(showOn>100 && showOn <= 190)
		{
			g2.drawImage(ImageManager.getImage("public", "about1"),100,100,null);
		}else if(showOn>190 && showOn <=280)
		{
			g2.drawImage(ImageManager.getImage("public", "about2"),100,100,null);
		}
		}
		flower.draw(g2);
	}
	
	private int showOn=0;
	private Timer gTimer;
}
