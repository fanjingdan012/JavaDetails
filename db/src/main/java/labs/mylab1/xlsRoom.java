package labs.mylab1;
import java.sql.*;
public class xlsRoom {
	private String filep="D:/DB/LAB/Lab1/LabÊý¾Ý/room.xls";
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
	//			System.out.println("result is not null");
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

	public String[] getExptime() {
		return exptime;
	}
	public String[] getKdname() {
		return kdname;
	}
	public String[] getPapername() {
		return papername;
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

	


}
