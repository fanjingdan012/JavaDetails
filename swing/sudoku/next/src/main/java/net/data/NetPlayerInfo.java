/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-6
 */
package net.data;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * NetPlayerInfo�� 
 * Lan��ս�����Ϣ
 * @version 0.1
 */
public class NetPlayerInfo implements java.io.Serializable{
	
	/**
	 * �����Ϣ
	 * @param _player �����
	 * @param _socketAddress Socket��ַ
	 * @param _editable �Ƿ�ɱ༭
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
	 * vacancy��Get����
	 * @return the vacancy
	 */
	public boolean isVacancy() {
		return vacancy;
	}
	/**
	 * playerName��Get����
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}
	/**
	 * editable��Get����
	 * @return the editable
	 */
	public boolean isEditable() {
		return editable;
	}
	/**
	 * group��Get����
	 * @return the group
	 */
	public int getGroup() {
		return group;
	}
	/**
	 * socketAddress��Get����
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
