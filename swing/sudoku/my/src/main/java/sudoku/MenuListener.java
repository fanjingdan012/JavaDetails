package sudoku;

import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;


public class MenuListener implements ActionListener {
	public void actionPerformed(ActionEvent e) { 
		if(e.getSource() == MainFrame.mnItems[0]){//game start
			MainFrame.game = new Sudoku();
			Sudoku.setTime();
			MainFrame.game.hints = 10;
			MainFrame.jbtHint.setVisible(true);
			MainFrame.jbtHint.setText("hint(10)");
			MainFrame.game.creatProblem();
			MainFrame.game.initialize();
		}    		
		if(e.getSource() == MainFrame.mnItems[1]){//game choose
			MainFrame.game = new Sudoku();
			JFileChooser openChooser;			
			switch (MainFrame.game.difficulty){
			    case Sudoku.EASY: openChooser = new JFileChooser(new File("problemsEASY"));break;
			    case Sudoku.NORMAL:openChooser = new JFileChooser(new File("problemsNORMAL"));break;
			    case Sudoku.HARD:openChooser = new JFileChooser(new File("problemsHARD"));break;
			    case Sudoku.EXHARD:openChooser = new JFileChooser(new File("problemsEXHARD"));break;
			    default:openChooser = new JFileChooser(new File("problemsASH"));break;				
			}
			openChooser.addActionListener(new Listener());		
			int returnVal = openChooser.showOpenDialog(null);			    
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				Scanner input = null;
				File openFile = openChooser.getSelectedFile();
			    try{						
					input = new Scanner(openFile);
					for (int i = 0;i <= 8;i++){
						for (int j = 0;j <= 8;j++){								
							    MainFrame.game.listProblem[i][j].setValue(Integer.parseInt(input.nextLine()));

						}
					}
					MainFrame.game.initialize();
				}
				catch(IOException ex) {	
					System.out.println("wrong");
				}
			}
			
			Sudoku.setTime();
			//MainFrame.game.hints = 10;
			MainFrame.jbtHint.setVisible(true);
			MainFrame.jbtHint.setText("hint(10)");
		}
		if(e.getSource() == MainFrame.mnItems[2]){//game replay
			for (int i = 0;i <= 8;i++){
				for (int j = 0;j <= 8;j++){
					if(MainFrame.game.list[i][j].getInProblem() == false){
						MainFrame.game.list[i][j].deleteValue();
					}
				}
			}
			Sudoku.setTime();
			//...
			MainFrame.game.hints = 10;
			MainFrame.jbtHint.setVisible(true);
			MainFrame.jbtHint.setText("hint(10)");
		}
		if(e.getSource() == MainFrame.mnItems[3]){//game/solve		
				if(Sudoku.answer(0,0,MainFrame.game.list) == false){
					JOptionPane.showMessageDialog(null, "This problem can't be solved");
				}
				else{
					for(int i = 0;i <= 8;i++){
						for(int j = 0;j <= 8;j++){
							MainFrame.game.list[i][j].setValue(MainFrame.game.list[i][j].answer);
							
						}
					}
				}
			 
			
		}
		if(e.getSource() == MainFrame.mnItems[4]){//game exit
			int returnVal = JOptionPane.showConfirmDialog(null, "game is not finished, save it?");
			if(returnVal == JOptionPane.OK_OPTION) {
				try {						
					PrintWriter saver = new PrintWriter(MainFrame.player.name + ".txt");
					for(int i = 0;i <= 8;i++){
						for(int j = 0;j <= 8;j++){
							saver.println(MainFrame.game.list[i][j].getValue());
						}
					}
					saver.close();
				} 
				catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}//????????????
				Timer timerEnd = new Timer(1,new TimerEndListener());
			    timerEnd.start();  
			}
			else if(returnVal == JOptionPane.NO_OPTION){
			    Timer timerEnd = new Timer(1,new TimerEndListener());
			    timerEnd.start();   
			}
		}
		if(e.getSource() == MainFrame.mnItems[5]){//edit/edit game
			int returnVal = JOptionPane.showConfirmDialog(null, "game not finished, go into edit mode will lose the status\nStill go to edit mode?");
			if(returnVal == JOptionPane.OK_OPTION) {
				for (int i = 0;i <= 8;i++){
		        	for( int j = 0;j <= 8;j++){
		        		MainFrame.game.list[i][j].deleteValue();
		        		MainFrame.game.list[i][j].setInProblem(false);
		        	}
		    	}
			} 
		}
		if(e.getSource() == MainFrame.mnItems[6]){//edit/save
			JFileChooser saveChooser = new JFileChooser(new File("selfDefinedProblems"));
			saveChooser.addActionListener(new Listener());
			int returnVal = saveChooser.showSaveDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File saveFile = saveChooser.getSelectedFile();
				try {
					PrintWriter saver = new PrintWriter(saveFile);
					for(int i = 0;i <= 8;i++){
						for(int j = 0;j <= 8;j++){
							saver.println(MainFrame.game.list[i][j].getValue());
						}
					}
					saver.close();
				} 
				catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}//????????????
			}
		}
		if(e.getSource() == MainFrame.mnItems[7]){//edit/save game
				MainFrame.game.saveGame();
		}
		if(e.getSource() == MainFrame.mnItems[8]){//help/about
			HelpFrame helpFrame = new HelpFrame();
			helpFrame.setBounds(100, 100, 440, 400);
			helpFrame.setVisible(true);
			helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		}
		if(e.getSource() == MainFrame.mnItems[9]){//game/color/simple
			for (int i = 0;i <= 8;i++){
				for (int j = 0;j <= 8;j++){    					
					MainFrame.picCells[i][j].jtfCell.setBackground(Color.WHITE); 
					MainFrame.picCells[i][j].setOriginalColor();
				}
			}
		}
		if(e.getSource() == MainFrame.mnItems[10]){
			for (int i = 0;i <= 8;i++){
				for (int j = 0;j <= 8;j++){
					if(((i>=3)&&(i<=5))||(j>=3)&&(j<=5)){
						MainFrame.picCells[i][j].jtfCell.setBackground(Color.YELLOW); 
						MainFrame.picCells[i][j].setOriginalColor();
					}
					
					else{
						MainFrame.picCells[i][j].jtfCell.setBackground(Color.WHITE);
						MainFrame.picCells[i][j].setOriginalColor();
					}
				}
			}
		}
		if(e.getSource() == MainFrame.mnItems[11]){
			for (int i = 0;i <= 8;i++){
				for (int j = 0;j <= 8;j++){
					if(((i==1)||(i==4)||(i==7))&&((j==1)||(j==4)||(j==7))){
						MainFrame.picCells[i][j].jtfCell.setBackground(Color.YELLOW);
						MainFrame.picCells[i][j].setOriginalColor();
					}
					else{
						MainFrame.picCells[i][j].jtfCell.setBackground(Color.GREEN);
						MainFrame.picCells[i][j].setOriginalColor();
					}
				}
			}
		}
		if(e.getSource() == MainFrame.mnItems[12]){
			for (int i = 0;i <= 8;i++){
				for (int j = 0;j <= 8;j++){
					if(((i>=3)&&(i<=5))||(j>=3)&&(j<=5)){						
						MainFrame.picCells[i][j].jtfCell.setBackground(Color.ORANGE); 
						MainFrame.picCells[i][j].setOriginalColor();
					}
					else{
						MainFrame.picCells[i][j].jtfCell.setBackground(Color.PINK);
						MainFrame.picCells[i][j].setOriginalColor();
					}
				}
			}
		}
		
		
	}
	
	
}