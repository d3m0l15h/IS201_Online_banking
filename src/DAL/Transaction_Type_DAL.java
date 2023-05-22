package DAL;

import DTO.Transaction_Type_DTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

public class Transaction_Type_DAL 
{
    public TreeMap<String, Transaction_Type_DTO> getTransactionTypeList()
    {
        TreeMap<String, Transaction_Type_DTO> transactionTypeList = new TreeMap<String, Transaction_Type_DTO>();
        try{
            Connection con = DBConnection.ConnectDb();
            String SQL = "SELECT * FROM TRANSACTION_TYPE";
            PreparedStatement prest = con.prepareStatement(SQL);
            ResultSet rs = prest.executeQuery();
            while(rs.next())
            {
                String transactionTypeId = rs.getString(1);
                String name = rs.getString(2);
                int fee = rs.getInt(3);
                Transaction_Type_DTO dtoTransactionType = new Transaction_Type_DTO(transactionTypeId, name, fee);
                transactionTypeList.put(transactionTypeId, dtoTransactionType);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return transactionTypeList;
    }
}
