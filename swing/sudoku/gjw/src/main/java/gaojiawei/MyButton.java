package gaojiawei;

import java.awt.Color;
import javax.swing.JButton;

public class MyButton extends JButton
{
	private static final long serialVersionUID = 2719816741587828892L;
	
	int n;
	MyButton(int n)
	{
		super(n+"");
		this.n=n;
		this.setBackground(new Color(228+n*3,140+n*6,180+n*6));
	}
}