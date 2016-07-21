package labs.lab1;
import java.sql.*;
public class DB {
	private final static String db_url="jdbc:oracle:thin:@127.0.0.1:1521:oracle";
	private final static String db_driver="oracle.jdbc.driver.OracleDriver";
	private final static String db_user="system";
	private final static String db_paw="qwe";
	
	public Connection getConnection() {  
  
       
        try {  
           
            Class.forName(db_driver);   
            Connection conn = DriverManager  
                    .getConnection(db_url,db_user, db_paw); 
            
            return conn;
            
        } catch (Exception ex) {  
            ex.printStackTrace();
            return null;
        }  
          
    }  
  
   
    public ResultSet executeQuery(String sql) {  
    	try {  
            Connection conn=getConnection();  
            Statement stmt = conn.createStatement();  
            ResultSet rs = stmt.executeQuery(sql);  
            return rs;  
        } catch (SQLException ex) {  
            ex.printStackTrace();  
            return null;  
        }  
  
    }  
  
     
    public boolean executeUpdate(String sql) {  
  
        if (sql == null || sql.length() == 0)  
            return false;  
        try {  
        	Connection conn=getConnection(); 
        	System.out.println("conn="+conn);
            Statement stmt = conn.createStatement(); 
            stmt.executeUpdate(sql);  
            
            return true;  
        } catch (SQLException ex) {  
        	System.out.println(sql);
            ex.printStackTrace();  
            
            return false;  
        }
        
  
    }  
  
    
}
