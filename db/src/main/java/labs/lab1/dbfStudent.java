package labs.lab1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class dbfStudent {

	private String filep="D:/Course/3rd-1/db/LAB/Lab1/LabData/student.dbf";
	private String[] registno;
	private String[] name;
	private int[] kdno;
	private double[] kcno;
	private double[] ccno;
	private String[] seat;
	private int RowCount;
	private int i;
	
	private Connection con;
	private Statement stmnt;
	private ResultSet rs;
	public dbfStudent(String filep)
	{
//		this.filep=filep;
		readData();
	}
	public void readData()
	{
		try{
		 Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
		 System.out.println(filep);
		 con = DriverManager.getConnection("jdbc:odbc:driver={Microsoft DBase Driver (*.dbf)};DBQ=" + filep);
		 stmnt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	 
		 String query = "select distinct * from studen_1.DBF "  ;
		 rs = stmnt.executeQuery( query );
			 if(rs!=null)
			 {
				 rs.last();
				 RowCount=rs.getRow();
				 rs.beforeFirst();
				 if(RowCount>0)
				 {
					 registno=new String[RowCount];
					 name=new String[RowCount];
					 kdno=new int[RowCount];
					 kcno=new double[RowCount];
					 ccno=new double[RowCount];
					 seat=new String[RowCount];
					 i=0;
					 while((rs.next())&&(rs.getString(1)!=null))
					 {
						 i++;
						 System.out.println(i);
						 rs.next();
						 try
						 {
							 registno[i]=rs.getString(1);
							 name[i]=rs.getString(2);
							 kdno[i]=Integer.parseInt(rs.getString(3));
							 kcno[i]=Double.parseDouble(rs.getString(4));
							 ccno[i]=Double.parseDouble(rs.getString(5));
							 seat[i]=rs.getString(6);
							 System.out.println(i);
						 }
						 catch(Exception e)
						 {
						//	e.printStackTrace();
						 }
					 }
				 }
			 }
			 rs.close();
				stmnt.close();
				con.close();
		}
		catch(Exception e)
		{
		//	e.printStackTrace();
	//	 System.out.println("fail to get student connection");
		}
	
	}
	public int getRow() {
		return i;
	}

	public String[] getRegistno() {
		return registno;
	}
	public String[] getName() {
		return name;
	}
	public int[] getKdno() {
		return kdno;
	}
	public double[] getKcno() {
		return kcno;
	}
	public double[] getCcno() {
		return ccno;
	}
	public String[] getSeat() {
		return seat;
	}
}
