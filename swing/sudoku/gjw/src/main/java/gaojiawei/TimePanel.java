package gaojiawei;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class TimePanel extends JPanel
{
	private static final long serialVersionUID = 3745609322342475873L;
	
	public int h=0,m=0,s=0;
	private String getTime()
	{
		return "Time used: "+(h<10?"0":"")+h+(m<10?":0":":")+m+(s<10?":0":":")+s;
	}
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setFont(new Font(null,Font.BOLD,20));
		g.drawString(getTime(),10,24);
	}
	public Dimension getPreferredSize()
	{
		return new Dimension(210,30);
	}
}
