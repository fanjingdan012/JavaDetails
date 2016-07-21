package er.oracle;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {
	public boolean Connector(String name, String num,String sex ,String class1,String grade,String techer,String adress) {

        
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();

        	String url="jdbc:oracle:thin:@localhost:1521:orcl";

        	//orcl为数据库的SID

        	String user="test";

        	String password="test";

        	Connection conn=DriverManager.getConnection(url,user,password);
        	
        	
        	
            Connection con = DriverManager.getConnection("jdbc:odbc:test");
            Statement stmt = con.createStatement();
            PreparedStatement ps = con.prepareStatement(
                    "update student set name=?,sex=?,class=?,grade=?,techer=?,adress=? where num=?");
            ps.setString(1, name);
            ps.setString(2,num);
            ps.setString(3,sex);
            ps.setString(4,class1);
            ps.setString(5,grade);
            ps.setString(6,techer);
            ps.setString(7,adress);
            int num1 = ps.executeUpdate();
            if (num1 != 0) {
                ps.close();
                con.close();
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
