package sudoku;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class MainFrame extends JFrame{
	static Player player ;
	static String playerName = "";
	static Sudoku game;
	
	static MainFrame frame;
	static PicCell [][] picCells = new PicCell[9][9];
	static JPanel jpCenter = new JPanel();
    static JPanel jpCenterC = new JPanel();
        static  JPanel []jpCenterCs = new JPanel[9];
    static JPanel jpCenterN = new JPanel();
        static JPanel []jpCenterNs = new JPanel[10];
        static JPanel jpNorth = new JPanel();
    static JPanel jpEast = new JPanel();                           
    static JPanel [] jpEasts = new JPanel[7];         
    static JMenuBar menuBar = new JMenuBar();
    static JMenu []menus = new JMenu[4];
    static JMenuItem [] mnItems = new JMenuItem[13];
    static ImageIcon iconSudoku = new ImageIcon("image/sudoku.png");
    static JLabel jlbSudoku = new JLabel(iconSudoku);
    static JLabel jlbTime = new JLabel("00:00:00");//计时标签
    static JLabel jlbMark = new JLabel("0");//计分标签
    static JLabel jlbRank = new JLabel("1");//排名标签
    static JLabel jlbDifficulty = new JLabel("难度");//排名标签
    static JButton []jbtNums = new JButton[10];//候选数按钮
    static JButton jbtHint = new JButton("提示(10)");
    static ButtonGroup group1 = new ButtonGroup();
    static ButtonGroup group2 = new ButtonGroup();
    static JRadioButton jrby;
    static JRadioButton jrbn;
    static JRadioButton jrbYellow;
    static JRadioButton jrbBlue;
    static JRadioButton jrbGreen;    
    static String[] difficultyTitles = {"简单","普通","困难","极难","骨灰"};
    static JComboBox jcboDifficulty = new JComboBox(difficultyTitles);
    //URL gameUrl = getClass().getResource("sound//game.WAV");    
    //AudioClip gameClip = Applet.newAudioClip(gameUrl);
   // URL winUrl = getClass().getResource("sound/win.rm");
    //AudioClip winClip = Applet.newAudioClip(winUrl);
   
    public MainFrame(){    	
    	//System.out.println(getClass().getResource("image/0.png"));
    	//gameClip.loop();
    	setLayout(new BorderLayout());
    	add(jpCenter,"Center");
    	add(jpNorth,"North");
    	add(jpEast,"East");
        jpNorth.setLayout(new BorderLayout());
        jpNorth.setBackground(Color.WHITE);
	        jpNorth.add(menuBar,"North");
	            menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	                menuBar.add(menus[0] = new JMenu("游戏"));
	                    menus[0].add(mnItems[0] = new JMenuItem("开局"));
	                    menus[0].add(mnItems[1] = new JMenuItem("选局"));
	                    menus[0].add(mnItems[2] = new JMenuItem("重玩"));
	                    menus[0].add(mnItems[3] = new JMenuItem("智能解题"));
	                    menus[0].add(menus[3] = new JMenu("配色方案"));
	                        menus[3].add(mnItems[9] = new JMenuItem("简单样式"));
	                        menus[3].add(mnItems[10] = new JMenuItem("经典样式"));
	                        menus[3].add(mnItems[11] = new JMenuItem("绿地黄金"));
	                        menus[3].add(mnItems[12] = new JMenuItem("粉红沙漠"));
	                    menus[0].add(mnItems[4] = new JMenuItem("退出"));    	                    
	                menuBar.add(menus[1] = new JMenu("编辑"));
	                    menus[1].add(mnItems[5] = new JMenuItem("新建题目"));
	                    menus[1].add(mnItems[6] = new JMenuItem("保存题目"));
	                    menus[1].add(mnItems[7] = new JMenuItem("保存游戏"));
	                menuBar.add(menus[2] = new JMenu("帮助"));
	                    menus[2].add(mnItems[8] = new JMenuItem("关于"));
	                for(int k = 0;k <= 12;k++){
	            	    mnItems[k].addActionListener(new MenuListener());
	                }
	        jpNorth.add(jlbSudoku,"Center");
	    jpEast.setLayout(new GridLayout(7,1));
	        for (int k = 0;k <= 6;k++){
	    	    jpEasts[k] = new JPanel(); 
	    	    jpEasts[k].setBackground(new Color(35 * k,255,35 * k));
	    	    jpEast.add(jpEasts[k]);
	        }
	        jpEasts[0].setBorder(new TitledBorder("计时"));
	            jpEasts[0].add(jlbTime);//timer
	        jpEasts[1].setBorder(new TitledBorder("计分"));
                jpEasts[1].add(jlbMark); //listener   	
	        jpEasts[2].setBorder(new TitledBorder("排名"));
                jpEasts[2].add(jlbRank);//listener  
                jpEasts[3].add(jbtHint);
                    jbtHint.addActionListener(new Listener());
            jpEasts[4].setLayout(new FlowLayout());
            jpEasts[4].add(jlbDifficulty);
                jpEasts[4].add(jcboDifficulty);
                    jcboDifficulty.addItemListener(new JcboItemListener());
            jpEasts[5].setBorder(new TitledBorder("候选数模式"));
                jpEasts[5].add(jrby = new JRadioButton("是"));
                jpEasts[5].add(jrbn = new JRadioButton("否"));
                jrby.addActionListener(new Listener());
                jrbn.addActionListener(new Listener());
                group1.add(jrby);
                group1.add(jrbn);
            jpEasts[6].setBorder(new TitledBorder("配色方案"));
            jpEasts[6].setLayout(new GridLayout(3,1));	                
                jpEasts[6].add(jrbYellow = new JRadioButton("黄色")); 
                    jrbYellow.addActionListener(new Listener());//add??????????????
                jpEasts[6].add(jrbBlue = new JRadioButton("蓝色")); 
                    jrbBlue.addActionListener(new Listener());
                jpEasts[6].add(jrbGreen = new JRadioButton("绿色")); 
                    jrbGreen.addActionListener(new Listener());
                group2.add(jrbYellow);
                group2.add(jrbBlue);
                group2.add(jrbGreen);
           jpCenter.setLayout(new BorderLayout());
	       jpCenter.add(jpCenterC,"Center");
	           jpCenterC.setBackground(Color.BLUE);
	           jpCenterC.setBorder(new LineBorder(Color.BLUE,30));
	           jpCenterC.setLayout(new GridLayout(3,3));
	           for(int k = 0;k <=8;k++){
	        	   jpCenterCs[k] = new JPanel();
	        	   //??????????????????????????????
	        	   jpCenterCs[k].setBackground(Color.GREEN);
	        	   jpCenterC.add(jpCenterCs[k]);
	        	   jpCenterCs[k].setBorder(new LineBorder(Color.RED));
	        	   jpCenterCs[k].setLayout(new GridLayout(3,3,1,1));
	        	   for (int i = 0;i <= 8;i++){
	        		   picCells[i / 3 + k / 3 * 3][i % 3 + k % 3 * 3] = new PicCell();
	        		   jpCenterCs[k].add(picCells[i / 3 + k / 3 * 3][i % 3 + k % 3 * 3].jtfCell);		        	
		           }
	           }
	       jpCenter.add(jpCenterN,"North");
	           jpCenterN.setBackground(Color.BLUE);
	           jpCenterN.setLayout(new GridLayout(1,10));
	           for (int k = 0;k <= 9;k++){
	            	if (k == 0){	            		    
	            		    jpCenterNs[k] = new JPanel();
	            		    jpCenterNs[k].setBackground(Color.BLUE);
	            		    jpCenterN.add(jpCenterNs[k]);
	            		    jpCenterNs[k].add(jbtNums[0] = new JButton("╳"));
	            		    jbtNums[0].setBackground(new Color(255,255,0));
	            		    jbtNums[0].addActionListener(new Listener());
	            		    //jbtNums[0].setMnemonic('D');????????????????????????????
	            	}
	            	else{
	            		    jpCenterNs[k] = new JPanel();
            		        jpCenterNs[k].setBackground(Color.BLUE); 
            		        jpCenterN.add(jpCenterNs[k]);
	            		    jpCenterNs[k].add(jbtNums[k] = new JButton("" + k));
	            		    jbtNums[k].setBackground(new Color(255,255,k * 20));
	            		    jbtNums[k].addActionListener(new Listener());
            		        //jbtNums[0].setMnemonic(k);?????????????????????????????
	            	}
	            }    	
    }
    
    public static void main(String[]args){    	
    	playerName = JOptionPane.showInputDialog(null,"输入您的尊姓大名：","数独游戏",JOptionPane.QUESTION_MESSAGE);
    	if((playerName == null) || (playerName.equals(""))){
			playerName = "匿名";
		}
    	Player.search(playerName);
    	player = new Player();
    	game = player.playingGame;
    	frame = new MainFrame();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setBounds(100,100,650,750);
    	frame.setVisible(true);
    	frame.setTitle("数独游戏");
    }
    
}
