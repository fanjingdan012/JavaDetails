package sudoku;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class HelpFrame extends JFrame{
	//JPanel jpCenterN = new JPanel();
	//JPanel jpCenterC = new JPanel();
	JPanel jpCenter = new JPanel();
    JPanel jpWest = new JPanel();
    JPanel jpNorth = new JPanel();
    JLabel label = new JLabel();
    JTextArea jta = new JTextArea();
    String[] titles = {"数独的简介","游戏规则","数独的游戏技巧","版本信息"};
    JList jlist = new JList(titles);
    String[] contents = {
    		"数独是一种源自18世纪末的瑞士，后在美国发\n展、并在日本得以发扬光大的数学智力拼图游\n戏。拼图是九宫格（即3格宽×3格高）的正方\n形状，每一格又细分为一个九宫格。在每一个\n小九宫格中，分别填上1至9的数字，让整个大\n九宫格每一列、每一行的数字都不重复。\n“Sudoku”起源于瑞士，是18世纪末大数学\n家欧拉发明的游戏。于1970 年代首先由美国的\n一家数学逻辑游戏杂志发表，当时名为\n Number Place 。现今流行的数独于1984年由\n日本游戏杂志《パズル通信ニコリ》发表并得\n了现时的名称。数独本是“独立的数字”的省\n略，因为每一个方格都填上一个个位数。 后来\n在日本流行起来。",
    		"数独游戏在一个有81个空格的大方块内进行，一共9行9列。\n大方块又可以分为9个中等方块，每个中等方块有9个空格。\n我们看到，每行、每列、每个中等方块，都有9个空格。\n要求你只用1到9这些数字，填满大方块中所有的81个空格，\n同时满足：\n1.大方块的每列都有1到9；\n2.大方块的每行都有1到9；\n3.每个中等方块都有1到9；\n也就是说1到9每个数字只能在每行、每列、每个中等方块\n中分别出现一次。所以，当81个空格都被填满时总共必须有\n9个1、9个2、…、9个9。\n当然，数独游戏题目一开始会给定了某些空格的值，\n你可以根据这些已知的值以及上面的约束条件，\n推理出剩余的空格的值。推理，就是游戏的精髓和乐趣所在。",
    		"1 两小九宫格定数字。根据每一横行、每一竖行、以及每个\n小九宫格中每个数字只出现一次的规律，来推定数字，适用\n于初、中级。例如：最左边三竖行中，若有两个小九宫格中\n出现同一数字A，则可判定另一小九宫格中该数字A出现在哪\n一竖行，运气好的话，该小九宫格的那一竖行只有一个空格；\n若不止一个空格，则再可根据横向对应的另外两个小九宫格\n中出现的该数字A来推定。 \n2 假定法。推算到后面总是会出现有2~3个数字都有可能适\n合的情况，那么可以先假定是其中一个数字，然后根据该数\n字进行推算，若出现矛盾则否定该数字，那么剩下的唯一数\n字则为正确答案。一般使用于不超过3个的可能答案，可能\n答案太多，则排列组合的次数就越多，推算就越困难。", 
    		"作者：09ss樊静丹\n版本1.0\n版权所有，仿冒必究"};    
    public HelpFrame(){
    	super("帮助");
    	setLayout(new BorderLayout());
    	add(jpCenter,"Center");
    	add(jpWest,"West");  
    	add(jpNorth,"North");
        jpWest.setLayout(new GridLayout(1,1));
	        jpWest.add(jlist); 
	        jlist.addListSelectionListener(new ListSelectionListener(){
	        	public void valueChanged(ListSelectionEvent e) {	        		
	        		int [] indices = jlist.getSelectedIndices();	        		
	        		    jta.setText(contents[indices[0]]);	        		   
	        		    label.setText(titles[indices[0]]);   	    
					
				}
	        });
	    jpCenter.setLayout(new GridLayout(1,1));
	        jta.setBackground(new Color(248,248,185));
	            jpCenter.add(jta);
	            jpNorth.setLayout(new GridLayout(1,1));
	            jpNorth.setBorder(new LineBorder(Color.BLUE,3));
	            jpNorth.add(label);
	            label.setFont(new Font("Times",Font.BOLD,20));
	            jpNorth.setBackground(Color.GREEN);
	            label.setText("标题");
	            label.setForeground(Color.RED);
    }
    
}
