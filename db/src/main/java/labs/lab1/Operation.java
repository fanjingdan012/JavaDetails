package labs.lab1;

public class Operation {
	
	public static	DB db=new DB();
	public static String xlsfile1p="D:/Course/3rd-1/db/LAB/Lab1/LabData/room.xls";
	public static String xlsfile2p="D:/Course/3rd-1/db/LAB/Lab1/LabData/student.xls";
	public static String dbffile1p="D:/Course/3rd-1/db/LAB/Lab1/LabData/";
	public static void main(String args[]) 
	{
		dropTable();
		createTable();
		insertValueFromXLS();
		//insertValueFromDBF();
	}
	public static void insertValueFromXLS()
	{
		xlsRoom xs=new xlsRoom(xlsfile1p);
		int row=xs.getRow();
		String st;
		for(int i=0;i<row;i++)
		{	
			st="insert into room values(";
	//		System.out.println(xs.getCcno()[i]);
			st+=xs.getKdno()[i]+","+xs.getKcno()[i]+","+xs.getCcno()[i]+",'"+xs.getKdname()[i]+"','"+xs.getExptime()[i]+"','"+xs.getPapername()[i]+"')";
		//	System.out.println(st);
			try{db.executeUpdate(st);}
			catch(Exception e){System.out.println(st);return ;}
		}
		xlsStudent xr=new xlsStudent(xlsfile2p);
		row=xr.getRow();
//		System.out.println(row);
		int i=0;
		for(;i<row;i++)
		{
			st="insert into student values(";
			st+=xr.getRegistno()[i]+",'"+xr.getName()[i]+"',"+xr.getKdno()[i]+","+xr.getKcno()[i]+","+xr.getCcno()[i]+","+xr.getSeat()[i]+")";
			try{db.executeUpdate(st);}
			catch(Exception e){System.out.println(st);return ;}
		}
//		System.out.println("i is"+i);
	}
	public static void insertValueFromDBF()
	{
		dbfRoom xs=new dbfRoom(dbffile1p);
		int row=xs.getRow();
		String st;
		for(int i=0;i<row;i++)
		{	
			st="insert into room values(";
			st+="'"+xs.getKdno()[i]+"',"+xs.getKcno()[i]+","+xs.getCcno()[i]+",'"+xs.getKdname()[i]+"','"+xs.getExptime()[i]+"','"+xs.getPapername()[i]+"')";
			try{db.executeUpdate(st);}
			catch(Exception e){System.out.println(st);return ;}
		}
		dbfStudent xr=new dbfStudent(dbffile1p);
		row=xr.getRow();
		for(int i=0;i<row;i++)
		{
			st="insert into student values(";
			st+=xr.getRegistno()[i]+",'"+xr.getName()[i]+"',"+xr.getKdno()[i]+","+xr.getKcno()[i]+","+xr.getCcno()[i]+","+xr.getSeat()[i]+")";
			try{db.executeUpdate(st);}
			catch(Exception e){System.out.println(st);return ;}
		}
	}
	public static void createTable()
	{
		String createTable="create table room (kdno number default 1101,kcno number  default 0,ccno number  default 0,kdname varchar(20),exptime varchar(30) ,papername varchar(20) default '')";
		if(!db.executeUpdate(createTable))
			{
				System.out.println("create table room fail!");
				return;
			}
			
		 createTable="create table student(registerno varchar(20) default '0000000',name varchar(20) default '',kdno number  default 1101,kcno number  default 0,ccno number  default 0,seat number default 0)";
		 if(!db.executeUpdate(createTable))
			 {
			 System.out.println("create table student fail!");
			 return;
			 }
		
	}
	public static void dropTable()
	{
		String drop="drop table room";
		if(!db.executeUpdate(drop))
		{
			System.out.println("drop table room fail!");
			return;
		}
		 drop="drop table student";
		if(!db.executeUpdate(drop))
		{
			System.out.println("drop table student fail!");
			return;
		}
	}

}
