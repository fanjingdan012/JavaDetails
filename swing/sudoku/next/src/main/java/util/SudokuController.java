/*
 * Project sudoku
 * Version: 0.8
 * Last Update: 2009/11/2
 * Description: ��ȡ�ļ��Լ���֤����
 */
package util;

import gui.ui.SudokuMainPanel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


import core.SudokuModel;
import core.SudokuPanel;


public class SudokuController 
{
	//��ȡ�����ļ�
	public static SudokuModel loadFile() {
		try {
			SudokuModel sModel = null;
			JFileChooser sudokuChooser=new JFileChooser();
			sudokuChooser.setCurrentDirectory(new File("./save"));
			sudokuChooser.setFileFilter(new FileNameExtensionFilter("����������ļ�(*.sudoku)","sudoku"));
			int result=sudokuChooser.showOpenDialog(null);
			if (result==JFileChooser.APPROVE_OPTION)
			{
				ObjectInputStream input = new ObjectInputStream(new FileInputStream(sudokuChooser.getSelectedFile()));
				sModel = (SudokuModel) input.readObject();
				input.close();
				return sModel;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "��ȡ�����ļ�����\n��ȷ�����򿪵���һ����ȷ��������","Sudoku Next!",JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}	
	
	public static String showValue(int a)
	{
		String s=(a==0)?"":Integer.toString(a);
		return s;
	}
	
	//��֤��(x,y)��value��ͻ��λ��
	public static List<Integer> validate(int x, int y, int value, SudokuModel s)
	{
		List<Integer> results=new LinkedList<Integer>();
		int i,j;
		//��֤ͬһ��
		for (i=0;i<s.edge;i++)
			if (i!=y&&s.getValue(x, i)!=0&&s.getValue(x, i)==value) 
				results.add((x*s.edge+i));
		//��֤ͬһ��
		for (i=0;i<s.edge;i++)
			if (i!=x&&s.getValue(i, y)!=0&&s.getValue(i, y)==value) 
				results.add((i*s.edge+y));
		//��֤ͬһ��С��
		for (i=x/s.sedge*s.sedge;i<x/s.sedge*s.sedge+s.sedge;i++)
			for (j=y/s.sedge*s.sedge;j<y/s.sedge*s.sedge+s.sedge;j++)
				if (!(i==x&&j==y)&&s.getValue(i, j)!=0&&s.getValue(i, j)==value)
					results.add((i*s.edge+j));	
		return results;
	}
	
	//��ȡ��(x,y)���������ֵ
	public static List<Integer> getAvailable(int x, int y, SudokuModel s)
	{
		List<Integer> results=new LinkedList<Integer>();
		int i;
		for (i=1;i<s.edge + 1;i++)
			if (validate(x,y,i,s).size()==0) results.add((i));
		return results;
	}
	
	//�ж��Ƿ�ʤ�����޿�����λ��
	public static int gameStatueVal()
	{
		SudokuModel s=SudokuPanel.getsModel();
		int i,j;
		boolean vFlag=true;
		boolean rFlag=true;
		int c1=0;
		int c2=0;
		for (i=0;i<s.edge;i++)
			for (j=0;j<s.edge;j++)
				if (s.getValue(i, j)==0)
				{
					c1++;
					vFlag=false;
					if (getAvailable(i,j,s).size()!=0) rFlag=false; else c2++;
				}
		if (vFlag) return 1; //�����ڿշ����Ѿ�����
		if (c1==c2) return -2;//���ڿշ��������пո񶼲�����
		if (rFlag) return 0;  //���з��񶼿���
		else return -1;   //��Щ������Щ������
	}
	
	
	/**
	 * ʹ��DFS������ Ч�ʽϵ� �޷�����16x16����Ҫ
	 * @param currentBlock
	 * @param a
	 * @return
	 */
	public static boolean sudokuDFS(int currentBlock, SudokuModel a)
	{
		if (currentBlock==a.edge * a.edge) //�������
				return true;
		int x=currentBlock/a.edge;
		int y=currentBlock%a.edge;
		if (a.getValue(x, y)!=0) return (sudokuDFS(currentBlock+1,a));
		List<Integer> cList=getAvailable(x,y,a);
		int cSize=cList.size();
		if (cSize==0) return false;
		int r=(int)(Math.random()*cSize);
		for (int i=r;i<r+cSize;i++)
		{
			a.setBoard(x, y, cList.get(i%cSize), false);
			if (sudokuDFS(currentBlock+1,a)) return true;
			a.setBoard(x, y, 0, false);
		}
		return false;
	}
	
	public static SudokuModel getRandomSudoku(int diff) //���ÿո�
	{
		
		SudokuModel a=null;
		int blanks = 0;
		switch (diff)
		{
		case 0:
			a = new SudokuModel(4);
			blanks = 8; break;
		case 1:
			a = new SudokuModel(9);
			blanks = 20; break;
		case 2:
			a = new SudokuModel(9);
			blanks = 40; break;
		case 3:
			a = new SudokuModel(16);
			blanks = 100; break;
		case 4:
			a = new SudokuModel(16);
			blanks = 180; break;
		}
		solveSudoku(a);
		SudokuMainPanel.setShowAnswer(false);
		for (int i=0;i<a.edge*a.edge;i++) a.setInitial(i/a.edge, i%a.edge, true);
		while(blanks>0)
		{
			int t1=(int)(Math.random()*a.edge);
			int t2=(int)(Math.random()*a.edge);
			if (a.getValue(t1, t2)!=0)
			{
				a.setBoard(t1, t2, 0, false);
				blanks--;
			}
		}
		a.setDiff(diff);
		return a;
	}
	
	/**
	 * @param a
	 */
	public static boolean solveSudoku(SudokuModel a) {
		int  inner[][] = new int[a.edge][a.edge];
		int  outer[][] = new int[a.edge][a.edge];
		for (int i = 0; i < a.edge; i++)
			for (int j = 0; j < a.edge; j++)
				inner[i][j] = a.getValue(i, j);
		int n = SudokuSolver.solve(a.edge, inner, outer);
		if (n > 0){
			for (int i = 0; i < a.edge; i++)
				for (int j = 0; j < a.edge; j++)
					a.setBoard(i, j, outer[i][j], false);
			return true;
		}else
			return false;
	}

	public static void saveSudoku(SudokuModel a)
	{
		try {
			JFileChooser sudokuChooser=new JFileChooser();
			sudokuChooser.setCurrentDirectory(new File("./save"));
			sudokuChooser.setFileFilter(new FileNameExtensionFilter("����������ļ�(*.sudoku)","sudoku"));
			int result=sudokuChooser.showOpenDialog(null);
			if (result==JFileChooser.APPROVE_OPTION)
			{
				String filename = sudokuChooser.getSelectedFile().getName();
				if (!filename.endsWith(".sudoku"))
					filename =sudokuChooser.getSelectedFile()+".sudoku";
				ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(new File(filename)));
				output.writeObject(a);
				output.close();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "����ʧ��","Sudoku Next!",JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
