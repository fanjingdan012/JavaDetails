/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-5
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * SudokuMessagePanel类 
 * MessagePanel 用于显示消息 提示
 * @version 0.1
 */
public class SudokuMessagePanel extends JPanel
{
	public SudokuMessagePanel()
	{
		this.setBounds(200, 150, 400, 300);
		//this.setLayout(null);
		//this.setOpaque(false);
		this.add(new JTextField("FUCK!"));
		Toolkit kit=Toolkit.getDefaultToolkit();
		bgImage = kit.getImage("./theme/air/message_panel_background.png");
		JLabel xxx = new JLabel(new ImageIcon("./theme/air/message_panel_background.png"));
		this.add(xxx);
	}

	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		//g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3f));
		g2.setColor(Color.WHITE);
		g2.drawString("欢迎回来,OTL", 300, 200);
		//g2.drawString("新用户创建", 500, 280);
	}
	
	Image bgImage = null;
	
}
