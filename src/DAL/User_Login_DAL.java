package DAL;

import DTO.Employee_DTO;
import DTO.Customer_DTO;
import DTO.User_Login_DTO;
import java.sql.*;
import javax.swing.JOptionPane;

public class User_Login_DAL
{
    public boolean updateInfo(User_Login_DTO dtoUserLogin)
    {
        try{
            Connection con = DBConnection.ConnectDb();
            String SQL = "UPDATE USER_LOGIN "
                    + "SET PASSWORD = ?, NUMBER_OF_FAILED_LOGIN = ?"
                    + "WHERE USERLOGIN_ID = ?";
            PreparedStatement prest = con.prepareStatement(SQL);
            prest.setString(1, dtoUserLogin.getPassword());
            prest.setInt(2, dtoUserLogin.getNumberOfFailedLogin());
            prest.setInt(3,  dtoUserLogin.getId());
            prest.executeUpdate();
            con.close();
            return true;
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);    
        }
        return false;
    }
    
    public boolean updateNumberOfFailedLogin(String username)
    {
        try{
            Connection con = DBConnection.ConnectDb();
            String SQL = "UPDATE USER_LOGIN "
                    + "SET NUMBER_OF_FAILED_LOGIN = NUMBER_OF_FAILED_LOGIN + 1 "
                    + "WHERE USERNAME = ?";
            PreparedStatement prest = con.prepareStatement(SQL);
            prest.setString(1, username);
            prest.executeUpdate();
            con.close();
            return true;
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);    
        }
        return false;
    }
    
    public boolean updateLastAccessTime(String username)
    {
        try{
            Connection con = DBConnection.ConnectDb();
            String SQL = "UPDATE USER_LOGIN "
                    + "SET Last_Access_Time = SYSDATE, NUMBER_OF_FAILED_LOGIN = 0"
                    + "WHERE USERNAME = ?";
            PreparedStatement prest = con.prepareStatement(SQL);
            prest.setString(1, username);
            prest.executeUpdate();
            con.close();
            return true;
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);    
        }
        return false;
    }
    
    public boolean checkRole(User_Login_DTO dtoUserLogin)
    {
        try{
            Connection con = DBConnection.ConnectDb();
            String SQL = "SELECT Role FROM User_Login WHERE Username = ? AND Password = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, dtoUserLogin.getUsername());
            ps.setString(2, dtoUserLogin.getPassword());
            ResultSet rs = ps.executeQuery();
            String role = "";
            while(rs.next())
                role = rs.getString(1);
            con.close();
            if(role.equals("Admin"))
                return true;
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return false; 
    }
    
    public User_Login_DTO getUserLogin(String username)
    {
        try
        {
            Connection con = DBConnection.ConnectDb();
            String SQL = "SELECT * FROM User_Login WHERE USERNAME = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            User_Login_DTO dtoUserLogin = null;
            while(rs.next())
                dtoUserLogin = new User_Login_DTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDate(6));
            con.close();
            return dtoUserLogin;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return null; 
    }
    
    public User_Login_DTO getUserLogin(Customer_DTO dtoCustomer)
    {
        try
        {
            Connection con = DBConnection.ConnectDb();
            String SQL = "SELECT * FROM User_Login WHERE UserLogin_ID = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setLong(1, dtoCustomer.getUserLoginID());
            ResultSet rs = ps.executeQuery();
            User_Login_DTO dtoUserLogin = null;
            while(rs.next())
                dtoUserLogin = new User_Login_DTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDate(6));
            con.close();
            return dtoUserLogin;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return null; 
    }
    
    public User_Login_DTO getUserLogin(Employee_DTO dtoAdmin)
    {
        try
        {
            Connection con = DBConnection.ConnectDb();
            String SQL = "SELECT * FROM User_Login WHERE UserLogin_ID = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setLong(1, dtoAdmin.getUserLoginId());
            ResultSet rs = ps.executeQuery();
            User_Login_DTO dtoUserLogin = null;
            while(rs.next())
                dtoUserLogin = new User_Login_DTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDate(6));
            con.close();
            return dtoUserLogin;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return null; 
    }
}


