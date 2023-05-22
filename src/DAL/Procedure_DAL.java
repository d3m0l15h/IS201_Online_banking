package DAL;

import DTO.Account_DTO;
import DTO.Bill_DTO;
import DTO.Customer_DTO;
import DTO.Transfer_Detail_DTO;
import java.sql.*;
import javax.swing.JOptionPane;

public class Procedure_DAL 
{
    public boolean deposit(Account_DTO dtoAccount, long amount)
    {
        try{
            Connection con = DBConnection.ConnectDb();
            String strCall = "{call DEPOSIT_TO_ACCOUNT(?, ?)}";
            CallableStatement caSt = con.prepareCall(strCall);
            caSt.setLong(1, dtoAccount.getId());
            caSt.setLong(2, amount);
            caSt.execute();
            con.close();
            return true;
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);    
        }
        return false;
    }
    
    public boolean settle(Account_DTO dtoSavingAccount)
    {
        try{
            Connection con = DBConnection.ConnectDb();
            String strCall = "{call settlement(?)}";
            CallableStatement caSt = con.prepareCall(strCall);
            caSt.setLong(1, dtoSavingAccount.getId());
            caSt.execute();
            con.close();
            return true;
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);    
        }
        return false;
    }
    
    public int transfer(Transfer_Detail_DTO dtoTransferDetail)
    {
        int transactionID = 0;
        try{
            Connection con = DBConnection.ConnectDb();
            String strCall = "{call transfer(?, ?, ?, ?, ?, ?)}";
            CallableStatement caSt = con.prepareCall(strCall);
            caSt.setLong(1, dtoTransferDetail.getSenderAccount());
            caSt.setLong(2, dtoTransferDetail.getReceiverAccount());
            caSt.setString(3, dtoTransferDetail.getReceiverBank());
            caSt.setLong(4, dtoTransferDetail.getAmount());
            caSt.setString(5, dtoTransferDetail.getContent());
            caSt.registerOutParameter(6, java.sql.Types.NUMERIC);
            caSt.executeUpdate();
            transactionID = caSt.getInt(6);
            con.close();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);    
        }
        return transactionID;
    }
    
    public boolean openSavingsAccount(Account_DTO dtoNewSavingAccount)
    {
        try{
            Connection con = DBConnection.ConnectDb();
            String strCall = "{call OPEN_SAVINGS_ACCOUNT(?, ?, ?, ?, ?)}";
            CallableStatement caSt = con.prepareCall(strCall);
            caSt.setLong(1, dtoNewSavingAccount.getCustomerID());
            caSt.setString(2, dtoNewSavingAccount.getAccountTypeID());
            caSt.setLong(3, dtoNewSavingAccount.getCurrentBalance());
            Date sqlMaturityDate = new java.sql.Date(dtoNewSavingAccount.getMaturityDate().getTime());
            caSt.setDate(4, sqlMaturityDate);
            caSt.setLong(5, dtoNewSavingAccount.getAnticipatedInterest());
            caSt.execute();
            con.close();
            return true;
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);    
        }
        return false;
    }
    
    public boolean payment(Bill_DTO dtoBill, Account_DTO dtoAccount)
    {
        try{
            Connection con = DBConnection.ConnectDb();
            String strCall = "{call payment(? , ?)}";
            CallableStatement caSt = con.prepareCall(strCall);
            caSt.setLong(1, dtoAccount.getId());
            caSt.setLong(2, dtoBill.getId());
            caSt.execute();
            con.close();
            caSt.close();
            return true;
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);    
        }
        return false;
    }
    
    public boolean delete(Customer_DTO ct)
    {
        try{
            Connection con = DBConnection.ConnectDb();
            String strCall = "{call DELETE_CUSTOMER(?)}";
            CallableStatement caSt = con.prepareCall(strCall);
            caSt.setLong(1, ct.getId());
           
            caSt.execute();
            con.close();
            return true;
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);    
        }
        return false;
    }
}
