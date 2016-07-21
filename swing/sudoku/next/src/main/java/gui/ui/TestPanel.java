/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-5
 */
package gui.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import pariticles.ParticlesSnow;

/**
 * LoadBlankPanel类 
 * 载入时加载的Panel
 * @version 0.1
 */
public class TestPanel extends JPanel {
	
	ParticlesSnow explosions = new ParticlesSnow(0,0,800,600);
	
	public TestPanel()
	{
		this.setLayout(null);
		Timer timer = new Timer(100,new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				explosions.update(1);
				repaint();
			}
		});
		timer.start();
		}
	
	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		super.paint(g);
		explosions.draw(g2);
	}
}
