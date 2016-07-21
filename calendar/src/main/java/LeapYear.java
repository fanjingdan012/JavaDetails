import javax.swing.JOptionPane;
public class LeapYear{
	public static void main(String[] args){
		boolean bool;
		String Inputyear=JOptionPane.showInputDialog(null,"Enter year:","Input Dialog",JOptionPane.QUESTION_MESSAGE);
		int year=Integer.parseInt(Inputyear);
		bool=((year%4==0)&(!(year%100==0)))|(year%400==0);
		JOptionPane.showMessageDialog(null,bool);
	}
	
}