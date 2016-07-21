package sudoku;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;

public class PicCell {
	int row,column;
    static JTextField [][]picCells = new JTextField[9][9];
    private Color originalColor;
    JTextField jtfCell	= new JTextField();
    Cell logCell = Cell.list[row][column];
    public PicCell(){
    	initialize();
    }
    private void initialize(){
		//if (Sudoku.listvalue != 0)
		    //jtfCell.setText(value + "");		
		//else
			//jtfCell.setText("");
		jtfCell.setFont(new Font("TimesNewRoman",Font.PLAIN,25));
		jtfCell.setBackground(Color.WHITE);
		setOriginalColor();
		jtfCell.setHorizontalAlignment(JTextField.CENTER);
		jtfCell.addFocusListener(new FListener());
		jtfCell.addKeyListener(new CellKeyListener());
	}
    public void setOriginalColor(){
		originalColor = jtfCell.getBackground();		
	}
	public Color getOriginalColor(){
		return originalColor;	
	}
	
	
}