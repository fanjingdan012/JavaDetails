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
 * HighScoreDB�� 
 * ��߷���ͳ����Ϣ
 * @version 0.3
 */
public class HighScore implements Serializable 
{
	/**
	 * ���캯�����½�HighScore����
	 * @param _playerName �����
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
	 * �����ù�����
	 * @param bool �Ƿ�ʹ��
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
	 * ���캯�������ļ���ȡ��ң���߷���Ϣ
	 * ����ִ�в����ļ���ȡ�Ա�ȷ�ϺϷ�����߷��ļ�����
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
	 * �����ļ���ȡ
	 * @return 0 ��ȡ����
	 * @return -1 ��ȡʧ�ܣ�δ�ҵ�����ļ�
	 * @return -2 ��ȡʧ�ܣ�����ļ���
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
	 * �ϴ����ļ�
	 */
	private void update() 
	{
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("./settings/HighScore.dat"));
			output.writeObject(this);
		} catch (Exception e) {}
		
	}

	/**
	 * �½�������� 
	 */
	public void newLocalGame()
	{
		totalLocal++;
		update();
	}
	
	/**
	 * �»���������
	 */
	public void newLanGame()
	{
		totalLan++;
		update();
	}
	
	/**
	 * ��ɱ��ر���
	 */
	public void winLocalGame()
	{
		winLocal++;
		update();
	}
	
	/**
	 * Ӯ����������
	 */
	public void winLan()
	{
		winLan++;
		update();
	}
	
	/**
	 * recordDate��Set����
	 * @param recordDate ��Ҫ���õ��ֶ�
	 */
	public void setRecordDate(Date recordDate, int diff) {
		this.recordDate[diff] = recordDate;
	}

	/**
	 * recordTime��Set����
	 * @param recordTime ��Ҫ���õ��ֶ�
	 */
	public void setRecordTime(int recordTime, int diff) {
		this.recordTime[diff] = recordTime;
	}

	/**
	 * �����������
	 */
	public void loseLan()
	{
		loseLan++;
		update();
	}
	
	/**
	 * ��ȡ������Ϸ�����
	 * @return ����������ַ���
	 */
	public String getLocalWinRate()
	{
		if (totalLocal==0) return "0%";
		return Integer.toString(winLocal*100/totalLocal)+"%";
	}
	
	/**
	 * ��ȡ������Ϸ�����
	 * @return ����������ַ���
	 */
	public String getLanWinRate()
	{
		if (totalLan==0) return "0%";
		return Integer.toString(winLan*100/totalLan)+"%";
	}
	
	/**
	 * playerName��Get����
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * playerName��Set����
	 * @param playerName ��Ҫ���õ��ֶ�
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
		update();
	}

	/**
	 * recordData��Get����
	 * @param _id �Ѷ�
	 * @return the recordData
	 */
	public String getRecordData(int _id) {
		Date date = recordDate[_id];
		return "ʱ��"+(date.getYear()+1900)+"��"+(1+date.getMonth())+"��"+date.getDate()+"��"+date.getHours()+"ʱ"+date.getMinutes()+"��";
	}

	/**
	 * recordTime��Get����
	 * @param _id �Ѷ�
	 * @return the recordTime
	 */
	public int getRecordTime(int _id) {
		return recordTime[_id];
	}

	/**
	 * winLocal��Get����
	 * @return the winLocal
	 */
	public int getWinLocal() {
		return winLocal;
	}

	/**
	 * winLan��Get����
	 * @return the winLan
	 */
	public int getWinLan() {
		return winLan;
	}

	/**
	 * loseLan��Get����
	 * @return the loseLan
	 */
	public int getLoseLan() {
		return loseLan;
	}

	/**
	 * totalLocal��Get����
	 * @return the totalLocal
	 */
	public int getTotalLocal() {
		return totalLocal;
	}

	/**
	 * totalLan��Get����
	 * @return the totalLan
	 */
	public int getTotalLan() {
		return totalLan;
	}

	/**
	 * ������߳ɼ�
	 * @param _id �Ѷ�
	 * @param _date ����
	 * @param _time �����ʱ��
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
	 * ����
	 * @param args
	 */
	public static void main(String args[])
	{
		HighScore a = new HighScore(true);
		System.out.println(a.getLoseLan());
	}
}
