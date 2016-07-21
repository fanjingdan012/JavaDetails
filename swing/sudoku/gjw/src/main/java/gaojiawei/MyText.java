package gaojiawei;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MyText extends JLabel
{
	private static final long serialVersionUID = 3512606064671499539L;
	
	int x,y;
	MyText(String t,int x,int y)
	{
		super.setText(t);
		this.x=x;this.y=y;
		setFont(new Font(null,Font.BOLD,20));
		setOpaque(true);
		setHorizontalAlignment(SwingConstants.CENTER);
	}
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}
}
