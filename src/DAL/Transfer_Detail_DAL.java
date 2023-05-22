package DAL;

import DTO.Transfer_Detail_DTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transfer_Detail_DAL 
{
    public Transfer_Detail_DTO getTransferDetail(int transactionId)
    {
        Transfer_Detail_DTO dtoTransferDetail = new Transfer_Detail_DTO();
        try{
            Connection con = DBConnection.ConnectDb();
            String SQL = "SELECT * FROM Transfer_Detail WHERE TRANSACTION_ID = ?";
            PreparedStatement prest = con.prepareStatement(SQL);
            prest.setInt(1, transactionId);
            ResultSet rs = prest.executeQuery();
            while(rs.next())
            {
                dtoTransferDetail = new Transfer_Detail_DTO(rs.getInt(1), rs.getLong(2), rs.getLong(3), rs.getString(4), rs.getLong(5), rs.getString(6), rs.getInt(7));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return dtoTransferDetail;
    }
}
