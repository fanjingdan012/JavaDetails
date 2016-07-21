package gaojiawei;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class Buttons extends JPanel
{
	private static final long serialVersionUID = -4341052054791531620L;
	
	MyButton[] btGrid=new MyButton[9];
	Buttons()
	{
		setLayout(new GridLayout(9,9));
		for(int i=0;i<9;i++)
		{
			btGrid[i]=new MyButton(i+1);
			add(btGrid[i]);
		}
	}		
}
