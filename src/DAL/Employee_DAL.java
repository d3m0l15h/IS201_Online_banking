package DAL;

import DTO.Employee_DTO;
import DTO.User_Login_DTO;
import java.sql.*;
import javax.swing.JOptionPane;


public class Employee_DAL
{
    public Employee_DTO getAdminInfo(User_Login_DTO dtoUserLogin) 
    {
        try{
            Connection con = DBConnection.ConnectDb();
            Employee_DTO dtoAdmin = null;
            String SQL2 = "SELECT * FROM EMPLOYEE WHERE UserLogin_ID = ?"; 
            PreparedStatement ps2 = con.prepareStatement(SQL2);
            ps2.setLong(1, dtoUserLogin.getId());
            ResultSet rs2 = ps2.executeQuery();
            while(rs2.next())
                dtoAdmin = new Employee_DTO(rs2.getInt(1), rs2.getString(2), rs2.getString(3), rs2.getInt(4)); 
            con.close();
            return dtoAdmin;
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return null; 
    }
    
    public  Employee_DTO getAdminInfo(int adminId)
    {
        Employee_DTO dtoAdmin = null;
        try
        {
            Connection con = DBConnection.ConnectDb();
            String SQL = "SELECT *"
                    + " FROM EMPLOYEE WHERE EMPLOYEE_ID = " + adminId;
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(SQL);
            while(rs.next())
                dtoAdmin = new Employee_DTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return dtoAdmin ;
    }
}
