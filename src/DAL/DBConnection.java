package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class DBConnection 
{
    public static Connection ConnectDb()
    {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##myuser1","admin1234");
            return con;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
}