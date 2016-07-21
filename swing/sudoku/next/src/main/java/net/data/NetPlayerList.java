/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-6
 */
package net.data;

import java.net.SocketAddress;

/**
 * NetPlayerList�� 
 * ����б�
 * @version 0.1
 */
public class NetPlayerList {
	
	public NetPlayerList(String str, int diff, SocketAddress sa)
	{
		svrinfo = new NetServerInfo(str,diff,sa);
		playerlist = new NetPlayerList[8];
	}
	
	
	/**
	 * �������
	 * @param npl ����б�
	 * @param id ���ID
	 */
	public void setPlayer(NetPlayerList npl , int id)
	{
		playerlist[id] = npl;
	}
	
	/**
	 * playerlist��Get����
	 * @return the playerlist
	 */
	public NetPlayerList[] getPlayerlist() {
		return playerlist;
	}

	/**
	 * svrinfo��Get����
	 * @return the svrinfo
	 */
	public NetServerInfo getSvrinfo() {
		return svrinfo;
	}


	/**
	 * ���÷������Ѷ�
	 * @param svrinfo ��Ҫ���õ��ֶ�
	 */
	public void setSvrDiff(int diff) {
		this.svrinfo.setDiff(diff);
	}




	private NetServerInfo svrinfo = null;
	private NetPlayerList[] playerlist = null;
}
