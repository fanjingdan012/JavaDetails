import javax.swing.JOptionPane;
public class MyCalendar{
	public static void main(String[] args){

		String yearString = JOptionPane.showInputDialog("enter year");
		String monthString = JOptionPane.showInputDialog("enter month");
		int year = Integer.parseInt(yearString);
		int month = Integer.parseInt(monthString);
		int week;
		long daycount=0;
		
		//daycount
		for (int yearcount=0;yearcount<year ; yearcount++){
			if (leapyear(yearcount)==true){
				daycount=daycount+366;
			}
			else{
				daycount=daycount+365;
			}
		}


		for (int monthcount=1;monthcount<month ;monthcount++ ){
			daycount=daycount+monthdays(monthcount,year);
		}

		daycount++;
		//weekday,0:Sun 1:Mon...
		week=(int)((daycount-2)%7);
		if (week<0)week+=7;
		

		System.out.println("             Calendar\n" +  "            "  + month+" month" + "   " + year+" year");
		System.out.println("-----------------------------------");
		System.out.println(" Sun  Mon  Tue  Wed  Thu  Fri  Sat");

		year=year%400;
		

		for (int space=1;space<=week ;space++ ){
			System.out.print("     ");
		}
		int days;
		for (days=1;days<=(7-week) ; days++){
			System.out.print("  "+days+"  ");
		}
		System.out.println();
		
		
		while (days<=(monthdays(month,year))){
			for (int huanhang=1; huanhang<=7; huanhang++){
				if (days<10){
					System.out.print("  "+days+"  ");
					days++;
					if (days>(monthdays(month,year))){
						break;
					}
				}
				else{
					System.out.print("  "+days+" ");
				    days++;
					if (days>(monthdays(month,year))){
						break;
					}
				}
			}
			System.out.println();
		}		
		
	}//end main


	public static boolean leapyear(int nian){
		boolean leapyear;
		leapyear=((nian%4==0)&(!(nian%100==0)))|(nian%400==0);
		return leapyear;		
	}//end leapyear

	public static int monthdays(int yue,int year){
		int daysThisMonth=0;
		switch (yue){
			case 1:daysThisMonth=31;break;
			case 2:
				if (leapyear(year)==true)	{
				daysThisMonth=29;
				}
			    else{
					daysThisMonth=28;
				}
				break;
			case 3:daysThisMonth=31;break;
			case 4:daysThisMonth=30;break;
			case 5:daysThisMonth=31;break;
			case 6:daysThisMonth=30;break;
			case 7:daysThisMonth=31;break;
			case 8:daysThisMonth=31;break;
			case 9:daysThisMonth=30;break;
			case 10:daysThisMonth=31;break;
			case 11:daysThisMonth=30;break;
			case 12:daysThisMonth=31;break;
		}
		return daysThisMonth;
	}
}