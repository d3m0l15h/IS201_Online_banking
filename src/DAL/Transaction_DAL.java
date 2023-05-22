package DAL;

import DTO.Account_DTO;
import DTO.Transaction_DTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class Transaction_DAL 
{

    public String getLatestTransactionDate(Account_DTO dtoAccount)
    {
        String latestTransactionDate = "";
        try
        {
            Connection con = DBConnection.ConnectDb();
            String SQL = "SELECT TRANSACTION_DATE  \n" +
                        "FROM (SELECT TRANSACTION_DATE\n" +
                        "FROM TRANSACTION\n" +
                        "WHERE ACCOUNT_ID = ?\n" +
                        "ORDER BY TRANSACTION_ID DESC)\n" +
                        "WHERE ROWNUM = 1";
            PreparedStatement prest = con.prepareStatement(SQL);
            prest.setLong(1, dtoAccount.getId());
            ResultSet rs = prest.executeQuery();
            while(rs.next())
                latestTransactionDate = rs.getString(1);
            con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return latestTransactionDate;
    }
    
    public ArrayList<Transaction_DTO> getTransactionHistory(Account_DTO dtoAccount)
    {
        ArrayList<Transaction_DTO> transactionList = new ArrayList<Transaction_DTO>();
        try
        {
            Connection con = DBConnection.ConnectDb();
            String SQL = "SELECT *\n" +
                        "FROM (SELECT * FROM TRANSACTION WHERE account_id = ? ORDER BY transaction_id DESC)\n" +
                        "WHERE ROWNUM <= 15 "; //Hiển thị 15 giao dịch gần nhất
            PreparedStatement prest = con.prepareStatement(SQL);
            prest.setLong(1, dtoAccount.getId());
            ResultSet rs = prest.executeQuery();
            while(rs.next())
            {
                Transaction_DTO dtoTransaction = new Transaction_DTO(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getLong(4), rs.getInt(5));
                transactionList.add(dtoTransaction);
            } 
            con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return transactionList; 
    }
    
    public ArrayList<Transaction_DTO> getStatement(Account_DTO dtoAccount, Date fromDate, Date toDate) 
    {
        ArrayList<Transaction_DTO> statementlist = new ArrayList<Transaction_DTO>();
         try
        {
            Connection con = DBConnection.ConnectDb();
            int i = -1;
            i = Connection.TRANSACTION_SERIALIZABLE;
            con.setAutoCommit(false);
            String SQL =    "SELECT * \n" +
                            "FROM TRANSACTION \n" +
                            "WHERE account_id = ?\n" +
                            "AND (TRANSACTION_DATE BETWEEN TO_DATE(?, 'dd/MM/yyyy') AND TO_DATE(?, 'dd/MM/yyyy HH24:MI:SS'))\n" +
                            "ORDER BY transaction_id DESC";
            PreparedStatement prest = con.prepareStatement(SQL);
            prest.setLong(1, dtoAccount.getId());
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String fDate = df.format(fromDate);
            String tDate = df.format(toDate) + " 23:59:00";
            prest.setString(2, fDate);
            prest.setString(3, tDate);
            ResultSet rs = prest.executeQuery();
            while(rs.next())
            {
                Transaction_DTO dtoTransaction = new Transaction_DTO(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getLong(4), rs.getInt(5));
                statementlist.add(dtoTransaction);
            } 
            con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return statementlist;
    }
}
