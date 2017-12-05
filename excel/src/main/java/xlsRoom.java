package db;
import java.sql.*;
public class xlsRoom {
	
	private String filep="D:\\112  智能系统原理与开发\\LAB\\lab3\\Data1.xlsx";
	private String[] U;
	private double[] a;
	private double[] b;
	private double[] c;
	private double[] d;
	private double[] e;
	
	private int RowCount = 0;
	
	private Connection con;
	private Statement stmnt;
	private ResultSet rs;
	private ResultSet rst;
	public xlsRoom(String filep)
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
	//			 System.out.println("result is not null");
				 rs.last();
				 RowCount=rs.getRow();
				 
				 rs.beforeFirst();
				 if(RowCount>0)
				 {
					 U = new String[RowCount];
					 a = new double[RowCount];
					 b = new double[RowCount];
					 c = new double[RowCount];
					 d = new double[RowCount];
					 e = new double[RowCount];
					 for(int i = 0;i < RowCount;i++)
					 {
						 rs.next();
						 try
						 {
							 U[i] = rs.getString(1);
							 a[i]=Double.parseDouble(rs.getString(2));
							 b[i]=Double.parseDouble(rs.getString(3));
							 c[i]=Double.parseDouble(rs.getString(4));
							 d[i]=Double.parseDouble(rs.getString(5));
							 e[i]=Double.parseDouble(rs.getString(6));
						 }
						 catch(Exception e)
						 {
							e.printStackTrace();
						 }
					 }
				 }
			 }
			 rs.close();
			 rst.close();
			 stmnt.close();
			 con.close();

		}
		catch(Exception e)
		{
			System.out.println("fail to get student connection");
		}
	
	}
	public int getRow() {
		return RowCount;
	}

	
	public static void main(String [] args){
		xlsRoom r = new xlsRoom("D:\\112  智能系统原理与开发\\LAB\\lab3\\Data1.xlsx");
		System.out.println("hello"+r.a[7]+r.a[6]);
		//readData
	}
	
	


}
