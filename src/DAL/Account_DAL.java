package DAL;

import DTO.Account_DTO;
import DTO.Customer_DTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Account_DAL 
{
    public boolean openPaymentAccount(long customerId, long initialAmount)
    {
        try{
            Connection con = DBConnection.ConnectDb();
            String SQL = "INSERT INTO ACCOUNT VALUES (ACCOUNT_ID_SEQUENCE.NEXTVAL, 'PA', ?, SYSDATE, NULL, NULL, 'Active',?)";
            PreparedStatement prest = con.prepareStatement(SQL);
            prest.setLong(1, initialAmount);
            prest.setLong(2, customerId);
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
    
    public boolean lockAccount(long accountId)
    {
        try{
            Connection con = DBConnection.ConnectDb();
            String SQL = "UPDATE ACCOUNT SET STATUS = 'Locked' WHERE ACCOUNT_ID = ?";
            PreparedStatement prest = con.prepareStatement(SQL);
            prest.setLong(1, accountId);
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
    
    public boolean unlockAccount(long accountId)
    {
        try{
            Connection con = DBConnection.ConnectDb();
            String SQL = "UPDATE ACCOUNT SET STATUS = 'Active' WHERE ACCOUNT_ID = ?";
            PreparedStatement prest = con.prepareStatement(SQL);
            prest.setLong(1, accountId);
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
    
    public ArrayList<Account_DTO> getAccountList()
    {
            ArrayList<Account_DTO> accountList = new ArrayList<Account_DTO>();
        try
        {
            Connection con = DBConnection.ConnectDb();
            String SQL = "SELECT * FROM ACCOUNT";
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(SQL);
            while(rs.next())
            {
                Account_DTO  ac = new Account_DTO(rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getDate(4), rs.getDate(5), rs.getLong(6),rs.getString(7), rs.getLong(8));
                accountList.add(ac);
            } 
            con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return accountList; 
    }
     

    
    public boolean isValidPaymentAccount(Account_DTO dtoAccount)
    {
        try{
            Connection con = DBConnection.ConnectDb();
            String SQL = "SELECT Account_ID, Status "
                    + "FROM Account "
                    + "WHERE Account_ID = ? AND Account_Type_ID = 'PA'";
            PreparedStatement prest = con.prepareStatement(SQL);
            prest.setLong(1, dtoAccount.getId());
            ResultSet rs = prest.executeQuery();
            long accountId = 0;
            String status = "";
            while(rs.next())
            {
                accountId = rs.getLong(1);
                status = rs.getString(2);
            } 
            con.close();
            if(accountId != 0 && status.equals("Active"))
                return true;
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);    
        }
        return false;
    }
    
    public long getTotalSavingsAmount(Customer_DTO dtoCustomer)
    {
        long totalSavingAccount = 0;
        try{
            Connection con = DBConnection.ConnectDb();
            String SQL = "SELECT SUM(CURRENT_BALANCE) "
                    + "FROM Account "
                    + "WHERE CUSTOMER_ID = ? "
                    + "AND ACCOUNT_TYPE_ID LIKE '%SA%' "
                    + "AND STATUS = 'Active'";
            PreparedStatement prest = con.prepareStatement(SQL);
            prest.setLong(1, dtoCustomer.getId());
            ResultSet rs = prest.executeQuery();
            while(rs.next())
            {
                totalSavingAccount = rs.getLong(1);
            } 
            con.close();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);    
        }
        return totalSavingAccount;
    }

    
    public ArrayList<Account_DTO> getSavingsAccountList(Customer_DTO dtoCustomer)
    {
        ArrayList<Account_DTO> accountList = new ArrayList<Account_DTO>();
        try
        {
            Connection con = DBConnection.ConnectDb();
            String SQL = "SELECT * FROM ACCOUNT WHERE CUSTOMER_ID = ? AND ACCOUNT_TYPE_ID LIKE '%SA%' AND STATUS = 'Active'";
            PreparedStatement prest = con.prepareStatement(SQL);
            prest.setLong(1, dtoCustomer.getId());
            ResultSet rs = prest.executeQuery();
            while(rs.next())
            {
                Account_DTO dtoAccount = new Account_DTO(rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getDate(4), rs.getDate(5), rs.getLong(6),rs.getString(7), rs.getLong(8));
                accountList.add(dtoAccount);
            } 
            con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return accountList; 
    }
    
    public Account_DTO getPaymentAccount(Customer_DTO dtoCustomer)
    {
        try
        {
            Connection con = DBConnection.ConnectDb();
            String SQL = "SELECT ACCOUNT_ID, ACCOUNT_TYPE_ID, CURRENT_BALANCE, OPEN_DAY, STATUS, CUSTOMER_ID"
                    + " FROM ACCOUNT "
                    + "WHERE Customer_ID = ? AND Account_Type_ID = 'PA'";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setLong(1, dtoCustomer.getId());
            ResultSet rs = ps.executeQuery();
            Account_DTO dtoAccount = null;
            while(rs.next())
                dtoAccount = new Account_DTO(rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getDate(4), rs.getString(5), rs.getLong(6));
            con.close();
            return dtoAccount;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return null; 
    }
}
