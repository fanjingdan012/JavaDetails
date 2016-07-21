/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-6
 */
package net.data;

import java.net.SocketAddress;

/**
 * NetServerInfo类 
 * 
 * @version 0.1
 */
public class NetServerInfo {

	public NetServerInfo(String str, int diff, SocketAddress sa)
	{
		this.serverName = str;
		this.diff = diff;
		this.serverAddress = sa;
	}
	
	
	
	/**
	 * diff的Get方法
	 * @return the diff
	 */
	public int getDiff() {
		return diff;
	}
	/**
	 * diff的Set方法
	 * @param diff 需要设置的字段
	 */
	public void setDiff(int diff) {
		this.diff = diff;
	}
	/**
	 * serverAddress的Get方法
	 * @return the serverAddress
	 */
	public SocketAddress getServerAddress() {
		return serverAddress;
	}
	/**
	 * serverName的Get方法
	 * @return the serverName
	 */
	public String getServerName() {
		return serverName;
	}
	private SocketAddress serverAddress;
	private String serverName;
	private int diff;
}
