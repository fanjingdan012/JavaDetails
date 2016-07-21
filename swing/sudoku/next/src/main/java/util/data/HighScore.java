/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-5
 */
package util.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * HighScoreDB类 
 * 最高分数统计信息
 * @version 0.3
 */
public class HighScore implements Serializable 
{
	/**
	 * 构造函数，新建HighScore对象
	 * @param _playerName 玩家名
	 */
	public HighScore(String _playerName)
	{
		playerName=_playerName;
		recordDate = new Date[5];
		for (int i=0;i<5;i++)
			recordDate[i] = new Date();
		recordTime = new int[5];
		for (int i=0;i<5;i++)
			recordTime[i] = 9999;
		winLocal = 0;
		winLan = 0;
		loseLan = 0;
		totalLocal = 0;
		totalLan = 0;		
		update();
	}
	
	/**
	 * 测试用构造器
	 * @param bool 是否使用
	 */
	public HighScore(Boolean bool)
	{
		if (bool == false) return;
		playerName= "justatest";
		recordDate = new Date[5];
		recordTime = new int[5];
		winLocal = 0;
		winLan = 0;
		loseLan = 0;
		totalLocal = 0;
		totalLan = 0;
	}
	
	/**
	 * 构造函数，从文件读取玩家，最高分信息
	 * 请先执行测试文件读取以便确认合法的最高分文件存在
	 */
	public HighScore()
	{
		HighScore tmpScore = null;
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("./settings/HighScore.dat"));
			try {
				tmpScore = (HighScore) input.readObject();
				input.close();
			} catch (ClassNotFoundException e) {
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		playerName= tmpScore.playerName;
		recordDate = tmpScore.recordDate;
		recordTime = tmpScore.recordTime;
		winLocal = tmpScore.winLocal;
		winLan = tmpScore.winLan;
		loseLan = tmpScore.loseLan;
		totalLocal = tmpScore.totalLocal;
		totalLan = tmpScore.totalLan;
	}
	
	
	/**
	 * 测试文件读取
	 * @return 0 读取正常
	 * @return -1 读取失败，未找到玩家文件
	 * @return -2 读取失败，玩家文件损坏
	 */
	public static int testHighScore()
	{
		HighScore tmpScore = null;
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("./settings/HighScore.dat"));
			try {
				tmpScore = (HighScore) input.readObject();
				input.close();
			} catch (ClassNotFoundException e) {
				return -2;
			}
		} catch (FileNotFoundException e) {
			return -1;
		} catch (IOException e) {
			return -2;
		}		
	return 0;
	}
	
	/**
	 * 上传到文件
	 */
	private void update() 
	{
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("./settings/HighScore.dat"));
			output.writeObject(this);
		} catch (Exception e) {}
		
	}

	/**
	 * 新建网络比赛 
	 */
	public void newLocalGame()
	{
		totalLocal++;
		update();
	}
	
	/**
	 * 新机联机比赛
	 */
	public void newLanGame()
	{
		totalLan++;
		update();
	}
	
	/**
	 * 完成本地比赛
	 */
	public void winLocalGame()
	{
		winLocal++;
		update();
	}
	
	/**
	 * 赢了联机比赛
	 */
	public void winLan()
	{
		winLan++;
		update();
	}
	
	/**
	 * recordDate的Set方法
	 * @param recordDate 需要设置的字段
	 */
	public void setRecordDate(Date recordDate, int diff) {
		this.recordDate[diff] = recordDate;
	}

	/**
	 * recordTime的Set方法
	 * @param recordTime 需要设置的字段
	 */
	public void setRecordTime(int recordTime, int diff) {
		this.recordTime[diff] = recordTime;
	}

	/**
	 * 输掉联机比赛
	 */
	public void loseLan()
	{
		loseLan++;
		update();
	}
	
	/**
	 * 获取本地游戏完成率
	 * @return 本地完成率字符串
	 */
	public String getLocalWinRate()
	{
		if (totalLocal==0) return "0%";
		return Integer.toString(winLocal*100/totalLocal)+"%";
	}
	
	/**
	 * 获取联机游戏完成率
	 * @return 联机完成率字符串
	 */
	public String getLanWinRate()
	{
		if (totalLan==0) return "0%";
		return Integer.toString(winLan*100/totalLan)+"%";
	}
	
	/**
	 * playerName的Get方法
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * playerName的Set方法
	 * @param playerName 需要设置的字段
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
		update();
	}

	/**
	 * recordData的Get方法
	 * @param _id 难度
	 * @return the recordData
	 */
	public String getRecordData(int _id) {
		Date date = recordDate[_id];
		return "时间"+(date.getYear()+1900)+"年"+(1+date.getMonth())+"月"+date.getDate()+"日"+date.getHours()+"时"+date.getMinutes()+"分";
	}

	/**
	 * recordTime的Get方法
	 * @param _id 难度
	 * @return the recordTime
	 */
	public int getRecordTime(int _id) {
		return recordTime[_id];
	}

	/**
	 * winLocal的Get方法
	 * @return the winLocal
	 */
	public int getWinLocal() {
		return winLocal;
	}

	/**
	 * winLan的Get方法
	 * @return the winLan
	 */
	public int getWinLan() {
		return winLan;
	}

	/**
	 * loseLan的Get方法
	 * @return the loseLan
	 */
	public int getLoseLan() {
		return loseLan;
	}

	/**
	 * totalLocal的Get方法
	 * @return the totalLocal
	 */
	public int getTotalLocal() {
		return totalLocal;
	}

	/**
	 * totalLan的Get方法
	 * @return the totalLan
	 */
	public int getTotalLan() {
		return totalLan;
	}

	/**
	 * 更新最高成绩
	 * @param _id 难度
	 * @param _date 日期
	 * @param _time 完成用时间
	 */
	public void updateRecord(int _id, Date _date, int _time)
	{
		recordDate[_id] = _date;
		recordTime[_id] = _time;
		update();
	}
	
	
	public String playerName;
	public Date[] recordDate;
	public int[] recordTime;
	public int winLocal;
	public int winLan;
	public int loseLan;
	public int totalLocal;
	public int totalLan;
	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String args[])
	{
		HighScore a = new HighScore(true);
		System.out.println(a.getLoseLan());
	}
}
