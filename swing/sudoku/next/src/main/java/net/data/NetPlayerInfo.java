/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-6
 */
package net.data;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * NetPlayerInfo类 
 * Lan对战玩家信息
 * @version 0.1
 */
public class NetPlayerInfo implements java.io.Serializable{
	
	/**
	 * 玩家信息
	 * @param _player 玩家名
	 * @param _socketAddress Socket地址
	 * @param _editable 是否可编辑
	 */
	public NetPlayerInfo(String _player, SocketAddress _socketAddress, boolean _editable)
	{
		this.vacancy = false;
		this.playerName = _player;
		this.socketAddress = (InetSocketAddress) _socketAddress;
		this.editable = _editable;
		this.group = 1;
	}
	
	public void ResetPlayerInfo()
	{
		this.vacancy = true;
		this.playerName = "";
		this.socketAddress = null;
		this.editable = true;
		this.group = 1;
	}
	
	/**
	 * vacancy的Get方法
	 * @return the vacancy
	 */
	public boolean isVacancy() {
		return vacancy;
	}
	/**
	 * playerName的Get方法
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}
	/**
	 * editable的Get方法
	 * @return the editable
	 */
	public boolean isEditable() {
		return editable;
	}
	/**
	 * group的Get方法
	 * @return the group
	 */
	public int getGroup() {
		return group;
	}
	/**
	 * socketAddress的Get方法
	 * @return the socketAddress
	 */
	public InetSocketAddress getSocketAddress() {
		return socketAddress;
	}

	private boolean vacancy;
	private String playerName;
	private boolean editable;
	private int group;
	private InetSocketAddress socketAddress;
	
	

}
