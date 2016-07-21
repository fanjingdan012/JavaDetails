/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-6
 */
package net.data;

import java.net.SocketAddress;

/**
 * NetServerInfo�� 
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
	 * diff��Get����
	 * @return the diff
	 */
	public int getDiff() {
		return diff;
	}
	/**
	 * diff��Set����
	 * @param diff ��Ҫ���õ��ֶ�
	 */
	public void setDiff(int diff) {
		this.diff = diff;
	}
	/**
	 * serverAddress��Get����
	 * @return the serverAddress
	 */
	public SocketAddress getServerAddress() {
		return serverAddress;
	}
	/**
	 * serverName��Get����
	 * @return the serverName
	 */
	public String getServerName() {
		return serverName;
	}
	private SocketAddress serverAddress;
	private String serverName;
	private int diff;
}
