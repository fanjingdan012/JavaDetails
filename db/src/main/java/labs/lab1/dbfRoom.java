package labs.lab1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class dbfRoom {
	private String filep="D:/Course/3rd-1/db/LAB/Lab1/LabData/room.dbf";
	private String[] exptime;
	private String[] kdname;
	private int[] kdno;
	private double[] kcno;
	private double[] ccno;
	private String[] papername;
	private int RowCount;
	
	private Connection con;
	private Statement stmnt;
	private ResultSet rs;

	public dbfRoom(String filep)
	{
		this.filep=filep;
		readData();
	}
	public void readData()
	{
		try{
		 Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
		 System.out.println(filep);
		 con = DriverManager.getConnection("jdbc:odbc:driver={Microsoft DBase Driver (*.dbf)};DBQ=" + filep);
		 stmnt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		 
			
			 String query = "select distinct * from room_001.DBF "  ;
			 rs = stmnt.executeQuery( query );
			 if(rs!=null)
			 {
	//			 System.out.println("result is not null");
				 rs.last();
				 RowCount=rs.getRow();
				 rs.beforeFirst();
				 if(RowCount>0)
				 {
					 papername=new String[RowCount];
					 kdname=new String[RowCount];
					 kdno=new int[RowCount];
					 kcno=new double[RowCount];
					 ccno=new double[RowCount];
					 exptime=new String[RowCount];
					 for(int i=0;i<RowCount;i++)
					 {
						 rs.next();
						 try
						 { 
						kdno[i]=Integer.parseInt(rs.getString(1));
						 kcno[i]=Double.parseDouble(rs.getString(2));
						 ccno[i]=Double.parseDouble(rs.getString(3));
						 kdname[i]=rs.getString(4);
						 exptime[i]=rs.getString(5);
						 papername[i]=rs.getString(6);
						 }
						 catch(Exception e)
						 {
							e.printStackTrace();
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
			e.printStackTrace();
		 System.out.println("fail to get room connection");
		}
	}
	public int getRow() {
		return RowCount;
	}

	public String[] getExptime() {
		return exptime;
	}
	public String[] getKdname() {
		return kdname;
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
	public String[] getPapername() {
		return papername;
	}
	


}
