/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-6
 */
package net.data;

import java.net.SocketAddress;

/**
 * NetPlayerList类 
 * 玩家列表
 * @version 0.1
 */
public class NetPlayerList {
	
	public NetPlayerList(String str, int diff, SocketAddress sa)
	{
		svrinfo = new NetServerInfo(str,diff,sa);
		playerlist = new NetPlayerList[8];
	}
	
	
	/**
	 * 设置玩家
	 * @param npl 玩家列表
	 * @param id 玩家ID
	 */
	public void setPlayer(NetPlayerList npl , int id)
	{
		playerlist[id] = npl;
	}
	
	/**
	 * playerlist的Get方法
	 * @return the playerlist
	 */
	public NetPlayerList[] getPlayerlist() {
		return playerlist;
	}

	/**
	 * svrinfo的Get方法
	 * @return the svrinfo
	 */
	public NetServerInfo getSvrinfo() {
		return svrinfo;
	}


	/**
	 * 设置服务器难度
	 * @param svrinfo 需要设置的字段
	 */
	public void setSvrDiff(int diff) {
		this.svrinfo.setDiff(diff);
	}




	private NetServerInfo svrinfo = null;
	private NetPlayerList[] playerlist = null;
}
