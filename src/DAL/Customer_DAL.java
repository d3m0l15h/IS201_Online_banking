package DAL;

import DTO.Account_DTO;
import DTO.Customer_DTO;
import DTO.User_Login_DTO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Customer_DAL 
{
    public boolean insert(Customer_DTO ct)
    {
        try{
            Connection con = DBConnection.ConnectDb();
            String SQL = "INSERT INTO CUSTOMER(CUSTOMER_ID, FIRST_NAME, LAST_NAME, GENDER, DATE_OF_BIRTH, ADDRESS, PHONE_NUMBER, ID_CARD) "
                    + " VALUES (CUSTOMER_ID_SEQUENCE.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prest = con.prepareStatement(SQL);
            prest.setString(1, ct.getFirstName());
            prest.setString(2, ct.getLastName());
            prest.setString(3, ct.getGender());
            Date sqlDateOfBirth = new java.sql.Date(ct.getDateOfBirth().getTime());
            prest.setDate(4, sqlDateOfBirth); // TO_DATE(?, 'MON DD, YYYY')
            prest.setString(5,  ct.getAddress());
            prest.setString(6, ct.getPhoneNumber());
            prest.setString(7, ct.getIDCard());
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
    
//    public boolean delete(Customer_DTO dtoCustomer)
//    {
//        try{
//            Connection con = DBConnection.ConnectDb();
//            String sql = "DELETE FROM CUSTOMER WHERE CUSTOMER_ID = ?";
//            PreparedStatement prest = con.prepareStatement(sql);
//            prest.setLong(1, dtoCustomer.getId());
//            prest.executeUpdate();
//            con.close();
//            return true;
//        }
//        catch(SQLException e)
//        {
//            JOptionPane.showMessageDialog(null, e);    
//        }
//        return false;
//            
//    }
    
    public boolean update(Customer_DTO ct)
    {
        try{
            Connection con = DBConnection.ConnectDb();
            String SQL = "UPDATE CUSTOMER SET FIRST_NAME = ?, LAST_NAME = ? ,GENDER = ?, DATE_OF_BIRTH = ?, ADDRESS = ?, PHONE_NUMBER = ?, ID_CARD = ? WHERE CUSTOMER_ID = ?";
            PreparedStatement prest = con.prepareStatement(SQL);
            prest.setString(1, ct.getFirstName());
            prest.setString(2, ct.getLastName());
            prest.setString(3, ct.getGender());
            Date sqlDateOfBirth = new java.sql.Date(ct.getDateOfBirth().getTime());
            prest.setDate(4, sqlDateOfBirth); // TO_DATE(?, 'MON DD, YYYY')
            prest.setString(5,  ct.getAddress());
            prest.setString(6, ct.getPhoneNumber());
            prest.setString(7, ct.getIDCard());
            prest.setLong(8, ct.getId());
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
    
    public Customer_DTO getInformation(long id)
    {
        Customer_DTO dotCustomer = null;
        try
        {
            Connection con = DBConnection.ConnectDb();
            String SQL = "SELECT CUSTOMER_ID, FIRST_NAME, LAST_NAME, GENDER, DATE_OF_BIRTH, ADDRESS, PHONE_NUMBER, ID_CARD, USERLOGIN_ID"
                    + " FROM CUSTOMER WHERE CUSTOMER_ID = " + id;
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(SQL);
            while(rs.next())
                dotCustomer = new Customer_DTO(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),rs.getString(6), rs.getString(7), rs.getString(8), rs.getLong(9));
            con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return dotCustomer; 
    }
    
    public Customer_DTO getCustomerInfo(Account_DTO dtoAccount)
    {
        Customer_DTO dotCustomer = null;
        try
        {
            Connection con = DBConnection.ConnectDb();
            String SQL = "SELECT C.CUSTOMER_ID, FIRST_NAME, LAST_NAME, GENDER, DATE_OF_BIRTH, ADDRESS, PHONE_NUMBER, ID_CARD, C.USERLOGIN_ID"
                    + " FROM CUSTOMER C JOIN ACCOUNT A ON C.CUSTOMER_ID = A.CUSTOMER_ID"
                    + " WHERE A.ACCOUNT_ID = " + dtoAccount.getId();
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(SQL);
            while(rs.next())
                dotCustomer = new Customer_DTO(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),rs.getString(6), rs.getString(7), rs.getString(8), rs.getLong(9));
            con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return dotCustomer; 
    }

    public ArrayList<Customer_DTO> getCustomersList()
    {
        ArrayList<Customer_DTO> customersList = new ArrayList<Customer_DTO>();
        try
        {
            Connection con = DBConnection.ConnectDb();
            String SQL = "SELECT CUSTOMER_ID, FIRST_NAME, LAST_NAME, GENDER, DATE_OF_BIRTH, ADDRESS, PHONE_NUMBER, ID_CARD, USERLOGIN_ID"
                    + " FROM CUSTOMER ORDER BY CUSTOMER_ID DESC";
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(SQL);
            while(rs.next())
            {
                Customer_DTO  ct = new Customer_DTO(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),rs.getString(6), rs.getString(7), rs.getString(8),rs.getLong(9));
                customersList.add(ct);
            } 
            con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return customersList; 
    }
    
    public Customer_DTO getCustomerInfo(User_Login_DTO dtoUserLogin)
    {
        try{
            Connection con = DBConnection.ConnectDb();
            Customer_DTO dtoCustomer = null;
            String SQL2 = "SELECT * FROM Customer WHERE UserLogin_ID = ?"; 
            PreparedStatement ps2 = con.prepareStatement(SQL2);
            ps2.setLong(1, dtoUserLogin.getId());
            ResultSet rs2 = ps2.executeQuery();
            while(rs2.next())
                dtoCustomer = new Customer_DTO(rs2.getLong(1), rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getDate(5),rs2.getString(6), rs2.getString(7), rs2.getString(8), rs2.getLong(9)); 
            con.close();
            return dtoCustomer;
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return null; 
    }
}
