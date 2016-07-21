package sudoku;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;

public class Player implements Serializable{
	static int numberOfPlayers;
	transient static Player []players = new Player [100];
	int rank;
	int mark;
	int easyTime = 100000000;
	int normalTime = 100000000;
	int hardTime = 100000000;
	int exHardTime = 100000000;
	int ashTime = 100000000;
	String name;
	transient Sudoku playingGame = new Sudoku();
	transient Sudoku loadGame;
	public Player(String newName){
	    name = newName;
		numberOfPlayers++;
		players[numberOfPlayers] = this;
		saveGeneralInformation();
		save(this);
	}
	public Player(){	    
	}
	private void setMark(){
		mark = 500000000 - easyTime - normalTime - hardTime - exHardTime - ashTime;
	}
    public int getMark(){
    	return mark;
    }
    public void save(Player newPlayer){
    	newPlayer.setMark();
    	FileOutputStream saverf;
		try {
			saverf = new FileOutputStream(newPlayer.name + ".dat");
			ObjectOutputStream saver = new ObjectOutputStream(saverf);
	    	//saver.writeInt(12345);
	    	//saver.writeObject("Today");
	    	saver.writeObject(newPlayer);
	    	saver.close();
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
     
    }
    public void saveLoadGame(){    	    
        playingGame.saveGame();
    }
    public static void setRank(){
    	
    }
    private static void saveGeneralInformation(){
    	PrintWriter generalSaver;
		try {
			generalSaver = new PrintWriter("players.txt");
			generalSaver.println(numberOfPlayers);
	    	for(int k = 1;k <= numberOfPlayers;k++){
	    		generalSaver.println(players[k].name);
	    	}
	    	generalSaver.close();
	    	//System.out.println(in.nextLine());
	    	
		} 
		catch (FileNotFoundException e) {
			System.out.println("wrong");
		}    	
    	//DataOutputStream o = new DataOutputStream(new FileOutputStream("temp.dat"));
    	//o.writeUTF("ooooooooo");
    	//o.write(2);
    	//o.close();
    	//DataInputStream i = new DataInputStream(new FileInputStream("temp.dat"));
    	//System.out.println(i.readUTF()+""+i.read());
    }
    public static void search(String newName){
    	Scanner searcher;
		try {
			searcher = new Scanner(new File("players.txt"));
			numberOfPlayers = Integer.parseInt(searcher.nextLine());
			if(numberOfPlayers == 0){
				MainFrame.player = new Player(newName);
			}
			else{
				FileInputStream fis ;
				ObjectInputStream ois = null;
				for(int k = 1;k <= numberOfPlayers;k++){					
					fis = new FileInputStream(searcher.nextLine() + ".dat");
			        ois = new ObjectInputStream(fis);
			        players[k] = (Player) ois.readObject();								 
					if(newName.equalsIgnoreCase(players[k].name)){				    	
						//int i = ois.readInt();
				        //String today = (String) ois.readObject();
				        MainFrame.player = players[k];
				        
	    		    }
				}
				ois.close();
				if(MainFrame.player == null){
    			    MainFrame.player = new Player(newName);
    		    }
	    	}
		} 
		catch (FileNotFoundException e) {
			System.out.println("no player");
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
}
