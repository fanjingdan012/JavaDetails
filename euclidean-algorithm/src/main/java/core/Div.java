package core;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Div {
	public static ArrayList<Integer> answer = new ArrayList<Integer>();
	public static void main(String [] args){
		String inStr = JOptionPane.showInputDialog("please input number to be divided:");
		int num = Integer.parseInt(inStr);
		divmain(num);
		
	}
	public static void divmain(int num){
		div(num);
		if(answer.contains(num)){
			answer.remove((Integer)num);
		}
	}
	public static void div(int num){
		if(num == 1 ){
			return;
		}
		for(int i = 2;i<=num;i++){
			if(num/i*i==num&&isSu(i)){
				answer.add(i);
				div(num/i);
				return;
				
			}
		}
		
		return;
		
	}
	public static boolean isSu(int num){
		double sqrtnum = Math.sqrt((double)num);
		for(int i = 2;i<=sqrtnum;i++){
			if(num/i*i==num){
				return false;
			}
		}
		return true;
	}

}
