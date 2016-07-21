import javax.swing.JOptionPane;
class Turn 
{
	public static void main(String[] args) 
	{
		String result = "";
        String numString = JOptionPane.showInputDialog("enter num(d2x)");	
			int src = Integer.parseInt(numString);
			int shang = 100;
			int yushu=16;	
			while(shang !=0){
				shang = src/16;				
				yushu = src-shang*16;		
				src=shang;
				if(yushu<10){
					result+=yushu;
				}
				else{
				    result+=((char)('a'+(yushu-10)));
	
				}
				
			}



		
		System.out.println("result is:(reverse it) " + result);

	}
}
