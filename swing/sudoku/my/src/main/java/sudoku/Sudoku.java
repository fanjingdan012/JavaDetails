package sudoku;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Sudoku{
	static long totalMilliseconds;
	long times;
	static Timer timer;
	static final int EASY = 1;
	static final int NORMAL = 2;
	static final int HARD = 3;
	static final int EXHARD = 4;
	static final int ASH = 5;
	static int difficulty = 1;
	Cell [][]list = Cell.list;
	Cell [][] listProblem = new Cell [9][9];//copy the problem
	int currentX,currentY;   
    boolean candidateModel = false;
	Timer timerEnd;
	int hints = 10;
	boolean isWin = false;
	public Sudoku(){//throws IOException{    	
		for (int i = 0;i <= 8;i++){
        	for( int j = 0;j <= 8;j++){
        		list[i][j] = new Cell(i,j);
        		listProblem[i][j] = new Cell(i,j);
        	}
    	}
	}
	public void saveProblem(){
		
	}
    public void readProblem(){
		
	}
    public void saveGame(){
    	  	PrintWriter gameSaver;
    		try {
    			gameSaver = new PrintWriter(MainFrame.player.name + ".loadGame");
    			gameSaver.println(times);
    			gameSaver.println(hints);
    			for (int i = 0;i <= 8;i++){
    	    		for (int j = 0;j <= 8;j++){
    	    		    gameSaver.println(list[i][j].getValue());
    	    		    gameSaver.println(list[i][j].getInProblem());
    	    		}
    	    	}
    	    	gameSaver.close();
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
    public void readGame(){
    	
    }
    public static void setTime(){
    	totalMilliseconds = System.currentTimeMillis() / 1000;
    	timer = new Timer(1000,new TimerListener());   
    	timer.start();
    }
    
    protected boolean isComplete(){
    	boolean complete = true;
    	for (int i = 0;i <= 8;i++){
    		for (int j = 0;j <= 8;j++){
    			if (list[i][j].getValue() != 0){
    				complete = false;
    			}
    		}
    	}
    	return complete;
    }
    public boolean isWin(){
    	if (isComplete()){
    		JOptionPane.showMessageDialog(null,"Congratrolations. You win.");
    		Sudoku.timer.stop();
    	}
    	isWin = isComplete();
    	return isComplete();
    }
    public void initialize(){
    	for (int i = 0;i <= 8;i++){
    		for (int j = 0;j <= 8;j++){
    	        list[i][j].setValue(listProblem[i][j].getValue());
    	        if(list[i][j].getValue() != 0){
					list[i][j].setInProblem(true);								
					list[i][j].answer = list[i][j].getValue();
				}
    	        else{
    	        	list[i][j].setInProblem(false);
    	        }
    		}
    	}
    }
    public void creatProblem(){//throws IOException{
    	int counterForProblem = 81;		
        int i,j;
        //while(answer(0,0,listProblem) == false){
            while (counterForProblem > 60){			
			    i = (int)((Math.random()) * 9);
                j = (int)((Math.random()) * 9);
			    listProblem[i][j].setValue((int)((Math.random())*8+1));	
			    listProblem[i][j].rule();
			    //judge counterForProblem
			    counterForProblem=0;
			    for (i = 0;i <= 8 ; i++){
				    for (j = 0;j <= 8 ;j++ ){
					    if (listProblem[i][j].getValue() == 0){
					            counterForProblem++	;
					    }
				    }
			    }
			
            }
            
        //}
        
        
    }
    /*public static void creatProblem(){//throws IOException{
	int counterForProblem = 81;		
    int i,j;
    while (counterForProblem > 60){			
		i = (int)((Math.random()) * 9);
        j = (int)((Math.random()) * 9);
		listProblem[i][j] = (int)((Math.random())*8+1);	
		//judge rule			
	    //judge 行
	    for (int jCount=0;jCount<=8 ; jCount++){
			if (jCount == j){
				continue;
			}
			if ((listProblem[i][j]==listProblem[i][jCount])&&(listProblem[i][j]!=0)){
				listProblem[i][j] = 0;					
			}
	    }
	    //judge 列
	   for (int iCount = 0;iCount<=8 ; iCount++){
			if (iCount==i){
				continue;
			}
			if ((list[i][j] == list[iCount][j])&(listProblem[i][j]!=0)){
				listProblem[i][j] = 0;
			}
	    }
	   for (int kCount=0;kCount<=8 ; kCount++){
			if ((kCount / 3 == i)&&(kCount % 3 == j)){
				continue;
			}
			if ((listProblem[i][j]==listProblem[kCount / 3][kCount % 3])&&(listProblem[i][j]!=0)){
				listProblem[i][j]=0;					
			}
	    }
		//judge 出满个数
		counterForProblem=0;
		for (i = 0;i <= 8 ; i++){
			for (j = 0;j <= 8 ;j++ ){
				if (listProblem[i][j] == 0){
				        counterForProblem++	;
				}
			}				
		}
		
	}  
    
}*/
    public static boolean answer(int row, int col,Cell[][]matrix) {
		int i, j,x,x1,y,y1; 
		// go to next line if col>8
		if (col > 8){
			row++; 
			col = 0; 
		} 
        
        if (row > 8) 
            return true; //accomplished
		//already have number, calculate next
		if (matrix[row][col].answer != 0) 
			return answer(row, col+1,matrix); 
		// test number
		for (i = 1; i < 10; i++) { 
			// search repeat for row
			for (j = 0; j < 9; j++){
				if (matrix[row][j].answer == i) 
					break; 
				} 
				if (j < 9)  { // repeat skip  
				    continue; 
				} 

                //  search repeat for column
				for (j = 0; j < 9; j++)  { 
					if (matrix[j][col].answer == i) 
						break; 
					} 

                if (j < 9){ // repeat skip  
				    continue; 
				} 
				//search repeat for 3*3
                j=0;
				x1=row/3*3+3;
				y1=col/3*3+3; 
                for(x=row/3*3;x<x1;x++) {
					if (j==1)
						break;
					for(y=col/3*3;y<y1;y++) { 
						if  (matrix[x][y].answer==i) {
							j=1;
							break;
						} 
					} 
				}
				if(j == 1){//repeat skip  
				    continue; 
				}
				matrix[row][col].answer = i;// put number in current position 
                
                // calculate the next position 
                if (answer(row, col+1,matrix)) 
					return true; // success, return

                // can't put this number in this position, try another
                matrix[row][col].answer = 0; 
				
		}
		matrix[row][col].answer = 0;
		
		// all numbers fail
		return false; 
	}

}
    
