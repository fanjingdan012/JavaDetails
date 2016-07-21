package labs.lab1;

import java.sql.*;
public class xlsStudent {
	private String filep="D:/Course/3rd-1/db/LAB/Lab1/LabData/student.xls";
	private String[] registno;
	private String[] name;
	private int[] kdno;
	private double[] kcno;
	private double[] ccno;
	private double[] seat;
	private int RowCount;
	private int i;
	
	private Connection con;
	private Statement stmnt;
	private ResultSet rs;
	private ResultSet rst;
	public xlsStudent(String filep)
	{
		this.filep=filep;
		readData();
	}
	public void readData()
	{
		try{
		 Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
		 con = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=" + filep);
		 stmnt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rst=con.getMetaData().getTables(null, null, "%", null);
			 rst.next();
			 String SheetName = rst.getString(3);
			 String query = "Select distinct * from ["+SheetName+"]"  ;
			 rs = stmnt.executeQuery( query );
			 if(rs!=null)
			 {
				 rs.last();
				 RowCount=rs.getRow();
				 rs.beforeFirst();
	//			 stmnt.close();
				 rst.close();
				 if(RowCount>0)
				 {
					 registno=new String[RowCount];
					 name=new String[RowCount];
					 kdno=new int[RowCount];
					 kcno=new double[RowCount];
					 ccno=new double[RowCount];
					 seat=new double[RowCount];
					  i=0;
					 while(rs.next()&&(rs.getString(1)!=null))
					 {
					//	 rs.next();
						 i++;
						 try
						 {registno[i]=rs.getString(1);
						 name[i]=rs.getString(2);
						 kdno[i]=Integer.parseInt(rs.getString(3));
						 kcno[i]=Double.parseDouble(rs.getString(4));
						 ccno[i]=Double.parseDouble(rs.getString(5));
						 seat[i]=Double.parseDouble(rs.getString(6));
						 System.out.println(i);
						 }
						 catch(Exception e)
						 {
							e.printStackTrace();
						 }
					 }
				 }
			 }
			 rs.close();
				
				con.close();
		}
		catch(Exception e)
		{
	//	 System.out.println("fail to get student connection");
		}
	

		 

	}
	
	public String[] getRegistno() {
		return registno;
	}

	public void setName(String[] name) {
		this.name = name;
	}

	public String[] getName() {
		return name;
	}

	public void setKdno(int[] kdno) {
		this.kdno = kdno;
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

	

	public double[] getSeat() {
		return seat;
	}
	
	public int getRow() {
		return i;
	}

	
	
}
