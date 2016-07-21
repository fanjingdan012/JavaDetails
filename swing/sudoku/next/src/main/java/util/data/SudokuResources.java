/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-7
 */
package util.data;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * SudokuResources�� 
 * ������Դ
 * @version 0.1
 */
public class SudokuResources implements Serializable{
	
	/**
	 * ���캯�� ��д���ļ���
	 */
	public SudokuResources()
	{
		
	}
	
	
	/**
	 * ��ȡ��Դ
	 * @param theme ��Ҫ��ȡ����Դ��
	 * @return
	 */
	public static SudokuResources getSudokuResources(String theme)
	{
		SudokuResources temp = null;
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("./theme/"+theme+"/resources.dat"));
			temp = (SudokuResources) input.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	/**
	 * �½���Ϸ��ť
	 */
	public ImageButtonRes bnew;
	/**
	 * ������Ϸ��ť
	 */
	public ImageButtonRes blan;
	/**
	 * ͳ�ư�ť
	 */
	public ImageButtonRes bstat;
	/**
	 * ѡ�ť
	 */
	public ImageButtonRes boption;
	/**
	 * ���ڰ�ť
	 */
	public ImageButtonRes babout;
	/**
	 * �˳���ť
	 */
	public ImageButtonRes bexit;
	/**
	 * ѡ��ذ�ť
	 */
	public ImageButtonRes oreturn;
	/**
	 * ѡ�����ⰴť
	 */
	public ImageButtonRes othemes;
	/**
	 * ѡ�����谴ť
	 */
	public ImageButtonRes oreset;
	/**
	 * ѡ�� ȫ����ť
	 */
	public ImageButtonRes oticker;
	/**
	 * ѡ�� ���ڻ�
	 */
	public ImageButtonRes otickeroff;
	/**
	 * ѡ�� BGM������
	 */
	public ImageButtonRes ospinner1;
	public ImageButtonRes ospinbg1;
	/**
	 * ѡ��SE������
	 */
	public ImageButtonRes ospinner2;
	public ImageButtonRes ospinbg2;
	/**
	 * �Ѷ�ѡ��
	 */
	public ImageButtonRes lev1;
	public ImageButtonRes lev2;
	public ImageButtonRes lev3;
	public ImageButtonRes lev4;
	public ImageButtonRes lev0;
	public ImageButtonRes levload;
	/**
	 * ������ť��Դ
	 */
	public SudokuButtonRes sbr;
	/**
	 * �߿�ť��Դ
	 */
	public BorderButtonRes bbr;
	/**
	 * ��Ϸ��ť����
	 */
	public ImageButtonRes iundo;
	/**
	 * ��Ϸ��ť�½�
	 */
	public ImageButtonRes inew;
	/**
	 * ��Ϸ��ť����
	 */
	public ImageButtonRes isave;
	/**
	 * ��Ϸ��ť����
	 */
	public ImageButtonRes islove;
	/**
	 * ��Ϸ��ť�������˵�
	 */
	public ImageButtonRes ireturn;
	
	
	public static void main(String args[])
	{
		SudokuResources a = new SudokuResources();
		a.bnew = new ImageButtonRes("air", "menu_newgame_off", "menu_newgame", 575, 220);
		a.blan = new ImageButtonRes("air", "menu_langame_off", "menu_langame", 575, 260);
		a.bstat = new ImageButtonRes("air", "menu_stat_off", "menu_stat", 575, 300);
		a.boption = new ImageButtonRes("air", "menu_option_off", "menu_option", 575, 340);
		a.babout = new ImageButtonRes("air", "menu_about_off", "menu_about", 575, 380);
		a.bexit = new ImageButtonRes("air", "menu_exit_off", "menu_exit",575, 420);
		a.oreset = new ImageButtonRes("air","opt_reset_off","opt_reset",555, 340);
		a.othemes = new ImageButtonRes("air","opt_themes_off","opt_themes",555, 380);
		a.oreturn = new ImageButtonRes("air","opt_return_off","opt_return",555, 420);
		a.oticker = new ImageButtonRes("air","winmode_off","winmode",555,195);
		a.otickeroff = new ImageButtonRes("air","fullscr_off","fullscr",555,195);
		a.ospinner1 = new ImageButtonRes("air","spinner","spinner",560,260);
		a.ospinbg1 = new ImageButtonRes("air","spinner_bg","spinner_bg",560,260);
		a.ospinner2 =  new ImageButtonRes("air","spinner","spinner",560,315);
		a.ospinbg2 = new ImageButtonRes("air","spinner_bg","spinner_bg",560,315);
		a.lev0 = new ImageButtonRes("air","diff_0_off","diff_0",160,280);
		a.lev1 = new ImageButtonRes("air","diff_1_off","diff_1", 240, 280);
		a.lev2 = new ImageButtonRes("air", "diff_2_off", "diff_2", 320, 280);
		a.lev3 = new ImageButtonRes("air", "diff_3_off", "diff_3", 400, 280);
		a.lev4 = new ImageButtonRes("air", "diff_4_off", "diff_4", 480, 280);
		a.levload = new ImageButtonRes("air","diff_load", "diff_load_on", 530, 430);
		a.sbr = new SudokuButtonRes("air", Color.WHITE, new Color(25,35,156), new Color(111,131,222), Color.RED, Color.YELLOW, new Color(52,0,101));
		a.bbr = new BorderButtonRes("air", Color.GRAY, new Color(217,228,205));
		a.inew = new ImageButtonRes("air","inew","inew_on",0,100);
		a.iundo = new ImageButtonRes("air", "iundo", "iundo_on" , 0, 50);
		a.isave = new ImageButtonRes("air", "isave", "isave_on" , 0, 150);
		a.islove = new ImageButtonRes("air", "islove", "islove_on", 0, 200);
		a.ireturn = new ImageButtonRes("air", "ireturn", "ireturn_on" , 0,250);
		
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("./theme/air/resources.dat"));
			output.writeObject(a);
		} catch (Exception e) {}
	}

}
