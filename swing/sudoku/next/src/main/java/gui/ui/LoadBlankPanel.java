/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-5
 */
package gui.ui;

import gui.SudokuMessagePanel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import pariticles.ParticlesSnow;
import core.SudokuNext;
import util.data.HighScore;
import util.data.ImageManager;

/**
 * LoadBlankPanel类 
 * 载入时加载的Panel
 * @version 0.1
 */
public class LoadBlankPanel extends JPanel {
	
	ParticlesSnow flower;
	
	public LoadBlankPanel()
	{
		flower = new ParticlesSnow(0,0,800,600);
		this.setLayout(null);
		textImage = ImageManager.getImage("public","loading_text");
		bgImage = ImageManager.getImage("public","loading_background");
		gTimer = new Timer(100,new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				alpha += dx;
				if (alpha > 1){	alpha = 0.8f;	dx = -dx; }
				if (alpha < 0){ alpha = 0.2f;  dx = -dx; }
				flower.update(1);
				repaint();			
			}
		});
		gTimer.start();
		barPanel = new JPanel();
		barPanel.setBounds(440, 320, 300, 300);
		this.add(barPanel);
		name = new JTextField(10);
		name.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY));
		name.setFont(new Font("Arial",Font.PLAIN,28));
		name.setBackground(Color.BLACK);
		name.setForeground(Color.WHITE);
		barPanel.add(name);
		name.setVisible(true);
		name.grabFocus();
		name.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()!=10) return;
				for (int i=0; i<name.getText().length(); i++)
					if (!Character.isLetterOrDigit(name.getText().charAt(i)))
					{
						name.setText("");
						return;
					}
				if (!name.getText().equals("")) SudokuNext.refresh(name.getText());
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
		});
		
	}
	
	/**
	 * 改写的paint 方法
	 */
	@Override
	public void paint(Graphics g)
	{
		g.setFont(new Font("楷体",Font.BOLD,32));
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.WHITE);
		g2.drawImage(bgImage, 0, 0, this);
		switch(HighScore.testHighScore())
		{
		case 0 : g2.drawString("欢迎光临Sudoku Next!", 410, 320); name.setVisible(false); if (load) { returnToMainMenu(); load = false; } break;
		case -1:	name.setVisible(true);  	
					g2.setFont(new Font(g2.getFont().getName(),g2.getFont().getStyle(),32));
					g2.drawString("新用户创建", 500, 280);
					g2.setFont(new Font(g2.getFont().getName(),g2.getFont().getStyle(),16));
					g2.drawString("只允许字母或数字作为用户名，按Enter继续", 450, 400);
					break;
		case -2:  	g2.setFont(new Font(g2.getFont().getName(),g2.getFont().getStyle(),32));
					g2.drawString("读取错误，请重新创建用户", 370, 280); 
					name.setVisible(true);
					g2.setFont(new Font(g2.getFont().getName(),g2.getFont().getStyle(),16));
					g2.drawString("只允许字母或数字作为用户名，按Enter继续", 450, 400);
					name.repaint();
					break;
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));
		g2.drawImage(textImage, 530, 430, this); 
		flower.draw(g2);
		name.repaint();		
	}

	
	/**
	 * 返回主菜单 山寨
	 */
	private void returnToMainMenu() {
		Timer timer = new Timer(2500,new ActionListener(){

			public void actionPerformed(ActionEvent e) {
			((Timer) e.getSource()).stop();
			gTimer.stop();
			SudokuNext.switchMainMenu();
			}
		});
		timer.start();
	}


	private Timer gTimer = null;
	private JPanel barPanel = null;
	private JTextField name = null;
	private float dx = 0.1f;
	private float alpha = 0;
	private Image textImage = null;
	private Image bgImage = null;
	private SudokuMessagePanel x = null;
	private boolean load=true; //临时
}
