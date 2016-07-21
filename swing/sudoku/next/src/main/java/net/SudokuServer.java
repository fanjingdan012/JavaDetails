/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-7
 */
package net;

import net.data.NetPlayerList;
import java.net.*;

/**
 * SudokuServer类 
 * 网络联机服务器端
 * @version 0.1
 */
public class SudokuServer implements Runnable {
	
	private NetPlayerList lst = null;
	private int port;
	private boolean loop;
	private ServerSocket server;
	
	public SudokuServer(int port)
	{
		super();
		this.port = port;
	}
	
	public void establish(String serverName)
	{
		try{
			loop = true;
			server = new ServerSocket(port);
			
		}catch (Exception e)
		{
			
		}
	}
	
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
}
